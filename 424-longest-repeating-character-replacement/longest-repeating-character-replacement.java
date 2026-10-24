// class Solution {
//     public int characterReplacement(String s, int k) {

//             int n = s.length();
         
//             int[] freq = new int[26];

//             Arrays.fill(freq,0);

//             int maxFreq = 0;
//             int maxFreqChar = -1;

//             int r=0,l=0;

//             int maxLen = 0;

//             while(r<n)
//             {
//                 char ch = s.charAt(r);
//                 freq[ch-'A']++;

//                 if(freq[ch-'A'] > maxFreq)
//                 {
//                     maxFreq = freq[ch-'A'];
//                     maxFreqChar = ch-'A';
//                 }

//                 int currWinLen = r-l+1;

//                 if(currWinLen - maxFreq <= k)
//                 {
//                   maxLen = Math.max(maxLen,currWinLen);
//                 }
//                 else
//                 {
//                     char temp_ch = s.charAt(l);
//                     freq[temp_ch-'A']--;

//                     if(temp_ch - 'A' == maxFreqChar)
//                     maxFreq--;

//                     l++;
//                 }

//                 r++;
//             }

//             return maxLen;
//     }
// }



class Solution {
    public int characterReplacement(String s, int k) {

            int n = s.length();
         
            int[] freq = new int[26];

            Arrays.fill(freq,0);

            int maxFreq = 0;
            int maxFreqChar = -1;

            int r=0,l=0;

            int maxLen = 0;

            while(r<n)
            {
                char ch = s.charAt(r);
                freq[ch-'A']++;

                   maxFreq = Math.max(maxFreq,freq[ch-'A']);

                int currWinLen = r-l+1;

                if(currWinLen - maxFreq <= k)
                {
                  maxLen = Math.max(maxLen,currWinLen);
                }
                else
                {
                    char temp_ch = s.charAt(l);
                    freq[temp_ch-'A']--;
                    l++;
                }

                r++;
            }

            return maxLen;
    }
}