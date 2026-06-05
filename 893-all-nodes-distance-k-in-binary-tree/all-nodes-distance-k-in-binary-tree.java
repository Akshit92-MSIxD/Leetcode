/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Pair{
  
  TreeNode curr;
  int lvl;

  Pair(TreeNode curr,int lvl)
  {
    this.curr = curr;
    this.lvl = lvl;
  }

}

class Solution {

    List<Integer> bfs(TreeNode start, int k, HashMap<TreeNode,TreeNode> parentMp)
    {
         List<Integer> res = new ArrayList<>();

         Queue<Pair> q = new ArrayDeque<>();
         HashSet<TreeNode> vis = new HashSet<>();

         q.add(new Pair(start,0));
         vis.add(start);

         while(!q.isEmpty())
         {
           Pair top = q.poll();

           TreeNode curr = top.curr;
           int lvl = top.lvl;

           if(lvl == k)
           res.add(curr.val);

           if(lvl > k)
           break;

           //explore the parent

           if(parentMp.get(curr) != null && !vis.contains(parentMp.get(curr)))
           {
            q.add(new Pair(parentMp.get(curr),lvl+1));
            vis.add(parentMp.get(curr));
           }

           // explore the left children

           if(curr.left != null && !vis.contains(curr.left))
           {
            q.add(new Pair(curr.left,lvl+1));
            vis.add(curr.left);
           }

           //explore the right children

           if(curr.right != null && !vis.contains(curr.right))
           {
            q.add(new Pair(curr.right,lvl+1));
            vis.add(curr.right);
           }
         }

         return res;  
    }

    void dfs(TreeNode root, TreeNode parent, HashMap<TreeNode,TreeNode> parentMp)
    {
        if(root == null)
        return;

        parentMp.put(root,parent);

        dfs(root.left,root,parentMp);
        dfs(root.right,root,parentMp);     
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            
            // if(root.left == null && root.right == null)
            // {
            //     if(k == 0 && target == root)
            //     {
            //         List<Integer> res = new ArrayList<>();
            //         res.add(root);
            //         return res;
            //     }

            //     return new ArrayList<>();
            // } 

           HashMap<TreeNode,TreeNode> parentMp = new HashMap<>();

           dfs(root,null,parentMp);

           return bfs(target,k,parentMp);

    }
}