
// 🔥 Main Idea / Intuition: (Written By Me :)
//
// - Each stone is treated as a node
// - If two stones share same row OR same column → they are connected
// - We use DSU to group such stones into connected components
//
// 💡 Key Observation:
// - In each connected component of size x,
//   we can remove at most (x - 1) stones
//   (because at least 1 stone must remain in each component)
//
// 👉 So final answer = sum of (size - 1) for all components
//
// ------------------------------------------------------------
//
// ⏱ Time Complexity (TC):
// - Loop through stones: O(n)
// - DSU operations: O(α(n)) ~ constant
// - Final loop: O(n)
//
// → Overall: O(n)
//
// 🧠 Space Complexity (SC):
// - DSU arrays: O(n)
// - HashMaps (row + col): O(n)
//
// → Overall: O(n)
//
// ------------------------------------------------------------


class DisjointSet{
 
 int n;
 int[] parent; // stores parent of each node
 int[] size;   // stores size of each component (only valid for leaders)

 DisjointSet(int n){
    this.n = n;
    parent = new int[n];
    size = new int[n];

    // initially every node is its own parent (separate component)
    for(int i=0;i<n;i++)
    {
        size[i] = 1;
        parent[i] = i;
    }
 }
 
 // find → returns leader (ultimate parent) of node
 int find(int node)
 {
    if(node == parent[node])
        return node;

    // path compression → directly connect to leader
    return parent[node] = find(parent[node]);
 }

 // union by size → connect two components
 void unionBySize(int node1, int node2)
 {
     int leader1 = find(node1);
     int leader2 = find(node2);

     // if already in same component → do nothing
     if(leader1 == leader2) return;

     int s1 = size[leader1];
     int s2 = size[leader2];

     // attach smaller component under bigger one
     if(s1 >= s2)
     {
       parent[leader2] = leader1;
       size[leader1] += size[leader2];
     }
     else
     {
        parent[leader1] = leader2;
        size[leader2] +=  size[leader1];
     }
     
 }
}


class Solution {
    public int removeStones(int[][] stones) {
         
         // maps to track first occurrence of each row and column
         HashMap<Integer,Integer> row = new HashMap<>();
         HashMap<Integer,Integer> col = new HashMap<>();

         int n = stones.length;
         
         // DSU for all stones (each stone = one node)
         DisjointSet ds = new DisjointSet(n);

         // ------------------------------------------------------------
         // Step 1: Connect stones sharing same row or column
         // ------------------------------------------------------------
         for(int node=0;node<n;node++)
         {
            int r = stones[node][0];
            int c = stones[node][1];

            // if row not seen → store this stone as representative
            if(!row.containsKey(r))
               row.put(r,node);
            else
               // union with previous stone in same row
               ds.unionBySize(node,row.get(r));


            // if column not seen → store this stone as representative
            if(!col.containsKey(c))
               col.put(c,node);
            else
               // union with previous stone in same column
               ds.unionBySize(node,col.get(c));

         }

         int count_removedStones = 0;

         // ------------------------------------------------------------
         // Step 2: Count removable stones
         // ------------------------------------------------------------
         for(int node=0;node<n;node++)
         {
             // if node is leader → it represents a component
             if(node == ds.parent[node])
             
             // from a component of size x → we can remove (x - 1) stones
              count_removedStones += ds.size[node] - 1;
         }

         // return total removable stones
         return count_removedStones;
    }
}