class Solution {
    public int minPathSum(int[][] grid) {
         
         int m = grid.length;
         int n = grid[0].length;

         int[][] dp = new int[m][n];

         for(int i=0;i<m;i++)
         {
            for(int j=0;j<n;j++)
            {
                int minPathSum = Integer.MAX_VALUE;

                if(i-1>=0)
                minPathSum = Math.min(minPathSum,dp[i-1][j]);

                if(j-1>=0)
                minPathSum = Math.min(minPathSum,dp[i][j-1]);
                
                if(minPathSum == Integer.MAX_VALUE)
                dp[i][j] = grid[i][j];
                else
                dp[i][j] = grid[i][j] + minPathSum;

            }
         }


         return dp[m-1][n-1];


    }
}