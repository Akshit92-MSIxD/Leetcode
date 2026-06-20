/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// class Solution {
//     public TreeNode lowestCommonAncestor(TreeNode curr, TreeNode p, TreeNode q) {
           
//            if(curr == null)
//            return null;

//            if(curr == p || curr == q)
//            return curr;

//            TreeNode left = lowestCommonAncestor(curr.left,p,q);
//            TreeNode right = lowestCommonAncestor(curr.right,p,q);

//            if(left == null)
//            return right;

//            if(right == null)
//            return left;

//            return curr;

//     }
// }


class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           
           TreeNode curr = root;

           while(curr != null)
           {
               if(p.val > curr.val && q.val > curr.val)
                curr = curr.right;
               else if(p.val < curr.val && q.val < curr.val)
                curr = curr.left;
               else
                  break;
           }

           return curr;


    }
}