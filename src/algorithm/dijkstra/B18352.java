package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B18352 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 도시 개수
        int M = Integer.parseInt(st.nextToken()); // 도로 개수
        int K = Integer.parseInt(st.nextToken()); // 거리 정보
        int X = Integer.parseInt(st.nextToken()); // 출발 도시 번호

        ArrayList<Integer>[] lists = new ArrayList[N + 1]; // 가능한 도로
        for (int i = 0; i < N + 1; i++) lists[i] = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lists[start].add(end);
        }

        int[] dijk = new int[N + 1];
        Arrays.fill(dijk, Integer.MAX_VALUE);
        Queue<Integer> q = new ArrayDeque<>();

        q.add(X);
        dijk[X] = 0;
        while(!q.isEmpty()){
            int p = q.poll();

            for(int tmp : lists[p]){
                if (dijk[tmp] > dijk[p] + 1){
                    dijk[tmp] = dijk[p] + 1;
                    q.add(tmp);
                }
            }
        }

        boolean flag = false;
        for(int i = 0; i < dijk.length; i++){
            if(dijk[i] == K){
                System.out.println(i);
                flag = true;
            }
        }

        if (!flag) System.out.println(-1);

    }
}
