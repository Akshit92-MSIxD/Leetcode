// I have written two approaches for this problem below. Please read both of them !!!

// Approach 2 is optimal !!!



// Approach 1 : Using PrefixSum + HashMap
// Prerequisite Problem : https://leetcode.com/problems/subarray-sum-equals-k/description/ 
// Hint : Use PrefixSum and its frequencies to determine the number of binary subarrays with sum = goal
// TC : O(n)
// SC : O(n) [using extra hashmap to store frequencies of individual prefixSums]

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




/*----------------------------------------------------------------------------------------------------------*/



// Approach 2 : Using Sliding Window
// Hint : If you want to find CountOfSubarraysSumEqualTo(Goal) --> CountOfSubarraysSumLessThanEqualTo(Goal) - CountOfSubarraysSumLessThanEqualTo(Goal-1)

// TC : O(n+n) ~ O(n)
// SC : O(1)

// class Solution {
    
//     int getCountOfSubarraysSumLessThanEqualTo(int[] nums, int goal)
//     {
//           int count = 0;

//           int l=0;
//           int r=0;
//           int n=nums.length;

//           int winSum = 0;

//           while(r<n)
//           {
//                winSum += nums[r];

//                while(winSum > goal)
//                {
//                  winSum -= nums[l];
//                  l++;
//                }

//                count = count + (r-l+1);
//                r++;
//           }

//           return count;


//     }

//     public int numSubarraysWithSum(int[] nums, int goal) {
             
//              int n = nums.length;
            
//             if(goal == 0)
//             return  getCountOfSubarraysSumLessThanEqualTo(nums,goal);       

//           return  getCountOfSubarraysSumLessThanEqualTo(nums,goal) -  getCountOfSubarraysSumLessThanEqualTo(nums,goal-1);

//     }
// }