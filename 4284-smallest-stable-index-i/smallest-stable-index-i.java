class Solution {
    public int firstStableIndex(int[] nums, int k) {

         int n = nums.length;
        
         int maxVar = Integer.MIN_VALUE;
        int minVar = Integer.MAX_VALUE;

        int[] min = new int[n];
        int[] max = new int[n];


        for(int i=0;i<n;i++)
            {
            min[i] = Integer.MAX_VALUE;
            max[i] = Integer.MIN_VALUE;
            }

        for(int i=0;i<n;i++)
            {
                if(nums[i] > maxVar)
                {
                    maxVar = nums[i];
                }

                max[i] = maxVar;
            }

        for(int i=n-1;i>=0;i--)
            {
                if(nums[i] < minVar)
                {
                    minVar = nums[i];
                }

                min[i] = minVar;
                    
            }


        for(int i=0;i<n;i++)
            {
                int score = max[i] - min[i];

             
                if(score<=k)
                {
                           return i;
                }  
            }

        return -1;
    }
}