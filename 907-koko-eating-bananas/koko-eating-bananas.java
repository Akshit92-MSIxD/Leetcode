class Solution {

    long
     getTotalHours(int mid, int[] piles, int h)
    {
        long totalHours = 0;

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

          int k = Integer.MAX_VALUE;

          while(low<=high)
          {
              int mid = low + (high-low)/2;

              long totalHours = getTotalHours(mid,piles,h);

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

          return k;
        
    }
}