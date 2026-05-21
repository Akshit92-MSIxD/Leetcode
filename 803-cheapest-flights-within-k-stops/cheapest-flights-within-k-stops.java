// *** NOTE : Please explore all the approaches below !!!
// *** NOTE : Found difficulty in understanding the TC and SC for the approaches below !!!
// *** NOTE : When you revisit this problem try to find and understand the Time and Space Complexity for all the three approaches below !!! :)

// *** IMP CONCEPT : We could have used Topo Sort here but this graph is not DAG -->  it is DCG !!!
//                   so only option we have left is Dijkstra's Algo !!!


// Approach 3 : Using Normal BFS (Priority is given to smaller stops first not smaller paths !!!)
// Main Idea:
// In this approach we use a normal Queue instead of a Priority Queue.
//
// Why BFS works here:
// The problem limits the number of stops to at most k.
// BFS naturally explores paths in increasing order of stops:
//
// level 0 → 0 stops
// level 1 → 1 stop
// level 2 → 2 stops
// ...
//
// So using a normal queue automatically ensures that
// smaller stop paths are processed before larger stop paths.
// Because of this, we do not need a min heap based on stops.

class Solution {

    class Node{
        int v;
        int price;  // total cost required to reach this node from source node
        int stopsSoFar;  // how many stops we have used to reach this node from source node

        Node(int v, int stopsSoFar , int price)
        {
            this.v = v;
            this.stopsSoFar = stopsSoFar;
            this.price = price;
        }
    }

    int getMinimumPrice(ArrayList<ArrayList<int[]>> graph, int src, int dest, int k)
    {
   
         Queue<Node> pq = new ArrayDeque<>();

         // Start from source node
        // stops = -1 because the first move will make it 0
         pq.add(new Node(src,-1,0));
         
    // minPrice[i] stores the minimum price found so far to reach node i from source node within k stops !!!
        int[] minPrice = new int[graph.size()];
        
        for(int i=0;i<minPrice.length;i++)
          minPrice[i] = Integer.MAX_VALUE;
         
         // Cost to reach source is 0
         minPrice[src] = 0;

         while(!pq.isEmpty())
         {
             Node top = pq.poll(); // get node having smallest price
             int cV = top.v; // current vertex
             int cPrice = top.price;
             int cStopsSoFar = top.stopsSoFar; // stops used so far
              

            // If we already used k stops, we cannot continue further
             // because the problem allows at most k stops
            if(cStopsSoFar == k) continue;
             
             // Explore all neighbours of current node
             for(int[] nbr : graph.get(cV))
             {
                int nbrV = nbr[0];
                int nbrPrice = cPrice + nbr[1];// cost to reach nbr node from source node within k stops!!
                int nbrStopsSoFar = cStopsSoFar + 1; // increase stop count
                  
                     if(nbrPrice < minPrice[nbrV])
                     {
                     minPrice[nbrV] = nbrPrice;
                     pq.add(new Node(nbrV,nbrStopsSoFar,nbrPrice));
                     }

                    
                 }
             }

            // If destination was never reached, return -1
          // otherwise return the minimum price found 
         return (minPrice[dest] == Integer.MAX_VALUE) ? -1 : minPrice[dest] ;
         }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

         // create an adjacency list first
        ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

        for(int i=0;i<n;i++)
        {
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<flights.length;i++)
        {
             int v1 = flights[i][0];
             int v2 = flights[i][1];
             int price = flights[i][2];

             graph.get(v1).add(new int[]{v2,price});
        }

         int minPrice = getMinimumPrice(graph,src,dst,k);



         return minPrice;
    } 
    }




/*-------------------------------------------------------------------------------------------------*/




// Approach 2 : Using Dijkstra (Modified Version of Approach 1 — No TLE)

// Main Idea:
// This approach is an improvement over Approach 1.
// The main difference is that we now store both:
// 1) Minimum price
// 2) Minimum stops
// for each node.

// Why this works better:
// In Approach 1 we only stored the minimum price for each node.
// That caused us to insert too many useless states into the priority queue,
// which eventually caused TLE.

// In this improved approach we store two values for each node:
// minPrice[node][0] → minimum price found so far
// minPrice[node][1] → minimum stops used to reach that node

// Now we only push a new state into the PQ if:
// 1) We found a cheaper price
// OR
// 2) We found a path that uses fewer stops

