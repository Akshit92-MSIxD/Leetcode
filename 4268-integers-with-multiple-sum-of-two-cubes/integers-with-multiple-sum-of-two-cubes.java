// class Solution {
//     public List<Integer> findGoodIntegers(int n) {

//         List<Integer> ans = new ArrayList<>();
         
//         Map<Integer,Integer> mp = new HashMap<>();

//         for(int a = 1; a*a*a < n;a++)
//         {
//             int sum = 0;
//             for(int b=a;b*b*b < n;b++)
//             {
//                sum = sum + a*a*a + b*b*b;
//                if(mp.containsKey(sum) == false && sum <= n)
//                 mp.put(sum,1);
//                else if(sum<=n)
//                 mp.put(sum,mp.get(sum)+1);
//                 else
//                 break;

//                 sum = 0;
//             }
//         }


//         for(int num : mp.keySet())
//         {
//             if(mp.get(num) > 1)
//             ans.add(num);
//         }
        
//         Collections.sort(ans);
//         return ans;

        
//     }
// }

// class Solution {
//     public List<Integer> findGoodIntegers(int n) {

//         List<Integer> ans = new ArrayList<>();
         
//         Map<Integer,Integer> mp = new HashMap<>();

//         for(int a = 1; a*a*a < n;a++)
//         {
//             int sum = 0;
//             for(int b=a;b*b*b < n;b++)
//             {
//                sum = sum + a*a*a + b*b*b;
//                if(mp.containsKey(sum) == false && sum <= n)
//                 mp.put(sum,1);
//                else if(sum<=n)
//                 mp.put(sum,mp.get(sum)+1);
//                 else
//                 break;

//                 sum = 0;
//             }
//         }


//         for(int num : mp.keySet())
//         {
//             if(mp.get(num) > 1)
//             ans.add(num);
//         }
        
//         Collections.sort(ans);
//         return ans;

        
//     }
// }

class Solution {
    public List<Integer> findGoodIntegers(int n) {

        Set<Integer> seen = new HashSet<>();
        Set<Integer> good = new HashSet<>();

        for (int a = 1; 2 * (long)a*a*a <= n; a++) {  // if 2*cubeA > n, no valid b exists
            int cubeA = a*a*a;  // cache a³

            for (int b = a; ; b++) {
                int cubeB = b*b*b;  // cache b³
                int sum = cubeA + cubeB;

                if (sum > n) break;

                if (!seen.add(sum))  // add() returns false if already present
                    good.add(sum);   // seen twice = good integer
            }
        }

        List<Integer> ans = new ArrayList<>(good);
        Collections.sort(ans);
        return ans;
    }
}