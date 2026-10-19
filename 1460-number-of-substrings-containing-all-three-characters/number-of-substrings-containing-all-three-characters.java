// class Solution {
//     public int numberOfSubstrings(String s) {
        
//             int n = s.length();

//             int count_sub = 0;

//             int l = 0;
//             int r = 0;

//             Map<Character,Integer> mp = new HashMap<>();

//             while(r<n)
//             {
//                 char ch = s.charAt(r);

//                 if(!mp.containsKey(ch))
//                  mp.put(ch,1);
//                  else
//                  mp.put(ch,mp.get(ch)+1);


//                 while(mp.size() == 3)
//                 {
//                     count_sub = count_sub + n - r;

//                     char temp_ch = s.charAt(l);

//                     mp.put(temp_ch,mp.get(temp_ch)-1);

//                     if(mp.get(temp_ch) == 0)
//                       mp.remove(temp_ch);

//                     l++;
//                 }

//                 r++;
//             }

//             return count_sub;     
//     }
// }



class Solution {
    public int numberOfSubstrings(String s) {
        
            int n = s.length();

            int count_sub = 0;

            int l = 0;
            int r = 0;

            int[] freq = new int[3];
            Arrays.fill(freq,0);

            while(r<n)
            {
                char ch = s.charAt(r);

                freq[ch-'a']++;

                while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0)
                {
                    count_sub = count_sub + n - r;

                    char temp_ch = s.charAt(l);

                     freq[temp_ch-'a']--;

                    l++;
                }

                r++;
            }

            return count_sub;     
    }
}




