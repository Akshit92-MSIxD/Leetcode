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

 // Note : I have written two approaches for this problem below . Please read both of them !!!
 // *** Note : First Read Approach1  then Approach 2 !!!



// Approach 1 : Recursive Expectation and Faith Approach
// Hint : Try Attaching RightSubtree to the rightmost node of leftSubTree(if leftSubtree exits !!!)
// TC : O(n) [in case of degenerate BST]
// SC : O(n) [ in case of generate BST]

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
            
          if(root == null)
          return null;


          if(root.val == key)
          {
             TreeNode leftSubtree = root.left;
             TreeNode rightSubtree = root.right;

             TreeNode curr = leftSubtree;

             while(curr != null && curr.right != null)
             curr = curr.right;

             if(curr != null)
             curr.right = rightSubtree;

             root.left = root.right = null;

             return (leftSubtree == null) ? rightSubtree : leftSubtree;
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





/*-----------------------------------------------------------------------------------------------------------------*/






// Approach 2 : Iterative Curr + Parent(Prev) Approach
// Hint : Try Attaching RightSubtree to the rightmost node of leftSubTree(if leftSubtree exits !!!)
// TC : O(n) [in case of degenerate BST]
// SC : O(1) [no extra space]


// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
            
//             if(root == null)
//             return null;

//             TreeNode keyNode = null;
            
//             TreeNode parent = null;
//             TreeNode curr = root;

//             while(curr != null)
//             {
//                 if(key == curr.val)
//                 {
//                     keyNode = curr;
//                     break;
//                 }
//                 else if(key < curr.val)
//                 {
//                     parent = curr;
//                     curr = curr.left;
//                 }
//                 else
//                 {
//                     parent = curr;
//                     curr = curr.right;
//                 }
//             }

//             if(keyNode == null)
//             return root;


//             TreeNode leftSubtree = keyNode.left;
//             TreeNode rightSubtree = keyNode.right;

//             curr = leftSubtree;

//             while(curr != null && curr.right != null)
//             curr = curr.right;

//             if(curr != null)
//             curr.right = rightSubtree;


//             if(parent == null)
//             {
//                 keyNode.left = keyNode.right = null;
//                 return (leftSubtree == null) ? rightSubtree : leftSubtree;
//             }


//             if(parent.left == keyNode)
//             {
//                 keyNode.left = keyNode.right = null;
//                 parent.left = (leftSubtree == null) ? rightSubtree : leftSubtree;
//             }
//             else
//             {
//                 keyNode.right = keyNode.left = null;
//                 parent.right = (leftSubtree == null) ? rightSubtree : leftSubtree;
//             }

//             return root;
//     }
// }









