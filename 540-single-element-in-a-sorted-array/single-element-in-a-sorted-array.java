// class Solution {
//     public int singleNonDuplicate(int[] nums) {

//           int i=0;

//           while(i<nums.length-1)
//           {
//              if(nums[i] != nums[i+1])
//              return nums[i];
//              i+=2;
//           }
          
//           return nums[nums.length-1];
//     }
// }


class Solution {
    public int singleNonDuplicate(int[] nums) {

             int low = 0;
             int high = nums.length - 1;

             int singleElm = -1;

             while(low<=high)
             {
                int mid = low + (high - low)/2;

                if((mid-1 < 0 || nums[mid-1] != nums[mid]) && (mid+1>high || nums[mid] != nums[mid+1]))
                {
                    singleElm = nums[mid];
                    break;
                }

                if(mid%2==0)
                {
                   if(nums[mid-1] == nums[mid])
                   {
                       high = mid - 1;
                   }
                   else
                   {
                       low = mid + 2;
                   }
                }
                else
                {
                    if(nums[mid-1] == nums[mid])
                    {
                        low = mid + 1;
                    }
                    else
                    {
                        high = mid - 1;
                    }
                }
             }

             return singleElm;
    }
}