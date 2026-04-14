class DisjointSet{
 
 int n;
 int[] parent;
 int[] rank;

  DisjointSet(int n)
  {
    this.n = n;
    this.parent = new int[n];
    this.rank = new int[n];

    for(int i=0;i<n;i++)
    {
        parent[i] = i;
        rank[i] = 1;
    }
  }

 int find(int node)
 {
    if(node == parent[node])
    return node;

    return parent[node] = find(parent[node]);
 }

 void union(int node1, int node2)
 {
 
   int leader1 = find(node1);
   int leader2 = find(node2);

   if(leader1 == leader2) return;

   int r1 = rank[leader1];
   int r2 = rank[leader2];

   if(r1 > r2)
   {
    parent[leader2] = leader1;
   }
   else if(r1 < r2)
   {
    parent[leader1] = leader2;
   }
   else
   {
     parent[leader2] = leader1;
     rank[leader1]++;
   }

 }

}

class Solution {

    public void solve(char[][] board) {
         
          int rows = board.length;
          int cols = board[0].length;

          DisjointSet ds = new DisjointSet(rows*cols);

          int[][] directions = {{0,1},{1,0}};

          for(int cr=0;cr<rows;cr++)
          {
            for(int cc=0;cc<cols;cc++)
            {
                if(board[cr][cc] == 'O')
                {
                    int curr_node = cr*cols + cc;

                    for(int[] dir : directions)
                    {
                       int nbr_r = cr + dir[0];
                       int nbr_c = cc + dir[1];

                       if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && board[nbr_r][nbr_c] == 'O')
                       {
                         int nbr_node = nbr_r*cols + nbr_c;
                         ds.union(curr_node,nbr_node);
                       }

                    }
                }
            }
          }

          Set<Integer> unsurrounded_group = new HashSet<>();  

          for(int node=0;node<rows*cols;node++)
          {
              int r = node/cols;
              int c = node%cols;

              if(r == 0 || r == rows-1 || c == 0 || c == cols-1)
              {
                   if(board[r][c] == 'O')
                   unsurrounded_group.add(ds.find(node));
              }
          }


          for(int node=0;node<rows*cols;node++)
          {
               int r = node/cols;
               int c = node%cols;

               if(board[r][c] == 'O')
               {
                  if(!unsurrounded_group.contains(ds.find(node)))
                     board[r][c] ='X';
               }
          }

          return;


    }
}