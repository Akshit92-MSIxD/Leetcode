// I have written three approaches for this problem below . Please read all the three approaches since all of them are important !!!!



// Approach 1 : Using DFS (visState concept !!!)
class Solution {
    
    boolean dfs(int[][] graph, int curr_node,int[] visState, boolean[] pathVis)
    {
          visState[curr_node] = 1;
          pathVis[curr_node] = true;

          // explore the neighbours

          boolean ans = true;

          for(int nbr_node : graph[curr_node])
          {
              if(visState[nbr_node] != 0)
              {
                  if(visState[nbr_node] == 2)
                  {
                    visState[curr_node] = 2;
                    ans = false;
                  }
                  else
                  {
                     if(pathVis[nbr_node])
                     {
                        visState[curr_node] = 2;
                        ans = false;
                     }
                  }
              }
              else
              {
                 if(dfs(graph,nbr_node,visState,pathVis) == false)
                 {
                    visState[curr_node] = 2;
                    ans = false;
                 }
              }
          }

          pathVis[curr_node] = false;

          return ans;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
          
          int n = graph.length;

          int[] visState = new int[n];  // 0 -> not vis yet , 1 -> safe node , 2 ->  unsafe node

          for(int i=0;i<n;i++)
          visState[i] = 0;  

          boolean[] pathVis = new boolean[n];


          for(int node=0;node<n;node++)
          {
               if(visState[node] == 0)
                dfs(graph,node,visState,pathVis);
          }

          List<Integer> ans = new ArrayList<>();

          for(int node=0;node<n;node++)
          {
            if(visState[node] == 1)
            ans.add(node);
          }

          return ans;
 
    }
}




/*--------------------------------------------------------------------------------------------------------------*/


// // Approach 2 : Using DFS (CodewithMik's Approach)

// class Solution {
    
//     boolean dfs(int[][] graph, int curr_node,boolean[] vis, boolean[] pathVis)
//     {
//           vis[curr_node] = true;
//           pathVis[curr_node] = true;

//           // explore the neighbours

//           for(int nbr_node : graph[curr_node])
//           {
//               if(vis[nbr_node])
//               {
//                 if(pathVis[nbr_node])
//                     return true; // when we directly return from the node its pathVis[] will always remain true !!!
//               }                  // that true in pathVis[] is a indicator that this node is part of cycle or connected
//               else               // to a cycle directly or indirectly --> its a unsafe node  !!!
//               {
//                 if(dfs(graph,nbr_node,vis,pathVis) == true)
//                 return true;
//               }
//           }

         

//           pathVis[curr_node] = false;  // only nodes those are safe will get a chance to execute this step !!!

//           return false;
//     }

//     public List<Integer> eventualSafeNodes(int[][] graph) {
          
//           int n = graph.length;

//           boolean[] vis = new boolean[n];

//           boolean[] pathVis = new boolean[n];


//           for(int node=0;node<n;node++)
//           {
//                if(!vis[node])
//                 dfs(graph,node,vis,pathVis);
//           }

//           List<Integer> ans = new ArrayList<>();

//           for(int node=0;node<n;node++)
//           {
//             if(pathVis[node] == false)
//             ans.add(node);
//           }

//           return ans;
 
//     }
// }


/*----------------------------------------------------------------------------------------------------------------*/




// // Approach 3 : BFS (Using Reverse Array + Kahns Algorithm) (CodewithMik's Approach)


//     // ===============================================================
//     // 🔥 EVENTUAL SAFE NODES (KAHN'S ALGO + REVERSED GRAPH)
//     // ===============================================================
    
    
//     // 🧠 PROBLEM INTUITION (VERY SIMPLE)
//     // ---------------------------------------------------------------
//     // A node is SAFE if:
//     // → it does NOT lead to a cycle
//     //
//     // A node is UNSAFE if:
//     // → it is part of a cycle OR
//     // → it can reach a cycle
    
    
//     // 🧠 KEY IDEA
//     // ---------------------------------------------------------------
//     // Instead of finding unsafe nodes directly (hard),
//     // we find SAFE nodes (easy)
    
    
//     // 🧠 WHY REVERSE THE GRAPH?
//     // ---------------------------------------------------------------
//     // Original graph:
//     // u → v means u can go to v
//     //
//     // In problem:
//     // terminal nodes (nodes with NO outgoing edges) are SAFE
//     //
//     // BUT Kahn’s algo works on:
//     // nodes with indegree = 0
//     //
//     // So we reverse the graph:
//     //
//     // Original: u → v
//     // Reverse : v → u
//     //
//     // Now:
//     // terminal nodes become nodes with indegree = 0
//     //
//     // So we can start from them easily

//     // *** Summary of Why reverse the graph ?? -> To make the terminal nodes indegree to 0 and  traverse from
//     // those terminal nodes to the nodes that are not a part of a cycle or not connected to a cycle directly
//     // or indirectly in the original graph!!!

//     // TC = SC = O(V+E)
    
// class Solution {
//     // ===============================================================
//     // BFS FUNCTION (KAHN'S ALGORITHM)
//     // ===============================================================
    
//     List<Integer> bfs(List<List<Integer>> graph)
//     {
//         int n = graph.size();

//         int[] indegree = new int[n]; // number of dependencies

//         Queue<Integer> q = new ArrayDeque<>();


//         // -----------------------------------------------------------
//         // STEP 1: CALCULATE INDEGREE
//         // -----------------------------------------------------------
//         for(List<Integer> nodes : graph)
//         {
//             for(int node : nodes)
//                 indegree[node]++;
//         }


//         // -----------------------------------------------------------
//         // STEP 2: ADD NODES WITH NO DEPENDENCIES
//         // -----------------------------------------------------------
//         for(int node = 0; node < n; node++)
//         {
//             if(indegree[node] == 0)
//             {
//                 // these are terminal nodes (SAFE)
//                 q.add(node);
//             }
//         }


//         // stores whether a node is safe
//         boolean[] is_safe = new boolean[n];


//         // -----------------------------------------------------------
//         // STEP 3: PROCESS NODES
//         // -----------------------------------------------------------
//         while(!q.isEmpty())
//         {
//             int curr_node = q.poll();

//             // mark as safe
//             is_safe[curr_node] = true;

//             // process nodes that depend on this node
//             for(int nbr_node : graph.get(curr_node))
//             {
//                 // reduce dependency
//                 indegree[nbr_node]--;

//                 // if all dependencies removed → safe
//                 if(indegree[nbr_node] == 0)
//                     q.add(nbr_node);
//             }
//         }


//         // -----------------------------------------------------------
//         // STEP 4: COLLECT SAFE NODES
//         // -----------------------------------------------------------
//         List<Integer> safe_nodes = new ArrayList<>();

//         for(int node = 0; node < n; node++)
//         {
//             if(is_safe[node])
//                 safe_nodes.add(node);
//         }
      
//         return safe_nodes;
//     }


//     public List<Integer> eventualSafeNodes(int[][] graph) {
          
//         // ==========================================================
//         // STEP 0: REVERSE THE GRAPH
//         // ----------------------------------------------------------
//         // Original: u → v
//         // Reverse : v → u
        
//         int n = graph.length;

//         List<List<Integer>> rev_graph = new ArrayList<>();

//         for(int i = 0; i < n; i++)
//             rev_graph.add(new ArrayList<>());


//         for(int u = 0; u < n; u++)
//         {
//             for(int v : graph[u])
//             {
//                 // reverse edge
//                 rev_graph.get(v).add(u);
//             }
//         }


//         // run Kahn's algorithm on reversed graph
//         return bfs(rev_graph);
//     }
// }