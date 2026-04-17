
// I have written two approaches for this problem below and both approaches are identical but the way to perceiving them is different !!!

// Approach 1 : A graph is bipartite if we can divide its nodes into two groups such that no two adjacent nodes belong to the same group.

// Approach 2 : A graph is bipartite if we can color all nodes using two colors such that no two adjacent nodes have the same color.

/* A graph is bipartite if 
   
   - it has even cycle length
   - linear (no cycle)
   - if its a tree
   - if its all components are bipartite

*/



// Approach 2 : Using BFS(space optmized than Approach 1)
// Note : A graph is bipartite if we can color all nodes using two colors such that no two adjacent nodes have the same color.

class Solution {

    boolean bfs(int[][] graph, int curr_node, int[] color)
    {
             int n = graph.length;

             Queue<Integer> q = new ArrayDeque<>();

             q.add(curr_node);
             color[curr_node] = 1;

             while(!q.isEmpty())
             {
                curr_node = q.poll();

                // explore its neighbours

                for(int nbr_node : graph[curr_node])
                {
                    if(color[nbr_node] == -1)
                    { 
                       q.add(nbr_node);
                       color[nbr_node] = (color[curr_node] == 1) ? 0 : 1;
                    }
                    else
                    {
                       if(color[nbr_node] == color[curr_node])
                       return false;
                    }
                }
             }


             return true;

             
    }

    public boolean isBipartite(int[][] graph) {
       
     int n = graph.length;

     int[] color = new int[n];   // default value is -1 mean the node is yet to be coloured, 1 and 0 represent two colors

     for(int i=0;i<n;i++)
     color[i] = -1;
     
     for(int node=0;node<n;node++)
     {
          if(color[node] == -1)
          {
             if(bfs(graph,node,color) == false)
             return false;
          }
     }

     return true;

    }
}




/*------------------------------------------------------------------------------------------------------------------------*/




// // Approach 3 : Using DFS 

// class Solution {

//     boolean dfs(int[][] graph, int curr_node, int[] color)
//     {
        
//         // explore the neighbours

//         for(int nbr_node : graph[curr_node])
//         {
//             if(color[nbr_node] != -1)
//             {
//                  if(color[nbr_node] == color[curr_node])
//                  return false;
//             }
//             else
//             {
//                color[nbr_node] = (color[curr_node] == 1) ? 0 : 1;
                
//                 if(dfs(graph,nbr_node,color) == false)
//                 return false;
//             }
//         }

//         return true;

//     }

    
//     public boolean isBipartite(int[][] graph) {
       
//      int n = graph.length;

//      int[] color = new int[n];   // default value is -1 mean the node is yet to be coloured, 1 and 0 represent two colors

//      for(int i=0;i<n;i++)
//      color[i] = -1;
     
//      for(int node=0;node<n;node++)
//      {
//           if(color[node] == -1)
//           {
//              color[node] = 1;
//              if(dfs(graph,node,color) == false)
//              return false;
//           }
//      }

//      return true;

//     }
// }




/*---------------------------------------------------------------------------------------------------------------------------*/




// Approach1 : Using BFS
// A graph is bipartite if we can divide its nodes into two groups such that no two adjacent nodes belong to the same group.

// class Solution {

//     boolean bfs(int[][] graph,int start_v, boolean[] vis, int[] set)
//     {
//           Queue<Integer> q = new ArrayDeque<>();

//           q.add(start_v);
//           set[start_v] = 1;

//           while(!q.isEmpty())
//           {
//             int curr_v = q.poll(); // it will remove and return the front node of queue
//             vis[curr_v] = true;

//             // traverse over its neighbours

//             for(int nbr_v : graph[curr_v])
//             {
//                 // Add unvisited neighbours in the queue and mark their set
//                 if(!vis[nbr_v])
//                 {
//                     q.add(nbr_v);
                    
//                     if(set[curr_v] == 1)
//                     {
//                         set[nbr_v] = 2;
//                     }
//                     else
//                     {
//                         set[nbr_v] = 1;
//                     }
//                 }
//                 else  // Check if the immediate visited neighbours of curr_v have different set or not !!!
//                 {
//                      if(set[curr_v] == set[nbr_v])
//                      return false;
//                 }
//             }

//           }
//             return true;
//     }

//     public boolean isBipartite(int[][] graph) {

//       boolean[] vis = new boolean[graph.length];

//       int[] set = new int[graph.length]; // It will tell about the set of a a particular node

//     for(int i=0;i<graph.length;i++)
//     {   
//         if(!vis[i])
//         {
//         if(bfs(graph,i,vis,set) == false) return false;
//         }
//     }

//     return true;

//     }
// }


