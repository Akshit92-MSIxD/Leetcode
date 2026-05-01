// I have written three approaches for this problem below. Please read all the three apparoches !!!
// *** Approach 2 and 3 are optimal approches but approach 3 is most readable and optimal !!!



/*
=========================================================
🧠 APPROACH 1 : GREEDY (Sort by Start Time) – BRUTE STYLE
=========================================================

💡 IDEA:
- Sort intervals by start time
- Traverse and detect overlaps
- If overlap happens → remove one interval

---------------------------------------------------------
🔥 KEY OBSERVATION (VERY IMPORTANT):
If two intervals overlap:
→ remove the one with larger end time

WHY?
→ Smaller end finishes earlier → leaves more space for future intervals
-> Since it has less end time so it has less possibility to overlap much with future intervals  --> thus less removals !!!
---------------------------------------------------------
⚠️ PROBLEM WITH THIS APPROACH:
- We sort by start → but greedy decision depends on end
- So we must manually decide which interval to keep
→ extra logic → more complexity → harder to reason

---------------------------------------------------------
⏱️ TIME COMPLEXITY:
Sorting → O(n log n)
Loop → O(n)
Total → O(n log n)

---------------------------------------------------------
💾 SPACE COMPLEXITY:
- Interval[] array → O(n)
- Stack → O(n)
- Sorting recursion → O(log n)

👉 Total → O(n)
=========================================================
*/


