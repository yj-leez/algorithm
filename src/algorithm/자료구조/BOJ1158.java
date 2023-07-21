package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1158 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> q = new LinkedList<Integer>();

        for (int i = 0; i < n; i++) {
            q.add(i+1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int count = 1;
        while(q.size()!=1){
            if(count == k){
                sb.append(q.remove()+", ");
                count = 1;
            }
            else {
                q.add(q.remove());
                count++;
            }
        }
        sb.append(q.remove()+">");
        System.out.println(sb);
    }
}
