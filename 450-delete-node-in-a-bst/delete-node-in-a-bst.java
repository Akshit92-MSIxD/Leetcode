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
    public TreeNode deleteNode(TreeNode root, int key) {
            
            if(root == null)
            return null;

            TreeNode keyNode = null;
            
            TreeNode prev = null;
            TreeNode curr = root;

            while(curr != null)
            {
                if(key == curr.val)
                {
                    keyNode = curr;
                    break;
                }
                else if(key < curr.val)
                {
                    prev = curr;
                    curr = curr.left;
                }
                else
                {
                    prev = curr;
                    curr = curr.right;
                }
            }

            if(keyNode == null)
            return root;


            TreeNode leftPart = keyNode.left;
            TreeNode rightPart = keyNode.right;

            curr = leftPart;

            while(curr != null && curr.right != null)
            curr = curr.right;

            if(curr != null)
            curr.right = rightPart;


            if(prev == null)
            {
                keyNode.left = keyNode.right = null;
                return (leftPart == null) ? rightPart : leftPart;
            }


            if(prev.left == keyNode)
            {
                keyNode.left = keyNode.right = null;
                prev.left = (leftPart == null) ? rightPart : leftPart;
            }
            else
            {
                keyNode.right = keyNode.left = null;
                prev.right = (leftPart == null) ? rightPart : leftPart;
            }

            return root;
    }
}




// class Solution {
//     public TreeNode deleteNode(TreeNode root, int key) {
            
//           if(root == null)
//           return null;


//           if(root.val == key)
//           {
//              TreeNode leftPart = root.left;
//              TreeNode rightPart = root.right;

//              TreeNode curr = leftPart;

//              while(curr != null && curr.right != null)
//              curr = curr.right;

//              if(curr != null)
//              curr.right = rightPart;

//              root.left = root.right = null;

//              return (leftPart == null) ? rightPart : leftPart;
//           }
//           else if(root.val > key)
//           {
//             root.left = deleteNode(root.left,key);
//           }
//           else
//           {
//             root.right = deleteNode(root.right,key);
//           }

//           return root;

//     }
// }