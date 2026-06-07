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
   
    int leftHeight(TreeNode root)
    {
        if(root == null)
        return 0;

        return 1 + leftHeight(root.left);
    }

     int rightHeight(TreeNode root)
     {
        if(root == null)
        return 0;

        return 1 + rightHeight(root.right);
     }


    public int countNodes(TreeNode root) {

         int lh = leftHeight(root);  // left height of root
         int rh = rightHeight(root);  // right height of root

         if(lh == rh)
            return (int)Math.pow(2,lh) - 1;

         int leftSubtree = countNodes(root.left);
         int rightSubtree = countNodes(root.right);

         return 1 + leftSubtree + rightSubtree;
    }
}