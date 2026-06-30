// class Solution {
//     public int findMin(int[] nums) {

         
//           int minElm = Integer.MAX_VALUE;

//           int low = 0;
//           int high = nums.length-1;

//           while(low<=high)
//           {
//              int mid = low + (high - low)/2;
             
//              if(nums[mid] < minElm)
//              minElm = nums[mid];

//              if(nums[high]<nums[mid])
//                 low = mid+1;
//              else
//                 high = mid-1;
//           }

//           return minElm;
        
//     }
// }




class Solution {
    public int findMin(int[] nums) {

          int low = 0;
          int high = nums.length-1;

          while(low<high)
          {
             int mid = low + (high - low)/2;

             if(nums[high]<nums[mid])
                low = mid+1;
             else
                high = mid;
          }

          return nums[low];
        
    }
}