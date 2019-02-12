package thientoan.library.Caro;

import thientoan.library.BoardLocation;

public class CaroBoardCell {
    public String getType() {
        return type;
    }

    public void tick(String type) {
        if (this.type.equals(defaultType)) {
            this.type = type;
        }
    }
    public void reset(){
        this.type = defaultType;
    }

    final String defaultType = ".";
    String type = defaultType;
    boolean isChecked = false;
    BoardLocation boardLoc;

    public CaroBoardCell(int x, int y){
        boardLoc = new BoardLocation(x,y);
    }


}
