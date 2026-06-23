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
class BSTIterator {
    
    TreeNode curr = null;
    TreeNode head = null;
    TreeNode tail = null;

    public BSTIterator(TreeNode root) {
           
           TreeNode[] endPoints = convertBSTintoInorderList(root);

           head = endPoints[0];
           tail = endPoints[1];

    }

    TreeNode[] convertBSTintoInorderList(TreeNode root)
    {
         if(root == null)
         {
            return new TreeNode[]{null,null};
         }


         TreeNode[] leftSubtreeEndPoints = convertBSTintoInorderList(root.left);
         TreeNode[] rightSubtreeEndPoints = convertBSTintoInorderList(root.right);

         TreeNode leftHead = leftSubtreeEndPoints[0];
         TreeNode leftTail = leftSubtreeEndPoints[1];

         TreeNode rightHead = rightSubtreeEndPoints[0];
         TreeNode rightTail = rightSubtreeEndPoints[1];

         root.left = null;
         root.right = null;

         if(leftTail != null)
         {
            leftTail.right = root;
            root.right = rightHead;
            
            if(rightHead != null)
            return new TreeNode[]{leftHead,rightTail};
            else
            return new TreeNode[]{leftHead,root};
         }

         root.right = rightHead;
         
         if(rightHead != null)
         return new TreeNode[]{root,rightTail};

         return new TreeNode[]{root,root};
    }
    
    public int next() {
       
       if(curr == null)
       curr = head;
       else
       curr = curr.right;

       return curr.val;
     
    }
    
    public boolean hasNext() {
        
        if(curr == null)
        return true;

        if(curr.right == null)
        return false;

        return true;
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */