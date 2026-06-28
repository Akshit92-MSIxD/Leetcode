// class Solution {
//     public int[] searchRange(int[] nums, int target) {

//          if(nums.length == 0)
//          return new int[]{-1,-1};
        
//          int low = 0;
//          int high = nums.length - 1;
//          int mid = -1;

//          while(low <= high)
//          {
//            mid = low + (high - low)/2;

//             if(nums[mid] == target)
//             break;
//             else if(nums[mid] < target)
//             low = mid + 1;
//             else
//             high = mid - 1;
//          }

//          if(low>high)
//          return new int[]{-1,-1};

//          int first = mid;
//          int last = mid;

//          low = 0;
//          high = mid - 1;

//          while(low <= high)
//          {
//             int new_mid_1 = low + (high - low)/2;

//             if(nums[new_mid_1] == target)
//             {
//                 first = new_mid_1;
//                 high = new_mid_1 - 1;
//             }
//             else if(nums[new_mid_1] > target)
//             {
//                high = new_mid_1 - 1;
//             }
//             else
//             {
//                 low = new_mid_1 + 1;
//             }
//          }

//          low = mid + 1;
//          high = nums.length - 1;

//          while(low <= high)
//          {
//             int new_mid_2 = low + (high - low)/2;

//             if(nums[new_mid_2] == target)
//             {
//                 last = new_mid_2;
//                 low = new_mid_2 + 1;
//             }
//             else if(nums[new_mid_2] > target)
//             {
//                high = new_mid_2 - 1;
//             }
//             else
//             {
//                 low = new_mid_2 + 1;
//             }
//          }


//          return new int[]{first,last};

//     }
// }


class Solution {

    int getFirstOccurrence(int[] nums, int mid)
    {
         int target = nums[mid];
         int first = mid;

         int low = 0;
         int high = mid - 1;

         while(low <= high)
         {
            int new_mid = low + (high - low)/2;

            if(nums[new_mid] == target)
            {
                first = new_mid;
                high = new_mid - 1;
            }
            else if(nums[new_mid] > target)
            {
               high = new_mid - 1;
            }
            else
            {
                low = new_mid + 1;
            }
         }

         return first;

    }

    int getLastOccurrence(int[] nums, int mid)
    {
         int target = nums[mid];
         int last = mid;

         int low = mid + 1;
         int high = nums.length - 1;

         while(low <= high)
         {
            int new_mid = low + (high - low)/2;

            if(nums[new_mid] == target)
            {
                last = new_mid;
                low = new_mid + 1;
            }
            else if(nums[new_mid] > target)
            {
               high = new_mid - 1;
            }
            else
            {
                low = new_mid + 1;
            }
         }

         return last;
    }




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

         int first = getFirstOccurrence(nums,mid);
         int last =  getLastOccurrence(nums,mid);


         return new int[]{first,last};

    }
}