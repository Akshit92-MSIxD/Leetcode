
// I have written two approaches for this problem below . Please read both of them !!!

// Approach 2 is slightly better than Approach 1 in practice, but not asymptotically (theoretical tc is the same as Approach 1).




/*
Approach 1 : Sliding Window using Two Pointers.
Expand the window by moving r. Count the number of zeros
in the current window. If the zero count exceeds k, shrink
the window from the left until it becomes valid again.
Update the maximum window length at each step.

TC: O(n+n) ~ O(n) [both pointers(l and r) traverse the array at most n times]

SC: O(1) [only a constant number of variables are used]
*/

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

            while (currWinZeroes > k) {
                if (nums[l] == 0)
                    currWinZeroes--;

                l++;
            }

            maxLen = Math.max(maxLen, r - l + 1);
            r++;
        }

        return maxLen;
    }
}


/*-------------------------------------------------------------------------------------------------------------------*/



/*
Approach 2 : Sliding Window using Two Pointers.
Optimize the standard sliding-window approach by eliminating
the internal while loop and shrinking the window by at most
one position per iteration.

Expand the window by moving r and count the number of zeros.
If the zero count exceeds k, move l forward by one position.
Update maxLen whenever the window contains at most k zeros.

TC: O(n) [r traverses the array n times, and l moves forward
at most n times. For example, with nums = [1,1,1,1,1,1,1,1,1,1,1,0,0,0]
and k = 2, r traverses all n elements while l advances only a few times.]

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

//             if (currWinZeroes > k) {
//                 if (nums[l] == 0)
//                     currWinZeroes--;

//                 l++;
//             }

//             if (currWinZeroes <= k)
//                 maxLen = Math.max(maxLen, r - l + 1);

//             r++;
//         }

//         return maxLen;
//     }
// }
