class Solution {
    public int change(int amount, int[] coins) {
           
           int n = coins.length;

           int[] dp = new int[amount+1];

           dp[0] = 1;

           for(int j=1;j<=amount;j++)
           dp[j] = (j%coins[n-1] == 0) ? 1 : 0;

           for(int i=n-2;i>=0;i--)
            for(int j=1;j<=amount;j++)
                  if(coins[i]<=j) dp[j]+=dp[j-coins[i]];

        return dp[amount];
 
    }
}