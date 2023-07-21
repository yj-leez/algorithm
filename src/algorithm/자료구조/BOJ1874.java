package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1874 {
    public static int[] stack;
    public static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        int[] cmp = new int[n];
        int[] put = new int[n];
        stack = new int[n];
        for (int i = 0; i < n; i++) {
            cmp[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;
        for (int i = 1; i <= n; i++) {
            push(i);
            sb.append("+\n");
            while((cnt<n)&&(top()==cmp[cnt])){
                put[cnt]=pop();
                cnt++;
                sb.append("-\n");
            }
        }
        boolean result = true;
        for (int i = 0; i < n; i++) {
            if(cmp[i]!=put[i]) {
                result = false;
                break;
            }
        }
        if(result) System.out.println(sb);
        else System.out.println("NO");

        br.close();
    }

    private static void push(int num){
        stack[size] = num;
        size++;
    }
    private static int pop(){
        if(size != 0) return stack[--size];
        else return -1;
    }
    private static int top(){
        if(size !=0) return stack[size-1];
        else return -1;
    }
}
