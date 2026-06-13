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

 // *** Note : Please solve and understand the "Morris Traversal for Inorder" first then only solve this one !!!





 // Approach : "Standard Morris Traversal" (Temporarily creates the new threads and restores the original input binary tree)
 // Hint : "insert curr into preorder just before updating it !!!"
 // TC : O(n)
 // SC : O(1)

class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> preOrder = new ArrayList<>();

        TreeNode curr = root;

         while(curr != null)
         {

            if(curr.left == null)
            {
                preOrder.add(curr.val); // add curr into preorder just before updating it  
                curr = curr.right;
            }
            else
            {
               TreeNode succ = curr.left;   // succ : preorder successor of curr 

               while(succ.right != null && succ.right != curr)
               succ = succ.right;

                if(succ.right == null)    // create new thread, add curr into preorder and update curr
                {
                  succ.right = curr;
                  preOrder.add(curr.val);
                  curr = curr.left;
                }
                else                   // broke the thread , update curr (since succ.right == curr here means curr is processed second time here so dont add it in preorder and just update it !!!)
                { 
                    succ.right = null;
                    curr = curr.right;
                }
            }
         }

         return preOrder;
                            
    }          
}