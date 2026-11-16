/*
Approach : Using Prefix Sum and a HashMap to find the maximum length
of a contiguous subarray containing an equal number of 0s and 1s.
Convert 0 into -1 and keep 1 as +1 so that a subarray with an equal
number of 0s and 1s has a sum of 0.
Initialize the map with 0 -> -1 to handle subarrays starting from index 0.
Traverse the array and maintain a running prefixSum.
If the prefixSum already exists in the map, calculate the subarray length
using the current index and the first occurrence index stored in the map.
Update maxLen if a longer valid subarray is found.
Otherwise, store the current prefixSum with its index.
Store only the first occurrence of each prefixSum to obtain the maximum
possible subarray length.

TC: O(n) — Traverse the array once, with O(1) average-time HashMap operations.
SC: O(n) — Store the first occurrence of distinct prefix sums in the HashMap.
*/



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