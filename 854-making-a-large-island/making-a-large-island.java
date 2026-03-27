// Please read both the approaches below !!!
// DFS/BFS approach left(very easy!!!)


// Approach 2 : Slighlty faster than Approach 1 but same TC ( DSU + unique[] array)
// 🔥 Main Idea / Intuition:
//
// - We are given a grid of 0s and 1s
// - 1 = land, 0 = water
// - We can convert at most one 0 → 1
// - We need to find the maximum size of island possible
//
// 💡 Key Idea:
// 1. First, connect all existing 1s using DSU (Disjoint Set)
//    → this gives us groups (islands) and their sizes
//
// 2. Then, for every 0 cell:
//    → check its 4 neighbors
//    → find which islands are around it
//    → combine their sizes (avoid duplicates using unique[] array to store surrounding leaders)
//    → +1 (because this 0 becomes 1)
//
// 3. Take the maximum of all possible results
//
// 💬 Important:
// - DSU helps us quickly know:
//   → which island a cell belongs to
//   → size of that island
//
// ------------------------------------------------------------
//
// ⏱ Time Complexity (TC):
// - Building DSU: O(n² * α(n))
// - Checking all 0 cells: O(n²)
// → Overall: O(n²)
//
// (α(n) is very small, almost constant)
//
// 🧠 Space Complexity (SC):
// - DSU arrays: O(n²)
// → Overall: O(n²)


class DisjointSet{
 
  int[] size; // stores size of each island (only valid for leaders)
  int[] parent; // stores size of each island (only valid for leaders)

  DisjointSet(int n)
  {
    size = new int[n];
    parent = new int[n];
    
     // initially each node is its own parent (separate island)
    for(int i=0;i<n;i++)
    {
        size[i] = 1;
        parent[i] = i;
    }
  }
 
   // find → returns ultimate parent (leader of island)
  int find(int node)
  {
    if(node == parent[node])
        return node;

    return parent[node] = find(parent[node]);  // path compression → directly connect to leader
  }
 
 // union by size → attach smaller island under bigger one
  void unionBySize(int node1, int node2)
  {
    int leader1 = find(node1);
    int leader2 = find(node2);
    
     // if already in same island → do nothing
    if(leader1 == leader2) return;

    if(size[leader1] >= size[leader2])
    {
        parent[leader2] = leader1;
        size[leader1] += size[leader2];
    }
    else
    {
        parent[leader1] = leader2;
        size[leader2] += size[leader1];
    }
  }
}

class Solution {
    public int largestIsland(int[][] grid) {
        
        int n = grid.length;
        // create DSU for n*n cells (convert 2D → 1D)
        DisjointSet ds = new DisjointSet(n*n);

        // Only 2 directions to avoid duplicate unions
        int[][] directions = {{1,0},{0,1}};

        // Step 1: Build DSU for all 1s(connect islands)
        for(int r=0;r<n;r++)
        {
            for(int c=0;c<n;c++)
            {
                if(grid[r][c] == 1)
                {
                    int node1 = r*n + c;

                    for(int[] dir : directions)
                    {
                        int nr = r + dir[0];
                        int nc = c + dir[1];
                        
                         // check valid cell and if it is land
                        if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc] == 1)
                        {
                            int node2 = nr*n + nc;
                            ds.unionBySize(node1,node2);
                        }
                    }
                }
            }
        }

        int maxSizeIsland = 0;

         // 4 directions (up, down, left, right)
        int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};

        for(int r=0;r<n;r++)
        {
            for(int c=0;c<n;c++)
            {
                if(grid[r][c] == 0)
                {
                    // store unique neighboring island leaders 
                    int[] unique = new int[4]; // max 4 neighbors
                    int count = 0;

                    for(int[] d : dirs)
                    {
                        int nr = r + d[0];
                        int nc = c + d[1];

                        if(nr>=0 && nr<n && nc>=0 && nc<n && grid[nr][nc] == 1)
                        {
                            int nbr = nr*n + nc;
                            int leader = ds.find(nbr);  // find island leader

                            // check if this leader already added (avoid duplicates)
                            boolean exists = false;
                            for(int i=0;i<count;i++)
                            {
                                if(unique[i] == leader)
                                {
                                    exists = true;
                                    break;
                                }
                            }
                            
                            // if not already present → add it
                            if(!exists)
                            {
                                unique[count++] = leader;
                            }
                        }
                    }
                 // current cell becomes land or current 0 becomes 1 → contributes 1 in size
                    int mergedSize = 1; 
                   
                    // add sizes of all unique neighboring islands
                    for(int i=0;i<count;i++)
                    {
                        mergedSize += ds.size[unique[i]];
                    }
                     
                       // update maximum island size
                    maxSizeIsland = Math.max(maxSizeIsland, mergedSize);
                }
            }
        }
        
        // If maxSizeIsland is still 0, it means there were no 0s in the grid
     // (i.e., the grid was already full of 1s), so the largest island is the whole grid → n*n
     // Otherwise, return the maximum island size we calculated after flipping a 0
        return (maxSizeIsland == 0) ? n*n : maxSizeIsland ;
    }
}




