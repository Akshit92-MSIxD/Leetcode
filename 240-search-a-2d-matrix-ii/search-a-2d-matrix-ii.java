class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
            
             int rows = matrix.length;
             int cols = matrix[0].length;

             int cr = 0;
             int cc = cols - 1;

             while(cr >= 0 && cr < rows && cc >= 0 && cc < cols)
             {
                 if(matrix[cr][cc] == target)
                  return true;
                 else if(target < matrix[cr][cc])
                  cc--;
                 else
                  cr++;
             }

             return false;
    }
}