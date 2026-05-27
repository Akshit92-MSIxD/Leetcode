// class Solution {
    
//     int dfs(int n1, int n2, String text1, String text2, int[][] dp)
//     {
//           if(n1 < 0 || n2 < 0)
//           return 0;

//           if(dp[n1][n2] != -1)
//           return dp[n1][n2];

//           int lcs_len = 0;

//           if(text1.charAt(n1) == text2.charAt(n2))
//              lcs_len = 1 + dfs(n1-1,n2-1,text1,text2,dp);
//           else
//              lcs_len = Math.max(dfs(n1-1,n2,text1,text2,dp),dfs(n1,n2-1,text1,text2,dp));

//         return dp[n1][n2] = lcs_len;
//     }
//     public int longestCommonSubsequence(String text1, String text2) {
             
//              int n1 = text1.length();
//              int n2 = text2.length();

//              int[][] dp = new int[n1][n2];

//              for(int i=0;i<n1;i++)
//               for(int j=0;j<n2;j++)
//                    dp[i][j] = -1;

//              return dfs(n1-1,n2-1,text1,text2,dp);

//     }
// }








class Solution {
    
 
    public int longestCommonSubsequence(String s1, String s2) {
             
         int n1 = s1.length();
         int n2 = s2.length();
         
         
         int[][] dp = new int[n1][n2];
         
         dp[0][0] = (s1.charAt(0) == s2.charAt(0)) ? 1 : 0;
         
         for(int j=1;j<n2;j++)
         dp[0][j] = (s1.charAt(0) == s2.charAt(j)) ? 1 : dp[0][j-1];
         
         for(int i=1;i<n1;i++)
         dp[i][0] = (s1.charAt(i) == s2.charAt(0)) ? 1 : dp[i-1][0];
         
         
         for(int i=1;i<n1;i++)
            for(int j=1;j<n2;j++)
                 if(s1.charAt(i) == s2.charAt(j))
                 dp[i][j] = 1 + dp[i-1][j-1];
                 else
                 dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);


          return dp[n1-1][n2-1];

    }
}

