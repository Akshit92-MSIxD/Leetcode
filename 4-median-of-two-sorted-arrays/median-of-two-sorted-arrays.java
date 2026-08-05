// Note : I have written three approaches for this problem below . Please read all of them !!!
// *** Note : Approach 3 is most optimal !!!



// Approach 1 : Brute Force (merging two sorted arrays and create a new one !!!)
// TC : O(m+n)
// Auxiliary SC : O(m+n)

class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n];

        int i = 0, j = 0, k = 0;

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j])
                merged[k++] = nums1[i++];
            else
                merged[k++] = nums2[j++];
        }

        while (i < m)
            merged[k++] = nums1[i++];

        while (j < n)
            merged[k++] = nums2[j++];

        if ((m + n) % 2 == 0)
            return (merged[(m + n) / 2] + merged[(m + n - 1) / 2]) / 2.0;

        return merged[(m + n) / 2];
    }
}





/*------------------------------------------------------------------------------------------------------------*/





// Approach 2 : Same as Approach 1 but we would not create an extra array and instead we use curr and prev pointer to find median !!!
// TC : O(m+n)
// Auxiliary SC : O(1)

// class Solution {
//     public double findMedianSortedArrays(int[] nums1, int[] nums2) {

//             int m  = nums1.length;
//             int n = nums2.length;

//             int i=0,j=0,k=0;

//             int curr = Integer.MIN_VALUE;
//             int prev = curr;

//             int end = (m+n)/2;

//             while(i<m && j<n && k<=end)
//             {
//                 if(nums1[i]<=nums2[j])
//                 {
//                     prev = curr;
//                     curr = nums1[i++];      
//                 }
//                 else
//                 {
//                    prev = curr;
//                    curr = nums2[j++];
//                 }
//                 k++;
//             }

//             while(i<m && k<=end)
//             {
//                 prev = curr;
//                 curr = nums1[i++];
//                 k++;
//             }

//             while(j<n && k<=end)
//             {
//                 prev = curr;
//                 curr = nums2[j++];
//                 k++;
//             }

//             if((m+n)%2==0)
//             return prev + (curr-prev)/2.0;

//             return curr + 0.00;
//     }
// }





/*----------------------------------------------------------------------------------------------------*/





// Approach 3 : Partition Binary Search (Strivers Approach !!!) 
// Concept : Think in terms of how much elements you would pick from array1 for the left partition and that would become your search space for binary search !!!
// TC : O(log(min(n,m))
// Auxiliary SC : O(1)

// class Solution {
//     public double findMedianSortedArrays(int[] nums1, int[] nums2) {

//         if (nums1.length > nums2.length) // trick to swap nums1 and nums2 var in case nums1 > nums2 
//             return findMedianSortedArrays(nums2, nums1);

//         int n1 = nums1.length;
//         int n2 = nums2.length;

//         int low = 0;
//         int high = n1;

//         while (low <= high) {
//             int mid1 = low + (high - low) / 2; // mid1 represent two things -> no. of elements to pick for left partition from smaller array n1 / index that is just one step ahead of left partition boundary in array n1

//             int mid2 = (n1 + n2) / 2 - mid1; // mid2 represent two things -> no. of elements to pick for left partition from larger array n2 / index that is just one step ahead of left partition boundary in array n2

//             int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1]; // possible ending value of left partition of the merged array 
//             int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1]; // possible ending value of left partition of the  merged array (out of l1 or l2 only one is going to become the ending value of left partion of the  merged array !!!)

//             int r1 = (mid1 >= n1) ? Integer.MAX_VALUE : nums1[mid1]; // possible starting value of the right partition of the merged array 

//             int r2 = (mid2 >= n2) ? Integer.MAX_VALUE : nums2[mid2]; // possible starting value of the right partition of the merged array (out of r1 and r2 only one is going to become the starting value of the right partion of the merged array !!!)

//             if (l1 <= r2 && l2 <= r1)
//                 return ((n1 + n2) % 2 == 0) ? (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0 : Math.min(r1, r2) + 0.0;
//             else if (l1 > r2) // in this case try picking less elements from smaller array n1
//                 high = mid1 - 1;
//             else // in this case try picking more elements from smaller array n1
//                 low = mid1 + 1;
//         }

//         return -1; // dummy return value

//     }
// }