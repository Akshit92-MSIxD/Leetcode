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

 // Note : I have written two approaches for this problem below. Please read both approaches!!!
 // Note : I have used the StringBuilder and String here in Approach 1 and 2 resp so please read both the approaches !!! 




 // Approach 1 : DFS + Backtracking + StringBuilder (more optmized than approach 2)
 // Note : It will not create seperate new String after each dfs() call and rather will append the characters to already formed string so far
 // TC : O(n) 
 // SC : O(n)  // at a leaf node it would generate a string of size equal to no. of nodes in the tree !!!

class Solution {
    
    void dfs(TreeNode root, StringBuilder pathSoFar,List<String> paths)
    {    
        if(root == null)
        return;

        int len = pathSoFar.length();     // store length of pathSofar length till this moment so we can backtrack effciently without any problem since later we can trim the length while backtracking !!!

        if(root.left == null && root.right == null)
        {
            pathSoFar.append(root.val); 
            String validPath = new String(pathSoFar);
            paths.add(validPath);
           
            pathSoFar.setLength(len);   // trims the length by removing the current node from the path
            return;
        }

          pathSoFar.append(root.val+"->");
 
        dfs(root.left,pathSoFar,paths);
        dfs(root.right,pathSoFar,paths);

        pathSoFar.setLength(len);  // trims the length

    }


    public List<String> binaryTreePaths(TreeNode root) {
           
           List<String> paths = new ArrayList<>();
           dfs(root,new StringBuilder(),paths);

           return paths;      
    }
}



/*----------------------------------------------------------------------------------------------------------------------*/





// // Approach 2 : DFS + Backtracking + String 
// // Note : It unnecessary create new Strings after each dfs() call and take more space than Approach 1
// // TC : O(n)
// // SC : O(n)  // at a leaf node it would generate a string of size equal to no. of nodes in the tree !!!

// class Solution {
    
//     void dfs(TreeNode root, String pathSoFar,List<String> paths)
//     {    
//         if(root == null)
//         return;

//           pathSoFar += root.val;   // this will create completely new string and then pathSoFar will refer to new String !!

//         if(root.left == null && root.right == null)
//         {
//             paths.add(pathSoFar);
//             return;
//         }
 
//         dfs(root.left,pathSoFar+"->",paths);  // this will not append "->" to current string refer by pathSoFar
//         dfs(root.right,pathSoFar+"->",paths);
//     }


//     public List<String> binaryTreePaths(TreeNode root) {
           
//            List<String> paths = new ArrayList<>();
//            dfs(root,new String(),paths);

//            return paths;      
//     }
// }