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

// // Approach 1 : Using Pure Recursive DFS(Expectation + Faith concept) !!! 

// // Prerequisite Problem (Must!!!) : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

// // Note : In this approach always traverse postorder from reverse-order/R-to-L and inorder L-to-R(as usual) !!!

// /* Worst case example [left skewed tree] :
//  preorder = [n,n-1,n-2,.......,3,2,1]  
//  inorder  = [n,n-1,n-2,.....,3,2,1]  */

// // TC : O(n^2) [ in case of left skewed tree] 
// // SC : O(n) [in case of left skewed tree]


// class Solution {
    
//     TreeNode createBTree(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd)
//     {
//          if(postEnd>postStart || inStart>inEnd)
//          return null;

//          TreeNode root = new TreeNode(postOrder[postStart]);

//          int rootPos = -1;

//          for(int i=inStart;i<=inEnd;i++)
//          {
//             if(inOrder[i] == root.val)
//             {
//             rootPos = i;
//             break;
//             }
//          }

//          int rightLength = inEnd - rootPos;

//          root.right = createBTree(postOrder,postStart-1,postStart-rightLength,inOrder,rootPos+1,inEnd);
//          root.left = createBTree(postOrder,postStart-rightLength-1,postEnd,inOrder,inStart,rootPos-1);

//          return root;  
//     }

//     public TreeNode buildTree(int[] inOrder, int[] postOrder) {
         
//          return createBTree(postOrder,postOrder.length-1,0,inOrder,0,inOrder.length-1);
//     }
// }





/*--------------------------------------------------------------------------------------------------------*/





// Approach 2 : Using Pure Recursive DFS(Expectation + Faith concept) + HashMap !!! 

// Prerequisite Problem (Must!!!) : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

// Note : In this approach always traverse postorder from reverse-order/R-to-L and inorder L-to-R(as usual) !!!

/* Worst case example [left skewed tree] :
 preorder = [n,n-1,n-2,.......,3,2,1]  
 inorder  = [n,n-1,n-2,.....,3,2,1]  */

// TC : O(n^2) [ in case of left skewed tree] 
// SC : O(n) [in case of left skewed tree]


// class Solution {

//     HashMap<Integer,Integer> indexMp = new HashMap<>();
    
//     TreeNode createBTree(int[] postOrder, int postStart, int postEnd, int[] inOrder, int inStart, int inEnd)
//     {
//          if(postEnd>postStart || inStart>inEnd)
//          return null;

//          TreeNode root = new TreeNode(postOrder[postStart]);

//          int rootPos = indexMp.get(root.val);

//          int rightLength = inEnd - rootPos;

//          root.right = createBTree(postOrder,postStart-1,postStart-rightLength,inOrder,rootPos+1,inEnd);
//          root.left = createBTree(postOrder,postStart-rightLength-1,postEnd,inOrder,inStart,rootPos-1);

//          return root;  
//     }

//     public TreeNode buildTree(int[] inOrder, int[] postOrder) {

//          for(int i=0;i<inOrder.length;i++)
//           indexMp.put(inOrder[i],i);

         
//          return createBTree(postOrder,postOrder.length-1,0,inOrder,0,inOrder.length-1);
//     }
// }




/*------------------------------------------------------------------------------------------------------------*/



// Approach 3 : DFS (Similar to Approach 1 and 2 but reduced function paremeters !!!) + HashMap !!! 

// Prerequisite Problem (Must!!!) : https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

// Note : In this approach always traverse postorder from reverse-order/R-to-L and inorder L-to-R(as usual) !!!

/* Worst case example [left skewed tree] :
 preorder = [n,n-1,n-2,.......,3,2,1]  
 inorder  = [n,n-1,n-2,.....,3,2,1]  */

// TC : O(n^2) [ in case of left skewed tree] 
// SC : O(n) [in case of left skewed tree]


class Solution {

    HashMap<Integer,Integer> indexMp = new HashMap<>();
    int idx = -1; // it is created to iterate over preOrder[] only and from R-to-L 
    
    TreeNode createBTree(int[] postOrder, int[] inOrder, int inStart, int inEnd)
    {
         if(inStart>inEnd || idx < 0)
         return null;

         TreeNode root = new TreeNode(postOrder[idx]);

         idx--;

         int rootPos = indexMp.get(root.val);

         root.right = createBTree(postOrder,inOrder,rootPos+1,inEnd);
         root.left = createBTree(postOrder,inOrder,inStart,rootPos-1);

         return root;  
    }

    public TreeNode buildTree(int[] inOrder, int[] postOrder) {

         for(int i=0;i<inOrder.length;i++)
          indexMp.put(inOrder[i],i);

          idx = postOrder.length-1;

         return createBTree(postOrder,inOrder,0,inOrder.length-1);
    }
}
