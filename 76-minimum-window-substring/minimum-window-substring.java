
/*
Approach: Using Sliding Window and a HashMap to find the minimum window
substring containing all characters of t, including their required frequencies.

Initialize the HashMap with the frequency of each character in t.
Expand the window by moving r and decrease the frequency of the current
character if it exists in the map.
Increment count only when the character is still required (frequency > 0).
When count == t.length(), all required character occurrences are present
in the current window.
Update minWinLength and si if a smaller valid window is found.
Shrink the window by moving l forward and restore the frequency of the
character leaving the window.
If its frequency becomes positive, decrement count because that character
is now missing from the window.
Continue until all possible minimum windows have been considered.

TC: O(n + m) — Each pointer traverses the string at most once, and the
HashMap operations take O(1) average time.
SC: O(u) — The HashMap stores only the distinct characters present in t.

*/

class Solution {
    public String minWindow(String s, String t) {

              
          Map<Character,Integer> mp = new HashMap<>();

          for(char ch : t.toCharArray())
          {
              if(!mp.containsKey(ch))
               mp.put(ch,1);
              else
               mp.put(ch,mp.get(ch)+1);
          }

          int l=0;
          int r=0;
          int count = 0;

          int n = s.length();
          
          int minWinLength = Integer.MAX_VALUE;
          int si = -1;

          while(r<n)
          {
              char ch = s.charAt(r);

              if(mp.containsKey(ch))
              { 
                 if(mp.get(ch) > 0)
                 count++;
                 
                 mp.put(ch,mp.get(ch)-1);
              }


              while(count == t.length() && l<n)
              {
                 if(r-l+1 < minWinLength)
                 {
                    minWinLength = r-l+1;
                    si = l;
                 }
                 
                 if(mp.containsKey(s.charAt(l)))
                 {
                   mp.put(s.charAt(l),mp.get(s.charAt(l))+1);

                   if(mp.get(s.charAt(l)) > 0)
                   count--;
                 }

                 l++;
              }

              r++;
           }

           if(si == -1)
           return new String("");

           
           return s.substring(si,si+minWinLength);
}
}