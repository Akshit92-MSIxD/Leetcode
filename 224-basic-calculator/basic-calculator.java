class Solution {

    boolean isDigit(char ch)
    {
        if(ch >='0' && ch <= '9')
        return true;

        return false;
    }


    public int calculate(String s) {
               
               int n = s.length();

               int sum = 0;

               int sign = 1;

               Deque<Integer> stack = new ArrayDeque<>(); // modern stack in Java :)

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

                   sum = sum + sign*val;
                 }
                 else if(ch == '+')
                 {
                   sign = 1;
                 }
                 else if(ch == '-')
                 {
                    sign = -1;
                 }
                 else if(ch == '(')
                 {
                    stack.push(sum);
                    stack.push(sign);

                    sum = 0;
                    sign = 1;
                 }
                 else
                 {
                      int prevSign = stack.pop();
                      int prevSum = stack.pop();

                      sum = prevSum + prevSign*sum;
                 }
                   
               }

               return sum;

            }
    }
