class Interval{

    int start;
    int end;

    Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
    }
}

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
         
        int n = intervals.length;

        if(n == 0)
        {
            int[][] ans = new int[1][2];
            ans[0][0] = newInterval[0];
            ans[0][1] = newInterval[1];
            return ans;
        }

        List<Interval> finalIntervals = new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            int start = intervals[i][0];
            int end = intervals[i][1];

            finalIntervals.add(new Interval(start,end));
        }
        
        for(int i=0;i<n;i++)
        {
             if(newInterval[0] < finalIntervals.get(i).start)
             {
                finalIntervals.add(i,new Interval(newInterval[0],newInterval[1]));
                break;
             }
        }

        if(finalIntervals.size() != n + 1)
        finalIntervals.add(new Interval(newInterval[0],newInterval[1]));
         


        Stack<Interval> st = new Stack<>();

        st.push(finalIntervals.get(0));
        int prevStart = finalIntervals.get(0).start;
        int prevEnd = finalIntervals.get(0).end;

        for(int i=1;i<finalIntervals.size();i++)
        {
            Interval currInterval = finalIntervals.get(i);

            int currStart = currInterval.start;
            int currEnd = currInterval.end;

            if(currStart <= prevEnd)
            {
                st.pop();

                Interval mergedInterval = new Interval(Math.min(prevStart,currStart),Math.max(prevEnd,currEnd));
                st.push(mergedInterval);
                prevStart = mergedInterval.start;
                prevEnd = mergedInterval.end;
            }
            else
            {
                st.push(currInterval);
                prevStart = currStart;
                prevEnd = currEnd;
            }
        }

        int[][] ans = new int[st.size()][2];

        for(int i=st.size()-1;i>=0;i--)
        {
             ans[i][0] = st.peek().start;
             ans[i][1] = st.peek().end;
             st.pop();
        }


     return ans;

    }
}