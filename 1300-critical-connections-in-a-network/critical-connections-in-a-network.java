class Solution {

    /*
    ===================== MAIN IDEA (VERY IMPORTANT) =====================

    We are using Tarjan's Algorithm to find BRIDGES in a graph.

    What is a bridge?
    → An edge is a bridge if removing it disconnects the graph or breaks the graphs into two components !!!.

    Core intuition (in simple words):
    → While doing DFS, we try to see:
      "Can a node (or its subtree) reach an ancestor WITHOUT using the current edge/parent edge?"

    If NOT, then that edge is a BRIDGE.

    We use two arrays:
    1. tin[] = insertion time when node was first visited
    2. low[] = lowest insertion-time node reachable from that node (including back edges)

    Key condition:
    → If low[child] > tin[parent], then edge (parent → child) is a BRIDGE
    */

    /*
    ===================== TIME COMPLEXITY =====================

    O(V + E)

    Explanation:
    - Each node is visited once → O(V)
    - Each edge is processed once → O(E)

    Total = O(V + E)


    ===================== SPACE COMPLEXITY =====================

    O(V + E)

    - Adjacency list → O(V + E)
    - tin[], low[], vis[] → O(V)
    - recursion stack → O(V) (in worst case)

    Total = O(V + E)


    ===================== FINAL INTUITION (ONE LINE) =====================

    "If a child or child subtree  cannot reach any ancestor of its parent,
     then the edge between them is a bridge."
    */

    void dfs(List<List<Integer>> graph, int curr_node, int parent_node, int ins_time,
             int[] tin, int[] low, boolean[] vis, List<List<Integer>> bridges)
    {
        // mark current node as visited
        vis[curr_node] = true;

        // assign insertion time
        tin[curr_node] = ins_time;

        // initially, lowest reachable time is its own time
        low[curr_node] = ins_time;

        // explore all neighbors
        for(int nbr_node : graph.get(curr_node))
        {
            // skip the parent (same edge in reverse direction)
            if(nbr_node == parent_node) continue;

            // ================= TREE EDGE =================
            // if neighbor is not visited → go deeper (DFS)
            if(!vis[nbr_node])
            {
                dfs(graph, nbr_node, curr_node, ins_time + 1,
                    tin, low, vis, bridges);

                // after returning, update low value
                low[curr_node] = Math.min(low[curr_node], low[nbr_node]);

                /*
                BRIDGE CONDITION:

                If child cannot reach current node or any ancestor,
                then this edge is a bridge.

                Meaning:
                low[child] > tin[parent]
                */
                if(low[nbr_node] > tin[curr_node])
                {
                    bridges.add(new ArrayList<>(List.of(curr_node, nbr_node)));
                }
            }
            // ================= BACK EDGE =================
            else
            {
                /*
                back edge means:
                we found a way to reach an already visited node (ancestor)

                so we update low value using tin[nbr]
                */
                low[curr_node] = Math.min(low[curr_node], low[nbr_node]);
                // NOTE:
                // Ideally this should be tin[nbr_node], but your version still works
                // because low[] already carries correct propagated values
            }
        }
    }


    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        /*
        STEP 1: Create adjacency list (graph representation)
        */
        List<List<Integer>> graph = new ArrayList<>();

        for(int i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        // add edges (undirected graph)
        for(int i = 0; i < connections.size(); i++)
        {
            int u = connections.get(i).get(0);
            int v = connections.get(i).get(1);

            graph.get(u).add(v);
            graph.get(v).add(u);
        }


        /*
        STEP 2: Initialize required arrays
        */

        int[] tin = new int[n];   // discovery time
        int[] low = new int[n];   // lowest reachable time
        boolean[] vis = new boolean[n]; // visited array

        List<List<Integer>> bridges = new ArrayList<>();


        /*
        STEP 3: Run DFS for all components
        */
        for(int node = 0; node < n; node++)
        {
            if(!vis[node])
            {
                dfs(graph, node, -1, 0, tin, low, vis, bridges);
            }
        }

        return bridges;
    }

}






