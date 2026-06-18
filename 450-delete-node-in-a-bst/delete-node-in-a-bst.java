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
//     public TreeNode deleteNode(TreeNode root, int key) {
            
//             if(root == null)
//             return null;
 
//             TreeNode prev = null;
//             TreeNode curr = root;

//             while(curr != null)
//             {
//                 if(key < curr.val)
//                 {
//                     prev = curr;
//                     curr = curr.left;
//                 }
//                 else if(key > curr.val)
//                 {
//                     prev = curr;
//                     curr = curr.right;
//                 }
//                 else
//                 {
//                     break;
//                 }
//             }


//             if(curr == null)
//             return root;

//             TreeNode rightPart = curr.right;
//             TreeNode leftPart = curr.left;

//             curr.left = null;
//             curr.right = null;
           
//              if(prev != null)
//              {
               
//                if(prev.left == curr)
//                {
//                    prev.left = leftPart;

//                    curr = leftPart;

//                    while(curr != null && curr.right != null)
//                    curr = curr.right;
                  
//                    if(curr != null)
//                    curr.right = rightPart;

//                    return root;
//                }
//                else
//                {
//                    prev.right = rightPart;

//                    curr = rightPart;

//                    while(curr != null && curr.left != null)
//                    curr = curr.left;
                  
//                   if(curr != null)
//                    curr.left = leftPart;

//                    return root;
//                }
//              }


//              curr = leftPart;

//              while(curr != null && curr.right != null)
//              curr = curr.right;

//              if(curr != null)
//              curr.right = rightPart;


//              if(leftPart == null)
//              return rightPart;

//              return leftPart;


//     }
// }




class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
            
          if(root == null)
          return null;


          if(root.val == key)
          {
             TreeNode leftPart = root.left;
             TreeNode rightPart = root.right;

             TreeNode curr = leftPart;

             while(curr != null && curr.right != null)
             curr = curr.right;

             if(curr != null)
             curr.right = rightPart;

             root.left = root.right = null;

             return (leftPart == null) ? rightPart : leftPart;
          }
          else if(root.val > key)
          {
            root.left = deleteNode(root.left,key);
          }
          else
          {
          root.right = deleteNode(root.right,key);
          }

          return root;

    }
}