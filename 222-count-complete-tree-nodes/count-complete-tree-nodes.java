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

// Approach : Using three seperate DFS functions
// Note : Please consider height as no. of nodes not no. of edges !!!
// TC : O(logn^2)
// SC : O(logn) [since given tree is always complete tree]

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

         if(lh == rh)  // condition to check if current tree starting from curr node is a perfect tree or node
            return (int)Math.pow(2,lh) - 1;   // in case of perfect tree if h is given height -> total nodes -> 2^h - 1 (Note : Consider h as height in context of no. of nodes not no. of edges !!!)

         int leftSubtree = countNodes(root.left);
         int rightSubtree = countNodes(root.right);

         return 1 + leftSubtree + rightSubtree;
    }
}


