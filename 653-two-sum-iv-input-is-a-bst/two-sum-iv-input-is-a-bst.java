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

    void dfs(TreeNode root,List<Integer> inorder)
    {
        if(root == null)
        return;

        dfs(root.left,inorder);
        inorder.add(root.val);
        dfs(root.right,inorder);
    }

    public boolean findTarget(TreeNode root, int k) {
        
       List<Integer> inorder = new ArrayList<>();
        
          dfs(root,inorder); 

        int left = 0;
        int right = inorder.size() - 1;

        while(left < right)
        {
            int pairSum = inorder.get(left) + inorder.get(right);

            if(pairSum == k)
                return true;
            else if(pairSum > k)
                right--;
            else
                left++;
        }

        return false;

    }
}