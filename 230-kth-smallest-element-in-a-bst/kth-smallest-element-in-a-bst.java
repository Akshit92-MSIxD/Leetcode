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

// Note : I have written 4 approches for this problem below. Please read all of them specially Approach 3 and 4 (very Imp !!!!)





// Approach 1 : Recursive Inorder DFS to Find Inorder(Sorted Array)
// TC : O(n) [in case of degenerated BST]
// SC : O(n) [extra inorder[] space] + O(n) [height in case of degenerated BST]

class Solution {
    
    void dfs(TreeNode root, List<Integer> inOrder)
    {
        if(root == null)
        return;

        dfs(root.left,inOrder);
        inOrder.add(root.val);
        dfs(root.right,inOrder);
    }

    public int kthSmallest(TreeNode root, int k) {
        
           List<Integer> inOrder = new ArrayList<>();

           dfs(root,inOrder);

           return inOrder.get(k-1);
    }
}




/*---------------------------------------------------------------------------------------------------------------*/





// Approach 2 : Recursive Inorder DFS + PriorityQueue to find kth smallest element
// TC : O(n) [in case of degenerated BST]
// SC : O(k) [extra PriorityQueue space] + O(n) [height in case of degenerated BST]

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




// Approach 3 : Recursive Inorder DFS + two variables(count and ans)
// TC : O(n) [in case of degenerated BST]
// SC : O(n) [height in case of degenerated BST]


// class Solution {

//     int count = 0;
//     int ans = -1;
    
//     void dfs(TreeNode root , int k)
//     {
//         if(root == null)
//         return;

//         if(ans != -1)
//         return;
         
 
//         dfs(root.left,k);
         
//          count++;

//          if(count == k)
//          ans = root.val;

//         dfs(root.right,k);
//     }

//     public int kthSmallest(TreeNode root, int k) {
           
//            dfs(root,k);
//            return ans;
//     }
// }





/*---------------------------------------------------------------------------------------------------------------*/





// Approach 4 : Morris Traversal for Inorder(Sorted Array In case of BST) + (count + ans) Approach!!!
// TC : O(n) [in case of degenerated BST]
// SC : O(1) [no extra space]

// class Solution {

//     public int kthSmallest(TreeNode root, int k) {
           
             
//             TreeNode curr = root;

//             int count = 0;
//             int ans = -1;


//             while(curr != null)
//             {
//                 if(curr.left == null)
//                 {
//                     count++;

//                     if(count == k)
//                     {
//                         ans = curr.val;
//                         break;
//                     }

//                     curr = curr.right;
//                 }
//                 else
//                 {
                  
//                    TreeNode pred = curr.left;

//                    while(pred.right != null && pred.right != curr)
//                    pred = pred.right;

//                    if(pred.right == null)
//                    {
//                      pred.right = curr;
//                      curr = curr.left;
//                    }
//                    else
//                    {
//                      count++;

//                      if(count == k)
//                      {
//                         ans = curr.val;
//                         break;
//                      }

//                      pred.right = null;
//                      curr = curr.right;
//                    }
//                 }
//             }


//             return ans;
//     }
// }
