// DFS approach left !!! https://www.youtube.com/watch?v=kmzlMoxmCs4
// This problem must be tagged hard !!!


//  Optimal Approach(by Striver) : Using DSU + HashMap (https://www.youtube.com/watch?v=FMwpt_aQOGw&list=PLgUwDviBIf0oE3gA41TKO2H5bHpPd7fzn&index=56)

// Idea:
// - Each account (row) is treated as a node/group/account (0 to n-1)
// - If two accounts share the same email → connect them (union)
// - Finally, group emails based on the leader account
//
// This is more efficient than comparing every pair of emails

// TC:
// - Processing accounts + mapping emails: O(n * m)
// - Union + Find operations: O(n * m * α(n))
// - Grouping emails: O(U * α(n))
// - Sorting emails: O(U log U)
// → Overall: O(n*m + U log U)
//
// SC:
// - O(n) for DSU arrays (parent, rank)
// - O(U) for HashMaps
// → Total: O(n + U)
//
// where:
// n = number of accounts
// m = avg emails per account
// U = number of unique emails

class DisjointSet{

  int n;  // number of nodes (accounts)
  int[] rank;
  int[] parent;

  DisjointSet(int n)
  {
     rank = new int[n];
     parent = new int[n];

     for(int i=0;i<n;i++)
     {
        rank[i] = 0;
        parent[i] = i;
     }
  }

  int find(int v)
  {
      if(v == parent[v])
      return v;

      return parent[v] = find(parent[v]);
  }

  void union(int u , int v)
  {
      int leader1 = find(u);
      int leader2 = find(v);

      if(leader1 == leader2) return;

      if(rank[leader1] > rank[leader2])
      {
        parent[leader2] = leader1;
      }
      else if(rank[leader1] < rank[leader2])
      {
        parent[leader1] = leader2;
      }
      else
      {
        parent[leader2] = leader1;
        rank[leader1]++;
      }
  }

}

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
       
        int n = accounts.size();

         // create DSU for n accounts
        DisjointSet ds = new DisjointSet(n);

        // map: email → account index
        // helps identify which account an email belongs to
        HashMap<String,Integer> mp = new HashMap<>();

         // Step 1: connect accounts that share same email
        for(int i=0;i<n;i++)
        {   
            int node = i;

            for(int j=1;j<accounts.get(i).size();j++)
            {
                String email = accounts.get(i).get(j);
                
                 // if email seen first time → store mapping
                if(!mp.containsKey(email))
                {
                    mp.put(email,node);
                }
                else
                {   
                // email already seen → union current account/node/group with previous account
                    int diff_node = mp.get(email);
                    ds.union(node,diff_node);
                }

            }
        }



      // Step 2: group emails by their leader account 
        List<List<String>> res = new ArrayList<>();

        HashMap<Integer,List<String>> mp2 = new HashMap<>();

        for(String email : mp.keySet())
        {
            Integer node = mp.get(email);
            Integer leader = ds.find(node);
            mp2.putIfAbsent(leader,new ArrayList<>());
            mp2.get(leader).add(email);
        }


        // Now build the result

        List<List<String>> mergedAccounts = new ArrayList<>();

        for(Integer node : mp2.keySet())
        {
            List<String> indMergedAccount = mp2.get(node);
            String name = accounts.get(node).get(0);

            Collections.sort(indMergedAccount);
            indMergedAccount.add(0,name);

            mergedAccounts.add(indMergedAccount);
        }

        return mergedAccounts;


    }
}





/*-------------------------------------------------------------------------*/


// // Optimized Approach (DSU + HashMap)
// //
// // Idea:
// // - Treat each email as a node
// // - If two emails belong to same account → connect them (union)
// // - Finally, group emails by their leader (root)
// //
// // ✅ Optimized: Grouping is done in one pass (no O(U²) scanning)

// // TC:
// // - Building DSU: O(n * m)
// // - Union operations: O(n * m * α(n))
// // - Grouping emails: O(U * α(n))
// // - Sorting emails: O(U log U)  (in worst case, all emails in one group)
// // → Overall: O(n*m + U log U)
// //
// // SC:
// // - O(U) for parent, rank, name, and groups maps
// // (U = number of unique emails)

// import java.util.*;

// class DisjointSet {

//     HashMap<String, String> parent; // stores parent (leader) of each email
//     HashMap<String, Integer> rank;  // helps keep tree height small

//     DisjointSet() {
//         parent = new HashMap<>();
//         rank = new HashMap<>();
//     }

//     // find → returns the ultimate leader (root) of an email
//     String find(String s) {

//         // if email is its own parent → it is leader
//         if (s.equals(parent.get(s)))
//             return s;

//         // recursively find leader
//         String leader = find(parent.get(s));

//         // path compression → directly connect email to leader
//         parent.put(s, leader);

//         return leader;
//     }

//     // union → connect two emails using rank
//     void unionByRank(String u, String v) {

//         // find leaders of both emails
//         String leader1 = find(u);
//         String leader2 = find(v);

//         // if already in same group → do nothing
//         if (leader1.equals(leader2)) return;

//         int r1 = rank.get(leader1);
//         int r2 = rank.get(leader2);

//         // attach smaller rank tree under bigger rank tree
//         if (r1 > r2) {
//             parent.put(leader2, leader1);
//         } 
//         else if (r1 < r2) {
//             parent.put(leader1, leader2);
//         } 
//         else {
//             // if ranks are equal → attach and increase rank
//             parent.put(leader2, leader1);
//             rank.put(leader1, r1 + 1);
//         }
//     }
// }

// class Solution {
//     public List<List<String>> accountsMerge(List<List<String>> accounts) {

//         DisjointSet ds = new DisjointSet();

