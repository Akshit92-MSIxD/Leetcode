// I have written two approches for this problem below in Java !!!
// Please find stack approach in C++ section of this page !!!



// Approach 2 : Greedy (By Striver !!!)
// **** Make sure you know count/open variable concept !!!! (Watch Strivers video !!!)
// One-line refined takeaway
// Use range in greedy only when possible states forms a continuous interval
// Use range when multiple possibilities exist AND they form a continuous interval
// TC : O(n)
// SC : O(1)

class Solution {
    public boolean checkValidString(String s) {
         
     int n = s.length();

     int min = 0;
     int max = 0;

     for(int i=0;i<n;i++)
     {
        char ch = s.charAt(i);

         if(ch == '(')
         { 
             min++;
             max++;
         }
         else if(ch == ')')
         {
           min--;
           max--;
         }
         else
         {
            min--;
            max++;
         }

         if(min < 0)
         min = 0;

         if(max<0)
         return false;
     }

     if(min == 0)
     return true;

     return false;
         
}
}


/*----------------------------------------------------------------------------------------------------------------------*/



// Approach 1 : DP(tabulation)
// Hint : Using count/open variable concept !!!
// TC : O(n^2)
// SC : O(n^2)

// class Solution {
//     public boolean checkValidString(String s) {
         
//      int n = s.length();

//     boolean[][] dp = new boolean[n+1][n+1];

//     dp[n][0] = true;

//     for(int count=1;count<=n;count++)
//      dp[n][count] = false;


//      for(int i=n-1;i>=0;i--)
//      {
//         char ch = s.charAt(i);

//         for(int count = 0;count<=i;count++)
//         {
//            if(ch == '(')
//            {
//              dp[i][count] = dp[i+1][count+1];
//            }
//            else if(ch == ')')
//            {
//               if(count > 0)
//               dp[i][count] = dp[i+1][count-1];
//            }
//            else
//            {
//                if(count == 0)
//                 dp[i][count] = dp[i+1][count] || dp[i+1][count+1];
//                else
//                 dp[i][count] = dp[i+1][count-1] || dp[i+1][count] || dp[i+1][count+1];
//            }
//         }
//      }

//      return dp[0][0];
    


         
// }
// }












