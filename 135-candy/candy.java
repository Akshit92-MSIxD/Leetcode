// Note : I have written three appoaches for this problem below. Please read all of them !!!
// Note : Approach 3 is most optimal but tricky so please watch its video again and dry run it on paper !!!



// Approach 2 : Strivers Approach Greedy (LtoR + RtoL Traversal!!!)
// TC : O(2n)
// SC : O(n)

class Solution {
    public int candy(int[] ratings) {
       
        int n = ratings.length;
        int[] candy = new int[n];

        candy[0] = 1;       
        for(int i=1;i<n;i++)         // Left to Right Traversal where we compare only left nbr index with current index i !!!
        {
            if(ratings[i] > ratings[i-1])
                candy[i] = candy[i-1] + 1;
            else
                candy[i] = 1;
        }

       int res = candy[n-1];

        for(int i=n-2;i>=0;i--)  // Right to Left Traversal where we compare only right nbr indexd with current index i !!!
        { 
             if(ratings[i] > ratings[i+1])
             candy[i] = Math.max(candy[i],candy[i+1] + 1);

             res += candy[i];
        }



       return res;

    }
}






/*-------------------------------------------------------------------------------------------------------*/





// // Approach 3 : Slope Approach (Peak+Down Approach) (Striver's Approach) [*** MOST OPTIMAL APPROACH]
// // Watch this : https://www.youtube.com/watch?v=IIqVFvKE6RY&list=PLgUwDviBIf0rF1w2Koyh78zafB0cz7tea
// // TC : O(n)
// // SC : O(1)

// class Solution {
//     public int candy(int[] ratings) {
       
//         int n = ratings.length;
       
       
//         int i = 1;
//         int sum = 1;   // till index 0

//         while(i<n)
//         {
//           while(i<n && ratings[i] == ratings[i-1])
//           {
//             sum += 1;
//             i++;
//             continue;
//           }

//           int peak = 1;

//           while(i<n && ratings[i] > ratings[i-1])
//           {
//             peak++;   // we incremented peak first since it is already included in sum for index 0
//             i++;
//             sum += peak;
//           }

//           int down = 1;

//           while(i<n && ratings[i] < ratings[i-1])
//           {
//              sum += down;  // we will add down first into the sum since downward slope starts with 1 !!!
//              down++;
//              i++;
//           }

//           if(down > peak)
//           sum = sum + (down - peak); // peak is already included in sum so remove the peak if down is > than peak !!!

//         }

//         return sum;

//     }
// }





/*-------------------------------------------------------------------------------------------------------*/





// // Approach 1 : Brute Force Greedy (Written by me !!!)
// // TC : O(n)
// // SC : O(n)

// class Solution {
//     public int candy(int[] ratings) {
       
//         int n = ratings.length;
//         int[] candy = new int[n];

//         if(n == 1)
//         return 1;
       
//        for(int i=0;i<n;i++)
//        {

//            if(i-1>=0)    // comparing left nbr index with current index i
//            {
//              if(ratings[i] > ratings[i-1])
//                 candy[i] = candy[i-1]+1;
//              else
//                 candy[i] = 1;
//            }

//            if(i+1<=n-1)  // comparing right nbr index with current index i
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






