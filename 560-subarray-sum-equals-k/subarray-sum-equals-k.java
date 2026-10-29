
class Solution {
    public int subarraySum(int[] nums, int k) {
           
           int n = nums.length;

           int[] prefix = new int[n];

           prefix[0] = nums[0];

           Map<Integer,Integer> prefixFreq = new HashMap<>();
           
           prefixFreq.put(0,1);

           if(nums[0] != 0)
           prefixFreq.put(nums[0],1);
           else
           prefixFreq.put(0,2);
           
           int ans = 0;

           if(nums[0] == k)
           ans++;


          for(int i=1;i<n;i++)
          {
             prefix[i] = prefix[i-1]+nums[i];

             int x = prefix[i] - k;

             if(prefixFreq.containsKey(x))
                ans = ans + prefixFreq.get(x);
             

             if(!prefixFreq.containsKey(prefix[i]))
             prefixFreq.put(prefix[i],1);
             else
             prefixFreq.put(prefix[i],prefixFreq.get(prefix[i])+1);

          }

          return ans;
    }
}