package algorithm.자료구조;

import java.io.*;
import java.util.Stack;


public class BOJ1406 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Stack<Character> leftStack = new Stack<>();
        Stack<Character> rightStack = new Stack<>();

        String start = br.readLine();
        int n = Integer.parseInt(br.readLine());

        for (int i=0;i<start.length();i++) {
            leftStack.push(start.charAt(i));
        }

        for (int i=0 ; i<n ; i++) {
            String s = br.readLine();

            switch (s.charAt(0)) {
                case 'P':
                    leftStack.push(s.charAt(2));
                    break;
                case 'B':
                    if (!leftStack.isEmpty()) leftStack.pop();
                    break;
                case 'L':
                    if(!leftStack.isEmpty()) rightStack.push(leftStack.pop());
                    break;
                case 'D':
                    if (!rightStack.empty()) leftStack.push(rightStack.pop());
                    break;
            }
        }
        while(!leftStack.empty()) {
            rightStack.push(leftStack.pop());
        }
        while (!rightStack.empty()) {
            bw.write(rightStack.pop());
        }
        bw.close();
        br.close();
    }
}
