/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author yigit
 */
import java.util.ArrayList;
import java.util.Collections;


public class KnightsTour {
    private Board board;
    
    public int[][] solve(int currentX, int currentY){
        Board clone = (Board)board.clone();
        int[][] valueGrid = new int[8][8];
        solveHelper(board.getSquare(currentY, currentX), valueGrid, 0);

        //if(solveHelper(board.getSquare(currentY, currentX), valueGrid)){
            return valueGrid;
        //}
        //return null;
    }
    public boolean solveHelper(Square s, int[][] valueGrid, int prevSquareValue){
        s.setValue(prevSquareValue + 1);
        if(this.board.isSolved()){
            //base case, return true
            return true;
        }
        boolean done = false;
        
        ArrayList<Square> moveables = s.calculateMoveableSquares();
        for(int i = 0; i < moveables.size(); i++){
            done = solveHelper(moveables.get(i), valueGrid, s.getValue());
            valueGrid[s.getColumnIndex()][s.getRowIndex()] = s.getValue();
            if(done){
                
                break;
            }
        }
        return done;
    }
    public static void main(String[] args){
        System.out.println("zaaaaa");
        KnightsTour tour = new KnightsTour();
        tour.board = new Board(2,2);
        int[][] grid = tour.solve(2,2);
        if(grid == null)
         System.out.println("xd");
        else
         print2D(grid);
    }
 public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++){
 
            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++)
                System.out.print(mat[i][j] + " ");
        
            System.out.println();
        }
        
    }    
    
}
class Board implements Cloneable{
    private Square[][] squares = new Square[8][8];//chess board, 0 specifies unmarked cells, whenever a move is made mark that cell with moveCount value
    private int moveCount = 0; //specifies the number of moves made, if it reaches 63 then we solved the puzzle
    private int currentRow, currentColumn;
    private Square currentSquare;
    
    public Board(int currentRow, int currentColumn){
        this.currentRow = currentRow;
        this.currentColumn = currentColumn;
        //instantiate squares one by one
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                squares[i][j] = new Square(i, j, this);
            }
        }
        this.setCurrentSquare();
    }
    public Board(int currentRow, int currentColumn, int moveCount){
        //this constructor does not set the squares
        this.currentRow = currentRow;
        this.currentColumn =  currentColumn;
        this.moveCount = moveCount;
        this.setCurrentSquare();
    }
    public int getMoveCount(){
        return this.moveCount;
    }
    public void moveTo(int row, int column){
        this.currentRow = row;
        this.currentColumn = column;
        this.moveCount++;
        this.squares[row][column].setValue(this.moveCount);
    }
    public void setSquares(Square[][] board){
        this.squares = board;
    }
    public void setCurrentSquare(){
        this.currentSquare = squares[currentRow][currentColumn];
    }
    @Override
    public Object clone(){
        Board board = new Board(currentRow, currentColumn, this.moveCount);
        Square[][] newSquares = new Square[8][8];
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                Square s = this.squares[i][j];
                Square newSquare = new Square(s.getRowIndex(), s.getColumnIndex(), board,s.getValue());
                newSquares[i][j] = newSquare;
            }
        }
        board.setSquares(newSquares);
        return board;
    }
    public Square getSquare(int row, int column){
        return this.squares[row][column];
    }
    public boolean isSolved(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(squares[i][j].getValue() == 63)
                    return true;
            }
        }
        return false;
    }
    
}

class Square implements Comparable{
    private Board board;
    private int row;
    private int column; //these specify coordinates of board, 
    private int value; //value represents the moveCount on the board
    private double shortestDistanceToCorner; //shortest of the corner distances
    
    public Square(int row, int column, Board board){
        this.board =  board;
        this.row = row;
        this.column = column;
        this.value = 0;
        this.calculateShortestDistance();
    }
    public Square(int row, int column, Board board, int value){
        this(row, column, board);
        this.value = value;
    }
    public int getRowIndex(){
        return this.row;
    }
    public int getColumnIndex(){
        return this.column;
    }
    public int getValue(){
        return this.value;
    }
    public void calculateShortestDistance(){
        double d1 = Math.sqrt(Math.pow(this.row,2) + Math.pow(this.column, 2));
        double d2 = Math.sqrt(Math.pow(7 - this.row,2) + Math.pow(this.column, 2)); // bottom left
        double d3 = Math.sqrt(Math.pow(7 - this.row,2) + Math.pow(7 - this.column, 2)); //bottom right
        double d4 = Math.sqrt(Math.pow(this.row,2) + Math.pow(7 - this.column, 2));
        
        double min1 = Math.min(d1, d2);
        double min2 = Math.min(min1, d3);
        double min3 = Math.min(min2,d4);
        
        this.shortestDistanceToCorner = min3;
    }
    public ArrayList<Square> calculateMoveableSquares(){
        ArrayList<Square> moveableSquares = new ArrayList<>();
        
        int value = this.value;
        int row = this.row;
        int column = this.column;
        
        //2 right 1 up
        Square s;
        if(row + 2 < 8 && column + 1 < 8){
            s = this.board.getSquare(row + 2, column + 1);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }
        //1 right 2 up
        if(row + 1 < 8 && column + 2 < 8){
            s = this.board.getSquare(row + 1, column + 2);
            if(s.getValue() == 0){
                moveableSquares.add(s);
            }
        }
        //1 left 2 up
        if(row - 1 >= 0 && column + 2 < 8){
            s = this.board.getSquare(row - 1, column + 2);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }
        //2 left 1 up
        if(row -2 >= 0 && column + 1 < 8){
            s = this.board.getSquare(row - 2, column + 1);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }//2 left 1 down
        if(row - 2 >= 0 && column - 1 >= 0){
            s = this.board.getSquare(row - 2, column - 1);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }
        //1 left 2 down
        if(row - 1 >= 0 && column - 2 >= 0 ){
            s = this.board.getSquare(row - 1, column - 2);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }
        //1 right 2 down
        if(row + 1 < 8 && column - 2 >= 0 ){
            s = this.board.getSquare(row + 1, column -2);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }
        //2 right 1 down
        if(row + 2  < 8 && column - 1 >= 0 ){
            s = this.board.getSquare(row + 2, column - 1);
            if(s.getValue() == 0){
                //we haven't been here before
                moveableSquares.add(s);
            }
        }
        Collections.sort(moveableSquares);
        
        return moveableSquares;
    }
    public void setValue(int value){
        this.value = value;
    }
    @Override
    public int compareTo(Object obj){ //closer to the corner returns - value
        Square s = (Square)(obj);
        return (int)(this.shortestDistanceToCorner - s.shortestDistanceToCorner);
    }
}