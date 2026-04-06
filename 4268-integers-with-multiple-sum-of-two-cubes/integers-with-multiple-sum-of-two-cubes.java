class Solution {
    public List<Integer> findGoodIntegers(int n) {

        List<Integer> ans = new ArrayList<>();
         
        Map<Integer,Integer> mp = new TreeMap<>();

        for(int a = 1; a*a*a < n;a++)
        {
            int sum = 0;
            for(int b=a;b*b*b < n;b++)
            {
               sum = sum + a*a*a + b*b*b;
               if(mp.containsKey(sum) == false && sum <= n)
                mp.put(sum,1);
               else if(sum<=n)
                mp.put(sum,mp.get(sum)+1);
                else
                break;

                sum = 0;
            }
        }


        for(int num : mp.keySet())
        {
            if(num <= n && mp.get(num) > 1)
            ans.add(num);
        }

        return ans;

        
    }
}