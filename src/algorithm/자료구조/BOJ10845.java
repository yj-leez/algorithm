package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class BOJ10845 {
    public static int[] queue = new int[10000];
    public static int first = 0;
    public static int last = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()){
                case "push":
                    push(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    sb.append(pop()+"\n");
                    break;
                case "size":
                    sb.append((last-first+1)+"\n");
                    break;
                case "empty":
                    sb.append(empty()+"\n");
                    break;
                case "front":
                    sb.append(front()+"\n");
                    break;
                case "back":
                    sb.append(back()+"\n");
                    break;
            }
        }
        System.out.println(sb);
        br.close();
    }

    private static int front() {
        if(first>last) return -1;
        else return queue[first];
    }
    private static int back() {
        if(first>last) return -1;
        else return queue[last];
    }
    private static int empty() {
        if(first>last) return 1;
        else return 0;
    }

    private static int pop() {
        if(first>last) return -1;
        else return queue[first++];
    }

    private static void push(int i) {
        queue[++last]=i;
    }
}
