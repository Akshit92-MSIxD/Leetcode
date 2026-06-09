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
        
//         int pos = -1;

//         for(int i=s;i<=e;i++)
//         {
//             if(inorder[i] == root.val)
//             {
//                 pos = i;
//                 break;
//             }

//         }

//         root.left = createBTree(s,pos-1,inorder,preorder);
//         root.right = createBTree(pos+1,e,inorder,preorder);

//         return root;
//     }

//     public TreeNode buildTree(int[] preorder, int[] inorder) {
           
//            return createBTree(0,inorder.length-1,inorder,preorder);
//     }
// }


class Solution {
    int idx = 0 ; // it is created to iterate over preorder[] only 

    TreeNode createBTree(int s, int e, int[] inorder, int[] preorder, HashMap<Integer,Integer> indexMp)
    {
        if(s > e)
        return null;

        TreeNode root = new TreeNode(preorder[idx]);
        idx++;
        
        int pos = indexMp.get(root.val);

        root.left = createBTree(s,pos-1,inorder,preorder,indexMp);
        root.right = createBTree(pos+1,e,inorder,preorder,indexMp);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
            
           HashMap<Integer,Integer> indexMp = new HashMap<>();

           for(int i=0;i<inorder.length;i++)
           indexMp.put(inorder[i],i); 

           return createBTree(0,inorder.length-1,inorder,preorder,indexMp);
    }
}