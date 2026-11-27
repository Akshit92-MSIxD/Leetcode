
// Note : I have written two approaches for this problem below. Please read both the approaches !!!

// Approach 2 is the optimal one , but approach 1 is more intuitive :) !!!



// Approach 2 : (Single stack + sum + sign) Concept
// TC : O(n)
// SC : O(n) [if maximum no. of parethesis inside input string] || O(1) (if no || minimum no. of parenthesis inside input string)
// Hint : Take care of the sign of each operand and also the sign of expression surrounded by parenthesis ( ) :)
// // +ve sign ==> 1 , -ve sign ==> -1

class Solution {

    boolean isDigit(char ch)
    {
        if(ch >= '0' && ch <= '9')
            return true;

        return false;
    }


    public int calculate(String s) {

        int n = s.length();

        int sum = 0;

        // Initially assume that first number inside the str has +ve sign,
        // if it is -ve then it will automatically get replaced with -1
        // so you don't need to worry :)

        int sign = 1;

        Deque<Integer> stack = new ArrayDeque<>(); // modern stack in Java :)

        for(int i = 0; i < n; i++)
        {

            char ch = s.charAt(i);

            if(ch == ' ')
                continue;

            if(isDigit(ch))
            {
                int val = 0;

                while(i < n && isDigit(s.charAt(i)))
                {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                i--;

                sum = sum + sign * val;
            }
            else if(ch == '+')
            {
                sign = 1; // sign for next incoming operand || (expression)
            }
            else if(ch == '-')
            {
                sign = -1; // sign for next incoming operand || (expression)
            }
            else if(ch == '(')
            {
                // If this is encountered that means now a new calculation
                // inside ( ) will be calculated, so please store the sign
                // for this ( ) and also the previous calculated result into the stack.

                stack.push(sum);  // previous calculated calculation
                stack.push(sign); // sign for ( )

                sum = 0;  // make sum = 0 because now we will calculate a new expression inside ( )
                sign = 1; // Let's assume the sign of first number inside ( ) is +ve
            }
            else // if ')' is encountered, that means our expression calculation inside ( ) has come to an end and now we have to add this expression calculation with its sign with the previous calculation :)
            {
                int prevSign = stack.pop(); // sign for this expression inside ( ) was stored when '(' was encountered :)

                int prevSum = stack.pop(); // previous calculated result was stored when '(' was encountered :)

                sum = prevSum + prevSign * sum; // here sum(RHS) refers to the result of the calculation of expression inside ( ) :)
            }

        }

        return sum;
    }
}




/*------------------------------------------------------------------------------------------------------*/



// // Approach 1 : Modified Shunt Yard Algo (Using two stacks i.e operand and operator stack)
// // TC : O(n)
// // SC : O(n) [ if there are lot of '(' in the string] || O(1) [if '(' are very less in the string]

// class Solution {



//   private boolean isDigit(char ch)
//    {
//       if(ch>='0' && ch<='9')
//       return true;

//       return false;
//    }

//    private int operation(int v1,int v2,char opr)
//    {
//        int result = 0;

//        switch(opr)
//        {
//           case '+' : result = v1 + v2;
//                      break;
//           case '-' : result = v1 - v2;
//                      break;
//           case '*' : result = v1 * v2;
//                      break;
//           case '/' : result = v1 / v2;
//                      break;
//        }

//        return result;
//    }
   

 
//   public   int calculate(String s) {
     
//           int n = s.length();

//           Deque<Integer> operand = new ArrayDeque<>();  // stack
//           Deque<Character> operator = new ArrayDeque<>(); // stack
          
//            char prevMeaningfulChar = '$';

//           for(int i=0;i<n;i++)
//           {
//              char ch = s.charAt(i);

//              if(ch == ' ')
//              continue;

//              if(isDigit(ch))
//              {
//                 int val = 0;

//                  while(i<n && isDigit(s.charAt(i)))
//                  {
//                     val = val*10 + (s.charAt(i) - '0');
//                     i++;
//                  }
//                  i--;

//                  operand.push(val);
//              }
//              else if(ch == '(')
//              {
//                 operator.push(ch);
//              }
//              else if(ch == ')')
//              {
//                  while(operator.peek() != '(')
//                  {
//                     int v2 = operand.pop();
//                     int v1 = operand.pop();

//                     char opr = operator.pop();

//                     operand.push(operation(v1,v2,opr));
//                  }
//                  operator.pop();
//              }
//              else
//              {
//                  if(ch == '-' && (prevMeaningfulChar == '$' || prevMeaningfulChar == '('))
//                  {
//                      operand.push(0);
//                      operator.push(ch);
//                  }
//                  else
//                  {
//                     while(!operator.isEmpty() && operator.peek() != '(')
//                     {
//                         int v2 = operand.pop();
//                         int v1 = operand.pop();

//                         char opr = operator.pop();

//                         operand.push(operation(v1,v2,opr));
//                     }

//                     operator.push(ch);
//                  }
//              } 

//              prevMeaningfulChar = ch;
//           }


//           while(!operator.isEmpty())
//           {
//             int v2 = operand.pop();
//             int v1 = operand.pop();

//             char opr = operator.pop();

//             operand.push(operation(v1,v2,opr));
//           }   

//           return operand.pop();
//     }
  
// };