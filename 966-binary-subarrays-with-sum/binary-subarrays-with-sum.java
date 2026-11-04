class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {
             
             int n = nums.length;

             Map<Integer,Integer> prefixFreq = new HashMap<>();

             prefixFreq.put(0,1);

             int prefixSum = 0;

             int ans = 0;

             for(int num : nums)
             {
                prefixSum += num;

                if(prefixFreq.containsKey(prefixSum - goal))
                ans += prefixFreq.get(prefixSum - goal);


                if(prefixFreq.containsKey(prefixSum))
                prefixFreq.put(prefixSum,prefixFreq.get(prefixSum)+1);
                else
                prefixFreq.put(prefixSum,1);
            
             }

             return ans;

    }
}