class Solution {
    public int findKthPositive(int[] arr, int k) {
           
            HashSet<Integer> set = new HashSet<>();

            for(int val : arr)
            set.add(val);

            int missing = 0;

            int num = 1;

            while(true)
            {
                if(!set.contains(num))
                {
                    missing++;

                    if(missing == k)
                    break;
                }

                num++;
            }

            return num;    
    }
}