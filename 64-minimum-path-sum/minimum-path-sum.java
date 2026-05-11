// Note : I have written two DP approaches below. Please read from top to bottom for better understanding !!!


// Approach 1 : DP Tabulation(Iterative)
// TC : O(m*n)
// SC : O(m*n)

class Solution {
    public int minPathSum(int[][] grid) {
         
         int m = grid.length;
         int n = grid[0].length;

         int[][] dp = new int[m][n];

         dp[0][0] = grid[0][0];

         for(int j=1;j<n;j++)
         dp[0][j] = dp[0][j-1] + grid[0][j];

         for(int i=1;i<m;i++)
         dp[i][0] = dp[i-1][0] + grid[i][0];

         for(int i=1;i<m;i++)
            for(int j=1;j<n;j++)
                dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];

         return dp[m-1][n-1];


    }
}




/*---------------------------------------------------------------------------------------------------------------*/




// // Approach 2 : Optimized version of Approach 1
// // TC : O(m*n)
// // SC : O(n)

// class Solution {
//     public int minPathSum(int[][] grid) {
         
//          int m = grid.length;
//          int n = grid[0].length;

//          int[] dp = new int[n];

//          dp[0] = grid[0][0];

//          for(int j=1;j<n;j++)
//           dp[j] = dp[j-1] + grid[0][j];

//          for(int i=1;i<m;i++)
//             for(int j=0;j<n;j++)
//             {   
//                 if(j == 0)
//                 dp[j] = dp[j] + grid[i][j];
//                 else
//                 dp[j] = Math.min(dp[j],dp[j-1]) + grid[i][j];
//             }

//          return dp[n-1];


//     }
// }


