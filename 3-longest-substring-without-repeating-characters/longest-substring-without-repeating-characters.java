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
             if(!set.contains(s.charAt(r)))
              {
                  set.add(s.charAt(r));
                  maxLen = Math.max(maxLen,r - l + 1);
              }
             else
             {
                  while(set.contains(s.charAt(r)))
                  {
                      set.remove(s.charAt(l));
                      l++;
                  }

                  set.add(s.charAt(r));
             }

              r++;
          }

          return maxLen;
    }
}