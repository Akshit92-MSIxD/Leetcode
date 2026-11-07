class Solution {

    int countNoOfSubarraysHavingDifferentIntegersAtmost(int[] nums, int k)
    {
        
        int l=0 , r=0;
        int countOfSubarrays = 0;

        Map<Integer,Integer> mp = new HashMap<>();

        int n = nums.length;

        while(r<n)
        {
            if(!mp.containsKey(nums[r]))
            mp.put(nums[r],1);
            else
            mp.put(nums[r],mp.get(nums[r])+1);

            while(mp.size() > k)
            {
                mp.put(nums[l],mp.get(nums[l])-1);

                if(mp.get(nums[l]) == 0)
                mp.remove(nums[l]);
                
                l++;
            }

            countOfSubarrays += (r-l+1);
            r++;
        }

        return countOfSubarrays;



    }

    public int subarraysWithKDistinct(int[] nums, int k) {
          
           return countNoOfSubarraysHavingDifferentIntegersAtmost(nums,k) - countNoOfSubarraysHavingDifferentIntegersAtmost(nums,k-1);
    }
}