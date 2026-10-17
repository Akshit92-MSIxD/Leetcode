
/*
Approach: Sliding Window using Two Pointers.
Expand the window by moving r. Count the number of zeros
in the current window. If the zero count exceeds k, shrink
the window from the left until it becomes valid again.
Update the maximum window length at each step.

TC: O(n+n) ~ O(n) [both pointers(l and r) traverse the array at most n times]

SC: O(1) [only a constant number of variables are used]
*/

// class Solution {
//     public int longestOnes(int[] nums, int k) {

//         int n = nums.length;

//         int l = 0;
//         int r = 0;
//         int currWinZeroes = 0;

//         int maxLen = 0;

//         while (r < n) {
//             if (nums[r] == 0)
//                 currWinZeroes++;

//             while (currWinZeroes > k) {
//                 if (nums[l] == 0)
//                     currWinZeroes--;

//                 l++;
//             }

//             maxLen = Math.max(maxLen, r - l + 1);
//             r++;
//         }

//         return maxLen;
//     }
// }



class Solution {
    public int longestOnes(int[] nums, int k) {

        int n = nums.length;

        int l = 0;
        int r = 0;
        int currWinZeroes = 0;

        int maxLen = 0;

        while (r < n) {
            if (nums[r] == 0)
                currWinZeroes++;

            if(currWinZeroes > k) {
                if (nums[l] == 0)
                    currWinZeroes--;

                l++;
            }

            if(currWinZeroes <= k)
            maxLen = Math.max(maxLen, r - l + 1);

            r++;
        }

        return maxLen;
    }
}
