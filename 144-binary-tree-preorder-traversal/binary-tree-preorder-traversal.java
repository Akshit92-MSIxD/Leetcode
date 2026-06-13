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
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> preOrder = new ArrayList<>();

        TreeNode curr = root;

         while(curr != null)
         {

            if(curr.left == null)
            {
                preOrder.add(curr.val);
                curr = curr.right;
            }
            else
            {
               TreeNode succ = curr.left;

               while(succ.right != null && succ.right != curr)
               succ = succ.right;

                if(succ.right == null)
                {
                  succ.right = curr;
                  preOrder.add(curr.val);
                  curr = curr.left;
                }
                else
                { 
                    succ.right = null;
                    curr = curr.right;
                }
            }
         }

         return preOrder;
                            
    }          
}