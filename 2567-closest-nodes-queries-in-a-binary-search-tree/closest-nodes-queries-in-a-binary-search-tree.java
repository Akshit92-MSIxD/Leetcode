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
    
//     int getFloorValue(TreeNode root, int k)
//     {
//         int floor = Integer.MIN_VALUE;

//         TreeNode curr = root;

//         while(curr != null)
//         {
//             if(curr.val <= k)
//             {
//                 floor = Math.max(floor,curr.val);
//                 curr = curr.right;
//             }
//             else
//             {
//                 curr = curr.left;
//             }
//         }

//         if(floor == Integer.MIN_VALUE)
//         floor = -1;

//         return floor;
//     }

//     int getCeilValue(TreeNode root,int k)
//     {
//          int ceil = Integer.MAX_VALUE;

//          TreeNode curr = root;

//          while(curr != null)
//          {
//             if(curr.val >= k)
//             {
//                ceil = Math.min(ceil,curr.val);
//                curr = curr.left;
//             }
//             else
//             {
//                 curr = curr.right;
//             }
//          }

//          if(ceil == Integer.MAX_VALUE)
//          ceil = -1;

//          return ceil;
//     }

//     public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
               
//              List<List<Integer>> ans = new ArrayList<>();

//              for(int k : queries)
//              {
                //  int floor = getFloorValue(root,k);
                //  int ceil =  getCeilValue(root,k);

                //  List<Integer> subans = new ArrayList<>();
                //  subans.add(floor);
                //  subans.add(ceil);

                //  ans.add(subans);
//              }
           
//            return ans;

//     }
// }



// class Solution {

//     int getFloorValue(List<Integer> inOrder, int k)
//     { 
//          int low = 0;
//          int high = inOrder.size()-1;

//          int floor = Integer.MIN_VALUE;

//          while(low<=high)
//          {
//             int mid = low + (high - low)/2;

//              if(inOrder.get(mid) <= k)
//              {
//                  floor = inOrder.get(mid);
//                  low = mid + 1;
//              }
//              else
//              {
//                 high = mid - 1;
//              } 

//          }

//          if(floor == Integer.MIN_VALUE)
//          floor = -1;

//          return floor;
//     }

//     int getCeilValue(List<Integer> inOrder, int k)
//     {
//            int low = 0;
//            int high = inOrder.size() - 1;

//            int ceil = Integer.MAX_VALUE;

//            while(low<=high)
//            {
//               int mid = low + (high - low)/2;

//               if(inOrder.get(mid) >= k)
//               {
//                  ceil = inOrder.get(mid);
//                  high = mid - 1;
//               }
//               else
//               {
//                 low = mid + 1;
//               }
//            }

//            if(ceil == Integer.MAX_VALUE)
//            ceil = -1;

//            return ceil;
//     }

//     void dfs(TreeNode root,List<Integer> inOrder)
//     {
//         if(root == null)
//         return;

//         dfs(root.left,inOrder);
//         inOrder.add(root.val);
//         dfs(root.right,inOrder);
//     }

//     public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
               
//              List<List<Integer>> ans = new ArrayList<>();
              
//               List<Integer> inOrder = new ArrayList<>();  // inOrder of BST give nodes in sorted order by their values !!!

//               dfs(root,inOrder);  

//              for(int k : queries)
//              {
//                  int floor = getFloorValue(inOrder,k);
//                  int ceil =  getCeilValue(inOrder,k);

//                  List<Integer> subans = new ArrayList<>();
//                  subans.add(floor);
//                  subans.add(ceil);

//                  ans.add(subans);
//              }
           
//            return ans;

//     }
// }


class Solution {

    void dfs(TreeNode root,List<Integer> inOrder)
    {
        if(root == null)
        return;

        dfs(root.left,inOrder);
        inOrder.add(root.val);
        dfs(root.right,inOrder);
    }

    public List<List<Integer>> closestNodes(TreeNode root, List<Integer> queries) {
               
             List<List<Integer>> ans = new ArrayList<>();
              
             List<Integer> inOrder = new ArrayList<>();  // inOrder of BST give nodes in sorted order by their values !!!

              dfs(root,inOrder);  

             for(int k : queries)
             {
                 int floor = Integer.MIN_VALUE;
                 int ceil =  Integer.MAX_VALUE;

                 int low = 0;
                 int high = inOrder.size() - 1;

                    while(low<=high)
                    {
                        int mid = low + (high - low)/2;

                        if(inOrder.get(mid) > k)
                        {
                            ceil = inOrder.get(mid);
                            high = mid - 1;
                        }
                        else if(inOrder.get(mid) < k)
                        {
                            floor = inOrder.get(mid);
                            low = mid + 1;
                        }
                        else
                        {
                            floor = ceil = k;
                            break;
                        }
                    }

                        if(floor == Integer.MIN_VALUE)
                        floor = -1;

                        if(ceil == Integer.MAX_VALUE)
                        ceil = -1;


                        List<Integer> subans = new ArrayList<>();
                        subans.add(floor);
                        subans.add(ceil);

                        ans.add(subans);
             }
           
           return ans;

    }
}