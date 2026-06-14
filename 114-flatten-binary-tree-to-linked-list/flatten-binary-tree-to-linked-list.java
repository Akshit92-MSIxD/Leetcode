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
// class Solution {

//     TreeNode dfs(TreeNode root)
//     {    
//           if(root == null)
//           return null;

//           if(root.left == null && root.right == null)
//           return root;

//           TreeNode leftBottom = dfs(root.left);
//           TreeNode rightBottom = dfs(root.right);

//           if(leftBottom == null)
//           return rightBottom;

//           if(rightBottom == null)
//           {
//             root.right = root.left;
//             root.left = null;
//             return leftBottom;
//           }

//           leftBottom.right = root.right;
//           TreeNode temp = root.left;
//           root.left = null;
//           root.right = temp;

//           return rightBottom;
//     }

//     public void flatten(TreeNode root) {
           
//             dfs(root);
//     }
// }



class Solution {
     
     TreeNode prev = null;
    public void flatten(TreeNode root) {
            
        if(root == null)
        return;

        flatten(root.right);
        flatten(root.left);

        root.right = prev;
        root.left = null;
        prev = root;
    }
}