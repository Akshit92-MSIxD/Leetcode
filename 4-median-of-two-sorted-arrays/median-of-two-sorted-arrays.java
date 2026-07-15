// class Solution {
//     public double findMedianSortedArrays(int[] nums1, int[] nums2) {
           
//             int m  = nums1.length;
//             int n = nums2.length;

//             int[] merged = new int[m+n];

//             int i=0,j=0,k=0;


//             while(i<m && j<n)
//             {
//                 if(nums1[i]<=nums2[j])
//                 merged[k++] = nums1[i++];
//                 else
//                 merged[k++] = nums2[j++];
//             }

//             while(i<m)
//             merged[k++] = nums1[i++];  

//             while(j<n)
//             merged[k++] = nums2[j++];

//             if((m+n)%2==0)
//             return (merged[(m+n)/2] + merged[(m+n-1)/2])/2.0;

//             return merged[(m+n)/2];
//     }
// }


class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
           
            int m  = nums1.length;
            int n = nums2.length;

            int i=0,j=0,k=0;

            int curr = Integer.MIN_VALUE;
            int prev = curr;

            int end = (m+n)/2;

            while(i<m && j<n && k<=end)
            {
                if(nums1[i]<=nums2[j])
                {
                    prev = curr;
                    curr = nums1[i++];      
                }
                else
                {
                   prev = curr;
                   curr = nums2[j++];
                }
                k++;
            }

            while(i<m && k<=end)
            {
                prev = curr;
                curr = nums1[i++];
                k++;
            }

            while(j<n && k<=end)
            {
                prev = curr;
                curr = nums2[j++];
                k++;
            }

            if((m+n)%2==0)
            return prev + (curr-prev)/2.0;

            return curr + 0.00;
    }
}