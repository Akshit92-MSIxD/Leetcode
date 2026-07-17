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




/*------------------------------------------------------------------------------------------------------------*/




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



class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
           
            int m  = nums1.length;
            int n = nums2.length;

           int[] smallArray = null;
           int[] largeArray = null;

           if(m <= n)
           {
             smallArray = nums1;
             largeArray = nums2;
           }
           else
           {
             smallArray = nums2;
             largeArray = nums1;
           }


           int low = 0;
           int high = smallArray.length;

           int leftHalf = (m+n+1)/2;


           while(low <= high)
           {
               int mid1 = low + (high - low)/2;
               int mid2 = leftHalf - mid1;

               int l1 = Integer.MIN_VALUE;
               int l2 = Integer.MIN_VALUE;

               int r1 = Integer.MAX_VALUE;
               int r2 = Integer.MAX_VALUE;

               if(mid1 > 0)
               l1 = smallArray[mid1-1];

               if(mid2 > 0)
               l2 = largeArray[mid2-1];

               if(mid1 < smallArray.length)
               r1 = smallArray[mid1];

               if(mid2 < largeArray.length)
               r2 = largeArray[mid2];


               if(l1 <= r2 && l2 <= r1)
               {

                   if((m+n)%2 == 0)
                   {
                    return (Math.max(l1,l2) + Math.min(r1,r2))/2.0;
                   }

                   return Math.max(l1,l2);
               }
               else if(l1 > r2)
               {
                  high = mid1 - 1;
               }
               else
               {
                 low = mid1 + 1;
               }
           }

           return -1; // dummy return value;
    }
}