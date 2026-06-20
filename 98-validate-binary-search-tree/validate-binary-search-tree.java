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

 // Approach 1 : Morris Traversal for Inorder !!!
 // TC : O(n) 
 // SC : O(1) [no extra space]


// class Solution {
//     public boolean isValidBST(TreeNode root) {
         
//             TreeNode curr = root;

//            TreeNode lastElmOfIn = null;

//             while(curr != null)
//             {
//                 if(curr.left == null)
//                 {
//                     if(lastElmOfIn != null && curr.val <= lastElmOfIn.val)
//                     return false;
                        
//                     lastElmOfIn = curr;

//                     curr = curr.right;
//                 }
//                 else
//                 {
                  
//                   TreeNode pred = curr.left;

//                   while(pred.right != null && pred.right != curr)
//                   pred = pred.right;

//                   if(pred.right == null)
//                   {
//                      pred.right = curr;
//                      curr = curr.left;
//                   }
//                   else
//                   {
//                       pred.right = null;
                       
//                        if(curr.val <= lastElmOfIn.val)
//                        return false;

//                        lastElmOfIn = curr;

//                       curr = curr.right;
//                   }

//                 }
//             }

//             return true;
//     }
// }



/*---------------------------------------------------------------------------------------------------------------*/



class Solution {

    boolean isValidBST(TreeNode root, long leftBound , long rightBound)
    {
        if(root == null)
        return true;

        if(root.val <= leftBound || root.val >= rightBound)
        return false;

        return isValidBST(root.left,leftBound,root.val) && isValidBST(root.right,root.val,rightBound);
    }


    public boolean isValidBST(TreeNode root) {
         
          return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);  
    }
}