class Node{

  int r;
  int c;
  int lvl;

  Node(int r,int c, int lvl)
  {
    this.r = r;
    this.c = c;
    this.lvl = lvl;
  }

}
class Solution {

    int[][] multiSourceBFS(int[][] mat)
    {
         int rows = mat.length;
         int cols = mat[0].length;

         int[][] dis = new int[rows][cols];


         Queue<Node> q = new ArrayDeque<>();

         for(int r=0;r<rows;r++)
         {
            for(int c=0;c<cols;c++)
            {
                if(mat[r][c] == 0)
                    q.add(new Node(r,c,0));
            }
         }

         boolean[][] vis = new boolean[rows][cols];

         int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top, down, left and right

         while(!q.isEmpty())
         {
            Node top = q.poll();
            int cr = top.r;
            int cc = top.c;
            int clvl = top.lvl;

            dis[cr][cc] = clvl;

            // explore the neighbours

            for(int[] dir : directions)
            {
                int nbr_r = cr + dir[0];
                int nbr_c = cc + dir[1];
                int nbr_lvl = clvl + 1;

                if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && mat[nbr_r][nbr_c] == 1)
                {
                    if(!vis[nbr_r][nbr_c])
                    {
                    q.add(new Node(nbr_r,nbr_c,nbr_lvl));
                    vis[nbr_r][nbr_c] = true;
                    }
                }
            }

         }

         return dis;
    }


    public int[][] updateMatrix(int[][] mat) {

         return multiSourceBFS(mat);


    }
}