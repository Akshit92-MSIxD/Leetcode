// class Solution {
//     public int findKthPositive(int[] arr, int k) {
           
//             HashSet<Integer> set = new HashSet<>();

//             for(int val : arr)
//             set.add(val);

//             int missing = 0;

//             int num = 1;

//             while(true)
//             {
//                 if(!set.contains(num))
//                 {
//                     missing++;

//                     if(missing == k)
//                     break;
//                 }

//                 num++;
//             }

//             return num;    
//     }
// }



/*-----------------------------------------------------------------------------------------------------------*/



// class Solution {
//     public int findKthPositive(int[] arr, int k) {
              
//             int kthMissingNumber = k; // consider all numbers are missing in the beginning

//             for(int num : arr)
//             {
//                 if(num <= kthMissingNumber)
//                     kthMissingNumber++;
//                 else
//                    break;
//             }

//             return kthMissingNumber;
//     }
// }


/*-----------------------------------------------------------------------------------------------------------*/


class Solution {
    public int findKthPositive(int[] arr, int k) {
              
          int low = 0;
          int high = arr.length-1;

          while(low<=high)
          {
            int mid = low + (high-low)/2;

            int leftMissing = arr[mid] - (mid+1);

            if(leftMissing < k)
                low = mid + 1;
            else
                high = mid - 1;
          }

          return k+low;
    }
}


