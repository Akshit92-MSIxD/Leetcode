
// Approach : Using BFS
// TC : O(n*m)
// SC : O(min(r,c)) (How ?? Paper pe analyze kr !!! if starting cell (0,0) se traversal suru krenge !!! )

class Node{
   
   int r , c;

   Node(int r, int c)
   {
     this.r = r;
     this.c = c;
   }

}
class Solution {

    void bfs(int[][] image, int sr, int sc, int color)
    {
        int rows = image.length;
        int cols = image[0].length;

        int org_color = image[sr][sc];

        if(org_color == color) return;


        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(sr,sc));

        image[sr][sc] = color;

        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top , bottom , left , right

        while(!q.isEmpty())
        {
            Node top = q.poll();

            int curr_r = top.r;
            int curr_c = top.c;


            // explore the neighbours

            for(int[] dir : directions)
            {
                int nbr_r = curr_r + dir[0];
                int nbr_c = curr_c + dir[1];

                if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && image[nbr_r][nbr_c] == org_color)
                {
                 q.add(new Node(nbr_r,nbr_c));
                 image[nbr_r][nbr_c] = color;
                }
            }
        }
    }



    public int[][] floodFill(int[][] image, int sr, int sc, int color) {

          bfs(image,sr,sc,color);

          return image;

    }
}