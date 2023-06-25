package algorithm.조건문;

import java.util.Scanner;

public class BOJ2525 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int h = in.nextInt();
        int m = in.nextInt();
        int add = in.nextInt();

        int total = h*60 + m + add;
        System.out.println(((total/60)%24) + " " +(total%60));
/**
        int minutes = m + time;
        if(minutes < 59) System.out.println(h + " " + minutes);
        else {
            if((h + (minutes/60)) > 23) System.out.println((h + (minutes/60) - 24) + " " +(minutes % 60));
            else System.out.println((h + (minutes/60))+ " " + (minutes % 60));
        }
 **/


    }
}
