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
//  // Approach : DFS
//  // Watch : https://www.youtube.com/watch?v=G5c1wM3Kpuw (for better understanding)

// class Solution {
//     int idx = 0 ; // it is created to iterate over preorder[] only 

//     TreeNode createBTree(int s, int e, int[] inorder, int[] preorder)
//     {
//         if(s > e)
//         return null;

//         TreeNode root = new TreeNode(preorder[idx]);
//         idx++;
        
//         int rootPos = -1;

//         for(int i=s;i<=e;i++)
//         {
//             if(inorder[i] == root.val)
//             {
//                 rootPos = i;
//                 break;
//             }

//         }

//         root.left = createBTree(s,rootPos-1,inorder,preorder);
//         root.right = createBTree(rootPos+1,e,inorder,preorder);

//         return root;
//     }

//     public TreeNode buildTree(int[] preorder, int[] inorder) {
           
//            return createBTree(0,inorder.length-1,inorder,preorder);
//     }
// }



// // Approach 1 : Using Pure Recursive DFS(Expectation + Faith concept) !!! (Strivers Solution)
// // Watch : https://www.youtube.com/watch?v=aZNaLrVebKQ&list=PLkjdNRgDmcc0Pom5erUBU4ZayeU9AyRRu&index=34

// /* Worst case example [left skewed tree] :
//  preorder = [1,2,3,4,5,6.......,n]  
//  inorder  = [n,n-1,n-2,.....,3,2,1]  */

// // TC : O(n^2) [ in case of left skewed tree] 
// // SC : O(n) [in case of left skewed tree]

// class Solution {


//     TreeNode createBTree(int[] preorder,int preStart, int preEnd, int[] inorder, int inStart, int inEnd)
//     {

//         if(preStart > preEnd || inStart > inEnd)
//         return null;

//      TreeNode root = new TreeNode(preorder[preStart]);

//      // find root in between inStart and inEnd in inorder[]

//       int rootPos = -1;

//       for(int i=inStart;i<=inEnd;i++)
//       {
//          if(inorder[i] == root.val)
//          {
//             rootPos = i;
//             break;
//          }
//       }

//       int leftLength = rootPos - inStart;    // left subtree inorder length
     
//       // create a new Preorder and Inorder range for left Subtree and return the root of rightSubtree
//       root.left = createBTree(preorder,preStart+1,preStart+leftLength,inorder,inStart,rootPos - 1);

//        // create a new Preorder and Inorder range for right Subtree and return the root of rightSubtree
//       root.right = createBTree(preorder,preStart+leftLength+1,preEnd,inorder,rootPos+1,inEnd);

//       return root;
//     }

//     public TreeNode buildTree(int[] preorder, int[] inorder) {
           
//            return createBTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
//     }
// }



// Approach 1 : Using Pure Recursive DFS(Expectation + Faith concept) !!! (Strivers Solution)
// Watch : https://www.youtube.com/watch?v=aZNaLrVebKQ&list=PLkjdNRgDmcc0Pom5erUBU4ZayeU9AyRRu&index=34

/* Worst case example [left skewed tree] :
 preorder = [1,2,3,4,5,6.......,n]  
 inorder  = [n,n-1,n-2,.....,3,2,1]  */

// TC : O(n) [ even in case of left skewed tree] 
// SC : O(n) [even in case of left skewed tree]

class Solution {
   
     Map<Integer,Integer> indexMp = new HashMap<>();

    TreeNode createBTree(int[] preorder,int preStart, int preEnd, int[] inorder, int inStart, int inEnd)
    {

        if(preStart > preEnd || inStart > inEnd)
        return null;
      
     TreeNode root = new TreeNode(preorder[preStart]);

     // find root in between inStart and inEnd in inorder[] directly through indexMp

      int rootPos = indexMp.get(root.val);

      int leftLength = rootPos - inStart;    // left subtree inorder length
     
      // create a new Preorder and Inorder range for left Subtree and return the root of rightSubtree
      root.left = createBTree(preorder,preStart+1,preStart+leftLength,inorder,inStart,rootPos - 1);

       // create a new Preorder and Inorder range for right Subtree and return the root of rightSubtree
      root.right = createBTree(preorder,preStart+leftLength+1,preEnd,inorder,rootPos+1,inEnd);

      return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

           for(int i=0;i<inorder.length;i++)
           indexMp.put(inorder[i],i);
           
           return createBTree(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }
}




