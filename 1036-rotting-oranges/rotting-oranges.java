
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


class Node{
   
   int i;
   int j;

   Node(int i, int j)
   {
    this.i = i;
    this.j = j;
   }

}

class Solution {
     
    int bfs(int[][] grid)
    {
        
        int rows = grid.length;
        int cols = grid[0].length;


        Queue<Node> q = new ArrayDeque<>();

        int count_1 = 0;
        
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(grid[i][j] == 2)
                q.add(new Node(i,j));
                else if(grid[i][j] == 1)
                count_1++;

            }
        } 

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top, down, left, right
        int level = 0; // level would tell the time taken

        while(!q.isEmpty())
        {
            int size = q.size();  // size --> size of current level 

            boolean makeInfected = false;

            while(size-- > 0)
            { 
            Node top = q.poll();

            int curr_r = top.i;
            int curr_c = top.j;

            for(int[] dir : directions)
            {
                int nbr_r = curr_r + dir[0];
                int nbr_c = curr_c + dir[1];

                if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && grid[nbr_r][nbr_c] == 1)
                {
                  grid[nbr_r][nbr_c] = 2;
                  makeInfected = true;
                  q.add(new Node(nbr_r,nbr_c));
                  count_1--;
                }
            }
            }

            if(makeInfected)
            level++;

        }


        if(count_1 > 0)
        return -1;

        return level;

    }
    public int orangesRotting(int[][] grid) {
           
        int rows = grid.length;
        int cols = grid[0].length;

        int time = bfs(grid);

        return time;
    }
}