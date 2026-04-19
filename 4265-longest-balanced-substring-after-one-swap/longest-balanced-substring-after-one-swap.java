
// I have written two approaches for this problem below . But please remember Approach 2 and 3 only !!!



// // Approach 1 : Brute Force One Traversal (written by me) (Prefix Sum + HashMap)


class Solution {
    public int longestBalanced(String s) {
        
        int n = s.length();
        HashMap<Character,Integer> freq = new HashMap<>();

        for(char ch : s.toCharArray())
        {
            if(!freq.containsKey(ch))
             freq.put(ch,1);
             else
             freq.put(ch,freq.get(ch)+1);
        }


        int[] prefix = new int[n];

        HashMap<Integer,List<Integer>> occ_index = new HashMap<>();
        
        occ_index.put(0,new ArrayList<>());
        occ_index.get(0).add(-1);

        int maxLen = 0;

        for(int i=0;i<n;i++)
                {   
                    char ch = s.charAt(i);

                if(i==0)
                {
                    prefix[0] = (ch == '0') ? -1 : 1;
                    occ_index.put(prefix[0],new ArrayList<>());
                    occ_index.get(prefix[0]).add(0);
                    continue;
                }

                prefix[i] = prefix[i-1] + ((ch == '0') ? -1 : 1);

                if(prefix[i] == 0)
                {
                    maxLen = Math.max(maxLen,i+1);
                    occ_index.get(0).add(i);
                    continue;
                }

                if(occ_index.containsKey(prefix[i]-2))
                {
                    for(int pi : occ_index.get(prefix[i]-2))
                    {
                        int len = i - pi;
                        
                        if(len > maxLen)
                        {
                            int zeroes = (len/2) - 1;

                            if(freq.containsKey('0') && (freq.get('0') - zeroes) > 0)
                            {
                            maxLen = Math.max(maxLen,len);
                            break;
                            }

                        }
                        else
                        {
                            break;
                        }
                    }
                }

                if(occ_index.containsKey(prefix[i]+2))
                {
                    for(int pi : occ_index.get(prefix[i]+2))
                    {
                        int len = i - pi;
                        
                        if(len > maxLen)
                        {
                            int ones = (len/2) - 1;

                            if(freq.containsKey('1') && (freq.get('1') - ones) > 0)
                            {
                            maxLen = Math.max(maxLen,len);
                            break;
                            }

                        }
                        else
                        {
                            break;
                        }
                    }
                }


              if(occ_index.containsKey(prefix[i]))
              {
                 int foi = occ_index.get(prefix[i]).get(0);  // foi -> first occurence index of prefix[i]
                 maxLen = Math.max(maxLen,i-foi);
                 occ_index.get(prefix[i]).add(i);
              }
              else
              {
                occ_index.put(prefix[i],new ArrayList<>());
                occ_index.get(prefix[i]).add(i);
              }

           }

          return maxLen;
           
        }
    }



/*--------------------------------------------------------------------------------------------------------*/


// Approach 2 : Two Traversals but Space Optimized than Approach 1 (PrefixSum + HashMap)
// Watch this : https://www.youtube.com/watch?v=Bfom9LV-s-c

// class Solution {

//     int getLongestBalancedSubstringLength(String s, int zeroes, int ones)
//     {
//            int n = s.length();
//            int[] prefix = new int[n];
//            HashMap<Integer,Integer> first_occ_index = new HashMap<>();
//            first_occ_index.put(0,-1);

//            int maxLen = 0;

//            for(int i=0;i<n;i++)
//            {
//                char ch = s.charAt(i);
//                if(i==0)
//                {
//                   prefix[i] = ((ch == '1') ? 1 : -1);

//                   if(prefix[i] == 1)
//                   ones--;
//                   else
//                   zeroes--;

//                   first_occ_index.put(prefix[i],i);
//                   continue;
//                }

//                prefix[i] = prefix[i-1];

