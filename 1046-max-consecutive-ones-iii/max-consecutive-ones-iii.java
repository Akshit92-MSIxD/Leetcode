class Solution {
    public int longestOnes(int[] nums, int k) {
         
            int n = nums.length;

            int l = 0;
            int r = 0;
            int currWinZeroes = 0;

            int maxLen = 0;

            while(r < n)
            {
                 if(nums[r] == 0)
                 currWinZeroes++;

                 while(currWinZeroes > k)
                 {
                    if(nums[l] == 0)
                    currWinZeroes--;

                    l++;
                 }
                   
                 maxLen = Math.max(maxLen,r-l+1);
                 r++;
            }   

            return maxLen;
    }
}