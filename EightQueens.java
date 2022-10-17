public class EightQueens {
    
    public static int[][] solve(int columnIndex){ //start from row 0, specify the column index of the first queen

    }
    public static int[][] solve(int[][] grid, int row){
        if(row == 7){
            if(isSolved(grid)){
                return grid;
            }
            else{
                return null;
            }
        }
        for(int i = 0; i < 8; i++){
            //recursively place the queen on each column at this row
            int[][] newGrid = cloneGrid(grid);
            newGrid[row][i] = 1;
            return solve(newGrid, ++row); 
        }
    }
    public static int[][] cloneGrid(int[][] grid){
        int[][] newGrid = new int[grid.length][grid[0].length];

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                newGrid[i][j] = grid[i][j];
            }
        }
        return newGrid;
    }
    public static boolean isSolved(int[][] grid){
        int countedQueens = 0;
        boolean isSolved = true;

        while(isSolved && countedQueens < 7){
            int row = countedQueens;
            int column = findQueen(row, grid[countedQueens]);
            
            isSolved = check(row, column, grid);
            countedQueens++; 
        }
    }
    public static int findQueen(int rowIndex, int[] row){
        for(int i = 0; i < row.length; i++){
            if(row[i] != 0){
                return i;
            }
        }
        return -1;
    }
    public static boolean check(int rowIndex, int columnIndex, int[][] grid){
        //check vertically both directions
        
        //upper direction
    }
}
