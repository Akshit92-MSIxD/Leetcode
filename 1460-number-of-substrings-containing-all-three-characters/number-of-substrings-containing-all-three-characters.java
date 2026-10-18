class Solution {
    public int numberOfSubstrings(String s) {
        
            int n = s.length();

            int count_sub = 0;

            int l = 0;
            int r = 0;

            Map<Character,Integer> mp = new HashMap<>();

            while(r<n)
            {
                char ch = s.charAt(r);

                if(!mp.containsKey(ch))
                 mp.put(ch,1);
                 else
                 mp.put(ch,mp.get(ch)+1);


                while(mp.size() == 3)
                {
                    count_sub = count_sub + n - r;

                    char temp_ch = s.charAt(l);

                    mp.put(temp_ch,mp.get(temp_ch)-1);

                    if(mp.get(temp_ch) == 0)
                      mp.remove(temp_ch);

                    l++;
                }

                r++;
            }

            return count_sub;     
    }
}