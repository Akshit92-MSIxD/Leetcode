class Solution{

  public int mirrorFrequency(String s)
  {
     Map<Character,Integer> mp = new HashMap<>();

     for(char ch : s.toCharArray())
     {
        if(mp.containsKey(ch) == false)
        mp.put(ch,1);
        else
        mp.put(ch,mp.get(ch)+1);
     }
     
     int res = 0;

     for(char ch : mp.keySet())
     {
        if(mp.get(ch) == 0) continue;
        
        int subRes = 0;
        char mirrorCh = ' ';

        if(ch >='a' && ch <= 'z')
           mirrorCh = (char)('z' - (ch - 'a'));
        else
           mirrorCh = (char)('9' - (ch - '0'));

           if(mp.containsKey(mirrorCh))
           {
              subRes = Math.abs(mp.get(ch) - mp.get(mirrorCh));
              mp.put(mirrorCh,0);
           }
           else
           {
              subRes = mp.get(ch);
           }

        res += subRes;
     }

     return res;
  }





}
