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



// class Solution {
//     public int lengthOfLongestSubstring(String s) {

//           int n = s.length();

//           if(n == 0)
//           return 0;
        
//           Map<Character,Integer> mp = new HashMap<>();

//           int l = 0;
//           int r = 0;

//           int maxLen = 1;

//           while(r < n)
//           {
//              if(!mp.containsKey(s.charAt(r)) || mp.get(s.charAt(r)) == 0)
//               {
//                   mp.put(s.charAt(r),1);
//                   maxLen = Math.max(maxLen,r - l + 1);
//               }
//              else
//              {
//                   while(mp.get(s.charAt(r)) != 0)
//                   {
//                      mp.put(s.charAt(l),0);
//                       l++;
//                   }

//                   mp.put(s.charAt(r),1);
//              }

//               r++;
//           }

//           return maxLen;
//     }
// }