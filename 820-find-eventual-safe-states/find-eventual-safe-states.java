// Please write proper comments and watch video solution for this problem !!!


// class Solution {
    
//     boolean dfs(int[][] graph, int curr_node,int[] visState, boolean[] pathVis)
//     {
//           visState[curr_node] = 1;
//           pathVis[curr_node] = true;

//           // explore the neighbours

//           boolean ans = true;

//           for(int nbr_node : graph[curr_node])
//           {
//               if(visState[nbr_node] != 0)
//               {
//                   if(visState[nbr_node] == 2)
//                   {
//                     visState[curr_node] = 2;
//                     ans = false;
//                   }
//                   else
//                   {
//                      if(pathVis[nbr_node])
//                      {
//                         visState[curr_node] = 2;
//                         ans = false;
//                      }
//                   }
//               }
//               else
//               {
//                  if(dfs(graph,nbr_node,visState,pathVis) == false)
//                  {
//                     visState[curr_node] = 2;
//                     ans = false;
//                  }
//               }
//           }

//           pathVis[curr_node] = false;

//           return ans;
//     }

//     public List<Integer> eventualSafeNodes(int[][] graph) {
          
//           int n = graph.length;

//           int[] visState = new int[n];

//           for(int i=0;i<n;i++)
//           visState[i] = 0;

//           boolean[] pathVis = new boolean[n];


//           for(int node=0;node<n;node++)
//           {
//                if(visState[node] == 0)
//                 dfs(graph,node,visState,pathVis);
//           }

//           List<Integer> ans = new ArrayList<>();

//           for(int node=0;node<n;node++)
//           {
//             if(visState[node] == 1)
//             ans.add(node);
//           }

//           return ans;
 
//     }
// }


class Solution {
    
    boolean dfs(int[][] graph, int curr_node,boolean[] vis, boolean[] pathVis)
    {
          vis[curr_node] = true;
          pathVis[curr_node] = true;

          // explore the neighbours

          for(int nbr_node : graph[curr_node])
          {
              if(vis[nbr_node])
              {
                if(pathVis[nbr_node])
                    return true;
              }
              else
              {
                if(dfs(graph,nbr_node,vis,pathVis) == true)
                return true;
              }
          }

         

          pathVis[curr_node] = false;

          return false;
    }

    public List<Integer> eventualSafeNodes(int[][] graph) {
          
          int n = graph.length;

          boolean[] vis = new boolean[n];

          boolean[] pathVis = new boolean[n];


          for(int node=0;node<n;node++)
          {
               if(!vis[node])
                dfs(graph,node,vis,pathVis);
          }

          List<Integer> ans = new ArrayList<>();

          for(int node=0;node<n;node++)
          {
            if(pathVis[node] == false)
            ans.add(node);
          }

          return ans;
 
    }
}