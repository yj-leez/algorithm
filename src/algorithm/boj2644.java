package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj2644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int people = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] connect = new boolean[people + 1][people + 1];
        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connect[a][b] = true;
            connect[b][a] = true;
        }

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[people + 1];

        queue.add(N);
        visited[N] = true;
        int[] answer = new int[people + 1];
        answer[1] = 0;

        while (!queue.isEmpty()){

            int poll = queue.poll();

            if(poll == M){
                System.out.println(answer[poll]);
                return;
            }

            for (int i = 1; i < people + 1; i++) {
                if(connect[poll][i] && !visited[i]){
                    visited[poll] = true;
                    answer[i] = answer[poll] + 1;
                    queue.add(i);
                }
            }

        }

        System.out.println(-1);

    }
}
