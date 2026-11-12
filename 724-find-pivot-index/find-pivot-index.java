
// Note : I have written two approaches for this problem below. Please read both of them !!!

// PrefixSum has two conventions i.e Inclusive(prefixSum[i] -> sum from 0 till i index) and Exclusive (prefixSum[i] -> sum from 0 till i-1 index)



// Approach 1 : PrefixSum + SuffixSum (Exclusive)
// TC : O(n+n+n)
// SC : O(n+n) [extra prefixSum[] + suffixSum[]]

class Solution {
    public int pivotIndex(int[] nums) {
          
          int n = nums.length;

          int[] prefixSum = new int[n];
          int[] suffixSum = new int[n];

          for(int i=1;i<n;i++)
          prefixSum[i] = prefixSum[i-1] + nums[i-1];

          for(int i=n-2;i>=0;i--)
          suffixSum[i] = suffixSum[i+1] + nums[i+1];
          
          int pivotIndex = -1;

          for(int i=0;i<n;i++)
          {
             if(prefixSum[i] == suffixSum[i])
             {
             pivotIndex = i;
             break;
             }
          }

          return pivotIndex;

          
    }
}


/*----------------------------------------------------------------------------------------------------------*/



// Approach 2 : PrefixSum + SuffixSum (Inclusive + Space Optimized)
// TC : O(n+n)
// SC : O(1)


// class Solution {
//     public int pivotIndex(int[] nums) {
          
//           int n = nums.length;

//           int sum = 0;

//           for(int num : nums)
//           sum += num;

//           int prefixSum = 0;

//           for(int i=0;i<n;i++)
//           {
//             prefixSum += nums[i];

//             int suffixSum = sum - prefixSum + nums[i];

//             if(prefixSum == suffixSum)
//             return i;
//           }

//           return -1;
            
//     }
// }