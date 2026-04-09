class Solution {

    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top , down , left, right

    boolean dfs(char grid[][] , int cr, int cc , int pr , int pc , boolean[][] vis)
    {
        vis[cr][cc] = true;

        // explore the neighbour cells

        for(int[] dir : directions)
        {
            int nbr_r = cr + dir[0];
            int nbr_c = cc + dir[1];

            if(nbr_r>=0 && nbr_r<grid.length && nbr_c>=0 && nbr_c<grid[0].length && grid[nbr_r][nbr_c] == grid[cr][cc])
            {
                if(!vis[nbr_r][nbr_c])
                {
                   if(dfs(grid,nbr_r,nbr_c,cr,cc,vis))
                   return true;
                }
                else if(nbr_r != pr || nbr_c != pc)
                {
                   return true; // cycle exits;
                }
            }
        }

        return false;
    }

    public boolean containsCycle(char[][] grid) {
        
    int rows = grid.length;
    int cols = grid[0].length;
      
    boolean[][] vis = new boolean[rows][cols];

    for(int i=0;i<rows;i++)
    {
        for(int j=0;j<cols;j++)
        {
            if(!vis[i][j])
            {
                if(dfs(grid,i,j,-1,-1,vis))
                return true;
            }
        }
    }

    return false;


    }
}