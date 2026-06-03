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

class Pair{
 
  TreeNode curr;
  int lvl;

  Pair(TreeNode curr,int lvl)
  {
    this.curr = curr;
    this.lvl = lvl;
  }
}

class Solution {
    
    List<Integer> bfs(TreeNode root)
    {
     
      HashMap<Integer,Integer> mp = new HashMap<>();

      Queue<Pair> q = new ArrayDeque<>();

      q.add(new Pair(root,0));
      
      while(!q.isEmpty())
      {
         Pair top = q.poll();

         TreeNode curr = top.curr;
         int lvl = top.lvl;

         mp.put(lvl,curr.val);

         // explore the childrens

         if(curr.left != null)
         q.add(new Pair(curr.left,lvl+1));

         if(curr.right != null)
         q.add(new Pair(curr.right,lvl+1));
      }

      int maxLevel = Integer.MIN_VALUE;

      for(int lvl : mp.keySet())
      maxLevel = Math.max(maxLevel,lvl);

      List<Integer> rightView = new ArrayList<>();

      for(int level = 0;level<=maxLevel;level++)
      rightView.add(mp.get(level));


      return rightView;

    }
    public List<Integer> rightSideView(TreeNode root) {
          
          if(root == null) return new ArrayList<>();

          return bfs(root);
    }
}