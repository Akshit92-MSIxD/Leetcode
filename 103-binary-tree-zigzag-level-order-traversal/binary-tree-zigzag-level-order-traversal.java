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


// Note : I have written two approaches for this problem below . Please read both of them !!!


// Approach 2 : Using Normal BFS
// TC : O(V)
// Auxiliary SC : O(V) 

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         
      if(root == null)
      return new ArrayList<>();

      List<List<Integer>> order = new ArrayList<>();

      Queue<TreeNode> q = new ArrayDeque<>();

      int currLevel = 0;

      q.add(root);

      while(!q.isEmpty())
      {
          int currLevelSize = q.size();
          List<Integer> currLevelValues = new ArrayList<>();

          while(currLevelSize-- > 0)
          {
            TreeNode curr = q.poll();
            currLevelValues.add(curr.val);

            if(curr.left != null)
            q.add(curr.left);

            if(curr.right != null)
            q.add(curr.right);
          }

          if(currLevel % 2  !=  0)
          Collections.reverse(currLevelValues);

          order.add(currLevelValues);

             currLevel++;
      }

       
       return order;

    }
}




/*---------------------------------------------------------------------------------------------------*/




// // Approach 1 : Using BFS + Stack !!!
// // TC : O(V)
// // Auxiliary SC : O(V/2) + O(V/2) [Since in case of Perfect Binary Tree Last Level has almost V/2 nodes so stack and queue can take max to max V/2 space each at a momement]

// class Solution {
//     public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
         
//       if(root == null)
//       return new ArrayList<>();

//       List<List<Integer>> order = new ArrayList<>();

//       Queue<TreeNode> q = new ArrayDeque<>();
//       Stack<TreeNode> st = new Stack<>();

//       int currLevel = 0;

//       q.add(root);

//       while(!q.isEmpty())
//       {
//           List<Integer> currLevelValues = new ArrayList<>();

//           while(!q.isEmpty())
//           {
//              TreeNode curr = q.poll();

//              currLevelValues.add(curr.val);

//              if(currLevel%2 == 0)
//              { 
//                 if(curr.left != null)
//                 st.push(curr.left);

//                 if(curr.right != null)
//                 st.push(curr.right);
//              }
//              else
//              {
//                 if(curr.right != null)
//                 st.push(curr.right);
                
//                 if(curr.left != null)
//                 st.push(curr.left);
//              }
//           }

//           order.add(currLevelValues);

//           while(!st.isEmpty())
//              q.add(st.pop());

//              currLevel++;
//       }

       
//        return order;

//     }
// }






