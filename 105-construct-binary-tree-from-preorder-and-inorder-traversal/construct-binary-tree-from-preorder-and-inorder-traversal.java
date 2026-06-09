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


class Solution {
    int idx = 0 ; // it is created to iterate over preorder[] only
    HashMap<Integer,Integer> indexMp = new HashMap<>();

    TreeNode createBTree(int s, int e, int[] inorder, int[] preorder)
    {
        if(s > e)
        return null;

        TreeNode root = new TreeNode(preorder[idx]);
        idx++;
        
        int rootPos = indexMp.get(root.val);

        root.left = createBTree(s,rootPos-1,inorder,preorder);
        root.right = createBTree(rootPos+1,e,inorder,preorder);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {

           for(int i=0;i<inorder.length;i++)
           indexMp.put(inorder[i],i); 

           return createBTree(0,inorder.length-1,inorder,preorder);
    }
}