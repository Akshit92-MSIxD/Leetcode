
// I have written three approaches for this problem below . Please read all of them !!!

// Approach 2 and 3 are slightly faster than Approach 1 in practical, but not asymptotically (theoretical tc is the same as Approach 1).




/*
Approach 1: Sliding Window using Two Pointers + HashMap.
Expand the window by moving r and maintain the frequency
of each character. While the window contains all three
distinct characters ('a', 'b', and 'c'), count the valid
substrings starting at l and ending at r or any index
after r. Then shrink the window from the left.

TC: O(n) [r traverses the string exactly n times, while l
advances at most n times in total across all iterations]

SC: O(1) [HashMap stores at most three distinct characters,
since the string contains only 'a', 'b', and 'c']
*/

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
                    count_sub = count_sub + n - r;  // this will count all possible valid substring starting from particular l till n !!!

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





/*--------------------------------------------------------------------------------------------------------------------*/




/*
Approach 2: Sliding Window using Two Pointers + Frequency Array.
Same logic as Approach 1, but use an array of size 3 instead
of a HashMap to store the frequencies of 'a', 'b', and 'c'.
While all three characters are present in the window, count
the valid substrings and shrink the window from the left.

TC: O(n) [r traverses the string exactly n times, while l
advances at most n times in total across all iterations]

SC: O(1) [fixed-size frequency array of size 3]

Note: Approach 2 may have lower constant-factor overhead
because array accesses avoid HashMap operations, but both
approaches have the same asymptotic time and space complexity.
*/

// class Solution {
//     public int numberOfSubstrings(String s) {
        
//             int n = s.length();

//             int count_sub = 0;

//             int l = 0;
//             int r = 0;

//             int[] freq = new int[3];
//             Arrays.fill(freq,0);

//             while(r<n)
//             {
//                 char ch = s.charAt(r);

//                 freq[ch-'a']++;

//                 while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0)
//                 {
//                     count_sub = count_sub + n - r; // this will count all possible valid substring starting from particular l till n !!!

//                     char temp_ch = s.charAt(l);

//                      freq[temp_ch-'a']--;

//                     l++;
//                 }

//                 r++;
//             }

//             return count_sub;     
//     }
// }




/*--------------------------------------------------------------------------------------------------------------------*/


/*
Approach 3: Using Last-Seen Indices. (Striver Solution)
Maintain an array to store the most recent index of each
character ('a', 'b', and 'c'), initialized to -1 to indicate
that the character has not been encountered yet.

Traverse the string from left to right. At each index i,
update the last-seen index of the current character. If all
three characters have been encountered, count the valid
substrings ending at i using:
1 + minimum of the three last-seen indices.

TC: O(n) [traverse the string exactly n times; each iteration
performs a constant number of operations]

SC: O(1) [fixed-size array of size 3]
*/

// class Solution {
//     public int numberOfSubstrings(String s) {

//         int n = s.length();

//         int[] lastSeenIndex = new int[3];
//         Arrays.fill(lastSeenIndex, -1);

//         int i = 0;
//         int ans = 0;

//         while (i < n) {
//             char ch = s.charAt(i);
//             lastSeenIndex[ch - 'a'] = i;
            
//             // count the total  no. of valid substrings ending at index i

//             if (lastSeenIndex[0] != -1 &&
//                 lastSeenIndex[1] != -1 &&
//                 lastSeenIndex[2] != -1) {

//                 ans += 1 + Math.min(
//                     lastSeenIndex[0],
//                     Math.min(lastSeenIndex[1], lastSeenIndex[2])
//                 );
//             }

//             i++;
//         }

//         return ans;
//     }
// }


