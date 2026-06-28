


// Approach 1 : Using Binary Search
// Hint : Find the sorted region first -> then check if target lies in that region or not !!!
// TC : O(logn)
// SC : O(1)

class Solution {
    public int search(int[] nums, int target) {

          int low = 0;
          int high = nums.length-1;

          while(low<=high)
          {
             int mid = low + (high - low)/2;

             if(nums[mid] == target)
             {
                return mid;
             }
             else   // Find the sorted region first -> then check if target lies in that sorted region or not!!! -> if yes -> then search the target in the sorted region , if no -> then it is surely present in non sorted region so search the target in that non sorted region !!!
             {
                if(nums[high] > nums[mid])
                {
                    if(target > nums[mid] && target <= nums[high])
                       low = mid + 1;   
                    else
                        high = mid - 1;
                }
                else
                {
                    if(target >= nums[low] && target < nums[mid])
                        high = mid - 1;
                    else
                        low = mid + 1;
                }
             }
          }

          return -1;
    }
}