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
    public boolean isValidBST(TreeNode root) {
         
            TreeNode curr = root;

           TreeNode lastElmOfIn = null;

            while(curr != null)
            {
                if(curr.left == null)
                {
                    if(lastElmOfIn != null)
                    {
                       if(curr.val <= lastElmOfIn.val)
                          return false;

                       lastElmOfIn = curr;
                    }
                    else
                    {
                        lastElmOfIn = curr;
                    }

                    curr = curr.right;
                }
                else
                {
                  
                  TreeNode pred = curr.left;

                  while(pred.right != null && pred.right != curr)
                  pred = pred.right;

                  if(pred.right == null)
                  {
                     pred.right = curr;
                     curr = curr.left;
                  }
                  else
                  {
                      pred.right = null;
                       
                       if(curr.val <= lastElmOfIn.val)
                       return false;

                       lastElmOfIn = curr;

                      curr = curr.right;
                  }

                }
            }

            return true;
    }
}