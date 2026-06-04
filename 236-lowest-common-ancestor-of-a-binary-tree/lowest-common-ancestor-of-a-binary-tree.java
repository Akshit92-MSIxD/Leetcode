/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 
 // NOTE : Please learn Binary Lifting method (Advanced Tree Method ) . It is useful when we have to find LCA for 100000 queries !!! 




 // Approach 1 : DFS (root to node path) + HashSet
 // TC : O(n)
 // Auxiliary SC : O(n) [space taken by two Lists] + O(n) [space taken by HashSet] + O(n) [recursive stack space in case of degenerate tree(i.e left/right skewed tree and zig zag tree)]

// class Solution {

//      void dfs(TreeNode root,TreeNode p,TreeNode q,List<TreeNode> pathSoFar,List<TreeNode> path1, List<TreeNode> path2)
//      {
//         if(root == null)
//         return;

//         if(!path1.isEmpty() && !path2.isEmpty()) return;

//         pathSoFar.add(root);

//         if(root == p)
//         path1.addAll(pathSoFar);

//         else if(root == q)
//         path2.addAll(pathSoFar);

//         dfs(root.left,p,q,pathSoFar,path1,path2);
//         dfs(root.right,p,q,pathSoFar,path1,path2);

//         pathSoFar.remove(pathSoFar.size()-1);
//      }
//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           
//           if(p == q)
//           return p;

//           List<TreeNode> path1 = new ArrayList<>();
//           List<TreeNode> path2 = new ArrayList<>();

//           dfs(root,p,q,new ArrayList<>(),path1,path2);

//           Set<TreeNode> s = new HashSet<>();

//           int n1 = path1.size();
//           int n2 = path2.size();

//           TreeNode lca = null;

//           for(int i=n1-1;i>=0;i--)
//           s.add(path1.get(i));

//           for(int i=n2-1;i>=0;i--)
//           {
//              if(s.contains(path2.get(i)))
//              {
//              lca = path2.get(i);
//              break;
//              }
//           }

//           return lca;

//     }
// }




 // Approach 2 : DFS (root to node path)
 // TC : O(n)
// Auxiliary SC : O(n) [space taken by two Lists] + O(n) [recursive stack space in case of degenerate tree(i.e left/right skewed tree and zig zag tree)] 

// class Solution {

//      void dfs(TreeNode root,TreeNode p,TreeNode q,List<TreeNode> pathSoFar,List<TreeNode> path1, List<TreeNode> path2)
//      {
//         if(root == null)
//         return;

//         if(!path1.isEmpty() && !path2.isEmpty()) return;

//         pathSoFar.add(root);

//         if(root == p)
//         path1.addAll(pathSoFar);

//         else if(root == q)
//         path2.addAll(pathSoFar);

//         dfs(root.left,p,q,pathSoFar,path1,path2);
//         dfs(root.right,p,q,pathSoFar,path1,path2);

//         pathSoFar.remove(pathSoFar.size()-1);
//      }
//     public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           
//           if(p == q)
//           return p;

//           List<TreeNode> path1 = new ArrayList<>();
//           List<TreeNode> path2 = new ArrayList<>();

//           dfs(root,p,q,new ArrayList<>(),path1,path2);

//           int n1 = path1.size();
//           int n2 = path2.size();

//           TreeNode lca = null;

//           int i=0;

//           while(i<n1 && i<n2 && path1.get(i) == path2.get(i))
//           {
//              lca = path1.get(i);
//              i++;
//           }

//           return lca;

//     }
// }



class Solution {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
           
           if(root == p || root == q || root == null)
           return root;

           TreeNode left = lowestCommonAncestor(root.left,p,q);
           TreeNode right = lowestCommonAncestor(root.right,p,q);

           if(left == null)
           return right;

           if(right == null)
           return left;

           return root;
    }
}