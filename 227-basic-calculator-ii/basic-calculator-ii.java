

// Approach 1 : Modified Shunt Yard Algo + No prevMeaningfulChar Concept(since this problem does not support unary minus) !!!
// TC : O(n)
// SC : O(1)

// class Solution {

//     boolean isDigit(char ch)
//     {
//         if(ch >= '0' && ch <= '9')
//         return true;

//         return false;
//     }

//     int operation(int v1,int v2, char operator)
//     {
//           int result = 0;

//           switch(operator)
//           {
//             case '+': result = v1 + v2;
//                       break;
//             case '-': result = v1 - v2;
//                       break;
//             case '/' : result = v1 / v2;
//                         break;
//             default : result = v1 * v2;
//           }

//           return result;
//     }

//     int precedence(char opr)
//     {
        
//         int precedence = -1;

//         switch(opr)
//         {
//             case '+' : precedence = 1;
//                        break;
//             case '-' : precedence = 1;
//                        break;
//             case '*' : precedence = 2;
//                        break;
//             case '/' :  precedence = 2;
//                        break;
//         }

//         return precedence;



//     }

//     public int calculate(String s) {
        
//                int n = s.length();


//                Deque<Integer> operand = new ArrayDeque<>();
//                Deque<Character> operator = new ArrayDeque<>();

//                for(int i=0;i<n;i++)
//                {
//                   char ch = s.charAt(i);

//                    if(ch == ' ')
//                    continue;

//                    if(isDigit(ch))
//                    {
//                       int val = 0;

//                       while(i<n && isDigit(s.charAt(i)))
//                       {
//                         val = val*10 + (s.charAt(i) - '0');
//                         i++;
//                       }

//                       operand.push(val);

//                       i--;
//                    }
//                    else
//                    {
//                          while(!operator.isEmpty() && precedence(operator.peek()) >= precedence(ch))
//                          {
//                             int v2 = operand.pop();
//                             int v1 = operand.pop();

//                             operand.push(operation(v1,v2,operator.pop()));
//                          }
//                          operator.push(ch);
//                    }
//                }


//              while(!operator.isEmpty())
//                {
//                  int v2 = operand.pop();
//                  int v1 = operand.pop();

//                  operand.push(operation(v1,v2,operator.pop())); 
//                }

//                return operand.pop();

//     }
// }








class Solution {

    boolean isDigit(char ch)
    {
        if(ch >= '0' && ch <= '9')
        return true;

        return false;
    }


    public int calculate(String s) {

               int n = s.length();
        
               Deque<Integer> stack = new ArrayDeque<>();

               char sign = '+';

               for(int i=0;i<n;i++)
               {
                  char ch = s.charAt(i);

                  if(ch == ' ')
                  continue;

                  if(isDigit(ch))
                  {
                    int val = 0;

                    while(i<n && isDigit(s.charAt(i)))
                    {
                        val = val*10 + (s.charAt(i) - '0');
                        i++;
                    }
                    i--;

                    if(sign == '+')
                    {
                       stack.push(val);
                    }
                    else if(sign == '-')
                    {
                        stack.push(-val);
                    }
                    else if(sign == '*')
                    {
                        stack.push(stack.pop() * val);
                    }
                    else
                    {
                        stack.push(stack.pop() / val);
                    }
                  }
                  else
                  {
                     sign = ch;
                  }
               }


               int result = 0;

               while(!stack.isEmpty())
               result += stack.pop();

               return result;

    }
}