// I have written two approaches for this problem below !!!


// Approach 1 : Using DP(Tabulation)
class Solution {
    public int minOperations(int[] nums, int k) {
         
         int n = nums.length;

        int cost[] = new int[n];

        for(int i=0;i<n;i++)
        {   
            if(i==0)
             cost[i] = Math.max(0,Math.max(nums[n-1],nums[1]) - nums[0] + 1);
            else
             cost[i] = Math.max(0,Math.max(nums[i-1],nums[(i+1)%n]) - nums[i] + 1);
        }
        
        // considers indices (0 - n-2) from nums (index 0 is always included -> index n-1 is not included permanently here !!)
        int[][] dp1 = new int[n+2][k+1]; 

        for(int i=n-1;i<=n+1;i++)
        {
            dp1[i][0] = 0;

            for(int j=1;j<=k;j++)
            dp1[i][j] = Integer.MAX_VALUE;
        }
        
    // consider indices (1 - n-1) from nums (index 0 is not included permanently -> index n-2 can either be included or not)
        int[][] dp2 = new int[n+2][k+1]; 

        for(int i=n;i<=n+1;i++)
        {
             dp2[i][0] = 0;
             for(int j=1;j<=k;j++)
             dp2[i][j] = Integer.MAX_VALUE;
        }


        for(int i=n-2;i>=0;i--)
        {
            for(int j=1;j<=k;j++)
            {
                int take = dp1[i+2][j-1];
                
                if(take != Integer.MAX_VALUE)
                 take = take + cost[i];

                int notTake = dp1[i+1][j];

                if(i == 0)
                dp1[i][j] = take;
                else
                dp1[i][j] = Math.min(take,notTake);
            }
        }


        for(int i=n-1;i>=1;i--)
        {
            for(int j=1;j<=k;j++)
            {
                int take = dp2[i+2][j-1];
                
                if(take != Integer.MAX_VALUE)
                 take = take + cost[i];

                int notTake = dp2[i+1][j];

                dp2[i][j] = Math.min(take,notTake);
            }
        }


        int ans = Math.min(dp1[0][k],dp2[1][k]);

        if(ans == Integer.MAX_VALUE)
        return -1;

        return ans;

    }
}



/*-------------------------------------------------------------------------------------------------------------------*/



// Approach 2 : Space optization of Approach 1
// class Solution {
//     public int minOperations(int[] nums, int k) {
         
//          int n = nums.length;

//         int cost[] = new int[n];

//         for(int i=0;i<n;i++)
//         {   
//             if(i==0)
//              cost[i] = Math.max(0,Math.max(nums[n-1],nums[1]) - nums[0] + 1);
//             else
//              cost[i] = Math.max(0,Math.max(nums[i-1],nums[(i+1)%n]) - nums[i] + 1);
//         }
        
//         // considers indices (0 - n-2) from nums (index 0 is always included -> index n-1 is not included permanently here !!)
//         int[] curr1 = new int[k+1];   // row -> dp1[i]
//         int[] next1 = new int[k+1];   // row -> dp1[i+1]
//         int[] nextNext1 = new int[k+1]; // row -> dp1[i+2]
        
//     // consider indices (1 - n-1) from nums (index 0 is not included permanently -> index n-2 can either be included or not)
        
//         int[] curr2 = new int[k+1];  // row -> dp2[i]
//         int[] next2 = new int[k+1];   // row -> dp2[i+1]
//         int[] nextNext2 = new int[k+1]; // row -> dp2[i+2]


//         for(int j=0;j<=k;j++)
//         {
//            if(j == 0)
//             next1[j] = next2[j] = nextNext1[j] = nextNext2[j] = 0;
//            else
//            next1[j] = next2[j] = nextNext1[j] = nextNext2[j] = Integer.MAX_VALUE;
//         }


//         for(int i=n-2;i>=0;i--)
//         {
//             for(int j=1;j<=k;j++)
//             {
//                 int take = nextNext1[j-1];
                
//                 if(take != Integer.MAX_VALUE)
//                  take = take + cost[i];

//                 int notTake = next1[j];

//                 if(i == 0)
//                 curr1[j] = take;
//                 else
//                 curr1[j] = Math.min(take,notTake);
//             }

//             int[] temp = curr1;
//             curr1 = nextNext1;
//             nextNext1 = next1;
//             next1 = temp;

//             curr1[0] = 0;
//         }


//         for(int i=n-1;i>=1;i--)
//         {
//             for(int j=1;j<=k;j++)
//             {
//                 int take = nextNext2[j-1];
                
//                 if(take != Integer.MAX_VALUE)
//                  take = take + cost[i];

//                 int notTake = next2[j];

//                 curr2[j] = Math.min(take,notTake);
//             }

//             int[] temp = curr2;
//             curr2 = nextNext2;
//             nextNext2 = next2;
//             next2 = temp;

//             curr2[0] = 0;
//         }


//         int ans = Math.min(next1[k],next2[k]);

//         if(ans == Integer.MAX_VALUE)
//         return -1;

//         return ans;

//     }
// }