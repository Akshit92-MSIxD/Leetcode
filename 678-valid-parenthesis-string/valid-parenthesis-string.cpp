// Approach 2 : DP(tabulation)(iterative) 
// TC : O(n*n) , where n = length of input string   
// SC : O(n*n) [extra dp array] 

class Solution {
   
   public:
    bool checkValidString(string s) {
        
          int n = s.length();
          vector<vector<bool>> dp(n,vector<bool>(n,false));
        
            dp[n-1][0] =  (s[n-1] == '*') ? true : false;
            dp[n-1][1] = (s[n-1] == ')' || s[n-1] == '*') ? true : false;

            for(int j = 2 ; j <= n;j++)
            dp[n-1][j] = false;

            for(int i = n-2 ; i>=0 ; i--)
            {
                for(int j=0;j<=i;j++)
                {
                        if(s[i] == '(')
                        {
                            dp[i][j] = dp[i+1][j+1];
                        }
                        else if(s[i] == ')')
                        {   
                            if(j>0)
                            dp[i][j] = dp[i+1][j-1];
                        }
                        else
                        {
                            if(j==0)
                                dp[i][j] = dp[i+1][j] || dp[i+1][j+1];
                            else
                                dp[i][j] = dp[i+1][j] || dp[i+1][j-1] || dp[i+1][j+1];
                        }
                }
            }

            return dp[0][0];
    }
};