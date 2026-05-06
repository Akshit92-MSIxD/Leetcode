

// class Solution {
//     public int candy(int[] ratings) {
       
//         int n = ratings.length;
//         int[] candy = new int[n];

//         if(n == 1)
//         return 1;
       
//        for(int i=0;i<n;i++)
//        {

//            if(i-1>=0)
//            {
//              if(ratings[i] > ratings[i-1])
//                 candy[i] = candy[i-1]+1;
//              else
//                 candy[i] = 1;
//            }

//            if(i+1<=n-1)
//            {
//              if(ratings[i] > ratings[i+1])
//              {
//                 int j = i+2;

//                 while(j<n && ratings[j] < ratings[j-1])
//                 j++;

//                 int candies = j - i;

//                 candy[i] = Math.max(candy[i],candies--);
                 
//                 for(int k=i+1;k<j;k++)
//                  candy[k] = candies--;

//                  i = j - 1;
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



// class Solution {
//     public int candy(int[] ratings) {
       
//         int n = ratings.length;
//         int[] candy = new int[n];

//         if(n == 1)
//         return 1;

//         candy[0] = 1;       
//         for(int i=1;i<n;i++)
//         {
//             if(ratings[i] > ratings[i-1])
//                 candy[i] = candy[i-1] + 1;
//             else
//                 candy[i] = 1;
//         }

//         for(int i=n-2;i>=0;i--)
//         { 
//              if(ratings[i] > ratings[i+1])
//              candy[i] = Math.max(candy[i],candy[i+1] + 1);
//         }

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

        candy[0] = 1;       
        for(int i=1;i<n;i++)
        {
            if(ratings[i] > ratings[i-1])
                candy[i] = candy[i-1] + 1;
            else
                candy[i] = 1;
        }

       int res = candy[n-1];

        for(int i=n-2;i>=0;i--)
        { 
             if(ratings[i] > ratings[i+1])
             candy[i] = Math.max(candy[i],candy[i+1] + 1);

             res += candy[i];
        }



       return res;

    }
}
