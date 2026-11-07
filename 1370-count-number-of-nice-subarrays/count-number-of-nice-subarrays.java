class Solution {

     int countOfSubarraysHavingOddsLessThanEqualTo(int[] nums,int k)
     {
        if(k == -1)
        return 0;

          int l=0;
          int r=0;
          int countOfOdds = 0;

          int n = nums.length;
          int countOfSubarrays = 0;

          while(r<n)
          {
             countOfOdds += nums[r]%2;

             while(countOfOdds > k)
             {
                countOfOdds -= nums[l]%2;
                l++;
             }

             countOfSubarrays += (r-l+1);
             r++;
          }      

          return countOfSubarrays;
     }

    public int numberOfSubarrays(int[] nums, int k) {

            return countOfSubarraysHavingOddsLessThanEqualTo(nums,k) - countOfSubarraysHavingOddsLessThanEqualTo(nums,k-1);
        
    }
}