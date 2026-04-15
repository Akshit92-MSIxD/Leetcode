// class DisjointSet{
 
//  int n;
//  int[] parent;
//  int[] rank;

//   DisjointSet(int n)
//   {
//     this.n = n;
//     this.parent = new int[n];
//     this.rank = new int[n];

//     for(int i=0;i<n;i++)
//     {
//         parent[i] = i;
//         rank[i] = 1;
//     }
//   }

//  int find(int node)
//  {
//     if(node == parent[node])
//     return node;

//     return parent[node] = find(parent[node]);
//  }

//  void union(int node1, int node2)
//  {
 
//    int leader1 = find(node1);
//    int leader2 = find(node2);

//    if(leader1 == leader2) return;

//    int r1 = rank[leader1];
//    int r2 = rank[leader2];

//    if(r1 > r2)
//    {
//     parent[leader2] = leader1;
//    }
//    else if(r1 < r2)
//    {
//     parent[leader1] = leader2;
//    }
//    else
//    {
//      parent[leader2] = leader1;
//      rank[leader1]++;
//    }

//  }

// }

// class Solution {

//     public void solve(char[][] board) {
         
//           int rows = board.length;
//           int cols = board[0].length;

//           DisjointSet ds = new DisjointSet(rows*cols);

//           int[][] directions = {{0,1},{1,0}};

//           for(int cr=0;cr<rows;cr++)
//           {
//             for(int cc=0;cc<cols;cc++)
//             {
//                 if(board[cr][cc] == 'O')
//                 {
//                     int curr_node = cr*cols + cc;

//                     for(int[] dir : directions)
//                     {
//                        int nbr_r = cr + dir[0];
//                        int nbr_c = cc + dir[1];

//                        if(nbr_r>=0 && nbr_r<rows && nbr_c>=0 && nbr_c<cols && board[nbr_r][nbr_c] == 'O')
//                        {
//                          int nbr_node = nbr_r*cols + nbr_c;
//                          ds.union(curr_node,nbr_node);
//                        }

//                     }
//                 }
//             }
//           }

//           Set<Integer> unsurrounded_group = new HashSet<>();  

//           for(int node=0;node<rows*cols;node++)
//           {
//               int r = node/cols;
//               int c = node%cols;

//               if(r == 0 || r == rows-1 || c == 0 || c == cols-1)
//               {
//                    if(board[r][c] == 'O')
//                    unsurrounded_group.add(ds.find(node));
//               }
//           }


//           for(int node=0;node<rows*cols;node++)
//           {
//                int r = node/cols;
//                int c = node%cols;

//                if(board[r][c] == 'O')
//                {
//                   if(!unsurrounded_group.contains(ds.find(node)))
//                      board[r][c] ='X';
//                }
//           }

//           return;


//     }
// }




// class Solution {

//     int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // top,down,left,right

//     int dfs(char[][] board, int cr, int cc,ArrayList<Integer> group, boolean[][] vis)
//     {
//          vis[cr][cc] = true;

//          if(cr == 0 || cr == board.length-1 || cc == 0 || cc == board[cr].length-1)
//           return 0;

//          group.add(cr*board[cr].length+cc);

//           int covered_status = 1;

//           // explore the neighbours

//           for(int[] dir : directions)
//           {
//              int nbr_r = cr + dir[0];
//              int nbr_c = cc + dir[1];

//             if(nbr_r>=0 && nbr_r<board.length && nbr_c>=0 && nbr_c<board[0].length && !vis[nbr_r][nbr_c] && board[nbr_r][nbr_c] == 'O')
//                    covered_status = Math.min(covered_status,dfs(board,nbr_r,nbr_c,group,vis));
//           }

//           return covered_status;

         
//     }

//     public void solve(char[][] board) {
         
//           int rows = board.length;
//           int cols = board[0].length;

//           boolean[][] vis = new boolean[rows][cols];
          
//           for(int i=0;i<rows;i++)
//           {
//                 for(int j=0;j<cols;j++)
//                 {

//                  ArrayList<Integer> group = new ArrayList<>();

//                   int covered_status = 0;
                
//                     if(i>0 && i<rows-1 && j>0 && j<cols-1 && !vis[i][j] && board[i][j] == 'O')
//                     {
//                         covered_status = dfs(board,i,j,group,vis);

//                             if(covered_status == 1)
//                             {
//                                 for(int node : group)
//                                 {
//                                     int r = node/cols;
//                                     int c = node%cols;

//                                     board[r][c] = 'X';
//                                 }
//                             }
//                     }
//                 }

//           }
// }
// }


class Solution {

    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};

    boolean dfs(char[][] board, int r, int c) {

        int rows = board.length;
        int cols = board[0].length;

        // mark visited temporarily
        board[r][c] = 'T';

        boolean enclosed = true;

        if(r == 0 || r == rows-1 || c == 0 || c == cols-1) {
            enclosed = false;
        }

        for(int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];

            if(nr >= 0 && nr < rows && nc >= 0 && nc < cols && board[nr][nc] == 'O') {
                if(!dfs(board, nr, nc)) {
                    enclosed = false;
                }
            }
        }

        return enclosed;
    }

    void convert(char[][] board, int r, int c, char target) {

        int rows = board.length;
        int cols = board[0].length;

        if(board[r][c] != 'T') return;

        board[r][c] = target;

        for(int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];

            if(nr >= 0 && nr < rows && nc >= 0 && nc < cols) {
                convert(board, nr, nc, target);
            }
        }
    }

    public void solve(char[][] board) {

        int rows = board.length;
        int cols = board[0].length;

        for(int i = 1; i < rows-1; i++) {
            for(int j = 1; j < cols-1; j++) {

                if(board[i][j] == 'O') {

                    boolean enclosed = dfs(board, i, j);

                    if(enclosed) {
                        convert(board, i, j, 'X');
                    } else {
                        convert(board, i, j, 'O');
                    }
                }
            }
        }
    }
}

