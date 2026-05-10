// Note: I have written two approaches for this problem below. Please read both of them !!



// Approach 1 : DP Tabulation(Iterative)
// TC : O(m*n)
// SC : O(m*n)

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
         
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

          if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0;

        int[][] dp = new int[m][n];

         dp[0][0] = 1;

          for(int i=0;i<m;i++)
          {
            for(int j=0;j<n;j++)
            {
                 if(obstacleGrid[i][j] != 1)
                 {
                     if(i-1>=0)
                     dp[i][j] += dp[i-1][j];

                     if(j-1>=0)
                     dp[i][j] += dp[i][j-1];
                 }
            }

         }
            return dp[m-1][n-1];
}
}




/*----------------------------------------------------------------------------------------------------------------*/




// // Approach 2 : Optmized version of Approach 1
// // TC : O(m*n)
// // SC : O(n)

// class Solution {
//     public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        
         
//         int m = obstacleGrid.length;
//         int n = obstacleGrid[0].length;

//           if(obstacleGrid[0][0] == 1 || obstacleGrid[m-1][n-1] == 1) return 0;

//         int[] dp = new int[n];

//          dp[0] = 1;

//           for(int i=0;i<m;i++)
//           {
//             for(int j=0;j<n;j++)
//             {
//                  if(obstacleGrid[i][j] != 1)
//                  {
//                      if(j-1>=0)
//                      dp[j] += dp[j-1];
//                  }
//                  else
//                  {
//                     dp[j] = 0;
//                  }
//             }

//          }
//             return dp[n-1];
// }
// }