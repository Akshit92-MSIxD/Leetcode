class Solution {

    int getRequiredDays(int cap, int[] weights,int days)
    {
         int requiredDays = 0;
         int weightSum = 0;

         for(int weight : weights)
         {
            weightSum += weight;

            if(weightSum == cap)
            {
                requiredDays++;
                weightSum = 0;
            }
            else if(weightSum > cap)
            {
                requiredDays++;
                weightSum = weight;
            }

            if(requiredDays > days)
            return requiredDays;
         }

         if(weightSum > 0)
         requiredDays++;

         return requiredDays;
    }

    public int shipWithinDays(int[] weights, int days) {
        
         int maxWeight = Integer.MIN_VALUE;
         int maxCapacity = 0;

         for(int val : weights)
         {
            maxWeight = Math.max(maxWeight,val);
            maxCapacity += val;
         }

         int low = maxWeight;
         int high = maxCapacity;

         while(low<=high)
         {
            int mid = low + (high-low)/2; // mid represent the capacity of the ship

            int requiredDays = getRequiredDays(mid,weights,days);

            if(requiredDays > days)
               low = mid + 1;
            else
               high = mid - 1;
         }

         return low;


    }
}