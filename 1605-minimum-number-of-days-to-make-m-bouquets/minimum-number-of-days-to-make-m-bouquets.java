class Solution {

    boolean isMakingAllBouquetsPossible(int[] bloomDay,int day, int m, int k)
    {
           int createdBouquets = 0;
           int pickedFlower = 0;

           int n = bloomDay.length;

           int i = 0;

           while(i<n)
           {
              if(bloomDay[i] <= day)
              {
                pickedFlower++;

                 if(pickedFlower == k)
                 {
                  createdBouquets++;
                  pickedFlower = 0;

                  if(createdBouquets == m)
                  return true;

                 } 
              }
              else
              {
                pickedFlower = 0;
              }
              i++;
           }


           return false;
    }

    public int minDays(int[] bloomDay, int m, int k) {
        
            
               int n = bloomDay.length;

               if((long)m*k > n)return -1;


               int minDay = Integer.MAX_VALUE;
               int maxDay = Integer.MIN_VALUE;

               for(int day : bloomDay)
               {
               minDay = Math.min(minDay,day);
               maxDay = Math.max(maxDay,day);
               }
            

               int low = minDay;
               int high = maxDay;

               while(low<=high)
               {
                 int mid = low + (high-low)/2;  // mid represents days

                 boolean is_possible = isMakingAllBouquetsPossible(bloomDay,mid,m,k);

                 if(is_possible == true)
                   high = mid - 1;
                 else
                    low = mid + 1;
               }

               return low;
    }
}