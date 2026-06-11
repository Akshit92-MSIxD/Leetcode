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
    
    TreeNode createBTree(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd)
    {
         if(postEnd>postStart || inStart>inEnd)
         return null;

         TreeNode root = new TreeNode(postOrder[postStart]);

         int rootPos = -1;

         for(int i=inStart;i<=inEnd;i++)
         {
            if(inOrder[i] == root.val)
            {
            rootPos = i;
            break;
            }
         }

         int rightLength = inEnd - rootPos;

         root.right = createBTree(postOrder,postStart-1,postStart-rightLength,inOrder,rootPos+1,inEnd);
         root.left = createBTree(postOrder,postStart-rightLength-1,postEnd,inOrder,inStart,rootPos-1);

         return root;  
    }

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
         
         return createBTree(postOrder,postOrder.length-1,0,inOrder,0,inOrder.length-1);
    }
}