/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

     void dfs(TreeNode root,TreeNode p,TreeNode q,List<TreeNode> pathSoFar,List<TreeNode> path1, List<TreeNode> path2)
     {
        if(root == null)
        return;

        if(!path1.isEmpty() && !path2.isEmpty()) return;

        pathSoFar.add(root);

        if(root == p)
        {
           for(TreeNode curr : pathSoFar)
           path1.add(curr);
        }
        else if(root == q)
        {
           for(TreeNode curr : pathSoFar)
            path2.add(curr);
        }

        dfs(root.left,p,q,pathSoFar,path1,path2);
        dfs(root.right,p,q,pathSoFar,path1,path2);

        pathSoFar.remove(pathSoFar.size()-1);
     }
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           
          if(p == q)
          return p;

          List<TreeNode> path1 = new ArrayList<>();
          List<TreeNode> path2 = new ArrayList<>();

          dfs(root,p,q,new ArrayList<>(),path1,path2);

          Set<TreeNode> mp = new HashSet<>();

          int n1 = path1.size();
          int n2 = path2.size();

          TreeNode lca = null;

          for(int i=n1-1;i>=0;i--)
          mp.add(path1.get(i));

          for(int i=n2-1;i>=0;i--)
          {
             if(mp.contains(path2.get(i)))
             {
             lca = path2.get(i);
             break;
             }
          }

          return lca;

    }
}