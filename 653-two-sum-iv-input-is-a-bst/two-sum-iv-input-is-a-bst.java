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


 // Note : I have written two approaches for this problem below . Approach 2 is most optimal and imp !!!




// Approach 1 : Brute Force (Find Sorted Inorder Array + 2 pointers)
// TC : O(n) [dfs] + O(n) [two pointer traversal]
// SC : + O(n) [extra Inorder array]  + O(h) [recursive stack space i.e height of the BST] 

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




/*------------------------------------------------------------------------------------------------------*/




// Approach 2 : Using One stack to store ascending inorder (from left to Right) + another stack to store descending inorder(from Right to Left) + use of next() and before() function !!!

/* Prerequisite :  https://leetcode.com/problems/binary-search-tree-iterator/description/
         You must know how to generate Inorder of a Tree using Stack (Iterative DFS Approach !!!) */

// TC of initializeBothStacks : O(h)
// TC of next() ~ O(1) [amortized]
// TC of before() ~ O(1) [amortized]
// TC of 2 pointer traversal : O(n)

// SC : O(h*2) [for both stacks] ~ O(h)



// class Solution {
    
//     Stack<TreeNode> ltrStack = new Stack<>();   // gives inorder in this fashion (Left To Right) in ascending order
//     Stack<TreeNode> rtlStack = new Stack<>();  // gives inorder in this fashion ( Right to Left) in descending order
    

//     void initializeBothStacks(TreeNode root)
//     {
//            TreeNode curr1 = root;
//            TreeNode curr2 = root;

//            while(curr1 != null)   // push all the left nodes from root
//            {
//              ltrStack.add(curr1);
//              curr1 = curr1.left;
//            }

//            while(curr2 != null) // push all the right nodes from root
//            {
//              rtlStack.add(curr2);
//              curr2 = curr2.right;
//            }
//     }

//     int next()
//     {
//         TreeNode top = ltrStack.pop();

//         int nextElm = top.val;

//         TreeNode curr = top.right;

//         while(curr != null)
//         {
//             ltrStack.push(curr);
//             curr = curr.left;
//         }

//         return nextElm;
//     }

//     int before()
//     {
//         TreeNode top = rtlStack.pop();

//         int beforeElm = top.val;

//         TreeNode curr = top.left;

//         while(curr != null)
//         {
//             rtlStack.push(curr);
//             curr = curr.right;
//         }

//         return beforeElm;
//     }


//     public boolean findTarget(TreeNode root, int k) {
         
//         initializeBothStacks(root);
         
//          int left = next();
//          int right = before();

//          while(left < right)
//          {
//              int pairSum = left + right;

//              if(pairSum == k)
//                return true;
//              else if(pairSum > k)
//              right = before();
//              else
//              left = next();
//          }

//          return false;

//     }
// }