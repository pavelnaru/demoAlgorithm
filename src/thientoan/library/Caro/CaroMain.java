package thientoan.library.Caro;

import thientoan.learning.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class CaroMain {
    int boardSizeX = 10;
    int boardSizeY = 10;
    int winStreak = 5;

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndtime() {
        return endtime;
    }

    public void setEndtime(long endtime) {
        this.endtime = endtime;
    }

    long startTime, endtime;
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
        long startTime, endtime;

        System.out.println("type 1 for randomly play\ntype 2 for manual play\ntype 3 for test\n");
        String choice = Main.sc.nextLine();

        System.out.println("game is starting");
        switch (choice){
            case "1":
                playAuto(markerCounter, x, y);
                break;
            case "2":

                break;
            case "3":
                test();
                break;
            default:
                playAuto(markerCounter, x, y);
                break;
        }


    }


    protected void drawBoardToConsole(){
        StringBuilder result = new StringBuilder();
        for (int colX=0 ; colX < boardSizeX; colX++){
            for(int colY=0 ; colY < boardSizeY; colY++){
                result.append(cellPadding + board[colX][colY].getType() + cellPadding);
            }
            result.append(System.lineSeparator());
        }
        System.out.println(result.toString());
    }

    protected void playAuto(int markerCounter, int x, int y){
        boolean gameIsOver = false;
        String winSide = null;
        while (!(gameIsOver || winSide!=null)){
            x = (int)(Math.random()*100)%boardSizeX;
            y= (int)(Math.random()*100)%boardSizeY;
            System.out.printf("\nround= %d; x= %d; y= %d; type = %s\r\n",markerCounter, x, y, markers[markerCounter%2] );
            board[x][y].tick(markers[markerCounter%2]);
            markerCounter++;
            drawBoardToConsole();

            gameIsOver = checkEnd();
            winSide =checkWin();
        }
//        if (winSide!=null){
//            System.out.printf("Side of \"%s\" is win\n", winSide);
//        }else {
//            System.out.println("All cells are ticked. Game over");
//        }

    }
    protected boolean checkEnd(){
        boolean result= true;
        Pattern emptyCell = Pattern.compile("\\.+");
        setStartTime(System.nanoTime());
        for (int x= 0 ; x< boardSizeX; x++){
            CaroBoardCell[] row = board[x];
            String rowValue = getCellsValue(row);
            if (emptyCell.matcher(rowValue).find()){
//                System.out.println("Empty cell are found. Keep playing");
//                setEndtime(System.nanoTime());
//                System.out.printf("check finished in %fms\n\n", (float)(getEndtime()-getStartTime())/1000000);
                result = false;
                break;
            }
//            System.out.println(rowValue);
        }
        setEndtime(System.nanoTime());
        System.out.printf("check END finished in %fms\n", (float)(getEndtime()-getStartTime())/1000000);
        if (result)System.out.println("All cells are ticked. Game over");
        return result;
    }

    protected String[] collectLines(){
        ArrayList<String> result = new ArrayList<>();

        // collect rows
        for (int x= 0 ; x< boardSizeX; x++){
            CaroBoardCell[] row = board[x];
            String rowValue = getCellsValue(row);
            result.add(rowValue);
        }
        ArrayList<CaroBoardCell> colCells= new ArrayList<>();

        // collect cols
        for (int y= 0 ; y< boardSizeY; y++){
            colCells.clear();
            for (int x= 0 ; x< boardSizeX; x++){
                colCells.add(board[x][y]);
            }
            CaroBoardCell[] cellArray = new CaroBoardCell[colCells.size()];
            cellArray = colCells.toArray(cellArray);
            String colValue = getCellsValue(cellArray);
            result.add(colValue);
        }

        //collect diagonals
        result.addAll(collectDiagonalLinesTopLeft2BotRight());
        result.addAll(collectDiagonalLinesTopRight2BotLeft());



        String[] resultArray = new String[result.size()];
        return (result.toArray(resultArray));
    }
    private ArrayList<String> collectDiagonalLinesTopLeft2BotRight(){
        int stepX=1;
        int stepY=1;
        ArrayList<String> diagonalLines = new ArrayList<>();
        ArrayList<CaroBoardCell> diagonalLineCells = new ArrayList<>();
        int defaultX = 0; int defaultY = 0;

        // collect at board's bottom
        for (int difX = 0; difX < boardSizeX ; difX++ ){
            diagonalLineCells.clear();
            int x = defaultX + difX;
            int y = defaultY;
            while ( x < boardSizeX && y < boardSizeY ){
                diagonalLineCells.add(board[x][y]);
                x+=stepX;
                y+=stepY;
            }
            CaroBoardCell[] lineArray = new CaroBoardCell[diagonalLineCells.size()];
            lineArray = diagonalLineCells.toArray(lineArray);
            String lineValue = getCellsValue(lineArray);
            diagonalLines.add(lineValue);
        }
        // collect at board's top
        for (int difY = 0; difY < boardSizeY ; difY++ ){
            diagonalLineCells.clear();
            int x = defaultX;
            int y = defaultY + difY;
            while ( x < boardSizeX && y < boardSizeY ){
                diagonalLineCells.add(board[x][y]);
                x+=stepX;
                y+=stepY;
            }
            CaroBoardCell[] lineArray = new CaroBoardCell[diagonalLineCells.size()];
            lineArray = diagonalLineCells.toArray(lineArray);
            String lineValue = getCellsValue(lineArray);
            diagonalLines.add(lineValue);
        }

        return diagonalLines;
    }

    private ArrayList<String> collectDiagonalLinesTopRight2BotLeft() {
        int stepX=1;
        int stepY=-1;
        ArrayList<String> diagonalLines = new ArrayList<>();
        ArrayList<CaroBoardCell> diagonalLineCells = new ArrayList<>();
        int defaultX = 0;
        int defaultY = 9;

        // collect at board's bottom
        for (int difX = 0; difX < boardSizeX ; difX++ ){
            diagonalLineCells.clear();
            int x = defaultX + difX;
            int y = defaultY;
            while ( x < boardSizeX && y >=0 ){
                diagonalLineCells.add(board[x][y]);
                x+=stepX;
                y+=stepY;
            }
            CaroBoardCell[] lineArray = new CaroBoardCell[diagonalLineCells.size()];
            lineArray = diagonalLineCells.toArray(lineArray);
            String lineValue = getCellsValue(lineArray);
            diagonalLines.add(lineValue);
        }
        // collect at board's top
        for (int difY = 0; difY < boardSizeX ; difY++ ){
            diagonalLineCells.clear();
            int x = defaultX;
            int y = defaultY - difY;
            while ( x < boardSizeX && y >=0 ){
                diagonalLineCells.add(board[x][y]);
                x+=stepX;
                y+=stepY;
            }
            CaroBoardCell[] lineArray = new CaroBoardCell[diagonalLineCells.size()];
            lineArray = diagonalLineCells.toArray(lineArray);
            String lineValue = getCellsValue(lineArray);
            diagonalLines.add(lineValue);
        }
        return diagonalLines;
    }

    protected String checkWin(){
        String markerWin = null;
        setStartTime(System.nanoTime());
        for (String line: collectLines()) {
            for (String marker: markers){
                boolean isWin = checkWin(line, marker);
                if (isWin){
                    markerWin= marker;
                }
            }
        }
        setEndtime(System.nanoTime());
        System.out.printf("check WIN finished in %fms\n", (float)(getEndtime()-getStartTime())/1000000);
        if (markerWin!=null) System.out.printf("Side of \"%s\" is win\n\n", markerWin);
        return markerWin;
    }
    protected boolean checkWin(String input, String marker){
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(marker).append("){").append(winStreak).append(",}");
        String patternString = sb.toString();
        Pattern pattern = Pattern.compile(patternString);
        return pattern.matcher(input).find();

    }
    protected String getCellsValue(CaroBoardCell[]inputCells){
        String result = "";
        for (int i = 0 ; i < inputCells.length; i++){
            result+=inputCells[i].getType();
        }
        return result;
    }

    private void test(){
        System.out.println("calling tests");
        subTest(2,5,0,1, markers[0]);
        subTest(2,5,1,0, markers[0]);
        subTest(2,0,1,1, markers[0]);
        subTest(2,4,1,1, markers[1]);
        subTest(0,8,1,-1, markers[0]);
        subTest(2,8,1,-1, markers[1]);


    }
    private void subTest(int initX, int initY, int difX, int difY, String marker){
        System.out.println("\nnew subtest");
        int x=initX;
        int y=initY;
        for (int i=0 ; i < winStreak; i++){
            board[x][y].tick(marker);
            x+=difX;y+=difY;
        }
        drawBoardToConsole();
        checkWin();
        System.out.println("end subtest");
        boardReset();
    }

    private void boardReset(){
        for (CaroBoardCell[] rows: board) {
            for (CaroBoardCell cell: rows) {
                cell.reset();
            }
        }
    }

}
