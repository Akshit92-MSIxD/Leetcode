// Note: I have written three approches for this problem below. Please read from top to bottom for better understanding !!!



// Approach 1 : Using DP Tabulation(Iterative)
// TC : O(n^2)
// SC : O(n^2)

class Solution {
    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        
        int[][] dp = new int[n][n];

        for(int j=0;j<n;j++)
        dp[0][j] = matrix[0][j];

        
        for(int i=1;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                if(j==0)
                  dp[i][j] = Math.min(dp[i-1][j],dp[i-1][j+1]) + matrix[i][j];
                else if(j == n-1)
                  dp[i][j] = Math.min(dp[i-1][j-1],dp[i-1][j]) + matrix[i][j];
                else
                  dp[i][j] = Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i-1][j+1]))+ matrix[i][j];
            }
        }

        int minSum = Integer.MAX_VALUE;

        for(int j=0;j<n;j++)
        {
            if(dp[n-1][j] < minSum)
            minSum = dp[n-1][j];
        }

        return minSum;
        
    }
}





/*---------------------------------------------------------------------------------------------------------------*/





// // Approach 2 : Space optmized version of Approach 1 (using rolling DP)
// // TC : O(n^2)
// // SC : O(n)


// class Solution {
//     public int minFallingPathSum(int[][] matrix) {

//         int n = matrix.length;
        
//         int[] prev = new int[n];

//         for(int j=0;j<n;j++)
//          prev[j] = matrix[0][j];

        
//         for(int i=1;i<n;i++)
//         {
//             int[] curr = new int[n];

//             for(int j=0;j<n;j++)
//             {
//                 if(j==0)
//                   curr[j] = Math.min(prev[j],prev[j+1]) + matrix[i][j];
//                 else if(j == n-1)
//                   curr[j] = Math.min(prev[j-1],prev[j]) + matrix[i][j];
//                 else
//                  curr[j] = Math.min(prev[j-1],Math.min(prev[j],prev[j+1]))+ matrix[i][j];
//             }

//             prev = curr;
//         }

//         int minSum = Integer.MAX_VALUE;

//         for(int j=0;j<n;j++)
//         {
//             if(prev[j] < minSum)
//             minSum = prev[j];
//         }

//         return minSum;
        
//     }
// }





/*---------------------------------------------------------------------------------------------------------------*/





// // Approach 3 : Updates the matrix in-place !!! (works only when we are allowed to change original input array)
// // Note : This approach is derived from DP tabulation !!!
// // TC : O(n^2)
// // SC : O(1)


// class Solution {
//     public int minFallingPathSum(int[][] matrix) {

//         int n = matrix.length;

//         for(int i=1;i<n;i++)
//         {
//             for(int j=0;j<n;j++)
//             {
//                 if(j==0)
//                   matrix[i][j] += Math.min(matrix[i-1][j],matrix[i-1][j+1]);
//                 else if(j == n-1)
//                   matrix[i][j] += Math.min(matrix[i-1][j-1],matrix[i-1][j]);
//                 else
//                  matrix[i][j] += Math.min(matrix[i-1][j-1],Math.min(matrix[i-1][j],matrix[i-1][j+1]));
//             }
//         }

//         int minSum = Integer.MAX_VALUE;

//         for(int j=0;j<n;j++)
//         {
//             if(matrix[n-1][j] < minSum)
//             minSum = matrix[n-1][j];
//         }

//         return minSum;
        
//     }
// }