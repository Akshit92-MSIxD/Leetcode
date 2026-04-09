// Similar to Covid Spread Problem in GFG
// https://www.youtube.com/watch?v=yf3oUhkvqA0&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn



// Approach 2 : Using BFS (Striver's Approach written by me)
// *** Hint : Level of nodes --> min time taken !!!
// TC : O(n*m)
// SC : O(n*m)

class Node{
   
   int i;
   int j;
   int lvl;

   Node(int i, int j, int lvl)
   {
    this.i = i;
    this.j = j;
    this.lvl = lvl;
   }

}

class Solution {
     
    int bfs(int[][] grid)
    {
        
        int rows = grid.length;
        int cols = grid[0].length;


        Queue<Node> q = new ArrayDeque<>();
        
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(grid[i][j] == 2)
                q.add(new Node(i,j,0)); // multiple source infected nodes has level 0
            }
        } 

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top, down, left, right
        int level = 0; // level would tell the time taken

        while(!q.isEmpty())
        {
            Node top = q.poll();

            int curr_r = top.i;
            int curr_c = top.j;
            int curr_lvl = top.lvl;

            level = Math.max(level,curr_lvl);

            for(int[] dir : directions)
            {
                int nbr_r = curr_r + dir[0];
                int nbr_c = curr_c + dir[1];
                int nbr_lvl = curr_lvl + 1;

                if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && grid[nbr_r][nbr_c] == 1)
                {
                  grid[nbr_r][nbr_c] = 2;
                  q.add(new Node(nbr_r,nbr_c,nbr_lvl));
                }
            }

        }

        return level;

    }
    public int orangesRotting(int[][] grid) {
           
        int rows = grid.length;
        int cols = grid[0].length;

           int time = bfs(grid);
      
          for(int i=0;i<rows;i++)
          {
            for(int j=0;j<cols;j++)
            {
                if(grid[i][j] == 1)
                return -1;
            }
          }

        return time;
    }
}



/*-------------------------------------------------------------------------------------------------------------*/



// // Approach 1 : Using BFS (written by me)
// // TC : O(n*m)
// // SC : O(n*m)

// class Node{
   
//    int i;
//    int j;

//    Node(int i, int j)
//    {
//     this.i = i;
//     this.j = j;
//    }

// }

// class Solution {
     
//     int bfs(int[][] grid)
//     {
        
//         int rows = grid.length;
//         int cols = grid[0].length;


//         Queue<Node> q = new ArrayDeque<>();
        
//         for(int i=0;i<rows;i++)
//         {
//             for(int j=0;j<cols;j++)
//             {
//                 if(grid[i][j] == 2)
//                 q.add(new Node(i,j));
//             }
//         } 

//         int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top, down, left, right
//         int level = 0; // level would tell the time taken

//         while(!q.isEmpty())
//         {
//             int size = q.size();  // size --> size of current level 

//             boolean makeInfected = false;

//             while(size-- > 0)
//             { 
//             Node top = q.poll();

//             int curr_r = top.i;
//             int curr_c = top.j;

//             for(int[] dir : directions)
//             {
//                 int nbr_r = curr_r + dir[0];
//                 int nbr_c = curr_c + dir[1];

//                 if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && grid[nbr_r][nbr_c] == 1)
//                 {
//                   grid[nbr_r][nbr_c] = 2;
//                   makeInfected = true;
//                   q.add(new Node(nbr_r,nbr_c));
//                 }
//             }
//             }

//             if(makeInfected)
//             level++;

//         }

//         return level;

//     }
//     public int orangesRotting(int[][] grid) {
           
//         int rows = grid.length;
//         int cols = grid[0].length;

//            int time = bfs(grid);
      
//           for(int i=0;i<rows;i++)
//           {
//             for(int j=0;j<cols;j++)
//             {
//                 if(grid[i][j] == 1)
//                 return -1;
//             }
//           }

//         return time;
//     }
// }



