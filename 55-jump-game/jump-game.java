class Solution {

    public boolean canJump(int[] nums) {
         
         int n = nums.length;

         int count_0 = 0;

         for(int i=0;i<n;i++)
         {
            if(nums[i] == 0)
            count_0++;
         }

         if(count_0 == 0) return true;

         boolean[] dp = new boolean[n];
         
         dp[n-1] = true;

         for(int i=n-2;i>=0;i--)
         {
            int jumpSize = nums[i];

            for(int jmp=1;jmp<=jumpSize && (i+jmp)<n;jmp++)
            {
                dp[i] = dp[i+jmp];

                if(dp[i] == true)
                break;     
            }
         }
         
         return dp[0];

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