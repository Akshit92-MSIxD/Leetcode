

// Approach 1 : Using Binary Search + Find Unsorted Region Concept
// TC : O(logn)
// SC : O(1) 

class Solution {
    public int findMin(int[] nums) {

          int low = 0;
          int high = nums.length-1;

          while(low<high)
          {
             int mid = low + (high - low)/2;

             if(nums[high]<nums[mid])   // check if right region is unsorted -> if yes then minimum element definitely lies in the right region !!! , if no -> definitely in the left region (sorted or unsorted does not matter) including mid(includive) !!!
                low = mid+1;
             else
                high = mid;
          }

          return nums[low];
        
    }
}