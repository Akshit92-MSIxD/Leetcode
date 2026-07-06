
// Note : I have written three approaches for this problem below. Approach 2 and 3 are most important !!!
// ***Note : When you revisit this problem please dry run this problem for sample testcases on  paper again and again for Approach 2 and 3 !!!

/* This problem's standard linear search and binary search is non-intuitive so please resolve it again and again 
 and visualize it on paper */




// Approach 1 : Using Brute Force Linear Search (Written by me !!!)
// TC : O(n)
// SC : O(n) [extra space taken by hashset]

class Solution {
    public int findKthPositive(int[] arr, int k) {
           
            HashSet<Integer> set = new HashSet<>();

            for(int val : arr)
            set.add(val);

            int missing = 0;

            int num = 1;

            while(true)
            {
                if(!set.contains(num))
                {
                    missing++;

                    if(missing == k)
                    break;
                }

                num++;
            }

            return num;    
    }
}



/*-----------------------------------------------------------------------------------------------------------*/


// Approach 2 : Standard Brute Force (Strivers Brute Force !!!)

/* Concept : Initially if all numbers are missing then kth missing number is always k !!! , if suddenly any number less than equal to current kth missing number appears non missing then kth missing number is shifted by 1 place i.e k+1 and this keeps on shifting everytime we find more non missing numbers like that !!!

 Ex : let have array be [2, 3, 4,5] and find 5th missing number

 so the concept is
 let consider intially that all numbers are missing and if all numbers are missing then our fifth missing number will be x= 5 so now if we iterate over array and we consider 2(0th element of array) so now  2 is not missing anymore so now our 5th missing number will be x= 6(shifted by 1),again on iterating we get 3 now our 3 is no more missing so now our 5th missing number will be x=7 ...........

 and in this process if we find any element of array greater then x(where x is 5th missing number)  it means that 5th missing number which is x till now is lesser then current element of array hence it is our answer   */


// class Solution {
//     public int findKthPositive(int[] arr, int k) {
              
//             int kthMissingNumber = k; // consider all numbers are missing in the beginning

//             for(int num : arr)
//             {
//                 if(num <= kthMissingNumber)
//                     kthMissingNumber++;   (shifted by 1)
//                 else
//                    break;
//             }

//             return kthMissingNumber;
//     }
// }




/*-----------------------------------------------------------------------------------------------------------*/



// Approach 3 : Using Binary Search (by striver !!!)
// Watch this : https://www.youtube.com/watch?v=uZ0N_hZpyps&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF

/* Hint :
   
   - Try calculating the no. of missing numbers to the left of current non-missing number at a particular index for each  indices where non-missing numbers are present !!!
   
    -> leftMissing = arr[mid] - (mid + 1) (How ???)

    -> Think if all the numbers are non-missing initially then -> at index i -> number = (i+1) will exist !!!
     (I want you to think about the remaining concept by your own !!!!)

   - Try Finding the range where kth missing number will lie using binary search !!! 
     
     -> After binary search is over high will point to i-1 and low will point to i and that kth missing will lie between arr[high] and arr[low] => (I want you to think about the remaining concept by your own !!!!)

   - *** After the binary search is over return (low+k) i.e derived from  (arr[high] + k - leftMissing_for_arr[high])  (Please derive it again using pen and paper !!!)
    
         
*/

// TC : O(logn)
// SC : O(1)

// class Solution {
//     public int findKthPositive(int[] arr, int k) {
              
//           int low = 0;
//           int high = arr.length-1;

//           while(low<=high)
//           {
//             int mid = low + (high-low)/2;

//             int leftMissing = arr[mid] - (mid+1);

//             if(leftMissing < k)
//                 low = mid + 1;
//             else
//                 high = mid - 1;
//           }

//           return low+k;  // This low+k is derived from (arr[high] + k - leftMissing_for_arr[high]) -> please derive it again using pen and paper !!!
//     }
// }


