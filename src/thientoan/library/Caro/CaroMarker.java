package thientoan.library.Caro;

import thientoan.library.BoardLocation;

import java.util.ArrayList;

public class CaroMarker {

    String type;
    ArrayList<BoardLocation> listLoc = new ArrayList<>();

    public String getType() {
        return type;
    }

    public ArrayList<BoardLocation> getListLoc() {
        return listLoc;
    }

    public void setListLoc(ArrayList<BoardLocation> listLoc) {
        this.listLoc = listLoc;
    }


    public CaroMarker(String type){
        this.type = type;
    }
    public void addMarker(int x, int y){
        this.listLoc.add(new BoardLocation(x,y));
    }









}
