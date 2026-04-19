// I have written two approaches for this problem below !!!



// Approach 1 : MultiSource BFS + HashMap Approach

class Node
    {
        int r;
        int c;
        int lvl;

        Node(int r, int c, int lvl)
        {
           this.r = r;
           this.c = c;
           this.lvl = lvl;
        }
    }

class Solution {
    public int[][] colorGrid(int n, int m, int[][] sources) {

         int[][] grid = new int[n][m];
         
        

        for(int i=0;i<sources.length;i++)
            { 
                int r = sources[i][0];
                int c = sources[i][1];
                int val = sources[i][2];

                grid[r][c] = val;

            }

            int rows = grid.length;
            int cols = grid[0].length;
        
            HashMap<Integer,Integer> vis_lvl = new HashMap<>();
        
           Queue<Node> q = new ArrayDeque<>();

        for(int i=0;i<rows;i++)
            {
                for(int j=0;j<cols;j++)
                    {
                        if(grid[i][j] != 0)
                        {
                        q.add(new Node(i,j,0));
                        int node = i*cols + j;
                        vis_lvl.put(node,0);
                        }
                    }
            }

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

        while(!q.isEmpty())
            {
                Node top = q.poll();
                int cr = top.r;
                int cc = top.c;
                int clvl = top.lvl;

                int curr_node = cr*cols + cc;

                 for(int[] dir : directions)
                     {
                         int nbr_r = cr + dir[0];
                         int nbr_c = cc + dir[1];

                         if(nbr_r >=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols)
                         {
                             int nbr_node = nbr_r*cols + nbr_c;

                             if(!vis_lvl.containsKey(nbr_node))
                             {
                                 grid[nbr_r][nbr_c] = grid[cr][cc];
                                 q.add(new Node(nbr_r,nbr_c,clvl+1));
                                 vis_lvl.put(nbr_node,clvl+1);
                             }
                             else
                             {
                                 if(clvl + 1 == vis_lvl.get(nbr_node) && grid[cr][cc] > grid[nbr_r][nbr_c])
                                 {
                                     grid[nbr_r][nbr_c] = grid[cr][cc];
                                 }
                             }
                         }
                     }

                
            }

         return grid;
           
    }
}






/*---------------------------------------------------------------------------------------------------------*/

   



// // Approach 2 : MultiSource BFS + Array Approach
// // Space Optimized and Time Optmized than Approach 1

// class Node
//     {
//         int r;
//         int c;
//         int lvl;

//         Node(int r, int c, int lvl)
//         {
//            this.r = r;
//            this.c = c;
//            this.lvl = lvl;
//         }
//     }

// class Solution {
//     public int[][] colorGrid(int rows, int cols, int[][] sources) {

//          int[][] grid = new int[rows][cols];
         
//         for(int i=0;i<sources.length;i++)
//             { 
//                 int r = sources[i][0];
//                 int c = sources[i][1];
//                 int val = sources[i][2];

//                 grid[r][c] = val;

//             }

        
//             int[][] vis_lvl = new int[rows][cols];

//             for(int i=0;i<rows;i++)
//             {
//                 for(int j=0;j<cols;j++)
//                 {
//                     vis_lvl[i][j] = -1;
//                 }
//             }
        
//            Queue<Node> q = new ArrayDeque<>();

//         for(int i=0;i<rows;i++)
//             {
//                 for(int j=0;j<cols;j++)
//                     {
//                         if(grid[i][j] != 0)
//                         {
//                         q.add(new Node(i,j,0));
//                         vis_lvl[i][j] = 0;
//                         }
//                     }
//             }

//         int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

//         while(!q.isEmpty())
//             {
//                 Node top = q.poll();
//                 int cr = top.r;
//                 int cc = top.c;
//                 int clvl = top.lvl;

//                  for(int[] dir : directions)
//                      {
//                          int nbr_r = cr + dir[0];
//                          int nbr_c = cc + dir[1];

//                          if(nbr_r >=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols)
//                          {

//                              if(vis_lvl[nbr_r][nbr_c] == -1)
//                              {
//                                  grid[nbr_r][nbr_c] = grid[cr][cc];
//                                  q.add(new Node(nbr_r,nbr_c,clvl+1));
//                                  vis_lvl[nbr_r][nbr_c] = clvl + 1;
//                              }
//                              else
//                              {
//                                  if(clvl + 1 == vis_lvl[nbr_r][nbr_c] && grid[cr][cc] > grid[nbr_r][nbr_c])
//                                      grid[nbr_r][nbr_c] = grid[cr][cc];
//                              }
//                          }
//                      }

                
//             }

//          return grid;
           
//     }
// }