// class Solution {
//     public int candy(int[] ratings) {
       
//         int n = ratings.length;
//         int[] candy = new int[n];

//         if(n == 1)
//         return 1;
       
//        for(int i=0;i<n;i++)
//        {
//            if(candy[i] != 0)
//            continue;

//            if(i-1>=0)
//            {
//              if(ratings[i] > ratings[i-1])
//                 candy[i] = Math.max(candy[i],candy[i-1]+1);
//              else
//                 candy[i] = 1;
//            }

//            if(i+1<=n-1)
//            {
//              if(ratings[i] > ratings[i+1])
//              {
//                 int j = i+1;

//                 while(j<n && ratings[j] < ratings[j-1])
//                 j++;

//                 int candies = j - i;

//                 for(int k=i;k<j;k++)
//                  candy[k] = Math.max(candy[k],candies--);
//              }
//              else
//              {
//                 candy[i] = Math.max(candy[i],1);
//              }
//            }
//        }


//        int minCandies = 0;

//        for(int val : candy)
//        minCandies+=val;

//        return minCandies;

//     }
// }


class Solution {
    public int candy(int[] ratings) {
       
        int n = ratings.length;
        int[] candy = new int[n];

        if(n == 1)
        return 1;
       
       for(int i=0;i<n;i++)
       {

           if(i-1>=0)
           {
             if(ratings[i] > ratings[i-1])
                candy[i] = candy[i-1]+1;
             else
                candy[i] = 1;
           }

           if(i+1<=n-1)
           {
             if(ratings[i] > ratings[i+1])
             {
                int j = i+2;

                while(j<n && ratings[j] < ratings[j-1])
                j++;

                int candies = j - i;

                candy[i] = Math.max(candy[i],candies--);
                 
                for(int k=i+1;k<j;k++)
                 candy[k] = candies--;

                 i = j - 1;
             }
             else
             {
                candy[i] = Math.max(candy[i],1);
             }
           }
       }


       int minCandies = 0;

       for(int val : candy)
       minCandies+=val;

       return minCandies;

    }
}