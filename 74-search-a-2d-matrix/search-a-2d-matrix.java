class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
         
              int rows = matrix.length;
              int cols = matrix[0].length;

              int low = 0;
              int high = rows*cols - 1;

              while(low <= high)
              {
                int mid = low + (high - low)/2;
                 
                 int i = mid / cols;
                 int j = mid % cols;

                 if(matrix[i][j] == target)
                  return true;
                 else if(target > matrix[i][j])
                  low = mid + 1;
                 else
                  high = mid - 1;
              }

              return false;
          
    }
}