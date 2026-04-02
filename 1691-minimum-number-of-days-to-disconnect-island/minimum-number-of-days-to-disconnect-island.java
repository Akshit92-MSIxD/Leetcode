// *** NOTE : “Grid graphs have low connectivity” --> easier to break into multiple comps by removing just few nodes or  in case of grid(4 directionally connected) -->  (nodes removal is always <= 2) to make grid graph disconnected !!!


class Solution {

    int timer = 1;
    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
    boolean has_art_pt = false;

    void dfs(int[][] grid, int r, int c, int parent_node,int rows,int cols, int[] tin, int[] low, boolean[] vis)
    {
      int curr_node = r*cols + c;

      low[curr_node] = tin[curr_node] = timer++;

      vis[curr_node] = true;

      int children = 0;

      // explore the neighbours

      for(int[] dir : directions)
      {
         int nbr_r = r + dir[0];
         int nbr_c = c + dir[1];

         int nbr_node = nbr_r*cols + nbr_c;

         if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && grid[nbr_r][nbr_c] == 1)
         {
            if(nbr_node == parent_node) continue;

            if(!vis[nbr_node])
            {
                children++;
              dfs(grid,nbr_r,nbr_c,curr_node,rows,cols,tin,low,vis);
              low[curr_node] = Math.min(low[curr_node],low[nbr_node]);

              if(low[nbr_node] >= tin[curr_node] && parent_node != -1)
                has_art_pt = true;
            }
            else
            {
              low[curr_node] = Math.min(low[curr_node],tin[nbr_node]);
            }
         }
      }

      if((children > 1 && parent_node == -1) || (children == 0 && parent_node == -1))
      has_art_pt = true;

        return;
    }



    public int minDays(int[][] grid) { 
     
     int rows = grid.length;
     int cols = grid[0].length;

     int[] tin = new int[rows*cols];
     int[] low = new int[rows*cols];

     boolean[] vis = new boolean[rows*cols];

     int count_calls = 0;

     for(int r=0;r<rows;r++)
     {
        for(int c=0;c<cols;c++)
        {
            int node = r*cols + c;

           if(!vis[node] && grid[r][c] == 1)
           {
             count_calls++;

             if(count_calls == 2)
             return 0;

             dfs(grid,r,c,-1,rows,cols,tin,low,vis);
           }
        }
     }

       if(count_calls == 0) return 0;


      return has_art_pt ? 1 : 2;


    }
}