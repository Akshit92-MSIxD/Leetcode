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
    
//     void dfs(TreeNode root, StringBuilder pathSoFar,List<String> paths)
//     {    
//         if(root == null)
//         return;

//         int len = pathSoFar.length();

//         if(root.left == null && root.right == null)
//         {
//             pathSoFar.append(root.val);
//             String validPath = new String(pathSoFar);
//             paths.add(validPath);
           
//             pathSoFar.setLength(len);
//             return;
//         }

//           pathSoFar.append(root.val+"->");
 
//         dfs(root.left,pathSoFar,paths);
//         dfs(root.right,pathSoFar,paths);

//         pathSoFar.setLength(len);

//     }


//     public List<String> binaryTreePaths(TreeNode root) {
           
//            List<String> paths = new ArrayList<>();
//            dfs(root,new StringBuilder(),paths);

//            return paths;      
//     }
// }



class Solution {
    
    void dfs(TreeNode root, String pathSoFar,List<String> paths)
    {    
        if(root == null)
        return;

          pathSoFar += root.val;

        if(root.left == null && root.right == null)
        {
            paths.add(pathSoFar);
            return;
        }
 
        dfs(root.left,pathSoFar+"->",paths);
        dfs(root.right,pathSoFar+"->",paths);
    }


    public List<String> binaryTreePaths(TreeNode root) {
           
           List<String> paths = new ArrayList<>();
           dfs(root,new String(),paths);

           return paths;      
    }
}