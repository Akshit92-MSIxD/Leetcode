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
class Solution {

    void dfs1(TreeNode root,List<Integer> inorder)
    {
            if(root == null)
            return;

            dfs1(root.left,inorder);
            inorder.add(root.val);
            dfs1(root.right,inorder);
    }
    
    int idx = 0;

    void dfs2(TreeNode root,List<Integer> inorder)
    {
           if(root == null)
           return;

           dfs2(root.left,inorder);
           root.val = inorder.get(idx++);
           dfs2(root.right,inorder);
    }

    public void recoverTree(TreeNode root) {

        List<Integer> inorder = new ArrayList<>();

        dfs1(root,inorder);

        Collections.sort(inorder);

        dfs2(root,inorder);
        
    }
}