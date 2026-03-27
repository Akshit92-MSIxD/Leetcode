// (Binary Search + BFS) and Union Find Approaches Left !!!


// Approach 1 : Using Dijkstra's Algorithm (Since here weights(difference btw two cells) are arbitrary !!!)

// TC : O(ElogV) --> O(4*n*m * log(n*m));
// SC : O(n*m)

class Solution {

 class Node{
   
    int i;
    int j;
    int effort;

    Node(int i, int j, int effort)
    {
        this.i = i;
        this.j = j;
        this.effort = effort;
    }

 }
    public int minimumEffortPath(int[][] heights) {
        
      // create a minHeap
      PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.effort,b.effort));
      
      // add the src node
      pq.add(new Node(0,0,0));

      // create a array to store minimum efforts for all individual nodes in a grid
      int[][] minEffort = new int[heights.length][heights[0].length];

      for(int i=0;i<minEffort.length;i++)
      {
         for(int j=0;j<minEffort[0].length;j++)
         {
            minEffort[i][j] = Integer.MAX_VALUE;
         }
      }

      minEffort[0][0] = 0;

      int[][] direction = {{-1,0},{1,0},{0,-1},{0,1}}; // up , down , left , right

      while(!pq.isEmpty())
      {
         Node top = pq.poll();
         int cr = top.i;
         int cc = top.j;
         int cEffort = top.effort;

         if(cEffort > minEffort[cr][cc]) continue;

         if(cr == heights.length-1 && cc == heights[0].length-1) break;

         // explore neighbours

         for(int[] dir : direction)
         {
            int nbrR = cr + dir[0];
            int nbrC = cc + dir[1];
             
             if(nbrR >= 0 && nbrR <= heights.length-1 && nbrC >= 0 && nbrC <= heights[0].length-1)
             {
                int nbrEffort = Math.max(cEffort,Math.abs(heights[nbrR][nbrC] - heights[cr][cc]));

                if(nbrEffort < minEffort[nbrR][nbrC])
                {
                    minEffort[nbrR][nbrC] = nbrEffort;
                    pq.add(new Node(nbrR,nbrC,nbrEffort));
                }
             }
         }

    }
        int dr = heights.length-1 , dc = heights[0].length-1;

       
      for(int i=0;i<minEffort.length;i++)
      {
         for(int j=0;j<minEffort[0].length;j++)
         {
            System.out.print(minEffort[i][j]+" ");
         }
           System.out.println();
      }
         return minEffort[dr][dc];
}
}