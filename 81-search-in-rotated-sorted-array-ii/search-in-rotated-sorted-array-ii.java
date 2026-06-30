
// Approach 1 : Using Binary Search + Trim Search Space Concept
// TC : O(logn) (average case)
// TC : O(n) [ in worst case] [Ex : {3,3,3,3,3,3,3,3,3,3,3,3,3,3} , target = 0]
// SC : O(1)

class Solution {
    public boolean search(int[] nums, int target) {
          
           int low = 0;
           int high = nums.length - 1;

           while(low <= high)
           {
               int mid = low + (high - low)/2;

               if(nums[mid] == target)
               {
                 return true;
               }
               
            // For this type of condition (bc konsa wala region sorted hai teeno to equal aarahe)
            // Solution : trim down the left and right region continuously until this problem disappeared

               if(low <= high && nums[low] == nums[mid] && nums[mid] == nums[high])
               {
                   low++;
                   high--;
                   continue;
               }

                  
                // Identify the sorted region first

                  if(nums[high] >= nums[mid]) // right region is sorted region if this case is true   
                  {
                      if(target > nums[mid] && target <= nums[high])  // check if target lies in right sorted region
                      {
                         low = mid + 1;
                      }
                      else   // target lies in left non-sorted region
                      {
                        high = mid - 1;
                      }
                  }
                  else      // left region is sorted region if this case is true                                             
                  {
                      if(target >= nums[low] && target < nums[mid]) // check if target lies in left sorted region
                      {
                         high = mid - 1;
                      }
                      else     // target lies in right non-sorted region
                      {
                        low = mid + 1;
                      }
                  }
           }

           return false;
    }
}