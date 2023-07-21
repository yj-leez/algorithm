package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9012 {
    public static char[] stack = new char[50];
    public static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < s.length(); j++) {
                if(s.charAt(j)=='(') push('(');
                else if (pop()!='(') {size=-1; break;}
            }
            if(size==0) sb.append("YES\n");
            else sb.append("NO\n");

            size=0;
        }
        System.out.println(sb);
        br.close();

    }
    private static void push(char ch){
        stack[size] = ch;
        size++;
    }
    private static char pop(){
        if(size != 0) return stack[--size];
        else return '0';
    }
}
