class Solution {
    public int minSubArrayLen(int target, int[] nums) {
         
           int n = nums.length;

           int minLen = Integer.MAX_VALUE;

           int winSum = 0;

           int l=0;
           int r=0;
           
           while(r<n)
           {
              winSum += nums[r];

                 while(winSum >= target)
                 {
                    minLen = Math.min(minLen,r-l+1);
                    winSum -= nums[l];
                    l++;
                 }

                 r++;
           }

          return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }
}