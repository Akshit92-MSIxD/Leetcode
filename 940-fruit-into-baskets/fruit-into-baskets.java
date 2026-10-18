// I have written two approaches for this problem below . Please read both of them !!!

// Approach 2 is slightly better than Approach 1 in practice, but not asymptotically (theoretical tc is the same as Approach 1).



/*
Approach 1: Sliding Window using Two Pointers + HashMap.
Expand the window by moving r and maintain the frequency
of each fruit type in the current window. If the window
contains more than two distinct fruit types, repeatedly
shrink it from the left until at most two distinct types
remain. Update maxLen for each valid window.

TC: O(n+n) ~ O(n)  [both pointers traverse the array at most n times;
each fruit is added to and removed from the window at most once]

SC: O(1) [HashMap stores at most three distinct fruit types
temporarily before the window is shrunk]
*/

class Solution {
    public int totalFruit(int[] fruits) {

          int n = fruits.length;
      
         Map<Integer,Integer> mp = new HashMap<>();

         int l=0;
         int r=0;

         int maxLen = 0;

         while(r<n)
         {
            if(!mp.containsKey(fruits[r]))
                mp.put(fruits[r],1);
            else
               mp.put(fruits[r],mp.get(fruits[r])+1);

             while(mp.size() > 2)
             {
                if(mp.get(fruits[l]) == 1)
                 mp.remove(fruits[l]);
                else
                 mp.put(fruits[l],mp.get(fruits[l])-1);

                 l++;
             }

             maxLen = Math.max(maxLen,r-l+1);
             r++;
         } 

         return maxLen;        

          
    }
}



/*--------------------------------------------------------------------------------------------------------------------*/




/*
Approach 2: Optimized Sliding Window using Two Pointers + HashMap.
Expand the window by moving r and maintain the frequency
of each fruit type. If the window contains more than two
distinct types, shrink it from the left by exactly one
position. Update maxLen whenever the window contains at
most two distinct fruit types.

TC: O(n) [r traverses the array n times, and l moves forward
at most n times]

SC: O(1) [HashMap stores at most three distinct fruit types
temporarily before the window is adjusted]

*** Note: Approach 2 eliminates the internal while loop and may
have lower overhead in practice, but both approaches have
the same asymptotic time and space complexity.
*/


// class Solution {
//     public int totalFruit(int[] fruits) {

//           int n = fruits.length;
      
//          Map<Integer,Integer> mp = new HashMap<>();

//          int l=0;
//          int r=0;

//          int maxLen = 0;

//          while(r<n)
//          {
//             if(!mp.containsKey(fruits[r]))
//                 mp.put(fruits[r],1);
//             else
//                mp.put(fruits[r],mp.get(fruits[r])+1);

//              if(mp.size() > 2)
//              {
//                 if(mp.get(fruits[l]) == 1)
//                  mp.remove(fruits[l]);
//                 else
//                  mp.put(fruits[l],mp.get(fruits[l])-1);

//                  l++;
//              }
             
//              if(mp.size() <= 2)
//              maxLen = Math.max(maxLen,r-l+1);

//              r++;
//          } 

//          return maxLen;        

          
//     }
// }