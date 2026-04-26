
// class Pair{

//     String word;
//     int lvl;

//     Pair(String word,int lvl)
//     {
//         this.word = word;
//         this.lvl = lvl;
//     }
// }

// class Solution {

//     void  link_patternToWords(HashMap<String,List<String>> words, String word){
         
//            char[] arr = word.toCharArray();

//           for(int i=0;i<arr.length;i++)
//           {
//              char org_char = arr[i];
//              arr[i] = '*';

//              String pattern = new String(arr);

//              if(!words.containsKey(pattern))
//              {
//                  words.put(pattern,new ArrayList<>());
//                  words.get(pattern).add(word);
//              }
//              else
//              {
//                 words.get(pattern).add(word);
//              }

//              arr[i] = org_char;
//           } 
         
//     }


//     void bfs(HashMap<String,List<String>> words, String beginWord, String endWord, HashMap<String,Integer> minDis)
//     {
//        HashSet<String> vis = new HashSet<>();

//        Queue<Pair> q = new ArrayDeque<>();
//        q.add(new Pair(beginWord,0));
//        vis.add(beginWord);

//        while(!q.isEmpty())
//        {
//           Pair front = q.poll();
//           String curr_word = front.word;
//           int curr_lvl = front.lvl;

//           minDis.put(curr_word,curr_lvl);

//           if(curr_word.equals(endWord))
//           {
//           return ;
//           }

//           char[] arr = curr_word.toCharArray();

//            for(int i=0;i<arr.length;i++)
//            {
//                char org_char = arr[i];
//                arr[i] = '*';

//                String pattern = new String(arr);

//                for(String nbr_word : words.get(pattern))
//                {
//                    if(!vis.contains(nbr_word))
//                    {
//                       q.add(new Pair(nbr_word,curr_lvl+1));
//                       vis.add(nbr_word);
//                    }
//                }
//                arr[i] = org_char;
//            }
//        }

//     }

//     void dfs(HashMap<String,List<String>> words, String curr_word, String endWord, List<String> pathSoFar, List<List<String>> ansPaths, HashMap<String,Integer> minDis)
//     {
//          pathSoFar.add(curr_word);

//          if(curr_word.equals(endWord))
//          {
//             List<String> validPath = new ArrayList<>(pathSoFar);
//             ansPaths.add(validPath);
//             pathSoFar.remove(pathSoFar.size()-1);
//             return;
//          }

//          // explore the neighbours

//          char[] arr = curr_word.toCharArray();

//          for(int i=0;i<arr.length;i++)
//          {
//              char org_char = arr[i];
//              arr[i] = '*';

//              String pattern = new String(arr);

//              for(String nbr_word : words.get(pattern))
//              {
//                  if(minDis.get(nbr_word) == minDis.get(curr_word) + 1)
//                  dfs(words,nbr_word,endWord,pathSoFar,ansPaths,minDis);
//              }
//                words.get(pattern).clear();
//              arr[i] = org_char;
//          }

//          pathSoFar.remove(pathSoFar.size() - 1);
//          return;
//     }

//     public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)   {
         
//          if(!wordList.contains(endWord))
//          return new ArrayList<>();

//         int n = wordList.size();
        
//         HashMap<String,List<String>> words = new HashMap<>();
      
//         if(!wordList.contains(beginWord))
//         link_patternToWords(words,beginWord);
        
//         for(int i=0;i<n;i++)
//         link_patternToWords(words,wordList.get(i));

//         HashMap<String,Integer> minDis = new HashMap<>(); 
        
//         bfs(words,beginWord,endWord,minDis);


//         if(!minDis.containsKey(endWord))
//          return new ArrayList<>();

//          List<String> pathSoFar = new ArrayList<>();

//          List<List<String>> ansPaths = new ArrayList<>();

//          dfs(words,beginWord,endWord,pathSoFar,ansPaths,minDis);

//          return ansPaths;
//     }
// }


class Solution {

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) return result;

        // Pattern map
        HashMap<String, List<String>> map = new HashMap<>();

        for (String word : dict) {
            for (int i = 0; i < word.length(); i++) {
                String pattern = word.substring(0, i) + "*" + word.substring(i + 1);
                map.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        // BFS variables
        HashMap<String, List<String>> parents = new HashMap<>();
        HashMap<String, Integer> level = new HashMap<>();

        Queue<String> q = new ArrayDeque<>();
        q.add(beginWord);
        level.put(beginWord, 0);

        int wordLen = beginWord.length();
        boolean found = false;

        while (!q.isEmpty() && !found) {
            int size = q.size();
            Set<String> visitedThisLevel = new HashSet<>();

            for (int s = 0; s < size; s++) {
                String curr = q.poll();
                int currLevel = level.get(curr);

                char[] arr = curr.toCharArray();

                for (int i = 0; i < wordLen; i++) {
                    char org = arr[i];
                    arr[i] = '*';

                    String pattern = new String(arr);

                    List<String> neighbors = map.getOrDefault(pattern, new ArrayList<>());

                    for (String next : neighbors) {

                        if (!level.containsKey(next)) {
                            level.put(next, currLevel + 1);
                            q.add(next);
                            visitedThisLevel.add(next);
                        }

                        if (level.get(next) == currLevel + 1) {
                            parents.computeIfAbsent(next, k -> new ArrayList<>()).add(curr);
                        }

                        if (next.equals(endWord)) {
                            found = true;
                        }
                    }

                    arr[i] = org;
                }
            }
        }

        if (!level.containsKey(endWord)) return result;

        // Backtracking
        List<String> path = new ArrayList<>();
        backtrack(endWord, beginWord, parents, path, result);

        return result;
    }

    void backtrack(String word, String beginWord,
                   HashMap<String, List<String>> parents,
                   List<String> path,
                   List<List<String>> result) {

        path.add(word);

        if (word.equals(beginWord)) {
            List<String> temp = new ArrayList<>(path);
            Collections.reverse(temp);
            result.add(temp);
        } else {
            for (String p : parents.getOrDefault(word, new ArrayList<>())) {
                backtrack(p, beginWord, parents, path, result);
            }
        }

        path.remove(path.size() - 1);
    }
}