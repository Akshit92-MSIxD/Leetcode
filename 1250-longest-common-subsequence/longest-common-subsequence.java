class Solution {
    
    int dfs(int n1, int n2, String text1, String text2, int[][] dp)
    {
          if(n1 < 0 || n2 < 0)
          return 0;

          if(dp[n1][n2] != -1)
          return dp[n1][n2];

          int lcs_len = 0;

          if(text1.charAt(n1) == text2.charAt(n2))
             lcs_len = 1 + dfs(n1-1,n2-1,text1,text2,dp);
          else
             lcs_len = Math.max(dfs(n1-1,n2,text1,text2,dp),dfs(n1,n2-1,text1,text2,dp));

        return dp[n1][n2] = lcs_len;
    }
    public int longestCommonSubsequence(String text1, String text2) {
             
             int n1 = text1.length();
             int n2 = text2.length();

             int[][] dp = new int[n1][n2];

             for(int i=0;i<n1;i++)
              for(int j=0;j<n2;j++)
                   dp[i][j] = -1;

             return dfs(n1-1,n2-1,text1,text2,dp);

    }
}