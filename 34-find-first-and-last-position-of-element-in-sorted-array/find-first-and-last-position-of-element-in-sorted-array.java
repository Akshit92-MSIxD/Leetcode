class Solution {
    public int[] searchRange(int[] nums, int target) {

         if(nums.length == 0)
         return new int[]{-1,-1};
        
         int low = 0;
         int high = nums.length - 1;
         int mid = -1;

         while(low <= high)
         {
           mid = low + (high - low)/2;

            if(nums[mid] == target)
            break;
            else if(nums[mid] < target)
            low = mid + 1;
            else
            high = mid - 1;
         }

         if(low>high)
         return new int[]{-1,-1};

         int first = mid;
         int last = mid;

         low = 0;
         high = mid - 1;

         while(low <= high)
         {
            int new_mid_1 = low + (high - low)/2;

            if(nums[new_mid_1] == target)
            {
                first = new_mid_1;
                high = new_mid_1 - 1;
            }
            else if(nums[new_mid_1] > target)
            {
               high = new_mid_1 - 1;
            }
            else
            {
                low = new_mid_1 + 1;
            }
         }

         low = mid + 1;
         high = nums.length - 1;

         while(low <= high)
         {
            int new_mid_2 = low + (high - low)/2;

            if(nums[new_mid_2] == target)
            {
                last = new_mid_2;
                low = new_mid_2 + 1;
            }
            else if(nums[new_mid_2] > target)
            {
               high = new_mid_2 - 1;
            }
            else
            {
                low = new_mid_2 + 1;
            }
         }


         return new int[]{first,last};

    }
}