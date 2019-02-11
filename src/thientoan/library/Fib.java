package thientoan.library;

import thientoan.learning.Main;

import java.util.*;

public class Fib
{
    ArrayList<Integer> fibSequence;
    Integer[] init = {0,1};
    public Fib(){
        fibSequence = new ArrayList<>();
    }
    public int[] checkInputAsInteger(String input){
        int[] result = {1,0};
        try{
            result[1] = Integer.parseInt(input);
        }catch (Exception ex){
            System.out.println(ex);
            result[0] = 0;
        }
        return result;
    }

    public boolean checkIntegerWithinFib(int input){
        Integer[] init = {0,1};
        fibSequence.addAll(Arrays.asList(init));
        while (fibSequence.get(1) < input){
            addNextFib();
        }
        if (fibSequence.get(1) == input){
            return true;
        }
        else {
            return false;
        }

    }

    public void addNextFib(){
        int a = fibSequence.get(fibSequence.size()-1)+ fibSequence.get(fibSequence.size()-2);
        fibSequence.add(Integer.valueOf(a));
    }

    public void check(){
        System.out.println("check if input integer belongs to Fibonanci series or not");
        System.out.println("Enter your integer: ");
        String input = Main.sc.nextLine();
        int[] checkInput = checkInputAsInteger(input);
        if (checkInput[0]!= 1 || checkInput[1]<=0){
            System.out.println("Input are not valid");
            return;
        }
        if (checkIntegerWithinFib(checkInput[1])){
            System.out.println("this belongs to Fibonancy series");
        }else{
            System.out.println("it dosnt belong to Fibonancy series");
        }
    }

    public void print1(){
        System.out.println("print Fib series");
        System.out.println("Enter index: ");
        String input = Main.sc.nextLine();
        int[] checkInput = checkInputAsInteger(input);
        if (checkInput[0]!= 1 || checkInput[1]<=0){
            System.out.println("Input are not valid");
            return;
        }
        generateFib(checkInput[1]);
        System.out.println(this.toString());

    }
    public void generateFib (int index){
        fibSequence.clear();
        fibSequence.addAll(Arrays.asList(init));
        int currentIndex = 2;
        while (currentIndex++<= index){
            addNextFib();
        }
    }
    @Override
    public String toString(){
        String result ="";
        Iterator<Integer> iter = fibSequence.iterator();
        while (iter.hasNext()){
            Integer i = iter.next();
            result = (result.equals(""))? i.toString() : result + " ; " + i.toString();
        }
        return result;
    }

}

