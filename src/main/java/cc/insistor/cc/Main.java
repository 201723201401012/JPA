package cc.insistor.cc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cc
 */


public class Main {
    private static interface Calculate{
        int calculate(int a,int b);
    }

    public static void main(String[] args) {
        Calculate calculate = Main::calculate;
        System.out.println(calculate.calculate(10,1));


    }
    private static int calculate(int a,int b){
       if(a > b){
           return a - b;
       }
       else{
           return b - a;
       }
    }

}

