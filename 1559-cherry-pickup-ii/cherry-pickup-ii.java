class Solution {
    public int cherryPickup(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        int[][][] dp = new int[rows][cols][cols];

        for(int j1=0;j1<cols;j1++)
        {
            for(int j2=j1+1;j2<cols;j2++)
                dp[rows-1][j1][j2] = grid[rows-1][j1] + grid[rows-1][j2];
        }
        
        int[] moveOprs = {-1,0,1};

       for(int i=rows-2;i>=0;i--)
       {
         for(int j1=0;j1<cols;j1++)
         {
            for(int j2=j1+1;j2<cols;j2++)
            {
                  for(int mv1 : moveOprs)
                  {
                     for(int mv2 : moveOprs)
                     {
                         if(j1+mv1 >=0 && j1+mv1 < cols && j2+mv2 >= 0 && j2+mv2 < cols)
                         dp[i][j1][j2] = Math.max(dp[i][j1][j2],dp[i+1][j1+mv1][j2+mv2]);
                     }
                  }

                  dp[i][j1][j2] += (grid[i][j1] + grid[i][j2]);
            }
         }
       }
      

      return dp[0][0][cols-1];


    }
}