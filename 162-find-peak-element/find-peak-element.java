

// Approach : Using Binary Search + Graph Slope Peak Concept + Trimming Oth and n-1 th index !!!
// Hint : Please draw a 2D graph on paper to visualize this concept in a better way !!!
// TC : O(logn)
// SC : O(1)

class Solution {
    public int findPeakElement(int[] nums) {
        

        int n = nums.length;

        if(nums.length == 1)
        return 0;

        if(nums[0] > nums[1])
        return 0;

        if(nums[n-1] > nums[n-2])
        return n-1;

        int low = 1;
        int high = n-2;


        while(low<=high)
        {
            int mid = low + (high - low)/2;

            if(nums[mid] > nums[mid-1] && nums[mid] > nums[mid+1]) // check if mid is peak or not
            return mid;

            if(nums[mid] > nums[mid+1]) // if this is true thats means mid is on downward slope so peak is on the left
            high = mid - 1;
            else                // else mid is on the upward slope  so peek is on the right
            low = mid + 1;
        }

        return -1;
    }
}