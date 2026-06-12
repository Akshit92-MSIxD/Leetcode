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
    public List<Integer> inorderTraversal(TreeNode root) {
          
          if(root == null)
          return new ArrayList<>();

          List<Integer> inorder = new ArrayList<>();

          TreeNode curr = root;

          while(curr != null)
          {
             TreeNode temp = curr.left;

             if(temp == null)
             {
                inorder.add(curr.val);
                curr = curr.right;
                continue;
             }

             while(temp.right != null)
             temp = temp.right;

             temp.right = curr;

             TreeNode nextNode = curr.left;

             curr.left = null;

             curr = nextNode;
          }

          return inorder;
    }
}