//                if(ch == '1')
//                {
//                   prefix[i] += 1;
//                   ones--;
//                }
//                else
//                {
//                   prefix[i] += -1;
//                   zeroes--;
//                }

//                if(prefix[i] == 0)
//                {
//                   maxLen = Math.max(maxLen,i+1);
//                   continue;
//                }

//                if(first_occ_index.containsKey(prefix[i]-2))
//                {
//                     int foi = first_occ_index.get(prefix[i]-2);
//                     int len = i - foi;

//                     if(zeroes > 0)
//                     maxLen = Math.max(maxLen,len);
//                }

//                if(first_occ_index.containsKey(prefix[i]+2))
//                {
//                    int foi = first_occ_index.get(prefix[i]+2);
//                    int len = i - foi;

//                    if(ones > 0)
//                    maxLen = Math.max(maxLen,len);
//                }


//                if(first_occ_index.containsKey(prefix[i]))
//                {
//                    int foi = first_occ_index.get(prefix[i]);
//                    int len = i - foi;
//                    maxLen = Math.max(maxLen,len);
//                }
//                else
//                {
//                   first_occ_index.put(prefix[i],i);
//                }

//            }

//            return maxLen;
//     }

//     String reverse(String s)
//     {
//         StringBuilder rev_s = new StringBuilder();

//         for(int i=s.length()-1;i>=0;i--)
//         {
//            char ch = s.charAt(i);
//            rev_s.append(ch);
//         }

//         return rev_s.toString();
//     }


//     public int longestBalanced(String s) {
         
//             int n = s.length();

//            int ones = 0;
//            int zeroes = 0;
           
//            for(int i=0;i<n;i++)
//            {
//               char ch = s.charAt(i);
                
//                 if(ch == '1')
//                 ones++;
//                 else
//                 zeroes++;
//            }
 
//           int maxLen = getLongestBalancedSubstringLength(s,zeroes,ones);

//           String rev_s = reverse(s);

//           maxLen = Math.max(maxLen,getLongestBalancedSubstringLength(rev_s,zeroes,ones));

//           return maxLen;

//         }
//     }



/*-------------------------------------------------------------------------------------------------*/


// // Approach 3 : Optimized Prefix[] to single prefixSum variable (PrefixSum + HashMap)
// // Watch this : https://www.youtube.com/watch?v=Bfom9LV-s-c

// class Solution {

//     int getLongestBalancedSubstringLength(String s, int zeroes, int ones)
//     {
//            int n = s.length();
//            int prefixSum = 0;
//            HashMap<Integer,Integer> first_occ_index = new HashMap<>();
//            first_occ_index.put(0,-1);

//            int maxLen = 0;

//            for(int i=0;i<n;i++)
//            {
//                char ch = s.charAt(i);

//                if(ch == '1')
//                {
//                   prefixSum += 1;
//                   ones--;
//                }
//                else
//                {
//                   prefixSum += -1;
//                   zeroes--;
//                }

//                if(prefixSum == 0)
//                {
//                   maxLen = Math.max(maxLen,i+1);
//                   continue;
//                }

//                if(first_occ_index.containsKey(prefixSum-2))
//                {
//                     int foi = first_occ_index.get(prefixSum-2);
//                     int len = i - foi;

//                     if(zeroes > 0)
//                     maxLen = Math.max(maxLen,len);
//                }

//                if(first_occ_index.containsKey(prefixSum+2))
//                {
//                    int foi = first_occ_index.get(prefixSum+2);
//                    int len = i - foi;

//                    if(ones > 0)
//                    maxLen = Math.max(maxLen,len);
//                }


//                if(first_occ_index.containsKey(prefixSum))
//                {
//                    int foi = first_occ_index.get(prefixSum);
//                    int len = i - foi;
//                    maxLen = Math.max(maxLen,len);
//                }
//                else
//                {
//                   first_occ_index.put(prefixSum,i);
//                }

//            }

//            return maxLen;
//     }

