package algorithm.dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B14496 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken()); // a -> b로 문자를 바꿈

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 총 문자의 개수
        int M = Integer.parseInt(st.nextToken()); // 치환 가능한 문자쌍의 개수

        ArrayList<Integer> [] lists = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) lists[i]=new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            lists[x].add(y);
            lists[y].add(x);
        }

        Queue<Integer> q = new ArrayDeque<>();
        int[] dijk = new int[N + 1];

        q.add(a);
        Arrays.fill(dijk, Integer.MAX_VALUE);
        dijk[a] = 0;
        while(!q.isEmpty()){
            int poll = q.poll();
            for(int tmp :lists[poll]){
                if(dijk[tmp] > dijk[poll] + 1){
                    q.add(tmp);
                    dijk[tmp] = dijk[poll] + 1;
                }
            }
        }
        System.out.println(dijk[b] == Integer.MAX_VALUE? -1: dijk[b]);
    }
}
