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
    
//     void dfs(TreeNode root, List<Integer> inOrder)
//     {
//         if(root == null)
//         return;

//         dfs(root.left,inOrder);
//         inOrder.add(root.val);
//         dfs(root.right,inOrder);
//     }

//     public int kthSmallest(TreeNode root, int k) {
        
//            List<Integer> inOrder = new ArrayList<>();

//            dfs(root,inOrder);

//            return inOrder.get(k-1);
//     }
// }


/*---------------------------------------------------------------------------------------------------------------*/


// class Solution {
    
//     void dfs(TreeNode root, PriorityQueue<Integer> pq , int k)
//     {
//         if(root == null)
//         return;
         
//          if(pq.size() != k)
//          {
//             pq.add(root.val);
//          }
//          else
//          {
//             if(root.val < pq.peek())
//             {
//             pq.poll();
//             pq.add(root.val);
//             }
//          }

//         dfs(root.left,pq,k);
//         dfs(root.right,pq,k);
//     }

//     public int kthSmallest(TreeNode root, int k) {
        
//            PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->Integer.compare(b,a));

//            dfs(root,pq,k);

//            return pq.peek();
//     }
// }



/*--------------------------------------------------------------------------------------------------------------*/


class Solution {

    int idx = -1;
    int ans = -1;
    
    void dfs(TreeNode root , int k)
    {
        if(root == null)
        return;

        if(ans != -1)
        return;
         
 
        dfs(root.left,k);
         
         idx++;

         if(idx == k-1)
         ans = root.val;

        dfs(root.right,k);
    }

    public int kthSmallest(TreeNode root, int k) {
           
           dfs(root,k);
           return ans;
    }
}