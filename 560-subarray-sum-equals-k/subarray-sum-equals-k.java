// I have written two approaches for this problem below. Please read both of them !!!

// Approach 2 is optimal !!!




/*
Approach 1 : Using Prefix Sum and a HashMap to store the frequency of prefix sums.
Initialize the map with 0 -> 1 to handle subarrays starting from index 0.
Calculate the prefix sum at each index and find (prefix[i] - k).

If this value exists in the map, add its frequency to ans because it represents
the number of previous prefix sums that form subarrays with sum k.
Update the frequency of the current prefix sum in the map.

TC: O(n) — Traverse the array once, with O(1) average-time HashMap operations.
SC: O(n) — Store prefix sums and their frequencies in the HashMap.

*/


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





/*------------------------------------------------------------------------------------------------------------*/


/*
Approach 2 : Using Prefix Sum and a HashMap to store the frequency of prefix sums.
Initialize the map with 0 -> 1 to handle subarrays starting from index 0.
Traverse the array and maintain a running prefixSum by adding the current element.
For each prefixSum, check whether (prefixSum - k) exists in the map.
If it exists, add its frequency to ans because it represents the number of
previous prefix sums that form subarrays with sum k.
Update the frequency of the current prefixSum in the map.

TC: O(n) — Traverse the array once, with O(1) average-time HashMap operations.
SC: O(n) — Store the frequencies of prefix sums in the HashMap.
*/


// class Solution {
//     public int subarraySum(int[] nums, int k) {
           
//            int n = nums.length;

//            int prefixSum = 0;

//            Map<Integer,Integer> prefixFreq = new HashMap<>();
           
//            prefixFreq.put(0,1);

//            int ans = 0;

//           for(int num : nums)
//           {
//              prefixSum += num;

//              if(prefixFreq.containsKey(prefixSum-k))
//                 ans += prefixFreq.get(prefixSum-k);

//              if(!prefixFreq.containsKey(prefixSum))
//               prefixFreq.put(prefixSum,1);
//              else
//               prefixFreq.put(prefixSum,prefixFreq.get(prefixSum)+1);

//           }

//           return ans;
//     }
// }