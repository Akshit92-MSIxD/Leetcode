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