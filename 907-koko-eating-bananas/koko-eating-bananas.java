
// Note : This problem is based on a pattern i.e Binary Search on Answers !!!




// Approach : Using Binary Search + Linear Search to calculate totalHours taken to eat every pile of bananas for different banana eating speed k !!

// *** Pattern : Binary Search on Answers !!! (since answer can lie from 1 to maxBanana in a particular pile) !!!
// https://www.youtube.com/watch?v=qyfekrNni90&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=13
// TC : O(n*logmax) , where n is the length of the pile !!!
// SC : O(1)

class Solution {

    int getTotalHours(int mid, int[] piles, int h)
    {
        int totalHours = 0;

        for(int val : piles)
        {
            if(val%mid==0)
            totalHours += val/mid;
            else
            totalHours += (val/mid) + 1;

            if(totalHours > h)
            return totalHours;
        }

        return totalHours;
    }


    public int minEatingSpeed(int[] piles, int h) {
          
            int max = Integer.MIN_VALUE;

            for(int val : piles)
            max = Math.max(val,max);
         
          int low = 1;
          int high = max;

          int k = Integer.MAX_VALUE;   // banana eating speed per hour

          while(low<=high)
          {
              int mid = low + (high-low)/2;

              int totalHours = getTotalHours(mid,piles,h);

              if(totalHours > h)
              {
                 low = mid + 1;
              }
              else
              {
                 k = mid;
                 high = mid - 1;
              }
          }

          return k;   // we can also return low in place of k
        
    }
}