
// Note : I have written two approaches for this problem below. Please read both of them !!!

// Approach 2 is the most optimal !!!


/*
Approach 2 : Using Prefix Sum and a HashMap to store the frequency of remainders.
Initialize the map with 0 -> 1 to handle subarrays starting from index 0.
Traverse the array and maintain a running prefixSum by adding the current element.
Calculate the remainder of prefixSum with k.
If the remainder is negative, add k to obtain its corresponding positive remainder.
Check whether the remainder exists in the map.
If it exists, add its frequency to ansCount because previous prefix sums
with the same remainder form subarrays whose sum is divisible by k.
Update the frequency of the current remainder in the map.

TC: O(n) — Traverse the array once, with O(1) average-time HashMap operations.
SC: O(min(n, k)) — Store the frequencies of distinct remainders in the HashMap.
*/


class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        
           
            int n = nums.length;

            Map<Integer,Integer> mp = new HashMap<>();

            mp.put(0,1);

            int ansCount = 0;

            int prefixSum = 0;

            for(int num : nums)
            {
                prefixSum += num;

                int rem = prefixSum % k;

                if(rem < 0) rem = rem + k; // will store the +ve corresponding remainder of -ve remainder

                if(mp.containsKey(rem))
                {
                   ansCount += mp.get(rem);
                   mp.put(rem,mp.get(rem)+1);
                }
                else
                {
                   mp.put(rem,1);
                }
            }

            return ansCount;

        
    } 
}




/*------------------------------------------------------------------------------------------------------------*/





/*
Approach 1 : Using two nested loops to generate all possible subarrays.
The outer loop fixes the starting index i of each subarray.
The inner loop extends the subarray by moving j from i to n-1.
Maintain a running sum by adding nums[j] to sum.
For every subarray, check whether sum is divisible by k.
If sum % k == 0, increment ansCount.

TC: O(n^2) — Generate all possible subarrays using two nested loops.
SC: O(1) — Use only a constant number of extra variables.
*/

// class Solution {
//     public int subarraysDivByK(int[] nums, int k) {
        
           
//             int n = nums.length;

//             int ansCount = 0;

//             for(int i=0;i<n;i++)
//             {
//                  int sum = 0;
//                 for(int j=i;j<n;j++)
//                 { 
//                    sum += nums[j];

//                    if(sum % k == 0)
//                    ansCount++;
//                 }
//             }

//             return ansCount;
//     } 
// }