//         HashMap<String, String> name = new HashMap<>(); // email → name mapping

//         // Step 1: Initialize DSU (each email is its own group)
//         for (List<String> acc : accounts) {

//             String currName = acc.get(0);

//             for (int j = 1; j < acc.size(); j++) {

//                 String email = acc.get(j);

//                 // if email not seen before → initialize
//                 if (!ds.parent.containsKey(email)) {
//                     ds.parent.put(email, email);
//                     ds.rank.put(email, 0);
//                     name.put(email, currName);
//                 }
//             }
//         }

//         // Step 2: Union emails in the same account
//         // connect adjacent emails in same account
//         for (int i = 0; i < accounts.size(); i++) {

//             for (int j = 1; j <= accounts.get(i).size() - 2; j++) {

//                 String u = accounts.get(i).get(j);
//                 String v = accounts.get(i).get(j + 1);

//                 ds.unionByRank(u, v);
//             }
//         }

//         // Step 3: Group emails by their leader (OPTIMIZED)
//         // Instead of scanning again and again, we group in one pass
//         HashMap<String, List<String>> groups = new HashMap<>();

//         for (String email : ds.parent.keySet()) {

//             String leader = ds.find(email); // find root

//             groups.putIfAbsent(leader, new ArrayList<>());
//             groups.get(leader).add(email);
//         }

//         // Step 4: Build final result
//         List<List<String>> resAccounts = new ArrayList<>();

//         for (String leader : groups.keySet()) {

//             List<String> indAccount = groups.get(leader);

//             // sort emails for output
//             Collections.sort(indAccount);

//             // add name at index 0
//             indAccount.add(0, name.get(leader));

//             resAccounts.add(indAccount);
//         }

//         return resAccounts;
//     }
// }


/*-------------------------------------------------------------------------------*/



// My Approach (DSU + HashMap)
//
// Idea:
// - Treat each email as a node of the graph
// - If two emails belong to same account → connect them (union)
// - Finally, group emails based on their leader (root)
//
// ⚠️ Note: Last grouping part is O(U²) → not efficient

// TC:
// - Building DSU: O(n * m)
// - Union operations: O(n * m * α(n))
// - Grouping (current code): O(U²)  ❌ (for each leader, scanning all emails)
// - Sorting emails: O(U log U)  (in worst case, all emails in one group)
//
// → Overall:
// O(n*m + U² + U log U)
// → Dominated by: O(U²)

// SC:
// - O(U) for parent, rank, name maps
// - O(U) for result storage
// → Total: O(U)

// class DisjointSet {

//     HashMap<String, String> parent; // stores parent (leader) of each email
//     HashMap<String, Integer> rank;  // helps keep tree small

//     DisjointSet() {
//         parent = new HashMap<>();
//         rank = new HashMap<>();
//     }

//     // find → returns ultimate leader of an email
//     String find(String s) {
          
//           // if email is its own parent → it is leader
//           if(s.equals(parent.get(s)))
//               return s;

//           // recursively find leader
//           String leader = find(parent.get(s));

//           // path compression → directly connect to leader
//           parent.put(s,leader);

//           return leader;
//     }

//     // union → connect two emails
//     void unionByRank(String u, String v) {
       
//         // find leaders of both emails
//         String leader1 = find(u);
//         String leader2 = find(v);

//         // if already in same group → do nothing
//         if(leader1.equals(leader2)) return;

//         // attach smaller rank tree under bigger rank tree
//         if(rank.get(leader1) > rank.get(leader2))
//         {
//             parent.put(leader2,leader1);
//         }
//         else if(rank.get(leader1) < rank.get(leader2))
//         {
//             parent.put(leader1,leader2);
//         }
//         else
//         {
//             // if equal rank → attach and increase rank
//             parent.put(leader2,leader1);
//             rank.put(leader1,rank.get(leader1)+1);
//         }
//     }

// }

// class Solution {
//     public List<List<String>> accountsMerge(List<List<String>> accounts) {

//         DisjointSet ds = new DisjointSet();

//         HashMap<String, String> name = new HashMap<>(); // email → name

//         // Step 1: Initialize DSU for all emails
//         for (int i = 0; i < accounts.size(); i++) {

//             String currName = accounts.get(i).get(0);

//             for (int j = 1; j < accounts.get(i).size(); j++) {

//                 String currEmail = accounts.get(i).get(j);

//                 // if email not seen before → initialize
//                 if (!ds.parent.containsKey(currEmail))
//                 {
//                     ds.parent.put(currEmail, currEmail);
//                     ds.rank.put(currEmail, 0);
//                     name.put(currEmail, currName);
//                 }
//             }
//         }

//         // Step 2: Union emails within same account
//         for (int i = 0; i < accounts.size(); i++) {

//             for (int j = 1; j <= accounts.get(i).size()-2; j++) {

//                   String u = accounts.get(i).get(j);
//                   String v = accounts.get(i).get(j+1);

//                   ds.unionByRank(u,v);
//             }
//         }

//         List<List<String>> resAccounts = new ArrayList<>();

//         // ❌ This part is costly → O(U²)
//         // For every leader, we scan all emails again
//         for(String email : ds.parent.keySet())
//         {
//               List<String> indAccount = new ArrayList<>();

//                // if email is leader (root)
//                if(email.equals(ds.parent.get(email)))
//                {
//                   // scan all emails to find same group
//                   for(String e : ds.parent.keySet())
//                   {
//                      if(ds.find(e).equals(email))
//                         indAccount.add(e);
//                   }
                  
//                   // sort emails
//                   indAccount.sort(null);

//                   // add name at beginning
//                   indAccount.add(0,name.get(email));

//                   resAccounts.add(indAccount);
//                }
//          }

//          return resAccounts;
//     }
// }