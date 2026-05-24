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

    class Path{
        int v;
        int price;  // total cost required to reach this node from source node
        int stopsSoFar;  // how many stops we have used to reach this node from source node

        Path(int v, int stopsSoFar , int price)
        {
            this.v = v;
            this.stopsSoFar = stopsSoFar;
            this.price = price;
        }
    }

    int getMinimumPrice(ArrayList<ArrayList<int[]>> graph, int src, int dest, int k)
    {
   
         Queue<Path> pq = new ArrayDeque<>();

         // Start from source node
        // stops = -1 because the first move will make it 0
         pq.add(new Path(src,-1,0));
         
    // minPrice[i] stores the minimum price found so far to reach node i from source node within k stops !!!
        int[] minPrice = new int[graph.size()];
        
        for(int i=0;i<minPrice.length;i++)
          minPrice[i] = Integer.MAX_VALUE;
         
         // Cost to reach source is 0
         minPrice[src] = 0;

         while(!pq.isEmpty())
         {
             Path top = pq.poll(); // get path having smallest price
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
                     pq.add(new Path(nbrV,nbrStopsSoFar,nbrPrice));
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




// // Approach 2 : Using Dijkstra (Modified Version of Approach 1 — No TLE)

// // Main Idea:
// // This approach is an improvement over Approach 1.
// // The main difference is that we now store both:
// // 1) Minimum price
// // 2) Minimum stops
// // for each node.

// // Why this works better:
// // In Approach 1 we only stored the minimum price for each node.
// // That caused us to insert too many useless states into the priority queue,
// // which eventually caused TLE.

// // In this improved approach we store two values for each node:
// // minPrice[node][0] → minimum price found so far
// // minPrice[node][1] → minimum stops used to reach that node

// // Now we only push a new state into the PQ if:
// // 1) We found a cheaper price
// // OR
// // 2) We found a path that uses fewer stops

// // This helps us avoid inserting many useless states into the PQ,
// // which keeps the algorithm efficient.

// class Node{

//     int v;
//     int wt;

//     Node(int v, int wt)
//     {
//         this.v = v;
//         this.wt = wt;
//     }
// }

// class Path{
  
//    int v;
//    int price;
//    int stops;

//    Path(int v, int price, int stops)
//    {
//     this.v = v;
//     this.price = price;
//     this.stops = stops;
//    }

// }

// class Solution {
  
//     int dijkstra(List<List<Node>> graph, int src_v, int dest_v, int k)
//     {
//        int n = graph.size();

//      PriorityQueue<Path> pq = new PriorityQueue<>((a,b)->Integer.compare(a.price,b.price));

//      int[][] minPrice = new int[n][2]; // minPrice[i][0] -> minPrice , minPrice[i][1] -> corrStops

//      for(int i=0;i<n;i++)
//          minPrice[i][0] = minPrice[i][1] = Integer.MAX_VALUE;


//         pq.add(new Path(src_v,0,-1));

//         while(!pq.isEmpty())
//         {
//             Path p =  pq.poll();

//             int curr_v = p.v;
//             int curr_price = p.price;
//             int curr_stop = p.stops;
            
//             // explore the neighbours

//             if(curr_v == dest_v)
//             {
//                 break;
//             }

//             for(Node nbr : graph.get(curr_v))
//             {
//                 int nbr_v = nbr.v;
//                 int nbr_wt = nbr.wt;
//                 int nbr_price = curr_price + nbr_wt;
//                 int nbr_stop = curr_stop + 1;

//                 if(nbr_price < minPrice[nbr_v][0] && nbr_stop <= k)
//                 {
//                     minPrice[nbr_v][0] = nbr_price;
//                     minPrice[nbr_v][1] = nbr_stop;
//                     pq.add(new Path(nbr_v,nbr_price,nbr_stop));
//                 }
//                 else if(nbr_stop < minPrice[nbr_v][1] && nbr_stop <= k)
//                 {
//                     pq.add(new Path(nbr_v,nbr_price,nbr_stop));
//                 }
//             }
//         }



//         if(minPrice[dest_v][0] == Integer.MAX_VALUE)
//         return -1;

//         return minPrice[dest_v][0];

//     }
//     public int findCheapestPrice(int n, int[][] flights, int src_v, int dest_v, int k) {
         

//           // first create the adjacency list

//           List<List<Node>> graph = new ArrayList<>();


//           for(int i=0;i<n;i++)
//             graph.add(new ArrayList<>());


//           for(int i=0;i<flights.length;i++)
//           {
//              int u = flights[i][0];
//              int v = flights[i][1];
//              int wt = flights[i][2];

//              graph.get(u).add(new Node(v,wt));   
//           }

//           return dijkstra(graph,src_v,dest_v,k);



//     }
// }





/*-----------------------------------------------------------------------------------------------------*/




// // Approach 1 : Using Normal Dijkstra's Algorithm (But this approach gives TLE)

// // Main Idea:
// // We try to use Dijkstra's idea: always explore the path with the smallest cost first
// // using a Min Heap (Priority Queue).

// // Why this approach gives TLE:
// // This problem has two conditions:
// // 1) Minimum total price
// // 2) Maximum number of stops (k)

// // Dijkstra normally only cares about the minimum price.
// // But in this problem the number of stops also matters.

// // Example situation:
// // Path A → cheaper price but uses many stops
// // Path B → higher price but uses fewer stops

// // Dijkstra will prefer Path A first because it is cheaper.
// // But Path B might be the only path that can still reach the destination
// // within the allowed number of stops.

// // Because of this, we cannot skip paths just because they are more expensive.
// // A more expensive path might still be useful if it uses fewer stops.

// // Normally in Dijkstra we would prune worse states using:
// // if(cPrice > minPrice[cV]) continue;
// // But here we cannot safely do that because the number of stops also matters.

// // Since we cannot prune those paths, we insert many states into the PQ.
// // This causes the priority queue to grow very large and the algorithm
// // explores too many paths, which leads to TLE.

// // So this approach is logically reasonable but inefficient because
// // it explores too many unnecessary states.


// class Solution {

//     class Path{
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
//          PriorityQueue<Path> pq = new PriorityQueue<>((a,b) -> Integer.compare(a.price,b.price));

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
//                      if(nbrPrice < minPrice[nbrV] && nbrStopsSoFar <= k)
//                      {
//                      minPrice[nbrV] = nbrPrice;
//                      pq.add(new Node(nbrV,nbrStopsSoFar,nbrPrice));
//                      }
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
