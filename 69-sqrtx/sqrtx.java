
// Approach : Using Binary Search + Consider low and high as numbers and not the indices !!!
// TC : O(logn)
// SC : O(1)

class Solution {
    public int mySqrt(int x) {
         
          if(x == 0 || x == 1) return x;
          

          int low = 1;
          int high = x;
          int sqrt = 0;

          while(low <= high)
          {
             int mid = low + (high - low)/2;

             if(mid == x/mid)    // means mid*mid == x (if this is true then mid is the square root of x)
             return mid;

             if(mid > x/mid)    // means mid*mid > x (if this is true that means mid is not the square root of x since its product with itself is greater than x so go left to find its valid square root)
             {
                high = mid - 1;
             }
             else     // means mid*mid < x (if this is true that means mid may or may not be the square root of x so include it in your answer and move right to find if there exists a valid square root in the right)
             {
                sqrt = mid;   // include the mid in your answer since it may or may not be the answer !!!
                low = mid + 1;
             }
          }

          return sqrt;
    }
}