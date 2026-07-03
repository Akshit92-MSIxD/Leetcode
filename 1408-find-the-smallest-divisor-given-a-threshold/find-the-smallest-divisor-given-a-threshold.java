class Solution {

    int getRequiredSum(int div, int[] nums,int threshold)
    {
        int sum = 0;

        for(int val : nums)
        {
            if(val%div==0)
            sum += val/div;
            else
            sum += (val/div) + 1;

            if(sum > threshold)
            break;
        }

        return sum;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        
            int max = Integer.MIN_VALUE;

            for(int val : nums)
                max = Math.max(max,val);

            int low = 1;
            int high = max;

            while(low <= high)
            {
                 int mid = low + (high-low)/2; // mid is the divisor

                 int sum = getRequiredSum(mid,nums,threshold);

                 if(sum > threshold)
                    low = mid + 1;
                 else
                    high = mid - 1;
            }

            return low;
         
    }
}