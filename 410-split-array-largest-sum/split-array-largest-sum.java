// Note : Please understand the Approach 2 of "Allocating Minimum Pages" problem before solving this problem !!!



// Approach : Binary Search on Answers + Standard Greedy Helper Function (Split Later wala concept !!!)
// Prerequisite Problem : https://www.geeksforgeeks.org/problems/allocate-minimum-number-of-pages0937/1
// TC : O(n*log(sum-maxElm))
// SC : O(1)

class Solution {

   boolean isValid(int[] nums , long maxSumLimit , int k)
   {
        int currSubarray = 1;
        long currSum = 0;

        for(int val : nums)
        {
            if(currSum + val <= maxSumLimit)
            {
                currSum += val;
            }
            else
            {
                currSubarray++;
                currSum = val;
            }    
        }

        if(currSubarray <=k)   // Dont worry for " < k subarrays" we can later split them to make exactly equal k subarrays where each subarray sum satisfies the "<= maxSumLimit" !!!
        return true;


        return false;
   }



    public int splitArray(int[] nums, int k) {
         
             
             int maxElm = Integer.MIN_VALUE;
             long sum = 0;

             for(int val : nums)
             {
                 sum += val;
                 maxElm = Math.max(maxElm,val);
             }


             long low = maxElm;
             long high = sum;

             long minMaximumSum = Long.MAX_VALUE;

             while(low<=high)
             {
                long mid = low + (high - low)/2;
                
                 if(isValid(nums,mid,k))
                 {
                    minMaximumSum = mid;
                    high = mid - 1;
                 }
                 else
                 {
                    low = mid + 1;
                 }

             }

             return (int)minMaximumSum;
    }
}