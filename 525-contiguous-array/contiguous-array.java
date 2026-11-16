class Solution {
    public int findMaxLength(int[] nums) {
        
          int n = nums.length;

          Map<Integer,Integer> mp = new HashMap<>();

          mp.put(0,-1);

          int prefixSum = 0;

          int maxLen = 0;

          for(int i=0;i<n;i++)
          {
            prefixSum += (nums[i]==0) ? -1 : 1;

             if(mp.containsKey(prefixSum))
                maxLen = Math.max(maxLen,i-mp.get(prefixSum));
             else
                mp.put(prefixSum,i);
          }

          return maxLen;
    }
}