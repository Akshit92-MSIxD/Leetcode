class Solution {
    public int coinChange(int[] coins, int amount) {
        
         int n = coins.length;

         int[] dp = new int[amount+1];

         dp[0] = 0;

         for(int j=1;j<=amount;j++)
         {
            if(j%coins[n-1] == 0)
            dp[j] = j/coins[n-1];
            else
            dp[j] = (int)1e6;
         }

         for(int i=n-2;i>=0;i--)
            for(int j=1;j<=amount;j++)
                if(coins[i] <= j) dp[j] = Math.min(dp[j],1 + dp[j-coins[i]]);


         return (dp[amount] == (int)1e6) ? -1 : dp[amount];
    }
}