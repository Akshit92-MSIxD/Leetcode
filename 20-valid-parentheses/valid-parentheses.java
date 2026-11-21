// class Solution {
//     public boolean isValid(String s) {
         
//           Stack<Character> st = new Stack<Character>();

//           for(char ch : s.toCharArray())
//           {
//              if(ch == '(' || ch == '[' || ch == '{')
//              {
//                 st.push(ch);
//              }
//              else if(ch == ')')
//              {
//                  if(st.isEmpty() || st.peek() != '(')
//                  return false;

//                  st.pop();
//              }
//              else if(ch == '}')
//              {
//                  if(st.isEmpty() || st.peek() != '{')
//                  return false;

//                  st.pop();
//              }
//              else
//              {
//                 if(st.isEmpty() || st.peek() != '[')
//                 return false;

//                 st.pop();
//              }
//           }

//           return st.isEmpty();
//     }
// }



class Solution {
    public boolean isValid(String s) {
         
          Stack<Character> st = new Stack<Character>();

          for(char ch : s.toCharArray())
          {
             if(ch == '(' || ch == '[' || ch == '{')
             {
                st.push(ch);
             }
             else
             {
                 if(st.isEmpty())
                 return false;

                 if((ch == ')' && st.peek() == '(') || (ch == '}' && st.peek() == '{') || (ch == ']' && st.peek() == '['))
                 st.pop();
                 else
                 break;
             }
             
          }

          return st.isEmpty();
    }
}