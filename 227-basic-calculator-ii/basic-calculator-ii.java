class Solution {

    boolean isDigit(char ch)
    {
        if(ch >= '0' && ch <= '9')
        return true;

        return false;
    }

    int operation(int v1,int v2, char operator)
    {
          int result = 0;

          switch(operator)
          {
            case '+': result = v1 + v2;
                      break;
            case '-': result = v1 - v2;
                      break;
            case '/' : result = v1 / v2;
                        break;
            default : result = v1 * v2;
          }

          return result;
    }

    public int calculate(String s) {
        
               int n = s.length();


               Deque<Integer> operand = new ArrayDeque<>();
               Deque<Character> operator = new ArrayDeque<>();

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

                      operand.push(val);

                      i--;
                   }
                   else if(ch == '+' || ch == '-')
                   {
                        while(!operator.isEmpty())
                        {
                            int v2 = operand.pop();
                            int v1 = operand.pop();

                            operand.push(operation(v1,v2,operator.pop()));
                        }
                        operator.push(ch);
                   }
                   else
                   {
                         while(!operator.isEmpty() && (operator.peek() == '*' || operator.peek() == '/'))
                         {
                            int v2 = operand.pop();
                            int v1 = operand.pop();

                            operand.push(operation(v1,v2,operator.pop()));
                         }
                         operator.push(ch);
                   }
               }


             while(!operator.isEmpty())
               {
                 int v2 = operand.pop();
                 int v1 = operand.pop();

                 operand.push(operation(v1,v2,operator.pop())); 
               }

               return operand.pop();

    }
}