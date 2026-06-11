/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
         
         if(root == null)
         return "";

        Queue<TreeNode> q = new LinkedList<>();

        q.add(root); 

        StringBuilder res = new StringBuilder();

        while(!q.isEmpty())
        {
            TreeNode curr = q.poll();
            
            if(curr == null)
            res.append("#");
            else
            res.append(curr.val);
     
              res.append(",");

              if(curr != null)
              {
                 q.add(curr.left);
                 q.add(curr.right);
              }
        }

          res.setLength(res.length()-1); // remove the last trailing comma

          System.out.println(res);

          return res.toString();

    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {

        if(data.length()==0)
        return null;
         
          String[] res = data.split(",");

          TreeNode root = new TreeNode(Integer.parseInt(res[0]));

          Queue<TreeNode> q = new ArrayDeque<>();

          q.add(root);

          int i = 1;

          while(!q.isEmpty())
          {
              TreeNode curr = q.poll();

              TreeNode left = null;
              TreeNode right = null;

              if(!res[i].equals("#"))
              {
                left = new TreeNode(Integer.parseInt(res[i]));
                q.add(left);
              }
               
               i++;

              if(!res[i].equals("#"))
              {
                right = new TreeNode(Integer.parseInt(res[i]));
                q.add(right);
              }

              i++;

              curr.left = left;
              curr.right = right;
          }
           
          i++;

          return root;

    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));