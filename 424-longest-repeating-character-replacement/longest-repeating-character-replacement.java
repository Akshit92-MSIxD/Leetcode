

/*

Approach : Sliding Window using a Frequency Array and Historical Maximum Frequency.
Expand the window by moving r and update the frequency of the current character.
Maintain maxFreq as the highest frequency observed so far; never decrement it.
If (window length - maxFreq) > k , shrink the window by moving l forward
and decrease the frequency of the character leaving the window.
Otherwise, update maxLen with the current window length.

TC: O(n) — r traverses the string once, and l advances at most n times.
SC: O(1) — the frequency array has a fixed size of 26.

*/


class Solution {
    public int characterReplacement(String s, int k) {

            int n = s.length();
         
            int[] freq = new int[26];

            int maxFreq = 0;

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