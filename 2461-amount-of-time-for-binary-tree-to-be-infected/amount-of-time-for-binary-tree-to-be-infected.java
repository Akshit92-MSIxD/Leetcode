/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// Note : One traversal DFS approach left (not intuitive at all)


// Approach 1 : Using DFS + BFS
// Prerequisite Problem : https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
// TC : O(n)
// SC : O(n)
class Pair
{
   TreeNode curr;
   int lvl;

   Pair(TreeNode curr,int lvl)
   {
     this.curr = curr;
     this.lvl = lvl;
   }

}

class Solution {

    int bfs(TreeNode start, HashMap<TreeNode,TreeNode> parentMp)
    {
        int maxLvl = Integer.MIN_VALUE;
        
        Queue<Pair> q = new ArrayDeque<>();
        HashSet<TreeNode> vis = new HashSet<>();

        q.add(new Pair(start,0));
        vis.add(start);

        while(!q.isEmpty())
        {
           Pair top = q.poll();

           TreeNode curr = top.curr;
           int lvl = top.lvl;

           maxLvl = Math.max(maxLvl,lvl);

           // explore the neighbours

           TreeNode parent = parentMp.get(curr);

           if(!vis.contains(parent) && parent != null)
           {
                q.add(new Pair(parent,lvl+1));
                vis.add(parent);
           }

           if(!vis.contains(curr.left) && curr.left != null)
           {
                q.add(new Pair(curr.left,lvl+1));
                vis.add(curr.left);
           }

           if(!vis.contains(curr.right) && curr.right != null)
           {
               q.add(new Pair(curr.right,lvl+1));
               vis.add(curr.right);
           }
        }

        return maxLvl;
    }
   
    void dfs(TreeNode curr, TreeNode parent, HashMap<TreeNode,TreeNode> parentMp)
    {
       if(curr == null)
       return;

       parentMp.put(curr,parent);

       dfs(curr.left,curr,parentMp);
       dfs(curr.right,curr,parentMp);

    }

    public int amountOfTime(TreeNode root, int start) {
         
          HashMap<TreeNode,TreeNode> parentMp = new HashMap<>();

          dfs(root,null,parentMp);

          TreeNode startNode = null;

          for(TreeNode curr : parentMp.keySet())
          {
             if(curr.val == start)
             {
                startNode = curr;
                break;
             }
          }

          return bfs(startNode,parentMp);
    }
}


