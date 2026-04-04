// Approach 1 : Using Normal DFS
// Info : You can assume this problem is based on graphs!!!
// This problem is related to Finding Connected Component in Graph !!!
// Time complexity: O(r∗c)
// Space complexity: O(r∗c)

// class Solution {

//     void dfs(char[][] grid,int cr, int cc, boolean[][] vis)
//     {
//          if(cr < 0 || cr >= grid.length || cc < 0 || cc >= grid[cr].length || grid[cr][cc] == '0' || vis[cr][cc])
//          return;

//         vis[cr][cc] = true;
          
//         dfs(grid,cr,cc-1,vis); // going to left neighbour vertex(element)
//         dfs(grid,cr,cc+1,vis); // going to right neighbour vertex(element)
//         dfs(grid,cr-1,cc,vis); // going to top neighbour vertex(element)
//         dfs(grid,cr+1,cc,vis); // going to bottom neighbour vertex(element)

//         return;
//     }

//     public int numIslands(char[][] grid) {
         
//          boolean[][] vis = new boolean[grid.length][grid[0].length];

//          int ans = 0;
        
//          // consider each block of grid array as vertex of a graph and each vertex is connected horizontally and vertically to
//          //   neighbour vertices with invisible edges in grid array !!!


//          // Traversing each vertex from left to right and top to bottom !!!
//          for(int i=0;i<grid.length;i++)
//          {
//             for(int j=0;j<grid[i].length;j++)
//             {
//                 if(grid[i][j] == '1' && !vis[i][j])
//                 {
//                     dfs(grid,i,j,vis);
//                     ans++;
//                 }
//             }
//          }

//          return ans;

          
//     }
// }



// class DisjointSet{

//  HashMap<Integer,Integer> parent;
//  HashMap<Integer,Integer> rank;

//  DisjointSet(){
//      parent = new HashMap<>();
//      rank = new HashMap<>();
//  }

//  int find(int node)
//  {
//     if(node == parent.get(node))
//      return node;

//      int leader = find(parent.get(node));
//      parent.put(node,leader);
//      return leader;
//  }

//  void unionByRank(int node1, int node2)
//  {
//     int leader1 = find(node1);
//     int leader2 = find(node2);

//     int r1 = rank.get(leader1);
//     int r2 = rank.get(leader2);

//     if(r1 > r2)
//     {
//        parent.put(leader2,leader1);
//     }
//     else if(r1 < r2)
//     {
//        parent.put(leader1,leader2);
//     }
//     else
//     {
//        parent.put(leader2,leader1);
//        rank.put(leader1,rank.get(leader1) + 1);
//     }
//  }

// }


// class Solution {


//     public int numIslands(char[][] grid) {

//         DisjointSet ds = new DisjointSet();
         
//         int rows = grid.length;
//         int cols = grid[0].length;
       
//         // traversing to put only valid island nodes in the ds
//         for(int r=0;r<rows;r++)
//         {
//             for(int c=0;c<cols;c++)
//             {
//                 if(grid[r][c] == '1')
//                 {
//                 int node = r*cols + c;
//                 ds.parent.put(node,node);
//                 ds.rank.put(node,1);
//                 }
//             }
//         }


//         int[][] directions = {{0,1},{1,0}}; // right and bottom


//         // traversing again to join/union island nodes

//         for(int r=0;r<rows;r++)
//         {
//             for(int c=0;c<cols;c++)
//             {
//                 if(grid[r][c] == '1')
//                 {
//                     int curr_node = r*cols + c;

//                     // explore its neigbhours only right and bottom !!!

//                     for(int[] dir : directions)
//                     {
//                         int nbr_r = r + dir[0];
//                         int nbr_c = c + dir[1];

//                         if(nbr_r<rows && nbr_c<cols && grid[nbr_r][nbr_c] == '1')
//                         {
//                           int nbr_node = nbr_r*cols + nbr_c;
//                           ds.unionByRank(curr_node,nbr_node);
//                         }
//                     }
//                 }
//             }
//         }

//         int count_islands = 0;

//         for(int node : ds.parent.keySet())
//         {
//             if(ds.parent.get(node) == node)
//             count_islands++;
//         }

//         return count_islands;



          
//     }
// }


class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank   = new int[n];
        Arrays.fill(parent, -1);      // -1 = water / not added
    }

    void add(int node) {
        parent[node] = node;
        rank[node]   = 0;
    }

    int find(int node) {
        if (parent[node] == node) return node;
        return parent[node] = find(parent[node]);  // path compression inline
    }

    boolean union(int a, int b) {
        int la = find(a), lb = find(b);
        if (la == lb) return false;                // already same component

        if (rank[la] < rank[lb]) { int t = la; la = lb; lb = t; }
        parent[lb] = la;
        if (rank[la] == rank[lb]) rank[la]++;      // only increment on tie
        return true;
    }
}

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        DisjointSet ds = new DisjointSet(rows * cols);
        int count = 0;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c] == '1') {
                    ds.add(r * cols + c);
                    count++;                       // every new land = potential island
                }

        int[][] dirs = {{0,1},{1,0}};

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++)
                if (grid[r][c] == '1')
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr < rows && nc < cols && grid[nr][nc] == '1')
                            if (ds.union(r * cols + c, nr * cols + nc))
                                count--;           // merged two islands → one less
                    }

        return count;
    }
}