/*-----------------------------------------------------------------------------------------------*/



// Approach1 : Written by Me (DSU + HashSet!!!)
// 🔥 Main Idea / Intuition:
//
// - We are given a grid of 0s and 1s
// - 1 = land, 0 = water
// - We can convert exactly one 0 → 1
// - We need to find the maximum island size possible
//
// 💡 Key Idea:
// 1. First, connect all existing 1s using DSU
//    → this forms islands (groups) and stores their sizes
//
// 2. Then for each 0 cell:
//    → check its 4 neighbors
//    → find which islands are around it
//    → combine their sizes (avoid duplicates using HashSet)
//    → +1 (current 0 becomes 1)
//
// 3. Take the maximum among all possibilities
//
// ------------------------------------------------------------
// ⏱ Time Complexity (TC):
// - DSU building: O(n² * α(n))
// - Checking all cells: O(n²)
// - HashSet operations (max 4 elements): O(1)
//
// → Overall: O(n²)
//
// 🧠 Space Complexity (SC):
// - DSU arrays: O(n²)
// - HashSet (at most 4 elements): O(1)
//
// → Overall: O(n²)

// class DisjointSet{
 
//   int n;
//   int[] size;
//   int[] parent;

//   DisjointSet(int n)
//   {
//     this.n = n;
//     size = new int[n];
//     parent = new int[n];

//     for(int i=0;i<n;i++)
//     {
//         this.size[i] = 1;
//         this.parent[i] = i;
//     }
//   }

//  int find(int node)
//  {
//     if(node == parent[node])
//     return node;

//     return parent[node] = find(parent[node]);
//  }

//  void unionBySize(int node1, int node2)
//  {
//     int leader1 = find(node1);
//     int leader2 = find(node2);

//     if(leader1 == leader2) return;

//     int s1 = size[leader1];
//     int s2 = size[leader2];

//     if(s1 >= s2)
//     {
//       parent[leader2] = leader1;
//       size[leader1] += size[leader2];
//     }
//     else if(s1 < s2)
//     {
//       parent[leader1] = leader2;
//       size[leader2] += size[leader1];
//     }
//  }



// }
// class Solution {
//     public int largestIsland(int[][] grid) {
        
//         int n = grid.length;

//         DisjointSet ds = new DisjointSet(n*n);

//         int[][] directions = {{0,1},{1,0}};

//         for(int r=0;r<n;r++)
//         {
//             for(int c=0;c<n;c++)
//             {
//                 if(grid[r][c] == 1)
//                 {
//                     int node1 = r*n + c;

//                     for(int[] dir : directions)
//                     {
//                         int nr = r + dir[0];
//                         int nc = c + dir[1];

//                         if(nr>=0 && nr<=n-1 && nc>=0 && nc<=n-1 && grid[nr][nc] == 1)
//                         {
//                             int node2 = nr*n + nc;
//                             ds.unionBySize(node1,node2);
//                         } 
//                     }
 
//                 }
//             }
//         }


//         int[][] fullDirections = {{-1,0},{1,0},{0,-1},{0,1}};
//           // HashSet to store unique neighboring island leaders
//         HashSet<Integer> leaders = new HashSet<>();
//         for(int r=0;r<n;r++)
//         {
//             for(int c=0;c<n;c++)
//             {
//                 if(grid[r][c] == 0)
//                 {
//                     for(int[] dir : fullDirections)
//                     {
//                         int nr = r + dir[0];
//                         int nc = c + dir[1];

//                         if(nr>=0 && nr<=n-1 && nc>=0 && nc<=n-1 && grid[nr][nc] == 1)
//                         {
//                              int nbr_node = nr*n + nc;
//                              leaders.add(ds.find(nbr_node));
//                         }
//                     }
                    
//                     int mergedSize = 0;
//                     for(int leader : leaders)
//                     {
//                         mergedSize = mergedSize + ds.size[leader]; 
//                     }
//                     mergedSize++;  // +1 because current 0 becomes land
//                      maxSizeIsland = Math.max(maxSizeIsland,mergedSize);
                           
//                         // clear set for next iteration
//                         leaders.clear();

//                     }
//                 }
//             }
//       // If maxSizeIsland is still 0, it means there were no 0s in the grid
//     // (i.e., the grid was already full of 1s), so the largest island is the whole grid → n*n
//      // Otherwise, return the maximum island size we calculated after flipping a 0
//              return (maxSizeIsland == 0) ? n*n : maxSizeIsland ;
//     }
// }