//     String reverse(String s)
//     {
//         StringBuilder rev_s = new StringBuilder();

//         for(int i=s.length()-1;i>=0;i--)
//         {
//            char ch = s.charAt(i);
//            rev_s.append(ch);
//         }

//         return rev_s.toString();
//     }


//     public int longestBalanced(String s) {
         
//             int n = s.length();

//            int ones = 0;
//            int zeroes = 0;
           
//            for(int i=0;i<n;i++)
//            {
//               char ch = s.charAt(i);
                
//                 if(ch == '1')
//                 ones++;
//                 else
//                 zeroes++;
//            }
 
//           int maxLen = getLongestBalancedSubstringLength(s,zeroes,ones);

//           String rev_s = reverse(s);

//           maxLen = Math.max(maxLen,getLongestBalancedSubstringLength(rev_s,zeroes,ones));

//           return maxLen;

//         }
//     }




/*--------------------------------------------------------------------------------------------------*/




// // Approach 4 :  Convert HashMap to Array (PrefixSum + Array)
// // Not intuitive :( but fastest

// class Solution {

//     int getLongestBalancedSubstringLength(String s, int zeroes, int ones)
//     {
//         int n = s.length();
//         int prefixSum = 0;

//         int[] first_occ_index = new int[2*n + 1];

//         // initialize
//         for(int i = 0; i < 2*n + 1; i++)
//             first_occ_index[i] = Integer.MIN_VALUE;

//         // correct base case (prefixSum = 0 → index = n)
//         first_occ_index[n] = -1;

//         int maxLen = 0;

//         for(int i = 0; i < n; i++)
//         {
//             char ch = s.charAt(i);

//             if(ch == '1')
//             {
//                 prefixSum += 1;
//                 ones--;
//             }
//             else
//             {
//                 prefixSum -= 1;
//                 zeroes--;
//             }

//             int idx = prefixSum + n;

//             // case 1: perfectly balanced
//             if(prefixSum == 0)
//                 maxLen = Math.max(maxLen, i + 1);

//             // case 2: prefixSum - 2
//             int idxMinus2 = prefixSum - 2 + n;
//             if(idxMinus2 >= 0 && idxMinus2 < first_occ_index.length &&
//                first_occ_index[idxMinus2] != Integer.MIN_VALUE)
//             {
//                 int foi = first_occ_index[idxMinus2];
//                 int len = i - foi;

//                 if(zeroes > 0)
//                     maxLen = Math.max(maxLen, len);
//             }

//             // case 3: prefixSum + 2
//             int idxPlus2 = prefixSum + 2 + n;
//             if(idxPlus2 >= 0 && idxPlus2 < first_occ_index.length &&
//                first_occ_index[idxPlus2] != Integer.MIN_VALUE)
//             {
//                 int foi = first_occ_index[idxPlus2];
//                 int len = i - foi;

//                 if(ones > 0)
//                     maxLen = Math.max(maxLen, len);
//             }

//             // case 4: same prefixSum
//             if(first_occ_index[idx] != Integer.MIN_VALUE)
//             {
//                 int foi = first_occ_index[idx];
//                 int len = i - foi;
//                 maxLen = Math.max(maxLen, len);
//             }
//             else
//             {
//                 first_occ_index[idx] = i;
//             }
//         }

//         return maxLen;
//     }

//     public int longestBalanced(String s) {

//         int n = s.length();

//         int ones = 0, zeroes = 0;

//         for(char ch : s.toCharArray())
//         {
//             if(ch == '1') ones++;
//             else zeroes++;
//         }

//         int maxLen = getLongestBalancedSubstringLength(s, zeroes, ones);

//         // reverse using StringBuilder (O(n))
//         String rev = new StringBuilder(s).reverse().toString();

//         maxLen = Math.max(maxLen,
//                 getLongestBalancedSubstringLength(rev, zeroes, ones));

//         return maxLen;
//     }
// }