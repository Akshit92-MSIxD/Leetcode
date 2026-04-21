class Solution {

   boolean checkCycleBFS(List<List<Integer>> graph, int n)
   {
        int[] indegree = new int[n];
        Queue<Integer> q = new ArrayDeque<>();

        for(int u=0;u<n;u++)
        {
            for(int v : graph.get(u))
                indegree[v]++;
        }

        for(int node=0;node<n;node++)
        {
            if(indegree[node] == 0)
            q.add(node);
        }
        
        int length_topoOrder = 0;

        while(!q.isEmpty())
        { 
            int curr_node = q.poll();
            length_topoOrder++;

            for(int nbr_node : graph.get(curr_node))
            {
                   indegree[nbr_node]--;

                   if(indegree[nbr_node] == 0)
                   q.add(nbr_node);
            }

        }

       return length_topoOrder == n;

   }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
         
             // first create the adjacency list

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<numCourses;i++)
        graph.add(new ArrayList<>());

        for(int i=0;i<prerequisites.length;i++)
        {
            int u = prerequisites[i][1];  // dependency_node
            int v = prerequisites[i][0];  // dependent_node

            graph.get(u).add(v);
        }

        return checkCycleBFS(graph,numCourses);
    }
}