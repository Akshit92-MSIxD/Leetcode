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

             if(curr.left == null)
             {
                inorder.add(curr.val);
                curr = curr.right;
             }
             else
             {
              
              TreeNode pred = curr.left;

             while(pred.right != null)
             pred = pred.right;

             pred.right = curr; // create a thread or link

             TreeNode leftChildren = curr.left;

             curr.left = null; // breaking the edge or connection between curr and its left children once curr moves to its left children

             curr = leftChildren;
             }

    }
          return inorder;
}
}



// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
           
//         List<Integer> inOrder = new ArrayList<>();

//         TreeNode curr = root;

//         while(curr != null)
//         {
//             if(curr.left == null)
//             {
//                 inOrder.add(curr.val);
//                 curr = curr.right;
//             }
//             else
//             {
//                 TreeNode pred = curr.left;   // inorder predecessor of curr 

//                 while(pred.right != null && pred.right != curr) 
//                 pred = pred.right;

//                 if(pred.right == null) // create a thread or link if this condition is true !!!
//                 {
//                 pred.right = curr;
//                 curr = curr.left;
//                 }
//                 else                // break the thread or link in case this condition is true !!!
//                 {
//                     pred.right = null;
//                     inOrder.add(curr.val);
//                     curr = curr.right;
//                 }
//             }
//         }

//         return inOrder;
          
//     }
// }