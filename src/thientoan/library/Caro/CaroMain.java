package thientoan.library.Caro;

import thientoan.learning.Main;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CaroMain {
    int boardSizeX = 5;
    int boardSizeY = 5;
    String cellPadding = " ";
    CaroBoardCell[][] board = new CaroBoardCell[boardSizeX][boardSizeY];
    String[] markers;

    public CaroMain(){
        // init board cells
        for (int colX=0 ; colX < boardSizeX; colX++){
            for(int colY=0 ; colY < boardSizeY; colY++){
                board[colX][colY] = new CaroBoardCell(colX , colY);
            }
        }
        //init markers
        markers = new String[]{"x", "o"};
    }
    public void play(){
        int markerCounter = 0;
        int x=0;
        int y=0;
        System.out.println("type \"y\" for randomly play\n");
        boolean isAuto = Main.sc.nextLine().equals("y");

        System.out.println("game is starting");
        if (isAuto){
            playAuto(markerCounter, x, y);

        }else{

        }

    }


    protected String drawBoardToConsole(){
        StringBuilder result = new StringBuilder();
        for (int colX=0 ; colX < boardSizeX; colX++){
            for(int colY=0 ; colY < boardSizeY; colY++){
                result.append(cellPadding + board[colX][colY].getType() + cellPadding);
            }
            result.append(System.lineSeparator());
        }
        return result.toString();
    }

    protected void playAuto(int markerCounter, int x, int y){
        while (!(checkEnd() || checkWin())){

            x = (int)(Math.random()*100)%boardSizeX;
            y= (int)(Math.random()*100)%boardSizeY;
            System.out.printf("x= %d ; y= %d; type = %s\r\n",x,y,markers[markerCounter%2] );
            board[x][y].tick(markers[markerCounter%2]);
            markerCounter++;
            System.out.println(drawBoardToConsole());


        }
//            for(int i=0; i< 20; i++){
//                x = (int)(Math.random()*100)%10;
//                y= (int)(Math.random()*100)%10;
//                System.out.printf("x= %d ; y= %d; type = %s\r\n",x,y,markers[markerCounter%2] );
//                board[x][y].tick(markers[markerCounter%2]);
//                markerCounter++;
//                System.out.println(drawBoardToConsole());
//        }
    }
    protected boolean checkEnd(){
        Pattern emptyCell = Pattern.compile("\\.+");

        for (int x= 0 ; x< boardSizeX; x++){
            CaroBoardCell[] row = board[x];
            String rowValue = getCellsValue(row);
            if (emptyCell.matcher(rowValue).find()){
                System.out.println("Empty cell are found. Keep playing");
                return false;
            }
//            System.out.println(rowValue);
        }
        System.out.println("All cells are ticked. Game over");
        return true;
    }
    protected boolean checkWin(){
        return false;
    }
    protected String[] collectLines(){
        ArrayList<String> result = new ArrayList<>();
        for (int x= 0 ; x< boardSizeX; x++){
            CaroBoardCell[] row = board[x];
            String rowValue = getCellsValue(row);
            result.add(rowValue);
        }
        ArrayList<CaroBoardCell> colCells= new ArrayList<>();
        for (int y= 0 ; y< boardSizeY; y++){
            colCells.clear();
            for (int x= 0 ; x< boardSizeX; x++){
                colCells.add(board[x][y]);
            }
            String colValue = getCellsValue((CaroBoardCell[]) colCells.toArray());
            result.add(colValue);
        }
        result.addAll(collectDiagonalLinesTopLeft2BotRight());

        return (String[]) result.toArray();
    }
    private ArrayList<String> collectDiagonalLinesTopLeft2BotRight(){
        int stepX=1;
        int stepY=1;
        ArrayList<String> diagonalLines = new ArrayList<>();
        ArrayList<CaroBoardCell> diagonalLineCells = new ArrayList<>();
        int defaultX = 0; int defaultY = 0;
        for (int difX = 0; difX < boardSizeX ; difX++ ){
            diagonalLineCells.clear();
            int x = defaultX + difX;
            int y = defaultY;
            while ( x < boardSizeX && y < boardSizeY ){
                diagonalLineCells.add(board[x][y]);
                x+=stepX;
                y+=stepY;
            }
            String lineValue = getCellsValue((CaroBoardCell[]) diagonalLineCells.toArray());
            diagonalLines.add(lineValue);
        }





        return diagonalLines;
    }


    protected boolean checkWin(String input, String marker){
        String patternString = "(" + marker + "){5}";
        Pattern pattern = Pattern.compile(patternString);
        return pattern.matcher(input).matches();

    }
    protected String getCellsValue(CaroBoardCell[]inputCells){
        String result = "";
        for (int i = 0 ; i < inputCells.length; i++){
            result+=inputCells[i].getType();
        }
        return result;
    }

}
