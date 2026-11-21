// Approach : Using one stack
// TC : O(n)
// SC : O(n)

class Solution {
    public boolean isValid(String s) {
         
          Stack<Character> st = new Stack<Character>();

          for(char ch : s.toCharArray())
          {
             if(ch == '(' || ch == '[' || ch == '{')
             {
                st.push(ch);
             }
             else if(ch == ')')
             {
                 if(st.isEmpty() || st.peek() != '(')
                 return false;

                 st.pop();
             }
             else if(ch == '}')
             {
                 if(st.isEmpty() || st.peek() != '{')
                 return false;

                 st.pop();
             }
             else
             {
                if(st.isEmpty() || st.peek() != '[')
                return false;

                st.pop();
             }
          }

          return st.isEmpty();
    }
}



