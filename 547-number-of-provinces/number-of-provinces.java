// Note : Basic DFS and BFS approach left (just find the number of connected components !!!)






// Approach : DFS

class Solution {

      void dfs(int[][] isConnected,int n,int curr_node, boolean[] vis)
      {
        vis[curr_node] = true;

        //explore its neighbours

        for(int nbr_node = 0;nbr_node<n;nbr_node++)
        {
              if(nbr_node != curr_node && isConnected[curr_node][nbr_node] == 1)
              {
                if(!vis[nbr_node])
                dfs(isConnected,n,nbr_node,vis);
              }
        }

      }

    public int findCircleNum(int[][] isConnected) {

          int n = isConnected.length;

          boolean[] vis = new boolean[n];

          int count_pv = 0;

          for(int i=0;i<n;i++)
          {
             if(!vis[i])
             {
             dfs(isConnected,n,i,vis);
             count_pv++;
             }
          }

          return count_pv;
    }
}
















// // Approach : DSU (Written by Me !!!)
// // TC: O(n^2 * α(n)) → we check all pairs + DSU operations are almost constant
// // SC: O(n) → parent[] and rank[] arrays

// class DisjointSet{

//   int n;
//   int rank[];
//   int parent[];

//    DisjointSet(int n)
//    {
//     this.n = n;
//     this.rank = new int[n];
//     this.parent = new int[n];

//     for(int i=0;i<n;i++)
//     {
//         rank[i] = 0;
//         parent[i] = i;
//     }
//    }

//    int find(int v)
//    {
//        if(v == parent[v])
//         return v;

//         return  parent[v] = find(parent[v]);
//    }

//    void unionByRank(int v1,int v2)
//    {
//        int leader1 = find(v1);
//        int leader2 = find(v2);

//        if(rank[leader1] > rank[leader2])
//        {
//            parent[leader2] = leader1;
//        }
//        else if(rank[leader1] < rank[leader2])
//        {
//            parent[leader1] = leader2;
//        }
//        else
//        {
//             parent[leader2] = leader1;
//             rank[leader1]++;
//        }
//    }
// }

// class Solution {
//     public int findCircleNum(int[][] isConnected) {

//           int n = isConnected.length;

//           DisjointSet ds = new DisjointSet(n);

//           int countPv = n; // consider in the beginning all cities are separated --> n provinces  !!!

//           for(int u=0;u<n;u++)
//           {
//              for(int v=0;v<n;v++)
//              {  
//                   // if:
//                 // 1. u and v are different
//                 // 2. they are directly connected
//                 // 3. they are not already in same group
//                 if( u != v && isConnected[u][v] == 1 && ds.find(u) != ds.find(v))
//                 {
//                     ds.unionByRank(u,v);
//                     countPv--;  // since two groups merged → total provinces reduce by 1
//                 }
//              }
//           }


//           return countPv;


//     }
// }


// class DisjointSet{

//   int n;
//   int rank[];
//   int parent[];

//    DisjointSet(int n)
//    {
//     this.n = n;
//     this.rank = new int[n];
//     this.parent = new int[n];

//     for(int i=0;i<n;i++)
//     {
//         rank[i] = 0;
//         parent[i] = i;
//     }
//    }

//    int find(int v)
//    {
//        if(v == parent[v])
//         return v;

//         return  parent[v] = find(parent[v]);
//    }

//    void unionByRank(int v1,int v2)
//    {
//        int leader1 = find(v1);
//        int leader2 = find(v2);

//        if(rank[leader1] > rank[leader2])
//        {
//            parent[leader2] = leader1;
//        }
//        else if(rank[leader1] < rank[leader2])
//        {
//            parent[leader1] = leader2;
//        }
//        else
//        {
//             parent[leader2] = leader1;
//             rank[leader1]++;
//        }
//    }
// }

// class Solution {
//     public int findCircleNum(int[][] isConnected) {

//           int n = isConnected.length;

//           DisjointSet ds = new DisjointSet(n);


//           for(int u=0;u<n;u++)
//           {
//              for(int v=0;v<n;v++)
//              {  
//                 if( u != v && isConnected[u][v] == 1 && ds.find(u) != ds.find(v))
//                     ds.unionByRank(u,v);
//              }
//           }

//           int countPv = 0;

//           for(int i=0;i<n;i++)
        //    {
        //       if(ds.parent[i] == i)
        //       countPv++;
        //    }

//           return countPv;


//     }
// }