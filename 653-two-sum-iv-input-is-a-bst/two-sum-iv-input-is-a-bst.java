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

//     void dfs(TreeNode root,List<Integer> inorder)
//     {
//         if(root == null)
//         return;

//         dfs(root.left,inorder);
//         inorder.add(root.val);
//         dfs(root.right,inorder);
//     }

//     public boolean findTarget(TreeNode root, int k) {
        
//        List<Integer> inorder = new ArrayList<>();
        
//           dfs(root,inorder); 

//         int left = 0;
//         int right = inorder.size() - 1;

//         while(left < right)
//         {
//             int pairSum = inorder.get(left) + inorder.get(right);

//             if(pairSum == k)
//                 return true;
//             else if(pairSum > k)
//                 right--;
//             else
//                 left++;
//         }

//         return false;

//     }
// }




/*------------------------------------------------------------------------------------------------------*/





class Solution {
    
    Stack<TreeNode> ltrStack = new Stack<>();   // gives inorder in this fashion (Left To Right) in ascending order
    Stack<TreeNode> rtlStack = new Stack<>();  // gives inorder in this fashion ( Right to Left) in descending order
    

    void initializeBothStacks(TreeNode root)
    {
           TreeNode curr1 = root;
           TreeNode curr2 = root;

           while(curr1 != null)
           {
             ltrStack.add(curr1);
             curr1 = curr1.left;
           }

           while(curr2 != null)
           {
             rtlStack.add(curr2);
             curr2 = curr2.right;
           }
    }

    int next()
    {
        TreeNode top = ltrStack.peek();
        ltrStack.pop();

        int nextElm = top.val;

        TreeNode curr = top.right;

        while(curr != null)
        {
            ltrStack.push(curr);
            curr = curr.left;
        }

        return nextElm;
    }

    int before()
    {
        TreeNode top = rtlStack.peek();
        rtlStack.pop();

        int beforeElm = top.val;

        TreeNode curr = top.left;

        while(curr != null)
        {
            rtlStack.push(curr);
            curr = curr.right;
        }

        return beforeElm;
    }


    public boolean findTarget(TreeNode root, int k) {
         
        initializeBothStacks(root);
         
         int left = next();
         int right = before();

         while(left < right)
         {
             int pairSum = left + right;

             if(pairSum == k)
               return true;
             else if(pairSum > k)
             right = before();
             else
             left = next();
         }

         return false;

    }
}