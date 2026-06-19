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
    
    void dfs(TreeNode root, List<Integer> inOrder)
    {
        if(root == null)
        return;

        dfs(root.left,inOrder);
        inOrder.add(root.val);
        dfs(root.right,inOrder);
    }

    public int kthSmallest(TreeNode root, int k) {
        
           List<Integer> inOrder = new ArrayList<>();

           dfs(root,inOrder);

           return inOrder.get(k-1);
    }
}