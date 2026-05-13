// Note : I have written two approaches for this problem below . Please read from top to bottom for better understanding !!!




// Approach 1 : Using DP Tabulation(Iterative)
// Prerequisite problem : Subset Sum Equal Target (GFG)
// Hint : If we can somehow find a subset that has a sum equivalent to half of the sum of the whole array then it is possible to partion the whole array into two subsets such that both have equal sum !!!

// TC : O(n*target) , where target in worst case is 10^4
// SC : O(n*target)

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




/*------------------------------------------------------------------------------------------------------------------*/





// // Approach 2 : Space Optimization Approach of Approach 1 (Using only one 1D boolean DP array and traverse from L to R)
// // TC : O(n*target) , where target in worst case is 10^4
// // SC : O(target)

// class Solution {
//     public boolean canPartition(int[] nums) {
         
//          int sum = 0;

//          for(int val : nums)
//          sum+=val;

//          if(sum%2 != 0)
//          return false;

//          int target = sum/2;

//          int n = nums.length;

//          boolean[] dp = new boolean[target+1];

//          dp[0] = true;

//          for(int j=1;j<=target;j++)
//          dp[j] = (nums[n-1] == j);

//          for(int i=n-2;i>=0;i--)
//          {
//             for(int j=target;j>=0;j--)
//             {
//                 if(nums[i]<=j)
//                 dp[j] = dp[j] || dp[j-nums[i]];  // Pick and notPick both included in this case !!!
//             }
//          }


//          return dp[target];

// }
// }