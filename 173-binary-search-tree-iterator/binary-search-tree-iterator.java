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

// Note : I have written two approaches for this problem below . Approach 2 is optimal one !!!



// Approach 1 : Transform given BST to Sorted Inorder Linked List !!! (My Brute force method !!! :)
// TC for Constructor(BSTIterator) = O(n)
// TC for next() = O(1)
// TC for hasNext() = O(1)

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
            return new TreeNode[]{null,null};


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



/*----------------------------------------------------------------------------------------------------------*/


// Approach 2 : Using Iterative Inorder DFS using stack !!! (By Striver !!!)
// TC for next() ~ O(1) [amortized]
// TC for hasNext() = O(1)
// TC for Constructor (BSTIterator) = O(h) [left height of the BST]
// SC : O(h) [height of the BST]


// class BSTIterator {
    
//     Stack<TreeNode> st = new Stack<>();
 
//     public BSTIterator(TreeNode root) {
           
//           while(root != null)
//           {
//             st.push(root);
//             root = root.left;
//           }

//     }

    
//     public int next() {
       
//        TreeNode top = st.peek();
//        st.pop();
       
//        int nextElm = top.val;

//        TreeNode curr = top.right;

//        while(curr != null)
//        {
//            st.push(curr);
//            curr = curr.left;
//        }

//        return nextElm; 
//     }
    
//     public boolean hasNext() {
        
//         if(st.isEmpty())
//         return false;

//         return true;
          
//     }
// }






/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */