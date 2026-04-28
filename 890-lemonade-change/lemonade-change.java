
// TC : O(n)
// SC : O(1)
// Why this problem is greedy ?? --> shopkeeper will first try to give priority to the greater denomination notes and keep the smaller denomination notes for future use !!!

class Solution {
    public boolean lemonadeChange(int[] bills) {
          
           int count_5 = 0;
           int count_10 = 0;

           for(int i=0;i<bills.length;i++)
           {
               if(bills[i] == 5)
               {
               count_5++;
               }
               else if(bills[i] == 10)
               {
                 count_10++;
        
                if(count_5 >=1)
                    count_5--;
                else
                    return false;
               }
               else
               {
                 if(count_10 >=1 && count_5>=1 )
                 {
                    count_10--;
                    count_5--;
                 }
                 else
                 {
                     if(count_5 >= 3)
                        count_5 -= 3;
                      else
                      return false;
                 }

               }
           }


           return true;
    }
}