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

// Note: I have written four approaches for this below . Please read all of them are important !!!






 // Approach 1 : Expectation + Faith DFS
 // TC : O(n)
 // SC : O(n) [in case of degenerate binary tree]

// class Solution {

//     TreeNode dfs(TreeNode root)
//     {    
//           if(root == null)
//           return null;

//           if(root.left == null && root.right == null)
//           return root;

//           TreeNode leftBottom = dfs(root.left);
//           TreeNode rightBottom = dfs(root.right);

//           if(leftBottom == null)
//           return rightBottom;

//           if(rightBottom == null)
//           {
//             root.right = root.left;
//             root.left = null;
//             return leftBottom;
//           }

//           leftBottom.right = root.right;
//           TreeNode temp = root.left;
//           root.left = null;
//           root.right = temp;

//           return rightBottom;
//     }

//     public void flatten(TreeNode root) {
           
//             dfs(root);
//     }
// }




/*----------------------------------------------------------------------------------------------------------------*/




// Approach 2 : DFS(Reverse Postorder) + Prev (By striver !!!)
// TC : O(n)
// SC : O(n)

// class Solution {
     
//      TreeNode prev = null;
//     public void flatten(TreeNode root) {
            
//         if(root == null)
//         return;

//         flatten(root.right);
//         flatten(root.left);

//         root.right = prev;
//         root.left = null;
//         prev = root;
//     }
// }




/*---------------------------------------------------------------------------------------------------------------*/





// Approach 3 : Iterative Preorder using Stack
// TC : O(n)
// SC : O(n/2) [ in case of perfect or complete binary tree]

// class Solution {
     
  
//     public void flatten(TreeNode root) {

//             if(root == null)
//             return;
            
//             TreeNode dummyNode = new TreeNode(-9999,null,null);
//             TreeNode tail = dummyNode;

//             Stack<TreeNode> st = new Stack<>();

//             st.push(root);

//             while(!st.empty())
//             {
//                 TreeNode curr = st.pop();

//                 if(curr.right != null)
//                 st.push(curr.right);

//                 if(curr.left != null)
//                 st.push(curr.left);

//                 tail.right = curr;
//                 tail = tail.right;
//                 tail.left = null;
//                 tail.right = null;
//             }

//            dummyNode = null;
//     }
// }




/*-------------------------------------------------------------------------------------------------------------*/




class Solution {


    public void flatten(TreeNode root) {
           
           if(root == null)
           return;

           TreeNode curr = root;

           while(curr != null)
           {
             if(curr.left == null)
             {
                curr = curr.right;
             }
             else
             {

             TreeNode succ = curr.left;

             while(succ.right != null)
             succ = succ.right;

             succ.right = curr.right;
             curr.right = null;
             curr.right = curr.left;
             curr.left = null;
             curr = curr.right;

             }

           }

           return;
           
    }
}
