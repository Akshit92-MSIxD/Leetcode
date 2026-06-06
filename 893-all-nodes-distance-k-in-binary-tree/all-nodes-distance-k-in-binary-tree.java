/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// class Pair{
  
//   TreeNode curr;
//   int lvl;

//   Pair(TreeNode curr,int lvl)
//   {
//     this.curr = curr;
//     this.lvl = lvl;
//   }

// }

// class Solution {

//     List<Integer> bfs(TreeNode start, int k, HashMap<TreeNode,TreeNode> parentMp)
//     {
//          List<Integer> res = new ArrayList<>();

//          Queue<Pair> q = new ArrayDeque<>();
//          HashSet<TreeNode> vis = new HashSet<>();

//          q.add(new Pair(start,0));
//          vis.add(start);

//          while(!q.isEmpty())
//          {
//            Pair top = q.poll();

//            TreeNode curr = top.curr;
//            int lvl = top.lvl;

//            if(lvl == k)
//            {
//            res.add(curr.val);
//            continue;
//            }

//            //explore the parent

//            TreeNode parent = parentMp.get(curr);

//            if(parent != null && !vis.contains(parent))
//            {
//             q.add(new Pair(parent,lvl+1));
//             vis.add(parent);
//            }

//            // explore the left children

//            if(curr.left != null && !vis.contains(curr.left))
//            {
//             q.add(new Pair(curr.left,lvl+1));
//             vis.add(curr.left);
//            }

//            //explore the right children

//            if(curr.right != null && !vis.contains(curr.right))
//            {
//             q.add(new Pair(curr.right,lvl+1));
//             vis.add(curr.right);
//            }
//          }

//          return res;  
//     }

//     void dfs(TreeNode root, TreeNode parent, HashMap<TreeNode,TreeNode> parentMp)
//     {
//         if(root == null)
//         return;

//         parentMp.put(root,parent);

//         dfs(root.left,root,parentMp);
//         dfs(root.right,root,parentMp);     
//     }

//     public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            

//            HashMap<TreeNode,TreeNode> parentMp = new HashMap<>();

//            dfs(root,null,parentMp);

//            return bfs(target,k,parentMp);

//     }
// }



class Solution {

    void dfs2(TreeNode curr,TreeNode prev, int curr_lvl , int k, HashMap<TreeNode,TreeNode> parentMp, List<Integer> res)
    {
         if(curr == null)
         return;

         if(curr_lvl == k)
         {
            res.add(curr.val);
            return;
         }

         // explore the neighbours

         TreeNode parent = parentMp.get(curr);

         if(parent != prev)
         dfs2(parent,curr,curr_lvl+1,k,parentMp,res);

         if(curr.left != prev)
         dfs2(curr.left,curr,curr_lvl+1,k,parentMp,res);

         if(curr.right != prev)
         dfs2(curr.right,curr,curr_lvl+1,k,parentMp,res);

         return;

    }

    void dfs1(TreeNode root, TreeNode parent, HashMap<TreeNode,TreeNode> parentMp)
    {
        if(root == null)
        return;

        parentMp.put(root,parent);

        dfs1(root.left,root,parentMp);
        dfs1(root.right,root,parentMp);     
    }

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
            

           HashMap<TreeNode,TreeNode> parentMp = new HashMap<>();

           dfs1(root,null,parentMp);
           
           List<Integer> res = new ArrayList<>();
           dfs2(target,null,0,k,parentMp,res);

           return res;

    }
}