// This helps us avoid inserting many useless states into the PQ,
// which keeps the algorithm efficient.

// class Solution {

//     // Node represents a state in the priority queue
//     // v → current node
//     // price → total price used to reach this node
//     // stopsSoFar → number of stops used to reach this node
//     class Node{
//         int v;
//         int price;
//         int stopsSoFar;

//         Node(int v, int price , int stopsSoFar)
//         {
//             this.v = v;
//             this.stopsSoFar = stopsSoFar;
//             this.price = price;
//         }
//     }

//     int getMinimumPrice(ArrayList<ArrayList<int[]>> graph, int src, int dest, int k)
//     {
//          // Priority Queue (Min Heap)
//          // Always process the node with the smallest price first
//          PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.price,b.price));

//          // Start from the source node
//          // stops = -1 because first flight will make stops = 0
//          pq.add(new Node(src,0,-1));

//         // For each node we store two things:
//         // index 0 → minimum price found so far
//         // index 1 → minimum stops used to reach this node
//         ArrayList<int[]> minPrice = new ArrayList<>();

//         for(int i=0;i<graph.size();i++)
//         {
//            minPrice.add(new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE});
//         }

//         // Initialize source node
//         minPrice.get(src)[0] = 0;
//         minPrice.get(src)[1] = -1;


//          while(!pq.isEmpty())
//          {
//              Node top = pq.poll(); // get node with smallest price
//              int cV = top.v; // current node
//              int cPrice = top.price; // price used to reach this node
//              int cStopsSoFar = top.stopsSoFar; // stops used so far


//             // If we already used k stops, we cannot continue further
//             if(cStopsSoFar == k) continue;

// // Redundant Code Line : if(cPrice > minPrice.get(cV)[0] && cStopsSoFar >  minPrice.get(cV)[1]) continue;

//              // If destination is reached, we stop the search
//              // because PQ guarantees this is the minimum price path
//              if(cV == dest) break;

//              // Explore all neighbours of current node
//              for(int[] nbr : graph.get(cV))
//              {
//                 int nbrV = nbr[0]; // neighbour node
//                 int nbrPrice = cPrice + nbr[1]; // new price to reach neighbour
//                 int nbrStopsSoFar = cStopsSoFar + 1; // increase stop count
                    
//                 // Case 1: Found a cheaper price to reach this neighbour
//                 if(nbrPrice < minPrice.get(nbrV)[0])
//                 {
//                     minPrice.get(nbrV)[0] = nbrPrice;
//                     minPrice.get(nbrV)[1] = nbrStopsSoFar;

//                     // push new state into PQ
//                     pq.add(new Node(nbrV,nbrPrice,nbrStopsSoFar));
//                 }
//                 // Case 2: price is not cheaper but we used fewer stops
//                 else if(nbrStopsSoFar < minPrice.get(nbrV)[1])
//                 {
//                     // push this state as it may lead to a better path later
//                     pq.add(new Node(nbrV,nbrPrice,nbrStopsSoFar));
//                 }
//              }
//          }

//         // If destination was never reached, return -1
//         // otherwise return the minimum price found
//         return (minPrice.get(dest)[0] == Integer.MAX_VALUE) ? -1 : minPrice.get(dest)[0] ;
//     }

//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

//         // Step 1 : Build adjacency list representation of graph
//         ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

//         for(int i=0;i<n;i++)
//         {
//             graph.add(new ArrayList<>());
//         }

//         // flights[i] = {from, to, price}
//         // create directed edges
//         for(int i=0;i<flights.length;i++)
//         {
//              int v1 = flights[i][0];
//              int v2 = flights[i][1];
//              int price = flights[i][2];

//              graph.get(v1).add(new int[]{v2,price});
//         }

//         // Run modified Dijkstra
//         int minPrice = getMinimumPrice(graph,src,dst,k);

//         return minPrice;
//     } 
// }





/*-----------------------------------------------------------------------------------------------------*/




// Approach 1 : Using Normal Dijkstra's Algorithm (But this approach gives TLE)

// Main Idea:
// We try to use Dijkstra's idea: always explore the path with the smallest cost first
// using a Min Heap (Priority Queue).

// Why this approach gives TLE:
// This problem has two conditions:
// 1) Minimum total price
// 2) Maximum number of stops (k)

