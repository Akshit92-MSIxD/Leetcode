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

// Note : I have written two approaches below . Please read both of them !!!
// **Note : I have written one more brute force unique approach in C++ section of this problem. Please read that also !!!



// Approach 1 : Pure Iterative Curr Approach !!!
// TC : O(n) [in case of degenerate BST tree]
// SC : O(1)

class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {

            if(root == null)
            return new TreeNode(val);
            
             TreeNode curr = root;
             TreeNode prev = null;

             while(curr != null)
             {   

                 if(val>curr.val)
                 {
                    prev = curr;
                    curr = curr.right;
                 }
                 else
                 {
                   prev = curr;
                   curr = curr.left;
                 }
             }

             if(val < prev.val)
                prev.left = new TreeNode(val);
             else
               prev.right = new TreeNode(val); 

            return root;

    }
}




/*------------------------------------------------------------------------------------------------------------------*/



// Approach 2 : Pure Recursive Expectation + Faith Approach
// TC : O(n) [in case of degenerate tree]
// SC : O(n) [in case of degenerate tree]

// class Solution {
//     public TreeNode insertIntoBST(TreeNode root, int val) {

//            if(root == null)
//            return new TreeNode(val);


//            if(val > root.val)
//              root.right = insertIntoBST(root.right,val);
//            else
//              root.left = insertIntoBST(root.left,val);

//              return root;

//     }
// }


