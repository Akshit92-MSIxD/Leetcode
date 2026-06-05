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


// Approach 1 : BFS + Binary Tree Indexing Concept in 1D Array + Deque 
// TC : O(n)
// SC : O(n/2) [in case of Perfect Binary Tree last level has around n/2 nodes in the last level]

class Pair{
 
  TreeNode curr;
  int index; // index represent the index of curr TreeNode if it is represented using 1D array !!!

  Pair(TreeNode curr,int index)
  {
    this.curr = curr;
    this.index = index;
  }
}

class Solution {
    
    int bfs(TreeNode root)
    {
        Deque<Pair> q = new ArrayDeque<>(); // Deque extends queue in Java so it has all methods of queue + special methods like addFirst(),addLast(),pollFirst(),pollLast(),peekFirst() and peekLast()
        
        q.add(new Pair(root,0)); // since root TreeNode is always placed on index 0 in 1D array !!!

        int maxWidth = Integer.MIN_VALUE;

        while(!q.isEmpty())
        {
            int size = q.size();
            
            int firstIndex = q.peekFirst().index;
            int lastIndex = q.peekLast().index;

            maxWidth = Math.max(maxWidth,lastIndex-firstIndex+1);   // max no. of nodes between  end-nodes (the leftmost and rightmost non-null nodes in this current level)

            while(size-->0)
            {
                Pair top = q.poll();

                TreeNode curr = top.curr;
                int index = top.index;

                if(curr.left != null)
                q.add(new Pair(curr.left,index*2+1));  // since for a TreeNode of index i -> left child is at index -> 2*i + 1

                if(curr.right != null)  // since for a TreeNode of index i -> right child is at index -> 2*i + 2
                q.add(new Pair(curr.right,index*2+2));
            }
        }

        return maxWidth;
    }

    public int widthOfBinaryTree(TreeNode root) {

           return bfs(root);
    }
}