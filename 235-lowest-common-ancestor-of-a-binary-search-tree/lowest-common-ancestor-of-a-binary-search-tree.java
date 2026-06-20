/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */


 // Note: I have written two approches for this problem below . Approach 1 is most optimal !!!
 // Note : Approach is optimal for 'Finding LCA of Normal Binary Tree' but not for BST !!!




// Approach 1 : Iterative Curr Approach (by Striver) 
// Watch this : https://www.youtube.com/watch?v=cX_kPV_foZc&list=PLkjdNRgDmcc0Pom5erUBU4ZayeU9AyRRu&index=47
// Hint : Think about (p,q) can lie in left subtree or right subree or in different subtree !!!
// TC : O(n) [ in case of left skewed BST] or O(logn) [ in case of balanced or perfect BST]
// SC : O(1)

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           
           TreeNode curr = root;

           while(curr != null)
           {
               if(p.val > curr.val && q.val > curr.val)  // that means  p and q lies in the right subtree so move to right 
                curr = curr.right;
               else if(p.val < curr.val && q.val < curr.val) // that means  p and q lies in the left subtree so move to left 
                curr = curr.left;
               else                 // that means either ("p lies in left subtree and q lies in right subtree") or vice versa !!!
                  break;           // why break ?? -> this is the first-node(LCA) from where p and q spilits in different subtree so this node is the Lowest Common Ancestor 
           }

           return curr;


    }
}




/*-----------------------------------------------------------------------------------------------------------------*/




// Note : Dont use this approach for "Finding LCA of a BST" !!!
// Approach 2 : Recursive Optimal Approach for Finding LCA of "Normal Binary Tree" and not "Binary Search Tree" !!!
// TC : O(n) [in case of any BST since this is "LCA for Normal BT" approach]
// SC : O(n) [ in case of degenerate tree]

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





