// Note : I have written two approaches for this problem below. Both of them are important !!!

// *** Approach 2 is slightly faster and optimal than Approach 1 because in Approach 1 shrinking of window takes time whereas in Approach 2 shrinking is done directly by moving the left pointer one position ahead of duplicate's previous occurrence !!!






// Approach 1 : Sliding Window using Set + Shrinking Window until all duplicate elements are removed. (My Approach :))
// TC : O(n+n) [expansion + shrinkage of window]
// SC : O(min(n,charset size) [HashSet stores distinct characters present in the current window]

class Solution {
    public int lengthOfLongestSubstring(String s) {

          int n = s.length();

          if(n == 0)
          return 0;
        
          Set<Character> set = new HashSet<>();

          int l = 0;
          int r = 0;

          int maxLen = 1;

          while(r < n)
          {
             char new_char = s.charAt(r);
             if(!set.contains(new_char))
              {
                  set.add(new_char);
                  maxLen = Math.max(maxLen,r - l + 1);
              }
             else
             {
                  while(set.contains(new_char))
                  {
                      set.remove(s.charAt(l));
                      l++;
                  }

                  set.add(new_char);
             }

              r++;
          }

          return maxLen;
    }
}



/*--------------------------------------------------------------------------------------------------------------------*/





// // Approach 2 : Sliding Window using HashMap !! -> Store each character's most recent index. If a duplicate appears within the current window, move the left pointer directly to one position after its previous occurrence.

// // TC: O(n) average [each character is processed once, with O(1) average-time HashMap operations]

// // SC: O(min(n, charset size)) [HashMap stores the most recent index of each distinct character encountered]

// class Solution {
//     public int lengthOfLongestSubstring(String s) {

//         int n = s.length();

//         Map<Character, Integer> mp = new HashMap<>();

//         int l = 0;
//         int r = 0;

//         int maxLen = 0;

//         while (r < n) {
//             char ch = s.charAt(r);

//             if (mp.containsKey(ch) && mp.get(ch) >= l)
//                 l = mp.get(ch) + 1;

//             maxLen = Math.max(maxLen, r - l + 1);

//             mp.put(ch, r);
//             r++;
//         }

//         return maxLen;
//     }
// }
