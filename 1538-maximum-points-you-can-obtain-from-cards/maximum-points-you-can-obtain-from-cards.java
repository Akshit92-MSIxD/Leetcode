

// Approach 1 : Sliding Window
// Hint : Use two windows i.e left and right window separately !!!
// TC : O(k) [expansion of left window] + O(k) [shrinking left window and expanding right window]
// SC : O(1)


class Solution {
    public int maxScore(int[] cardPoints, int k) {

         int n = cardPoints.length;

         int lsum = 0;  // lsum represents  left window
         int rsum = 0;  // rsum represents right window

         for(int i=0;i<k;i++)    // expansion of left window
         lsum += cardPoints[i];

         int rindex = n-1;

         int maxSum = lsum;

         for(int i=k-1;i>=0;i--)    // shrink the left window and expand the right window  both from right to left
         {
              lsum -= cardPoints[i];
              rsum += cardPoints[rindex];
              rindex--;

              maxSum = Math.max(lsum + rsum,maxSum);
         }

         return maxSum;        
    }
}