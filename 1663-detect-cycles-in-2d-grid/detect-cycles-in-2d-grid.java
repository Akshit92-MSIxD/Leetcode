
// // Approach : Using DFS 
// // Prerequisite Problem : https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
// // TC : O(4*n*m)
// // SC : O(n*m)

// class Solution {

//     int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top , down , left, right

//     boolean dfs(char grid[][] , int cr, int cc , int pr , int pc , boolean[][] vis)
//     {
//         vis[cr][cc] = true;

//         // explore the neighbour cells

//         for(int[] dir : directions)
//         {
//             int nbr_r = cr + dir[0];
//             int nbr_c = cc + dir[1];

//             if(nbr_r>=0 && nbr_r<grid.length && nbr_c>=0 && nbr_c<grid[0].length && grid[nbr_r][nbr_c] == grid[cr][cc])
//             {
//                 if(!vis[nbr_r][nbr_c])
//                 {
//                    if(dfs(grid,nbr_r,nbr_c,cr,cc,vis))
//                    return true;
//                 }
//                 else if(nbr_r != pr || nbr_c != pc)
//                 {
//                    return true; // cycle exits;
//                 }
//             }
//         }

//         return false;
//     }

//     public boolean containsCycle(char[][] grid) {
        
//     int rows = grid.length;
//     int cols = grid[0].length;
      
//     boolean[][] vis = new boolean[rows][cols];

//     for(int i=0;i<rows;i++)
//     {
//         for(int j=0;j<cols;j++)
//         {
//             if(!vis[i][j])
//             {
//                 if(dfs(grid,i,j,-1,-1,vis))
//                 return true;
//             }
//         }
//     }

//     return false;


//     }
// }



/*--------------------------------------------------------------*/



// Approach : Using DSU
// Concept : "Convert grid to graph, connect same cells, and if we try to connect already connected cells → cycle"
// TC : O(n*m)
//  SC : O(n*m)

class DisjointSet{

 int n;
 int[] parent;
 int[] rank;

 DisjointSet(int n)
 {
    this.n = n;
    parent = new int[n];
    rank = new int[n];

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

 void union(int node1 , int node2)
 { 
    int leader1 = find(node1);
    int leader2 = find(node2);

    if(leader1 == leader2)
    return;

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

    public boolean containsCycle(char[][] grid) {
        
     int rows = grid.length;
     int cols = grid[0].length;

     DisjointSet ds = new DisjointSet(rows*cols);

     int[][] directions = {{0,1},{1,0}} ; // right , down

     for(int r=0;r<rows;r++)
     {
        for(int c=0;c<cols;c++)
        {
            int curr_node = r*cols + c;

            // explore neighbours

            for(int[] dir : directions)
            {
                int nbr_r = r + dir[0];
                int nbr_c = c + dir[1];

                if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && grid[nbr_r][nbr_c] == grid[r][c])
                {
                    int nbr_node = nbr_r*cols + nbr_c;

                    if(ds.find(curr_node) == ds.find(nbr_node))
                    return true;

                    ds.union(curr_node,nbr_node);
                } 
            }
        }
     }

     return false;


    }
}