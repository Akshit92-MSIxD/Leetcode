
// Prerequisite Problem : https://leetcode.com/problems/surrounded-regions/

// Note : I have written only one approach for this problem below since its similar to prerequisite problem !!!
// Note : DFS and DSU approach left (but easy !!!)



// Approach : Multisource BFS
// Hint : Boundary '1' is the main guy who makes the whole group easily walk off the boundary :)

class Node{
   
    int r;
    int c;

    Node(int r, int c)
    {
        this.r = r;
        this.c = c;
    }

}

class Solution {

    int multiSourceBFS(int[][] grid)
    {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<Node> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[rows][cols];

        int count_1 = 0;
     
        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                if(grid[i][j] == 1)
                count_1++;

                if(i == 0 || i == rows-1 || j == 0 || j == cols-1)
                {
                  if(grid[i][j] == 1)
                  {
                  q.add(new Node(i,j));
                  vis[i][j] = true;
                  } 
                }
            }
        }

         int[][] directions = {{-1,0},{1,0},{0,1},{0,-1}}; // top,down,left,right

        int count_walkOff_1 = 0;

        while(!q.isEmpty())
        {
            Node top = q.poll();
            int cr = top.r;
            int cc = top.c;

            count_walkOff_1++; // since this node is connected to a boundary 1

            // explore the unvisited neighbour lands(1)

            for(int[] dir : directions)
            {
                int nbr_r = cr + dir[0];
                int nbr_c = cc + dir[1];

                if(nbr_r >=0 && nbr_r < rows && nbr_c>=0 && nbr_c <cols && !vis[nbr_r][nbr_c] && grid[nbr_r][nbr_c] == 1)
                {
                    q.add(new Node(nbr_r,nbr_c));
                    vis[nbr_r][nbr_c] = true;
                }
            }

        }

        int ans = count_1 - count_walkOff_1;

        return ans;


    }

    public int numEnclaves(int[][] grid) {

        return multiSourceBFS(grid);
        
    }
}