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

// Note : I have written approaches for this problem below i.e "Destructive Morris Traversal" and "Standard Morris Traversal"

// *** Note : Please dry run this algo first on pen and paper using "Example 2" in description section !!!

// *** Note : Please dry run the "Destructive Morris Traversal" first then only dry run the "Standard Morris Traversal"

// *** Note : "Standard Morris Traversal" is little tricky than "Destructive Morris Traversal" !!!







// Approach 1 : "Destructive Morris Traversal" (Here once the links or threads are created , they are permanent and not changed till the end of the program and preFormed links between parent and children are broken down to perform this algorithm efficiently) (CodeWithMik Approach !!!!)

// Watch this video : https://www.youtube.com/watch?v=Wq3ibaP4dJY

// Note : This approach permanently modify the original binary tree input !!!

// Concept : It is used to find inorder of the given tree in O(1) extra space(no recursive stack and iterative stack)

// TC : O(n)
// SC : O(1)

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
          
          if(root == null)
          return new ArrayList<>();

          List<Integer> inorder = new ArrayList<>();

          TreeNode curr = root;

          while(curr != null)
          {

             if(curr.left == null)  // since left is null so process curr and move to right children
             {
                inorder.add(curr.val); //In : Left Root Right (since left is null so process the curr)
                curr = curr.right;
             }
             else
             {
              
              TreeNode pred = curr.left;  // pred : this is inorder predecessor of curr !!!

             while(pred.right != null)
             pred = pred.right;

             pred.right = curr; // create a thread or link

             TreeNode leftChildren = curr.left;

             curr.left = null; // breaking the edge or connection between curr and its left children once curr moves to its left children

             curr = leftChildren;
             }

    }
          return inorder;
}
}




/*-----------------------------------------------------------------------------------------------------*/






// Approach 2 : "Standard Morris Traversal" (Here the new links or threads being created , they are not permanent  till the end of the program and preFormed links between parent and children are broken down but later new links are broken down again and preFormed links would get restored !!!)

// Note : This approach temporarily modify the given input binary tree but "restores" the "original" input binary tree!!!

// Concept : It is used to find inorder of the given tree in O(1) extra space(no recursive stack and iterative stack)

// TC : O(n)
// SC : O(1)

// class Solution {
//     public List<Integer> inorderTraversal(TreeNode root) {
           
//         List<Integer> inOrder = new ArrayList<>();

//         TreeNode curr = root;

//         while(curr != null)
//         {
//             if(curr.left == null)  // since left is null so process curr and move to right children
//             {
//                 inOrder.add(curr.val); //In : Left Root Right (since left is null so process the curr)
//                 curr = curr.right;
//             }
//             else       
//             {
//                 TreeNode pred = curr.left;    // pred : this is inorder predecessor of curr !!!

//                 while(pred.right != null && pred.right != curr) 
//                 pred = pred.right;

//                 if(pred.right == null) // create a thread or link if this condition is true !!!
//                 {
//                 pred.right = curr;
//                 curr = curr.left;
//                 }
//                 else       // break the created thread or link in case this condition is true !!!
//                 {
//                     pred.right = null;
//                     inOrder.add(curr.val); 
//                     curr = curr.right;
//                 }
//             }
//         }

//         return inOrder;
          
//     }
// }