class Solution {
    public boolean checkValidString(String s) {
         
     int n = s.length();

     int min = 0;
     int max = 0;

     for(int i=0;i<n;i++)
     {
        char ch = s.charAt(i);
        
         if(ch == '(')
         { 
             min++;
             max++;
         }
         else if(ch == ')')
         {
           min--;
           max--;
         }
         else
         {
            min--;
            max++;
         }

         if(min < 0)
         min = 0;

         if(max<0)
         return false;
     }

     if(min == 0)
     return true;

     return false;
         
}
}