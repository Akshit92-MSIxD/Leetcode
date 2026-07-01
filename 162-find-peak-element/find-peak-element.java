// class Solution {
//     public int findPeakElement(int[] nums) {
        
//         if(nums.length == 1)
//         return 0;

//         int n = nums.length;

//         if(nums[0] > nums[1])
//         return 0;

//         if(nums[n-1] > nums[n-2])
//         return n-1;

//         int low = 1;
//         int high = n-2;

//         while(low<=high)
//         {
//             int mid = low + (high - low)/2;

//             if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1])
//             return mid;

//             if(nums[mid] > nums[mid+1])
//             high = mid - 1;
//             else
//             low = mid + 1;
//         }

//         return -1;
//     }
// }



class Solution {
    public int findPeakElement(int[] nums) {
        
        int n = nums.length;

        int low = 0;
        int high = n-1;

        while(low<high)
        {
            int mid = low + (high - low)/2;

            if(nums[mid] > nums[mid+1])
            high = mid;
            else
            low = mid + 1;
        }

        return low;
    }
}