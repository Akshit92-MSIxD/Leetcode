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

// class Pair{
 
//   TreeNode curr;
//   int lvl;

//   Pair(TreeNode curr,int lvl)
//   {
//     this.curr = curr;
//     this.lvl = lvl;
//   }
// }

// class Solution {
    
//     List<Integer> bfs(TreeNode root)
//     {
     
//       HashMap<Integer,Integer> mp = new HashMap<>();

//       Queue<Pair> q = new ArrayDeque<>();

//       q.add(new Pair(root,0));
      
//       while(!q.isEmpty())
//       {
//          Pair top = q.poll();

//          TreeNode curr = top.curr;
//          int lvl = top.lvl;

//          mp.put(lvl,curr.val);

//          // explore the childrens

//          if(curr.left != null)
//          q.add(new Pair(curr.left,lvl+1));

//          if(curr.right != null)
//          q.add(new Pair(curr.right,lvl+1));
//       }

//       int maxLevel = Integer.MIN_VALUE;

//       for(int lvl : mp.keySet())
//       maxLevel = Math.max(maxLevel,lvl);

//       List<Integer> rightView = new ArrayList<>();

//       for(int level = 0;level<=maxLevel;level++)
//       rightView.add(mp.get(level));


//       return rightView;

//     }
//     public List<Integer> rightSideView(TreeNode root) {
          
//           if(root == null) return new ArrayList<>();

//           return bfs(root);
//     }
// }




class Solution {
    
    List<Integer> bfs(TreeNode root)
    {
      Queue<TreeNode> q = new ArrayDeque<>();

      q.add(root);

      List<Integer> rightView = new ArrayList<>();
      
      while(!q.isEmpty())
      {
         int size = q.size();

         while(size-- > 1)
         {
            TreeNode curr = q.poll();
            
            if(curr.left != null)
            q.add(curr.left);

            if(curr.right != null)
            q.add(curr.right);
         }

         TreeNode curr = q.poll();  // last node of current Level
         rightView.add(curr.val);

         if(curr.left != null)
         q.add(curr.left);

         if(curr.right != null)
         q.add(curr.right);
      }

      return rightView;

      
    }
    public List<Integer> rightSideView(TreeNode root) {
          
          if(root == null) return new ArrayList<>();

          return bfs(root);
    }
}