class Solution {
    public boolean canPartition(int[] nums) {
         
         int sum = 0;

         for(int val : nums)
         sum+=val;

         if(sum%2 != 0)
         return false;

         int target = sum/2;

         int n = nums.length;

         boolean[][] dp = new boolean[n][target+1];

         for(int i=0;i<n;i++)
         dp[i][0] = true;

         for(int j=1;j<=target;j++)
         dp[n-1][j] = (nums[n-1] == j);

         for(int i=n-2;i>=0;i--)
         {
            for(int j=1;j<=target;j++)
            {
                dp[i][j] = dp[i+1][j]; // notPick;

                if(nums[i]<=j)
                dp[i][j] = dp[i][j] || dp[i+1][j-nums[i]];  // Pick
            }
         }


         return dp[0][target];

}
}