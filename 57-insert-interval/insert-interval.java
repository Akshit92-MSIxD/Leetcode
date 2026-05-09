// Note: I have written two approaches for this problem below !!!




// Approach 2 : Greedy Optimal (By CodeWithMik Sir)
// Hint : This approach says build the answer along with the traversal of intervals[][] !!!
// TC : O(n)
// SC : O(n) [Auxiliary array List<int[]> ]

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
         
        int n = intervals.length;

        List<int[]> res = new ArrayList<>();
       
        int i = 0;

        boolean inserted = false;

    while(i<n)
    {
        if(newInterval[0] > intervals[i][1])         // newInterval lies to the left of current interval
        {
            res.add(intervals[i]);
            i++;
        }
        else if(newInterval[1] < intervals[i][0])   // newInterval lies to the right of current interval
        {
                res.add(newInterval);
                inserted = true;
                break;
        }
        else                                      // newInterval overlaps with current interval
        {
          newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
          newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
          i++;
        }
    }

    while(i<n)                   // add the remaining untouched intervals
        res.add(intervals[i++]);

        if(!inserted)                  // if this--> true --> that means newInterval will lie at the extreme end !!!
            res.add(newInterval);


         int[][] ans = new int[res.size()][2];

         for(int idx=0;idx<res.size();idx++)
         {
            ans[idx][0] = res.get(idx)[0];
            ans[idx][1] = res.get(idx)[1];
         }

         return ans;


        

    }
}





/*---------------------------------------------------------------------------------------------------------------*/




// Approach 1 : Greedy Brute Force (List<int[]> + Stack<int[]> Approach Written By Me !!!)
// Hint : Use the stack to perform the merging process !!!
// TC : O(3n)
// SC : O(2n) [Auxiliary List<int[]> + Stack<int[]>]

// class Solution {
//     public int[][] insert(int[][] intervals, int[] newInterval) {
         
//         int n = intervals.length;

//         if(n == 0)
//         {
//             int[][] ans = new int[1][2];
//             ans[0][0] = newInterval[0];
//             ans[0][1] = newInterval[1];
//             return ans;
//         }

//         List<int[]> finalIntervals = new ArrayList<>();

//         for(int i=0;i<n;i++)
//             finalIntervals.add(intervals[i]);
        
//         for(int i=0;i<n;i++)
//         {
//              if(newInterval[0] < finalIntervals.get(i)[0])
//              {
//                 finalIntervals.add(i,newInterval);
//                 break;
//              }
//         }

//         if(finalIntervals.size() != n + 1)
//         finalIntervals.add(newInterval);
         


//         Stack<int[]> st = new Stack<>();

//         st.push(finalIntervals.get(0));
//         int prevStart = st.peek()[0];
//         int prevEnd =  st.peek()[1];

//         for(int i=1;i<finalIntervals.size();i++)
//         {
//             int[] currInterval = finalIntervals.get(i);

//             int currStart = currInterval[0];
//             int currEnd = currInterval[1];

//             if(currStart <= prevEnd)
//             {
//                 st.pop();

//                 int[] mergedInterval = new int[]{Math.min(prevStart,currStart),Math.max(prevEnd,currEnd)};
//                 st.push(mergedInterval);
//                 prevStart = mergedInterval[0];
//                 prevEnd = mergedInterval[1];
//             }
//             else
//             {
//                 st.push(currInterval);
//                 prevStart = currStart;
//                 prevEnd = currEnd;
//             }
//         }

//         int[][] ans = new int[st.size()][2];

//         for(int i=st.size()-1;i>=0;i--)
//         {
//              ans[i][0] = st.peek()[0];
//              ans[i][1] = st.peek()[1];
//              st.pop();
//         }


//      return ans;

//     }
// }



