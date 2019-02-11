package thientoan.learning;

import thientoan.library.Caro.CaroMain;
import thientoan.library.Fib;

import java.util.Scanner;

public class Main {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {

        String nextLoop;
        do{
//            Fib fib = new Fib();
////            fib.check();
//            fib.print1();

            CaroMain caro = new CaroMain();
            caro.play();


            System.out.println("press y to continue");
            nextLoop = sc.nextLine();
            System.out.println("");System.out.println("");
        }while (nextLoop!="y");
        System.out.println(nextLoop);
        System.out.println("end of process");

    }




}