// Dijkstra normally only cares about the minimum price.
// But in this problem the number of stops also matters.

// Example situation:
// Path A → cheaper price but uses many stops
// Path B → higher price but uses fewer stops

// Dijkstra will prefer Path A first because it is cheaper.
// But Path B might be the only path that can still reach the destination
// within the allowed number of stops.

// Because of this, we cannot skip paths just because they are more expensive.
// A more expensive path might still be useful if it uses fewer stops.

// Normally in Dijkstra we would prune worse states using:
// if(cPrice > minPrice[cV]) continue;
// But here we cannot safely do that because the number of stops also matters.

// Since we cannot prune those paths, we insert many states into the PQ.
// This causes the priority queue to grow very large and the algorithm
// explores too many paths, which leads to TLE.

// So this approach is logically reasonable but inefficient because
// it explores too many unnecessary states.


// class Solution {

//     class Node{
//         int v;
//         int price;  // total cost required to reach this node from source node
//         int stopsSoFar;  // how many stops we have used to reach this node from source node

//         Node(int v, int stopsSoFar , int price)
//         {
//             this.v = v;
//             this.stopsSoFar = stopsSoFar;
//             this.price = price;
//         }
//     }

//     int getMinimumPrice(ArrayList<ArrayList<int[]>> graph, int src, int dest, int k)
//     {
//          // Priority Queue (Min Heap)
//          // Nodes with smaller price are processed first (Dijkstra behaviour)
//          PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.price,b.price));

//          // Start from source node
//         // stops = -1 because the first move will make it 0
//          pq.add(new Node(src,-1,0));
         
//     // minPrice[i] stores the minimum price found so far to reach node i from source node with k stops !!!
//         int[] minPrice = new int[graph.size()];
        
//         for(int i=0;i<minPrice.length;i++)
//           minPrice[i] = Integer.MAX_VALUE;
         
//          // Cost to reach source is 0
//          minPrice[src] = 0;

//          while(!pq.isEmpty())
//          {
//              Node top = pq.poll(); // get node having smallest price
//              int cV = top.v; // current vertex
//              int cPrice = top.price;
//              int cStopsSoFar = top.stopsSoFar; // stops used so far
              
//             // Normally in Dijkstra we skip outdated states like this:
//              // (if(cPrice > minPrice[cV]) continue;)
//              // But here this pruning may remove paths that have slightly higher
//              // price but fewer stops, which might still lead to the correct answer.
//             //   if(cPrice > minPrice[cV]) continue;
              
//                // If we reached the destination, we stop the search
//               // (because PQ guarantees we got the smallest price first)
//              if(cV == dest) break;

//              // If we already used k stops so far , we cannot continue further !!
//             if(cStopsSoFar == k) continue;
             
//              // Explore all neighbours of current node
//              for(int[] nbr : graph.get(cV))
//              {
//                 int nbrV = nbr[0];
//                 int nbrPrice = cPrice + nbr[1];// cost to reach nbr node from source node within k stops!!
//                 int nbrStopsSoFar = cStopsSoFar + 1; // increase stop count
                     
//                       // If we found a cheaper way to reach neighbour
//                       // update the minimum price
//                      if(nbrPrice < minPrice[nbrV])
//                      {
//                     minPrice[nbrV] = nbrPrice;
//                      }

//                      // Push neighbour state into PQ
//                     // Even if price was not improved, we still push it
//                    // because the stop count might still allow reaching destination
//                     pq.add(new Node(nbrV,nbrStopsSoFar,nbrPrice));
//                  }
//              }

//             // If destination was never reached, return -1
//           // otherwise return the minimum price found 
//          return (minPrice[dest] == Integer.MAX_VALUE) ? -1 : minPrice[dest] ;
//          }

//     public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

//          // create an adjacency list first
//         ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

//         for(int i=0;i<n;i++)
//         {
//             graph.add(new ArrayList<>());
//         }

//         for(int i=0;i<flights.length;i++)
//         {
//              int v1 = flights[i][0];
//              int v2 = flights[i][1];
//              int price = flights[i][2];

//              graph.get(v1).add(new int[]{v2,price});
//         }

//          int minPrice = getMinimumPrice(graph,src,dst,k);



//          return minPrice;
//     } 
//     }
