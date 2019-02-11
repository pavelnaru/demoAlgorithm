package thientoan.library;

public class BoardLocation {
    private int locX;
    private int locY;
    public BoardLocation(int locX, int locY){
        this.locX = locX;
        this.locY= locY;
    }

    public int getLocX() {
        return locX;
    }

    public void setLocX(int locX) {
        this.locX = locX;
    }

    public int getLocY() {
        return locY;
    }

    public void setLocY(int locY) {
        this.locY = locY;
    }
}
