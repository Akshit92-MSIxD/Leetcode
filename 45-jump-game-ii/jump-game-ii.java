class Solution {
    public int jump(int[] nums) {
         
          int n = nums.length;
          
          int[] dp = new int[n];

          for(int i=0;i<n;i++)
           dp[i] = Integer.MAX_VALUE;

          dp[n-1] = 0;

          for(int i=n-2;i>=0;i--)
          {
              int jmpSize = nums[i];

              for(int jmp=1;jmp<=jmpSize && (i+jmp)<n;jmp++)
              {  
                 if(dp[i+jmp] != Integer.MAX_VALUE)
                 dp[i] = Math.min(dp[i],1 + dp[i+jmp]);
              }
          }
         
          for(int i=n-1;i>=0;i--)
          {
            System.out.print(dp[i]+" ");
          }
          return dp[0];


    }
}