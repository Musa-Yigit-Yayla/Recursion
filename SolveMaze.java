

public class SolveMaze {
    
    public static final int UP_DIRECTION = 0;
    public static final int LEFT_DIRECTION = 1;
    public static final int DOWN_DIRECTION = 2;
    public static final int RIGHT_DIRECTION = 3;
    
    private int[][] maze =        {{0,0,0,1,0,0,0,0,0},
                                   {0,1,0,1,0,0,0,0,0},
                                   {0,1,1,0,0,0,0,0,0},
                                   {0,1,0,0,0,0,0,0,0},
                                   {0,0,1,1,1,1,1,1,0},
                                   {0,0,0,1,0,1,0,0,0},
                                   {0,1,0,0,0,0,0,1,5}};
    
    
    public static void main(String[] args){
        SolveMaze mazeSolver = new SolveMaze();
        mazeSolver.solveMaze(0,0);
        printMatrix(mazeSolver.maze);
    }
    //3 is a cell that we have already been, 1 is a wall, 0 is empty cell, 7 is correct path, destination is 5
    public boolean solveMaze( int currentX, int currentY){//startX startY are starting points
        if(maze[currentX][currentY] == 5){
            return true; // destination
        }
        else if(maze[currentX][currentY] == 3){
            return false; // already marked
        }
        //Before moving to somewhere, mark this cell with 3
        maze[currentX][currentY] = 3;
        
        //Clone the current maze(inefficient)
        
        boolean isCorrectPath = false;
        //After the base cases check if you can move to somewhere
        if(isMoveable(currentX, currentY, UP_DIRECTION)){
            isCorrectPath = solveMaze(currentX, currentY - 1);
        }
        if(isMoveable(currentX, currentY, LEFT_DIRECTION) && !isCorrectPath){ // if we already have the correct path, don't change it
            isCorrectPath = solveMaze(currentX - 1, currentY);
        }if(isMoveable(currentX, currentY, DOWN_DIRECTION) && !isCorrectPath){
            isCorrectPath = solveMaze(currentX, currentY + 1);
        }if(isMoveable(currentX, currentY, RIGHT_DIRECTION) && !isCorrectPath){
            isCorrectPath = solveMaze(currentX + 1, currentY);
        }
        if(isCorrectPath){
            //mark this cell with 7
            maze[currentX][currentY] = 7;
        }
        
        return isCorrectPath;
        //!!!!!UPDATE MAZE AFTER EACH FUCKING MOVE MATE
        
    }
    //directin is 0 for up, 1 for left, 2 for down, 3 for right
    public boolean isMoveable(int currentX, int currentY, int direction){
        //check bounds
        if(direction == 0){
            //check if we can move up
            if(currentY == 0){
                return false; //out of bounds
            }
            else if(maze[currentY - 1][currentX] == 1){
                return false; //hit the wall
            }
            //Don't check whether we have already been there
        }
        else if(direction == 1){
            //check if we can move left
            if(currentX == 0){
                return false; //out of bounds
            }
            else if(maze[currentY][currentX - 1] == 1){
                return false; //hit the wall
            }
            //Don't check whether we have already been there
        }
        else if(direction == 2){
            //check if we can move down
            if(currentY == maze.length - 1){ // after maze length is out of bounds
                return false; //out of bounds
            }
            else if(maze[currentY + 1][currentX] == 1){
                return false; //hit the wall
            }
            //Don't check whether we have already been there
        }
        else if(direction == 3){
            //check if we can move right
            if(currentX == maze[0].length - 1){
                return false; //out of bounds
            }
            else if(maze[currentY][currentX + 1] == 1){
                return false; //hit the wall
            }
            //Don't check whether we have already been there
        }
        return true; // everything is satisfied, return true
    }
    public static int[][] clone2DArray(int[][] m){
        int[][] value = new int[m.length][m[0].length];
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                value[i][j] = m[i][j];
            }
        }
        return value;
    }
    public static void printMatrix(int[][] m){
        for(int i = 0; i < m.length; i++){
            for(int j = 0; j < m[i].length; j++){
                System.out.print(m[i][j]);
            }
            System.out.println();
        }
    }
}

