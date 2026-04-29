class Solution {
    public int jump(int[] nums) {
         
         int n = nums.length;

         int l = 0;
         int r = 0;
         int farthest = 0;
         int jumps = 0;

          while(r < n - 1)
          {
             for(int i=l;i<=r;i++)
             {
                int maxJmp = nums[i];
                farthest = Math.max(farthest,i+maxJmp);
             }
             l = r+1;
             r = farthest;
             jumps++;
          }

          return jumps;

    }
}



// class Solution {
//     public int jump(int[] nums) {
         
//           int n = nums.length;
          
//           int[] dp = new int[n];

//           for(int i=0;i<n;i++)
//            dp[i] = 100000;

//           dp[n-1] = 0;

//           for(int i=n-2;i>=0;i--)
//           {
//               int jmpSize = nums[i];

//               for(int jmp=1;jmp<=jmpSize && (i+jmp)<n;jmp++)
//                  dp[i] = Math.min(dp[i],1 + dp[i+jmp]);
//           }

//           return dp[0];


//     }
// }

