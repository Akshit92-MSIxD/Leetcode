
class Pair{

 String word;
 int lvl;

  Pair(String word, int lvl)
  {
    this.word = word;
    this.lvl = lvl;
  }

}

class Solution {

    void fill_patForWords_and_wordsForPat(String word, HashMap<String,List<String>> patterns, HashMap<String,List<String>> words)
    {
         int length = word.length();

         for(int i=0;i<length;i++)
         {
             String left = "";
             String middle = "";
             String right = "";

             if(i==0)
             {
                left = "*";
                right = word.substring(1);
             }
             else if(i == length - 1)
             {
                left = word.substring(0,length-1);
                right = "*";
             }
             else
             {
               left = word.substring(0,i);
               middle = "*";
               right = word.substring(i+1);
             }

             String pattern = left + middle + right;

             if(!patterns.containsKey(word))
             {
                patterns.put(word,new ArrayList<>());
                patterns.get(word).add(pattern);
             }
             else
             {
                patterns.get(word).add(pattern);
             }

             if(!words.containsKey(pattern))
             {
                words.put(pattern,new ArrayList<>());
                words.get(pattern).add(word);
             }
             else
             {
                words.get(pattern).add(word);
             }

         }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
           
      HashMap<String,List<String>> patterns = new HashMap<>();
      HashMap<String,List<String>> words = new HashMap<>();

      fill_patForWords_and_wordsForPat(beginWord,patterns,words);

      for(int i=0;i<wordList.size();i++)
       fill_patForWords_and_wordsForPat(wordList.get(i),patterns,words);

       int minWords = Integer.MAX_VALUE;

       HashSet<String> vis = new HashSet<>();

       Queue<Pair> q = new ArrayDeque<>();
       q.add(new Pair(beginWord,0));

       vis.add(beginWord);

       while(!q.isEmpty())
       {
          Pair front = q.poll();
          String curr_word = front.word;
          int curr_lvl = front.lvl;

          if(curr_word.equals(endWord))
          {
            minWords = curr_lvl + 1;
            break;
          }

          vis.add(curr_word);

          // explore the correct neighbour nodes differ by single letter

          for(String pattern : patterns.get(curr_word))
          {
                for(String nbr_word : words.get(pattern))
                {
                    if(!vis.contains(nbr_word))
                    {
                        q.add(new Pair(nbr_word,curr_lvl+1));
                        vis.add(nbr_word);
                    }
                }
          }

       }


       return (minWords == Integer.MAX_VALUE) ? 0 : minWords;

    }
}