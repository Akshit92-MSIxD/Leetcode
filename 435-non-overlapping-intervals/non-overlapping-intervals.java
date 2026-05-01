// class Interval{

//     int start;
//     int end;

//     Interval(int start, int end)
//     {
//         this.start = start;
//         this.end = end;
//     }
// }

// class Solution {
//     public int eraseOverlapIntervals(int[][] intervals) {
         
//      int n = intervals.length;

//      Interval[] in = new Interval[n];

//      for(int i=0;i<n;i++)
//      {
//         int start = intervals[i][0];
//         int end = intervals[i][1];

//         in[i] = new Interval(start,end);
//      }


//      Arrays.sort(in,(a,b)->{
//         if(a.start != b.start) return a.start - b.start;
//         return a.end - b.end;
//      });

//      Stack<Interval> st = new Stack<>();
//      int minRemovals = 0;

//        st.push(in[0]);

//       for(int i=1;i<n;i++)
//       {
//           int s1 = st.peek().start;
//           int e1 = st.peek().end;
//           int s2 = in[i].start;
//           int e2 = in[i].end;

//           if(s2 >= s1 && s2 < e1)
//           {
//               minRemovals++;

//               if(e2 < e1)
//               {
//                 st.push(in[i]);
//               }
//           }
//           else
//           {
//              st.push(in[i]);
//           }
//       }

//       return minRemovals;
           
//     }
// }


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
    public int eraseOverlapIntervals(int[][] intervals) {
         
     int n = intervals.length;

     Interval[] in = new Interval[n];

     for(int i=0;i<n;i++)
     {
        int start = intervals[i][0];
        int end = intervals[i][1];

        in[i] = new Interval(start,end);
     }


     Arrays.sort(in,(a,b)->{
        if(a.start != b.start) return a.start - b.start;
        return a.end - b.end;
     });

     int minRemovals = 0;

      Interval comp = in[0]; 

      for(int i=1;i<n;i++)
      {
          int s1 = comp.start;
          int e1 = comp.end;
          int s2 = in[i].start;
          int e2 = in[i].end;

          if(s2 >= s1 && s2 < e1)
          {
              minRemovals++;

              if(e2 < e1)
              comp = in[i]; 
          }
          else
          {
            comp = in[i];
          }
      }

      return minRemovals;
           
    }
}