class Interval {
    int start;
    int end;

    Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class Solution {

    public int eraseOverlapIntervals(int[][] intervals) {

        int n = intervals.length;

        /*
        ---------------------------------------------------------
        🧠 Step 1: Convert input to objects (optional but easier)
        ---------------------------------------------------------
        */
        Interval[] in = new Interval[n];

        for (int i = 0; i < n; i++) {
            in[i] = new Interval(intervals[i][0], intervals[i][1]);
        }

        /*
        ---------------------------------------------------------
        🧠 Step 2: Sort by start time
        ---------------------------------------------------------
        If start is same → sort by end
        */
        Arrays.sort(in, (a, b) -> {
            if (a.start != b.start) return a.start - b.start;
            return a.end - b.end;
        });

        /*
        ---------------------------------------------------------
        🧠 Step 3: Use stack to track last chosen interval
        ---------------------------------------------------------
        (NOTE: Stack is actually unnecessary, but used here
         to simulate "previous interval")
        */
        Stack<Interval> st = new Stack<>();

        int minRemovals = 0;

        // First interval is always taken
        st.push(in[0]);

        /*
        ---------------------------------------------------------
        🧠 Step 4: Process intervals
        ---------------------------------------------------------
        */
        for (int i = 1; i < n; i++) {

            int s1 = st.peek().start;
            int e1 = st.peek().end;

            int s2 = in[i].start;
            int e2 = in[i].end;

            /*
            ---------------------------------------------------------
            🔥 CASE 1: Overlap
            ---------------------------------------------------------
            Condition:
            current.start < previous.end
            */
            if (s2 < e1) {

                minRemovals++; // one interval must be removed

                /*
                ---------------------------------------------------------
                🔥 Decide which interval to keep
                ---------------------------------------------------------
                Keep the one with smaller end
                (better for future intervals)
                */
                if (e2 < e1) {
                    // current interval is better → replace previous
                    st.pop();
                    st.push(in[i]);
                }
                // else: keep previous (do nothing)
            }

            /*
            ---------------------------------------------------------
            ✅ CASE 2: No overlap
            ---------------------------------------------------------
            */
            else {
                st.push(in[i]);
            }
        }

        return minRemovals;
    }
}



/*-------------------------------------------------------------------------------------------------------*/


// /*
// =========================================================
// 🧠 APPROACH 2 : GREEDY (Sort by Start Time, Cleaner Code)
// =========================================================

// 💡 IDEA:
// - Same logic as Approach 1
// - Improvements:
//    ✔️ Removed Stack
//    ✔️ Removed custom Interval class
//    ✔️ Directly used input 2D array

// 👉 Flow:
// Sort by start → detect overlap → decide which interval to keep

// ---------------------------------------------------------
// 🔥 KEY OBSERVATION (VERY IMPORTANT):
// If two intervals overlap:
// → Remove the one with larger end time

// WHY?
// → Smaller end finishes earlier
// → Leaves more space for future intervals
// -> Since it has less end time so it has less possibility to overlap much with future intervals  --> thus less removals !!!

// ---------------------------------------------------------
// ⚠️ LIMITATION:
// - We sort by start time
// - But greedy decision depends on end time

// 👉 So we MUST manually decide:
//    “Which interval to keep?”

// → Extra logic needed
// → Slightly harder than optimal approach

// ---------------------------------------------------------
// ⏱️ TIME COMPLEXITY:
// Sorting → O(n log n)
// Traversal → O(n)

// 👉 Total → O(n log n)

// ---------------------------------------------------------
// 💾 SPACE COMPLEXITY:
// O(log n) → due to recursion stack of sorting
// (No extra DS used)
// =========================================================
// */


// class Solution {
//     public int eraseOverlapIntervals(int[][] intervals) {

//         int n = intervals.length;

//         /*
//         ---------------------------------------------------------
//         🧠 Step 1: Sort intervals by start time
//         ---------------------------------------------------------
//         If two intervals have same start,
//         order doesn’t matter much for correctness
//         */
//         Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

//         /*
//         ---------------------------------------------------------
//         🧠 Step 2: Track last chosen interval
//         ---------------------------------------------------------
//         comp = currently selected interval
//         */
//         int[] comp = intervals[0];

//         int minRemovals = 0;

//         /*
//         ---------------------------------------------------------
//         🧠 Step 3: Traverse all intervals
//         ---------------------------------------------------------
//         */
//         for (int i = 1; i < n; i++) {

//             int s1 = comp[0];
//             int e1 = comp[1];

//             int s2 = intervals[i][0];
//             int e2 = intervals[i][1];

//             /*
//             ---------------------------------------------------------
//             🔥 CASE 1: Overlap
//             ---------------------------------------------------------
//             condition → current.start < previous.end
//             */
//             if (s2 < e1) {

//                 minRemovals++; // one interval must be removed

//                 /*
//                 ---------------------------------------------------------
//                 🔥 Decide which interval to keep
//                 ---------------------------------------------------------
//                 Keep the one with smaller end
//                 */
//                 if (e2 < e1) {
//                     // current interval ends earlier → better choice
//                     comp = intervals[i];
//                 }
//                 // else: keep previous interval
//             }

//             /*
//             ---------------------------------------------------------
//             ✅ CASE 2: No overlap
//             ---------------------------------------------------------
//             */
//             else {
//                 comp = intervals[i];
//             }
//         }

//         return minRemovals;
//     }
// }



/*-----------------------------------------------------------------------------------------------------------*/




// /**********************************************************
// 🧠 APPROACH 3 : GREEDY (Sorting by End Time) – BEST APPROACH
// ***********************************************************

// 💡 MAIN IDEA:
// - Sort intervals by their END time
// - Always keep the interval that finishes earliest

// 👉 Why this works?
// → Earlier ending interval leaves more space for future intervals
// → So it minimizes overlap automatically

// ----------------------------------------------------------
// 🔥 KEY GREEDY RULE:
// If intervals overlap → remove the current one
// Else → keep it and update prevEnd

// ----------------------------------------------------------
// ⚠️ IMPORTANT DIFFERENCE FROM PREVIOUS APPROACHES:
// - No need to compare two intervals (e1 vs e2)
// - No need to decide which one to keep
// - Sorting by end already ensures best choice

// ----------------------------------------------------------
// ⏱️ TIME COMPLEXITY:
// Sorting → O(n log n)
// Traversal → O(n)

// 👉 Total → O(n log n)

// ----------------------------------------------------------
// 💾 SPACE COMPLEXITY:
// O(log n) → due to recursion stack used by sorting
// (No extra data structures used)

// **********************************************************/
// class Solution {
//     public int eraseOverlapIntervals(int[][] intervals) {
         
//         int n = intervals.length;

//         /*
//         ------------------------------------------------------
//         🧠 Step 1: Sort intervals by END time
//         ------------------------------------------------------
//         intervals[i][1] → end time
//         */
//         Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));

//         int minRemovals = 0;

//         /*
//         ------------------------------------------------------
//         🧠 Step 2: Track last selected interval's end
//         ------------------------------------------------------
//         */
//         int prevEnd = intervals[0][1]; 

//         /*
//         ------------------------------------------------------
//         🧠 Step 3: Traverse intervals
//         ------------------------------------------------------
//         */
//         for(int i = 1; i < n; i++)
//         {
//             /*
//             --------------------------------------------------
//             🔥 CASE 1: Overlap
//             --------------------------------------------------
//             current start < previous end
//             */
//             if(intervals[i][0] < prevEnd)
//             {
//                 // overlap → remove current interval
//                 minRemovals++;
//             }
//             else
//             {
//                 /*
//                 --------------------------------------------------
//                 ✅ CASE 2: No overlap
//                 --------------------------------------------------
//                 accept interval and update prevEnd
//                 */
//                 prevEnd = intervals[i][1];
//             }
//         }

//         return minRemovals;
//     }
// }