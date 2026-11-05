// class Solution {
//     public int numSubarraysWithSum(int[] nums, int goal) {
             
//              int n = nums.length;

//              Map<Integer,Integer> prefixFreq = new HashMap<>();

//              prefixFreq.put(0,1);

//              int prefixSum = 0;

//              int ans = 0;

//              for(int num : nums)
//              {
//                 prefixSum += num;

//                 if(prefixFreq.containsKey(prefixSum - goal))
//                 ans += prefixFreq.get(prefixSum - goal);


//                 if(prefixFreq.containsKey(prefixSum))
//                 prefixFreq.put(prefixSum,prefixFreq.get(prefixSum)+1);
//                 else
//                 prefixFreq.put(prefixSum,1);
            
//              }

//              return ans;

//     }
// }




/*----------------------------------------------------------------------------------------------------------*/




class Solution {
    
    int getCountOfSubarraysSumLessThanEqualTo(int[] nums, int goal)
    {
          int count = 0;

          int l=0;
          int r=0;
          int n=nums.length;

          int winSum = 0;

          while(r<n)
          {
               winSum += nums[r];

               while(winSum > goal)
               {
                 winSum -= nums[l];
                 l++;
               }

               count = count + (r-l+1);
               r++;
          }

          return count;


    }

    public int numSubarraysWithSum(int[] nums, int goal) {
             
             int n = nums.length;
            
            if(goal == 0)
            return  getCountOfSubarraysSumLessThanEqualTo(nums,goal);       

          return  getCountOfSubarraysSumLessThanEqualTo(nums,goal) -  getCountOfSubarraysSumLessThanEqualTo(nums,goal-1);

    }
}