// Please watch strivers soolution video for this problem when you revisit this problem !!! 


class Pair{

    String word;
    int lvl;

    Pair(String word,int lvl)
    {
        this.word = word;
        this.lvl = lvl;
    }
}

class Solution {

    void  link_patternToWords(HashMap<String,List<String>> words, String word){
         
           char[] arr = word.toCharArray();

          for(int i=0;i<arr.length;i++)
          {
             char org_char = arr[i];
             arr[i] = '*';

             String pattern = new String(arr);

             if(!words.containsKey(pattern))
             {
                 words.put(pattern,new ArrayList<>());
                 words.get(pattern).add(word);
             }
             else
             {
                words.get(pattern).add(word);
             }

             arr[i] = org_char;
          } 
         
    }


    boolean bfs(HashMap<String,List<String>> words, String beginWord, String endWord, HashMap<String,List<String>> parent)
    {
       boolean is_endWord_connected = false;

       HashSet<String> vis = new HashSet<>();
       HashMap<String,Integer> level = new HashMap<>();  

       Queue<Pair> q = new ArrayDeque<>();
       q.add(new Pair(beginWord,0));
       vis.add(beginWord);
       level.put(beginWord,0);

       while(!q.isEmpty())
       {
          Pair front = q.poll();
          String curr_word = front.word;
          int curr_lvl = front.lvl;

          if(curr_word.equals(endWord))
          {
            is_endWord_connected = true;
             break;
          }

          char[] arr = curr_word.toCharArray();

           for(int i=0;i<arr.length;i++)
           {
               char org_char = arr[i];
               arr[i] = '*';

               String pattern = new String(arr);

               for(String nbr_word : words.get(pattern))
               {
                   if(!vis.contains(nbr_word))
                   {
                      q.add(new Pair(nbr_word,curr_lvl+1));
                      vis.add(nbr_word);
                      parent.put(nbr_word,new ArrayList<>());
                      parent.get(nbr_word).add(curr_word);
                      level.put(nbr_word,curr_lvl + 1);
                   }
                   else if(level.get(nbr_word) == level.get(curr_word) + 1)
                   {
                      parent.get(nbr_word).add(curr_word);
                   }
               }
               arr[i] = org_char;
           }
       }

       return is_endWord_connected;

    }

    void dfs(String curr_word, String beginWord, List<String> pathSoFar, List<List<String>> ansPaths, HashMap<String,List<String>> parent)
    {
         pathSoFar.add(curr_word);

         if(curr_word.equals(beginWord))
         {
            List<String> reversePathSoFar = new ArrayList<>();

             for(int i=pathSoFar.size()-1;i>=0;i--)
                reversePathSoFar.add(pathSoFar.get(i));

            ansPaths.add(reversePathSoFar);
            pathSoFar.remove(pathSoFar.size()-1);
            return;
         }

         // explore parent neighbours only from the parent map

         List<String> neighbours = parent.get(curr_word);

         for(String parent_node : neighbours)
             dfs(parent_node,beginWord,pathSoFar,ansPaths,parent);

         pathSoFar.remove(pathSoFar.size() - 1);
         return;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)   {
         
         if(!wordList.contains(endWord))
         return new ArrayList<>();

        int n = wordList.size();
        
        HashMap<String,List<String>> words = new HashMap<>();
      
        if(!wordList.contains(beginWord))
        link_patternToWords(words,beginWord);
        
        for(int i=0;i<n;i++)
        link_patternToWords(words,wordList.get(i));

        HashMap<String,List<String>> parent = new HashMap<>();
    
        if(bfs(words,beginWord,endWord,parent) == false)
        return new ArrayList<>();


         List<String> pathSoFar = new ArrayList<>();

         List<List<String>> ansPaths = new ArrayList<>();

         dfs(endWord,beginWord,pathSoFar,ansPaths,parent);

         return ansPaths;
    }
}

