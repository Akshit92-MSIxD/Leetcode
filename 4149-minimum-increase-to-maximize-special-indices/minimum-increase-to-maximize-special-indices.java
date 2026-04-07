// class Solution {
//     public long minIncrease(int[] nums) {
         
//          int n = nums.length;

//           int oprs = 0;

//           for(int i=1;i<n-1;i+=2)
//           {
//              int large = Math.max(nums[i+1],nums[i-1]);

//              if(nums[i] <= large)
//              oprs = oprs + (large-nums[i]+1);
//           }  

//           return oprs;
//     }
// }



// class Pair{
 
//  int maxInd = 0;
//  long minOprs = 0;
 
//   Pair(int minInd,long minOprs)
//   {
//     this.maxInd = maxInd;
//     this.minOprs = minOprs;
//   }

//   Pair(Pair p)
//   {
//     this.maxInd = p.maxInd;
//     this.minOprs = p.minOprs;
//   }
// }
// class Solution {

//     public long minIncrease(int[] nums) {
         
//          int n = nums.length;
        
//          int maxSpecialindices = -1;

//          long[] cost = new long[n];
//          cost[0] = 0;
//          cost[n-1] = 0;

//          for(int i=1;i<n-1;i++)
//          {   
//             long large = Math.max(nums[i-1],nums[i+1]);
//              if(large >= nums[i])
//              cost[i] = large - nums[i] + 1;
//              else
//              cost[i] = 0;
//          }
         
//          Pair[] dp = new Pair[n+1];

//          for(int i=0;i<=n;i++)
//          dp[i] = new Pair(0,0);


//          for(int i = n-2;i>=1;i--)
//          {
//             Pair take = new Pair(dp[i+2]);
//             take.maxInd += 1;
//             take.minOprs += cost[i];

//             Pair notTake = new Pair(dp[i+1]);

//             if(take.maxInd > notTake.maxInd)
//             {
//                 dp[i].maxInd = take.maxInd;
//                 dp[i].minOprs = take.minOprs;
//             }
//             else if(take.maxInd == notTake.maxInd)
//             {
//                 if(take.minOprs < notTake.minOprs)
//                 {
//                  dp[i].maxInd = take.maxInd;
//                  dp[i].minOprs = take.minOprs;
//                 }
//                 else
//                 {
//                  dp[i].maxInd = notTake.maxInd;
//                  dp[i].minOprs = notTake.minOprs;
//                 }
//             }
//             else
//             {
//                 dp[i].maxInd = notTake.maxInd;
//                 dp[i].minOprs = notTake.minOprs;
//             }

            
//          }

//          return dp[1].minOprs;


    

//     }
// }


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