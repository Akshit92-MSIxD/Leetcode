class Solution {

    public boolean canJump(int[] nums) {
         
         int n = nums.length;

         int[] dp = new int[n];
         
         dp[n-1] = 1;

         for(int i=n-2;i>=0;i--)
         {
            int jumpSize = nums[i];

            for(int jmp=1;jmp<=jumpSize && (i+jmp)<n;jmp++)
            {
                dp[i] = Math.max(dp[i],dp[i+jmp]);     
            }
         }

         if(dp[0] == 1)
         return true;

         return false;


    }
}







// class Solution {
    
//     int dfs(int[] nums,int start, int end, int[] dp)
//     {
//          if(dp[start] != -1)
//          return dp[start];
         
//          if(start == end)
//          return 1;

//          int jumpSize = nums[start];

//          int ans = 0;

//          for(int jmp=1;jmp<=jumpSize && (start+jmp)<=end;jmp++)
//          {
//             ans = dfs(nums,start+jmp,end,dp);

//             if(ans == 1)
//             return dp[start] = 1;
//          }

//          return dp[start] = ans;

//     }

//     public boolean canJump(int[] nums) {
         
//          int n = nums.length;

//          int[] dp = new int[n];

//          for(int i=0;i<n;i++)
//            dp[i] = -1;
         
//          int ans = dfs(nums,0,n-1,dp);

//          if(ans == 1)
//          return true;

//          return false;
//     }
// }