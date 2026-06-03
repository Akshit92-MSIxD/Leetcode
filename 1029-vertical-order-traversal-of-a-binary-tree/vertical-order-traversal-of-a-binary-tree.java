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
 
   int val;
   int x;
   int y;

   Pair(int val,int x,int y)
   {
    this.val = val;
    this.x = x;
    this.y = y;
   }

}


class Solution {

    void dfs(TreeNode root, int x, int y, List<Pair> ls)
     {
      if(root == null)
      return;

      ls.add(new Pair(root.val,x,y));
      dfs(root.left,x-1,y+1,ls);
      dfs(root.right,x+1,y+1,ls);
     }


    public List<List<Integer>> verticalTraversal(TreeNode root) {
        
        List<Pair> ls = new ArrayList<>();
        dfs(root,0,0,ls);

        Collections.sort(ls,(a,b)->{
             if(a.x != b.x) return Integer.compare(a.x,b.x);
             else if(a.y != b.y) return Integer.compare(a.y,b.y);
         
            return Integer.compare(a.val,b.val);
        });

        List<List<Integer>> order = new ArrayList<>();
        
        int n = ls.size();
        int i=0;

        while(i<n)
        {
            List<Integer> suborder = new ArrayList<>();
            suborder.add(ls.get(i++).val);

            while(i<n && ls.get(i).x == ls.get(i-1).x)
            {
                suborder.add(ls.get(i).val);
                i++;
            }

            order.add(suborder);
        }

        return order;
    }
}