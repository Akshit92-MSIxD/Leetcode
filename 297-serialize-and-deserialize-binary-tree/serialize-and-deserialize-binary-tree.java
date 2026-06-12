/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// public class Codec {

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
         
//          if(root == null)
//          return "";

//         Queue<TreeNode> q = new LinkedList<>();

//         q.add(root); 

//         StringBuilder res = new StringBuilder();

//         while(!q.isEmpty())
//         {
//             TreeNode curr = q.poll();
            
//             if(curr == null)
//             res.append("#");
//             else
//             res.append(curr.val);
     
//               res.append(",");

//               if(curr != null)
//               {
//                  q.add(curr.left);
//                  q.add(curr.right);
//               }
//         }

//           res.setLength(res.length()-1); // remove the last trailing commas

//           return res.toString();

//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {

//         if(data.isEmpty())
//         return null;
         
//           String[] res = data.split(",");

//           TreeNode root = new TreeNode(Integer.parseInt(res[0]));

//           Queue<TreeNode> q = new ArrayDeque<>();

//           q.add(root);

//           int i = 1;

//           while(!q.isEmpty() && i<res.length)
//           {
//               TreeNode curr = q.poll();

//               if(!res[i].equals("#"))
//               {
//                 curr.left = new TreeNode(Integer.parseInt(res[i]));
//                 q.add(curr.left);
//               }
               
//                i++;

//               if(!res[i].equals("#"))
//               {
//                 curr.right = new TreeNode(Integer.parseInt(res[i]));
//                 q.add(curr.right);
//               }

//               i++;
//           }

//           return root;

//     }
// }




/*------------------------------------------------------------------------------------------------------------*/


// class Pair{

//     TreeNode curr;
//     int state;

//     Pair(TreeNode curr, int state)
//     {
//         this.curr = curr;
//         this.state = state;
//     }
// } 

// public class Codec {

//     void dfs(TreeNode root, StringBuilder preOrder)
//     {
//          if(root == null)
//          {
//          preOrder.append("#,");
//          return;
//          }

//          preOrder.append(root.val+",");

//          dfs(root.left,preOrder);
//          dfs(root.right,preOrder);
//     }

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {

//          if(root == null)
//          return "";
         
//          StringBuilder preOrder = new StringBuilder();

//          dfs(root,preOrder);

//          preOrder.setLength(preOrder.length()-1);

//          return preOrder.toString();
          
//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
        
//         if(data.isEmpty())
//         return null;

//         String[] preOrder = data.split(",");

//         Stack<Pair> st = new Stack<>();

//         TreeNode root = new TreeNode(Integer.parseInt(preOrder[0]));

//         st.push(new Pair(root,0));

//         int i = 1;

//         while(!st.empty())
//         {

//             TreeNode newNode = null;

//             if(!preOrder[i].equals("#"))
//             newNode = new TreeNode(Integer.parseInt(preOrder[i]));

//             if(st.peek().state == 0)
//             {
//                 st.peek().curr.left = newNode;
//                 st.peek().state += 1;
//             }
//             else
//             {
//                  st.peek().curr.right = newNode;
//                  st.pop();
//             }
             
//              if(newNode != null)
//              st.push(new Pair(newNode,0));

//            i++;
//         }

//         return root;
 
//     }
// }




public class Codec {
    
     int idx = 0; // global index used in deserialize build() function to iterate over the preOrder[] (of type String[])

    void dfs(TreeNode root, StringBuilder preOrder)
    {
         if(root == null)
         {
         preOrder.append("#,");
         return;
         }

         preOrder.append(root.val+",");

         dfs(root.left,preOrder);
         dfs(root.right,preOrder);
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

         if(root == null)
         return "";
         
         StringBuilder preOrder = new StringBuilder();

         dfs(root,preOrder);

         preOrder.setLength(preOrder.length()-1);

         return preOrder.toString();
          
    }

    TreeNode build(String[] preOrder)
    {
        if(preOrder[idx].equals("#"))
        {
         idx++;
         return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(preOrder[idx]));
        idx++;

        root.left = build(preOrder);
        root.right = build(preOrder);

        return root;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        
        if(data.isEmpty())
        return null;

        String[] preOrder = data.split(",");
        
        return build(preOrder);
 
    }
}



// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));