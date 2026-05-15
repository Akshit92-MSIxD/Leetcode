class Solution {
    public int findTargetSumWays(int[] nums, int target) {
          
           int n = nums.length;
           int sum = 0;

           for(int val : nums)
           sum += val;

          int dp[] = new int[sum+1];

          if(nums[n-1] == 0)
          dp[0] = 2;
          else
          dp[0] = 1;

          for(int j=1;j<=sum;j++)
          dp[j] = (j == nums[n-1]) ? 1 : 0;

          for(int i=n-2;i>=0;i--)
            for(int j=sum;j>=0;j--)
                   if(nums[i] <= j) dp[j] += dp[j-nums[i]];


           int totalWays = 0;

           for(int j=0;j<=sum;j++)
           {
                  int sumOfS1 = j;
                  int sumOfS2 = sum - sumOfS1;

                  int curr_diff = sumOfS1 - sumOfS2;

                  if(curr_diff == target)
                  totalWays += dp[j];
           }

           return totalWays;



    }
}