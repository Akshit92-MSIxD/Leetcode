// I have written both DFS and BFS approaches below !!!



// Approach 1 : Using BFS (Kahn's Algorthm to detect cycle in Directed Graph)
// TC = SC = O(V+E)

class Solution {

   boolean checkCycleBFS(List<List<Integer>> graph, int numCourses)
   {
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new ArrayDeque<>();

        for(int u=0;u<numCourses;u++)
        {
            for(int v : graph.get(u))
                indegree[v]++;
        }

        for(int course=0;course<numCourses;course++)
        {
            if(indegree[course] == 0)
            q.add(course);
        }
        
        int visCourses = 0;

        while(!q.isEmpty())
        { 
            int dependency_course = q.poll();
            visCourses++;

            for(int dependent_course : graph.get(dependency_course))
            {
                   indegree[dependent_course]--;

                   if(indegree[dependent_course] == 0)
                   q.add(dependent_course);
            }

        }

       return visCourses != numCourses;  // if true -> cycle exits !!!

   }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
         
             // first create the adjacency list

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<numCourses;i++)
        graph.add(new ArrayList<>());

        for(int i=0;i<prerequisites.length;i++)
        {
            int u = prerequisites[i][1];  // dependency_course
            int v = prerequisites[i][0];  // dependent_course

            graph.get(u).add(v);
        }

        return !checkCycleBFS(graph,numCourses);   // if cycle exists -> return false, otherwise return true
    }
}




/*--------------------------------------------------------------------------------------------------------*/




// Approach 2 : Using DFS(to detect cycle in directed graph !!!)
// TC = SC = O(V+E)

// class Solution {

//    boolean checkCycleDFS(List<List<Integer>> graph, int curr_node, boolean[] vis, boolean[] pathVis)
//    {
//         vis[curr_node] = true;
//         pathVis[curr_node] = true;

//         // explore the dependents

//         for(int nbr_node : graph.get(curr_node))
//         {
//             if(!vis[nbr_node])
//             {
//                 if(checkCycleDFS(graph,nbr_node,vis,pathVis))
//                 return true;
//             }
//             else
//             {
//                   if(pathVis[nbr_node])
//                   return true;
//             }
//         }

//         pathVis[curr_node] = false;
//         return false;
        
//    }
//     public boolean canFinish(int numCourses, int[][] prerequisites) {
         
//              // first create the adjacency list

//         List<List<Integer>> graph = new ArrayList<>();

//         for(int i=0;i<numCourses;i++)
//         graph.add(new ArrayList<>());

//         for(int i=0;i<prerequisites.length;i++)
//         {
//             int u = prerequisites[i][1];  // dependency_course
//             int v = prerequisites[i][0];  // dependent_course

//             graph.get(u).add(v);
//         }

//         boolean[] vis = new boolean[numCourses];
//         boolean[] pathVis = new boolean[numCourses];



//         for(int course=0;course<numCourses;course++)
//         {
//             if(!vis[course])
//             {
//             if(checkCycleDFS(graph,course,vis,pathVis) == true)return false;
//             }
//         }

//         return true;
  
//     }
// }