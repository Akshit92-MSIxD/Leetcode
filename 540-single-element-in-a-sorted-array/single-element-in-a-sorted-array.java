// Note : I have written three approaches for this problem below . Approach 2 and 3 are most optimal !!!
// ***Note : Please read both Approach 2 and 3 !!!





// Approach 1 : Using Optmized Linear Search
// TC : O(n/2)
// SC : O(1)

class Solution {
    public int singleNonDuplicate(int[] nums) {

          int i=0;

          while(i<nums.length-1)
          {
             if(nums[i] != nums[i+1])
             return nums[i];
             i+=2;
          }
          
          return nums[i];
    }
}





/*----------------------------------------------------------------------------------------------------*/





// Approach 2 : Using Binary Search + even-odd/odd-even index pair for couples Concept !!! (Written by me !!!)

// Hint : A elm is a single elm if and only if neither of its adjacent left or right element is equal to it.

// Watch this : https://www.youtube.com/watch?v=AZOmHuHadxQ&list=PLF6ChxadzFf8vjafLIxxbKUfarW4V4IOh&index=8

/* Concept :

   -> even-odd index pair for couples -> means we are standing in left region/side of single elm -> so go 
   towards right direction to find single elm

   -> odd-even index pair for couples -> means we are standing in right region/side of single elm -> so go towards left direction to find the single elm
                                                            */

// TC : O(logn)
// SC : O(1)



// class Solution {
//     public int singleNonDuplicate(int[] nums) {

//              int low = 0;
//              int high = nums.length - 1;

//              int singleElm = -1;

//              while(low<=high)
//              {
//                 int mid = low + (high - low)/2;

//                 if((mid-1 < 0 || nums[mid-1] != nums[mid]) && (mid+1>high || nums[mid] != nums[mid+1]))
//                 {
//                     singleElm = nums[mid];
//                     break;
//                 }

//                 if(mid%2==0)
//                 {
//                    if(nums[mid-1] == nums[mid])
//                    {
//                        high = mid - 1;
//                    }
//                    else
//                    {
//                        low = mid + 2;
//                    }
//                 }
//                 else
//                 {
//                     if(nums[mid-1] == nums[mid])
//                     {
//                         low = mid + 1;
//                     }
//                     else
//                     {
//                         high = mid - 1;
//                     }
//                 }
//              }

//              return singleElm;
//     }
// }






/*-----------------------------------------------------------------------------------------------------------*/






// Approach 3 : Using Binary Search + even-odd/odd-even index pair for couples Concept + Trimming first and last index and start binary search from  index 1 - index n-2 !!! -> to avoid multiple if/else conditions !!! (By Striver !!!)

// Watch this : https://www.youtube.com/watch?v=AZOmHuHadxQ&list=PLF6ChxadzFf8vjafLIxxbKUfarW4V4IOh&index=8

// Hint : A elm is a single elm if and only if neither of its adjacent left or right element is equal to it.

/* Concept :

   -> even-odd index pair for couples -> means we are standing in left region/side of single elm -> so go 
   towards right direction to find single elm

   -> odd-even index pair for couples -> means we are standing in right region/side of single elm -> so go towards left direction to find the single elm
                                                            */

// TC : O(logn)
// SC : O(1)


// class Solution {
//     public int singleNonDuplicate(int[] nums) {
             
//              int n = nums.length;

//              if(n == 1)
//              return nums[0];

//              if(nums[0] != nums[1])
//              return nums[0];

//              if(nums[n-1] != nums[n-2])
//              return nums[n-1];

//              int low = 1;
//              int high = n-2;

//              while(low<=high)
//              {
//                 int mid = low + (high - low)/2;

//                 if(nums[mid-1] != nums[mid]  && nums[mid] != nums[mid+1])
//                 return nums[mid];

//                 if(mid%2==1 && nums[mid] == nums[mid-1] || mid%2==0 && nums[mid] == nums[mid+1])
//                    low = mid + 1;
//                 else
//                    high = mid - 1;
//              }

//              return -1;   // dummy return statement
//     }
// }