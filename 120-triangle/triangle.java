// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
          
//         int m = triangle.size();
//         int n = triangle.get(m-1).size();

//         int[][] dp = new int[m][];

//         for(int i=0;i<m;i++)
//         dp[i] = new int[i+1];

//         dp[0][0] = triangle.get(0).get(0);

//         for(int i=1;i<m;i++)
//         {
//             for(int j=0;j<dp[i].length;j++)
//             {
//                if(j==0)
//                 dp[i][j] = dp[i-1][j] + triangle.get(i).get(j);
//                else if(j == dp[i].length-1)
//                  dp[i][j] = dp[i-1][j-1] + triangle.get(i).get(j);
//                else
//                  dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j-1]) + triangle.get(i).get(j);
//             }
//         }

//         int minPath = Integer.MAX_VALUE;

//         for(int j=0;j<n;j++)
//         {
//             if(dp[m-1][j] < minPath)
//             minPath = dp[m-1][j];
//         }

//         return minPath;
           
//     }
// }



// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
          
//         int m = triangle.size();
//         int n = triangle.get(m-1).size();

//         int[] prev = new int[n];

//         prev[0] = triangle.get(0).get(0);

//         for(int i=1;i<m;i++)
//         {
//                 int[] curr = new int[n];
//             for(int j=0;j<triangle.get(i).size();j++)
//             {
//                if(j==0)
//                 curr[j] = prev[j] + triangle.get(i).get(j);
//                else if(j == triangle.get(i).size() - 1)
//                  curr[j] = prev[j-1] + triangle.get(i).get(j);
//                else
//                  curr[j] = Math.min(prev[j],prev[j-1]) + triangle.get(i).get(j);
//             }

//             prev = curr;
//         }

//         int minPath = Integer.MAX_VALUE;

//         for(int j=0;j<n;j++)
//         {
//             if(prev[j] < minPath)
//             minPath = prev[j];
//         }

//         return minPath;
           
//     }
// }



// class Solution {
//     public int minimumTotal(List<List<Integer>> triangle) {
          
//         int m = triangle.size();

//         int[] prev = new int[1];

//         prev[0] = triangle.get(0).get(0);

//         for(int i=1;i<m;i++)
//         {
//                 int[] curr = new int[i+1];
//             for(int j=0;j<triangle.get(i).size();j++)
//             {
//                if(j==0)
//                 curr[j] = prev[j] + triangle.get(i).get(j);
//                else if(j == triangle.get(i).size() - 1)
//                  curr[j] = prev[j-1] + triangle.get(i).get(j);
//                else
//                  curr[j] = Math.min(prev[j],prev[j-1]) + triangle.get(i).get(j);
//             }

//             prev = curr;
//         }

//         int minPath = Integer.MAX_VALUE;

//         for(int j=0;j<prev.length;j++)
//         {
//             if(prev[j] < minPath)
//             minPath = prev[j];
//         }

//         return minPath;
           
//     }
// }


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
          
        int m = triangle.size();
        int n = triangle.get(m-1).size();

        int[] dp = new int[n];

        dp[0] = triangle.get(0).get(0);

        for(int i=1;i<m;i++)
        {
            for(int j = triangle.get(i).size() - 1;j>=0;j--)
            {
               if(j==0)
                dp[j] = dp[j] + triangle.get(i).get(j);
               else if(j == triangle.get(i).size() - 1)
                 dp[j] = dp[j-1] + triangle.get(i).get(j);
               else
                 dp[j] = Math.min(dp[j],dp[j-1]) + triangle.get(i).get(j);
            }
        }

        int minPath = Integer.MAX_VALUE;

        for(int j=0;j<dp.length;j++)
        {
            if(dp[j] < minPath)
            minPath = dp[j];
        }

        return minPath;
           
    }
}