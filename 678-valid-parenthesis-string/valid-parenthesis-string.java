
// Approach : Greedy (By Striver !!!)
// One-line refined takeaway
// Use range in greedy only when possible states forms a continuous interval
// Use range when multiple possibilities exist AND they form a continuous interval
// TC : O(n)
// SC : O(1)

// class Solution {
//     public boolean checkValidString(String s) {
         
//      int n = s.length();

//      int min = 0;
//      int max = 0;

//      for(int i=0;i<n;i++)
//      {
//         char ch = s.charAt(i);

//          if(ch == '(')
//          { 
//              min++;
//              max++;
//          }
//          else if(ch == ')')
//          {
//            min--;
//            max--;
//          }
//          else
//          {
//             min--;
//             max++;
//          }

//          if(min < 0)
//          min = 0;

//          if(max<0)
//          return false;
//      }

//      if(min == 0)
//      return true;

//      return false;
         
// }
// }


/*----------------------------------------------------------------------------------------------------------------------*/




class Solution {
    public boolean checkValidString(String s) {
         
     int n = s.length();

    boolean[][] dp = new boolean[n+1][n+1];

    dp[n][0] = true;

    for(int count=1;count<=n;count++)
     dp[n][count] = false;


     for(int i=n-1;i>=0;i--)
     {
        char ch = s.charAt(i);

        for(int count = 0;count<=i;count++)
        {
           if(ch == '(')
           {
             dp[i][count] = dp[i+1][count+1];
           }
           else if(ch == ')')
           {
              if(count == 0)
              dp[i][count] = false;
              else
              dp[i][count] = dp[i+1][count-1];
           }
           else
           {
               if(count == 0)
                dp[i][count] = dp[i+1][count] || dp[i+1][count+1];
               else
                dp[i][count] = dp[i+1][count-1] || dp[i+1][count] || dp[i+1][count+1];
           }
        }
     }

     return dp[0][0];
    


         
}
}








/*------------------------------------------------------------------------------------------------------------------------*/





// // Note : I have written two approaches for this problem below. Please read from top to bottom for better understanding !!!


// // Approach 1 : DP(memoization)(recursive) 
// // TC : O(n*n) , where n = length of input string   , Normal Recursion TC : O(3^n) !!!
// // SC : O(n*n) [extra dp array] + O(n) [recursive stack space]  , Normal Recursion SC : O(n)
// // Hint : You should know how 'open variable' approach work in valid parethesis problem !!!

// class Solution {

// private:

//  bool checkValid(string &s , int index , int open , vector<vector<int>> &dp)
//  {
//      if(index == s.length())
//         return  open == 0 ? true : false;

//      if(open < 0)
//      return false;

//      if(dp[index][open] != -1)
//      return dp[index][open];

//      bool ans = false;

//      if(s[index] == '(')
//         ans = checkValid(s,index+1,open+1,dp);
//      else if(s[index] == ')')
//         ans = checkValid(s,index+1,open-1,dp);
//      else
//         ans = checkValid(s,index+1,open,dp) || checkValid(s,index+1,open+1,dp) || checkValid(s,index+1,open-1,dp);

//      return dp[index][open] = ans;
//  }

// public:
//     bool checkValidString(string s) {

//           vector<vector<int>> dp(s.length(),vector<int>(s.length(),-1));
        
//           return  checkValid(s,0,0,dp);
//     }
// };




/*----------------------------------------------------------------------------------------------------------------*/




// Approach 2 : DP(tabulation)(iterative) 
// TC : O(n*n) , where n = length of input string   
// SC : O(n*n) [extra dp array] 

// class Solution {

// public:
//     bool checkValidString(string s) {
        
//           int n = s.length();
//           vector<vector<bool>> dp(n,vector<bool>(n,false));
        
//             dp[n-1][0] =  (s[n-1] == '*') ? true : false;
//             dp[n-1][1] = (s[n-1] == ')' || s[n-1] == '*') ? true : false;

//             for(int j = 2 ; j <= n;j++)
//             dp[n-1][j] = false;

//             for(int i = n-2 ; i>=0 ; i--)
//             {
//                 for(int j=0;j<=i;j++)
//                 {
//                         if(s[i] == '(')
//                         {
//                             dp[i][j] = dp[i+1][j+1];
//                         }
//                         else if(s[i] == ')')
//                         {   
//                             if(j>0)
//                             dp[i][j] = dp[i+1][j-1];
//                         }
//                         else
//                         {
//                             if(j==0)
//                                 dp[i][j] = dp[i+1][j] || dp[i+1][j+1];
//                             else
//                                 dp[i][j] = dp[i+1][j] || dp[i+1][j-1] || dp[i+1][j+1];
//                         }
//                 }
//             }

//             return dp[0][0];
//     }
// };




/*----------------------------------------------------------------------------------------------------------------*/




// Approach 3 : Using 2 stacks
// TC : O(n) , where n = length of input string   
// SC : O(n) 

// class Solution {

// public:
//     bool checkValidString(string str) {
        
//       stack<int> st1;  // will contain the indexes of left parenthesis
//       stack<int> st2; // will contain the indexes of '*'


//       for(int i=0;i<str.length();i++)
//       {
//            int ch = str[i];

//            if(ch == '(')
//            {
//              st1.push(i);
//            }
//            else if(ch == '*')
//            {
//              st2.push(i);
//            }
//            else
//            {
//                 if(st1.empty() && st2.empty())
//                 {
//                     return false;
//                 }
//                 else if(!st1.empty())
//                 {
//                     st1.pop();
//                 }
//                 else
//                 {
//                     st2.pop();
//                 }
//            }
//       }

//        if(st1.empty())
//        {
//        return true;
//        }
//        else if(st2.empty())
//        {
//        return false;
//        }
//        else
//        {
//          if(st1.size() > st2.size()) return false;

//          while(!st1.empty())
//          {
//              if(st1.top() > st2.top())
//              return false;

//              st1.pop();
//              st2.pop();
//          }


//        }

//        return true;

//     }
// };