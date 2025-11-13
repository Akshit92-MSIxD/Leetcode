
// Approach 1 : Using DFS

// Time (worst): O(V + E) 
// Space (extra): O(V + E) (including adjacency lists and visited array)
// Recursion stack space : up to O(V or n)

class Solution {

    // using normal DFS --> TC = O(n) since each vertex is visited only once !!!! , Rescurive Stack Space = O(n)
    boolean has_path(ArrayList<Integer>[] graph, int src, int des, boolean[] visited)
    {
          if(src == des) return true;
          if(visited[src]) return false;

          visited[src] = true;

          for(int nbr_v : graph[src])
          {  
             boolean res = has_path(graph,nbr_v,des,visited);
             if(res==true)return true;
          }

          return false;
    }
    

    public boolean validPath(int n, int[][] edges, int source, int desination) {
        
        // first create adjacency list --> TC = O(n)

        ArrayList<Integer>[] graph = new ArrayList[n]; 

        // initialize each block of graph array

        for(int i=0;i<n;i++)
         graph[i] = new ArrayList<>();
        
        // traversing over all the edges one by one --> O(E) where E = no. of edges !!!
        for(int i=0;i<edges.length;i++)
        {
               int v1 = edges[i][0];
               int v2 = edges[i][1];

               graph[v1].add(v2);
               graph[v2].add(v1);
        }

        // now check if path exists or not

        boolean[] visited = new boolean[n]; // bydefault all values are false in visited array in java !!!!  

        return has_path(graph,source,desination,visited);
    }
}