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
//     public TreeNode insertIntoBST(TreeNode root, int val) {

//             if(root == null)
//             return new TreeNode(val);
            
//              TreeNode curr = root;
//              TreeNode prev = null;

//              while(curr != null)
//              {   

//                  if(val>curr.val)
//                  {
//                     prev = curr;
//                     curr = curr.right;
//                  }
//                  else
//                  {
//                    prev = curr;
//                    curr = curr.left;
//                  }
//              }

//              if(val < prev.val)
//                 prev.left = new TreeNode(val);
//              else
//                prev.right = new TreeNode(val); 

//             return root;

//     }
// }


class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {

           if(root == null)
           return new TreeNode(val);


           if(val > root.val)
             root.right = insertIntoBST(root.right,val);
           else
             root.left = insertIntoBST(root.left,val);

             return root;

    }
}


