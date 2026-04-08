// Approach : DP tabulation
class Pair{
 
 int maxInd = 0;
 long minOprs = 0;
 
  Pair(int minInd,long minOprs)
  {
    this.maxInd = maxInd;
    this.minOprs = minOprs;
  }

}
class Solution {

    public long minIncrease(int[] nums) {
         
         int n = nums.length;
        
         int maxSpecialindices = -1;

         long[] cost = new long[n];
         cost[0] = 0;
         cost[n-1] = 0;

         for(int i=1;i<n-1;i++)
         {   
            long large = Math.max(nums[i-1],nums[i+1]);
            cost[i] = Math.max(0,large - nums[i] + 1);
         }
         
         Pair[] dp = new Pair[n+1];

         for(int i=0;i<=n;i++)
         dp[i] = new Pair(0,0);


         for(int i = n-2;i>=1;i--)
         {
            Pair take = dp[i+2];
            take.maxInd += 1;
            take.minOprs += cost[i];

            Pair notTake = dp[i+1];

            if(take.maxInd > notTake.maxInd)
            {
                dp[i].maxInd = take.maxInd;
                dp[i].minOprs = take.minOprs;
            }
            else if(take.maxInd == notTake.maxInd)
            {
                if(take.minOprs < notTake.minOprs)
                {
                 dp[i].maxInd = take.maxInd;
                 dp[i].minOprs = take.minOprs;
                }
                else
                {
                 dp[i].maxInd = notTake.maxInd;
                 dp[i].minOprs = notTake.minOprs;
                }
            }
            else
            {
                dp[i].maxInd = notTake.maxInd;
                dp[i].minOprs = notTake.minOprs;
            }

            
         }

         return dp[1].minOprs;


    

    }
}



/*--------------------------------------------------------------------------------------------------*/


// Approach : Space optimized version of DP tabulation
 
// class Solution {
//     public long minIncrease(int[] nums) {
//         int n = nums.length;

//         // dp[i] depends only on dp[i+1] and dp[i+2]
//         // so just use 6 variables!
//         long ind1 = 0, oprs1 = 0;  // dp[i+1]
//         long ind2 = 0, oprs2 = 0;  // dp[i+2]
//         long indCurr, oprsCurr;

//         for(int i = n-2; i >= 1; i--) {
//             long cost = Math.max(0, Math.max(nums[i-1], nums[i+1]) - nums[i] + 1);

//             long takeInd  = ind2 + 1;
//             long takeOprs = oprs2 + cost;

//             if(takeInd > ind1 || (takeInd == ind1 && takeOprs < oprs1)) {
//                 indCurr  = takeInd;
//                 oprsCurr = takeOprs;
//             } else {
//                 indCurr  = ind1;
//                 oprsCurr = oprs1;
//             }

//             // shift windows
//             ind2 = ind1;   oprs2 = oprs1;
//             ind1 = indCurr; oprs1 = oprsCurr;
//         }

//         return oprs1;
//     }
// }