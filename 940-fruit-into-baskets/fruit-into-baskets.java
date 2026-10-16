class Solution {
    public int totalFruit(int[] fruits) {

          int n = fruits.length;
      
         Map<Integer,Integer> mp = new HashMap<>();

         int l=0;
         int r=0;

         int maxLen = 0;

         while(r<n)
         {
            if(!mp.containsKey(fruits[r]))
                mp.put(fruits[r],1);
            else
               mp.put(fruits[r],mp.get(fruits[r])+1);

             while(mp.size() > 2)
             {
                if(mp.get(fruits[l]) == 1)
                 mp.remove(fruits[l]);
                else
                 mp.put(fruits[l],mp.get(fruits[l])-1);

                 l++;
             }

             maxLen = Math.max(maxLen,r-l+1);
             r++;
         } 

         return maxLen;        

          
    }
}