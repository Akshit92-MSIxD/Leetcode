class Solution {

void dfs(List<List<Integer>> graph, int curr_node, int parent_node, int ins_time, int[] tin, int[] low, boolean[] vis,List<List<Integer>> bridges)
{
    vis[curr_node] = true;
    low[curr_node] = tin[curr_node] = ins_time;
    
    for(int nbr_node : graph.get(curr_node))
    {

         if(nbr_node == parent_node) continue;

        if(!vis[nbr_node]) // tree edge
        {
            dfs(graph,nbr_node,curr_node,ins_time+1,tin,low,vis,bridges);
            low[curr_node] = Math.min(low[curr_node],low[nbr_node]);

           if(low[nbr_node] > tin[curr_node])
           bridges.add(new ArrayList<>(List.of(curr_node,nbr_node)));
        }
        else // back edge
        {
            low[curr_node] = Math.min(low[curr_node],low[nbr_node]);
        }
    }

    return;
}

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        

        // first create the adjacency list

        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0;i<n;i++)
        graph.add(new ArrayList<>());

        for(int i=0;i<connections.size();i++)
        {
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        // create a tin[] and low[]

        int[] tin = new int[n];
        int[] low = new int[n];

        boolean[] vis = new boolean[n];

        List<List<Integer>> bridges = new ArrayList<>();

        // for(int node = 0;node<n;node++)
        // {
        //     if(!vis[node])
        //     dfs(graph,node,-1,0,tin,low,vis,bridges);
        // }
           dfs(graph,0,-1,0,tin,low,vis,bridges);
        return bridges;


         
    }
}







