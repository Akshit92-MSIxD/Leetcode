// /*
//  * Definition for a binary tree node.
//  * public class TreeNode {
//  *     int val;
//  *     TreeNode left;
//  *     TreeNode right;
//  *     TreeNode() {}
//  *     TreeNode(int val) { this.val = val; }
//  *     TreeNode(int val, TreeNode left, TreeNode right) {
//  *         this.val = val;
//  *         this.left = left;
//  *         this.right = right;
//  *     }
//  * }
//  */


// Approach 1 : Creating BST from Preorder and Inorder !!!
// Hint : To find the inorder --> just sort the preorder since we already have preorder of a BST
// TC : O(nlogn) [sorting preorder to find inorder] + O(n) [recursive DFS to create BST]
// SC : O(n) [recursive stack space i.e height of tree in case of degenerate BST] + O(n) [extra inorder[] array]

// class Solution {

//     TreeNode bstFromPreorderAndInorder(int[] preorder,int preStart, int preEnd, int[] inorder, int inStart, int inEnd)
//     {
//            if(preStart > preEnd || inStart > inEnd)
//            return null;

//            TreeNode root = new TreeNode(preorder[preStart]);

//            int rootPos = -1;

//            for(int i=inStart;i<=inEnd;i++)
//            {
//              if(inorder[i] == root.val)
//              {
//                 rootPos = i;
//                 break;
//              }
//            }

//            int leftLength = rootPos - inStart;

//            root.left = bstFromPreorderAndInorder(preorder,preStart+1,preStart+leftLength,inorder,inStart,rootPos-1);
//            root.right = bstFromPreorderAndInorder(preorder,preStart+leftLength+1,preEnd,inorder,rootPos+1,inEnd);

//            return root;
//     }



//     public TreeNode bstFromPreorder(int[] preorder) {
           
//             int[] inorder = new int[preorder.length];

//             for(int i=0;i<inorder.length;i++)
//              inorder[i] = preorder[i];

//              Arrays.sort(inorder);

//              return bstFromPreorderAndInorder(preorder,0,preorder.length-1,inorder,0,inorder.length-1);

//     }
// }




/*-------------------------------------------------------------------------------------------------------------*/




// class Solution {
   
//     int idx = 0;

//     TreeNode createBST(int[] preorder, int upperBound)
//     {
//           if(idx == preorder.length ||  preorder[idx] > upperBound)
//           return null;

//           TreeNode root = new TreeNode(preorder[idx++]);

//           root.left = createBST(preorder,root.val);
//           root.right = createBST(preorder,upperBound);

//           return root;
//     }
//     public TreeNode bstFromPreorder(int[] preorder) {
           
//            return createBST(preorder,Integer.MAX_VALUE);

//     }
// }



/*-------------------------------------------------------------------------------------------------------------------*/


class Solution {
   
    int idx = 0;

    TreeNode createBST(int[] preorder, int lowerBound, int upperBound)
    {
          if(idx == preorder.length || preorder[idx] < lowerBound || preorder[idx] > upperBound)
          return null;

          TreeNode root = new TreeNode(preorder[idx]);
          idx++;

          root.left = createBST(preorder,lowerBound,root.val);
          root.right = createBST(preorder,root.val,upperBound);

          return root;

    }
    public TreeNode bstFromPreorder(int[] preorder) {
           
           return createBST(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE);

    }
}