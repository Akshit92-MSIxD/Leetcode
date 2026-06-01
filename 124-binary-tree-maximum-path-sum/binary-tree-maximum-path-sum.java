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
    
    int maxSum = Integer.MIN_VALUE;

    int getMaxRootToLeafSum(TreeNode root)
    {
       if(root == null)
       return 0;


       int left = getMaxRootToLeafSum(root.left);
       int right = getMaxRootToLeafSum(root.right);

    //    System.out.println("root : "+root.val+" and left : "+left+" , right : "+right);

       if(left < 0 && right < 0)
       {
          maxSum = Math.max(maxSum,root.val);
          return root.val;
       }
       else if(left < 0)
       {
         maxSum = Math.max(maxSum,root.val + right);
         return root.val + right;
       }
       else if(right < 0)
       {
         maxSum = Math.max(maxSum,root.val + left);
         return root.val + left;
       }

       maxSum = Math.max(maxSum,root.val + left + right);

       return root.val+Math.max(left,right);
    }

    public int maxPathSum(TreeNode root) {

        getMaxRootToLeafSum(root);
        return maxSum;
    }
}