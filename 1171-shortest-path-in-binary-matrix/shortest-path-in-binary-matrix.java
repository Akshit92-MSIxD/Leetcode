//**Prerequisite Problem : https://www.geeksforgeeks.org/problems/shortest-path-in-a-binary-maze-1655453161/1


//Approach 1 : Using Normal BFS (*** for unweighted edges or  all edges having equal +ve weights)

// ** Dijktra works better for weighted graphs with different weights for each edge !!!

/* *** NOTE : BFS does level by level traversal so the first time when it reaches a particular
            node in grid or process that node in Queue it is already the minimum distance(no. of steps) needed to 
             reach that node */
             
// TC = O(n*m) 
// SC = O(n*m)

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {

           if(grid[0][0] == 1 || grid[grid.length-1][grid[0].length-1] == 1)
           return -1;

           Queue<int[]> q = new ArrayDeque<>();
           q.add(new int[]{0,0});

           int[][] minDis = new int[grid.length][grid[0].length];
           
           for(int i=0;i<minDis.length;i++)
           {
             for(int j=0;j<minDis[0].length;j++)
             {
                 minDis[i][j] = Integer.MAX_VALUE;
             }
           }

           minDis[0][0] = 0;

             // direction[0] -> up , direction[1] = down
        // direction[2] -> left , direction[3] = right
        // direction[4] -> up left ,  direction[5] -> up right
        // direction[6] -> down left ,  direction[7] -> down right
        final int[][] direction = {{-1,0},{1,0},{0,-1},{0,1},{-1,-1},{-1,1},{1,-1},{1,1}}; 

           while(!q.isEmpty())
           {
               int[] cell = q.poll();
               int cr = cell[0];
               int cc = cell[1];
               int cDis = minDis[cr][cc];

               if(cr == grid.length-1 && cc == grid[0].length-1) break;

               for(int[] dir : direction)
               {
                     int nbrR = cr + dir[0];
                     int nbrC = cc + dir[1];
                     int nbrDis = cDis + 1;

                if(nbrR >= 0 && nbrR <= grid.length-1 && nbrC >= 0 && nbrC <=grid[0].length-1 && grid[nbrR][nbrC] == 0)
                {
                     if(nbrDis < minDis[nbrR][nbrC])
                     {
                     minDis[nbrR][nbrC] = nbrDis;
                     q.add(new int[]{nbrR,nbrC});
                     }
                }
               } 
           }
               int dr = grid.length - 1;
               int dc = grid[0].length - 1;

       int length = (minDis[dr][dc] == Integer.MAX_VALUE) ?  -1 : minDis[dr][dc] + 1 ;

       return length;
    }
}