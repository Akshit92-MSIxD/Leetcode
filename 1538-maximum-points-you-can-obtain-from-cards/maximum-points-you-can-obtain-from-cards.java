class Solution {
    public int maxScore(int[] cardPoints, int k) {

         
         int n = cardPoints.length;

         int currRightWindowScore = 0;

         for(int i=n-1;i>=n-k;i--)
         currRightWindowScore += cardPoints[i];

         int rightWindowEndingIndex = n-k;

         int currLeftWindowScore = 0;

         int leftWindowEndingIndex = -1;

         int maxScore = Integer.MIN_VALUE;

         while(leftWindowEndingIndex < k)
         {
             maxScore = Math.max(maxScore,currLeftWindowScore + currRightWindowScore);
             leftWindowEndingIndex++;

             if(leftWindowEndingIndex == k)
             break;

             currLeftWindowScore += cardPoints[leftWindowEndingIndex];

             currRightWindowScore -= cardPoints[rightWindowEndingIndex];
             rightWindowEndingIndex++;
         }

         return maxScore;
             
    }
}