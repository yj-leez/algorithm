package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ10828 {
    public static int[] stack;
    public static int size = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        stack = new int[n];

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();

            switch (s){
                case "push":
                    push(Integer.parseInt(st.nextToken())); break;
                case "pop":
                    sb.append(pop()).append("\n"); break;
                case "size":
                    sb.append(size).append("\n"); break;
                case "empty":
                    sb.append(empty()).append("\n"); break;
                case "top":
                    sb.append(top()).append("\n"); break;
            }
        }
        System.out.println(sb);
    }

    private static int top() {
        if(size!=0) return stack[size-1];
        else return -1;
    }

    private static int empty() {
        if(size==0) return 1;
        else return 0;
    }

    private static int pop() {
        if(size != 0) return stack[--size];
        else return -1;
    }

    private static void push(int num) {
        stack[size] = num;
        size++;
    }
}
