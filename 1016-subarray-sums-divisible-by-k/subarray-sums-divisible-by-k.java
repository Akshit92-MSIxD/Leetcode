// class Solution {
//     public int subarraysDivByK(int[] nums, int k) {
        
           
//             int n = nums.length;

//             int ansCount = 0;

//             for(int i=0;i<n;i++)
//             {
//                  int sum = 0;
//                 for(int j=i;j<n;j++)
//                 { 
//                    sum += nums[j];

//                    if(sum % k == 0)
//                    ansCount++;
//                 }
//             }

//             return ansCount;
//     } 
// }



/*------------------------------------------------------------------------------------------------------------*/




class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        
           
            int n = nums.length;

            Map<Integer,Integer> mp = new HashMap<>();

            mp.put(0,1);

            int ansCount = 0;

            int prefixSum = 0;

            for(int num : nums)
            {
                prefixSum += num;

                int rem = ((prefixSum % k) + k) % k;

                if(mp.containsKey(rem))
                {
                   ansCount += mp.get(rem);
                   mp.put(rem,mp.get(rem)+1);
                }
                else
                {
                   mp.put(rem,1);
                }
            }

            return ansCount;

        
    } 
}