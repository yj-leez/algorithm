package algorithm.unionFind;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1976 {
    static int[] parents;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // 도시의 수, 0 ~ N - 1
        int M = Integer.parseInt(br.readLine()); // 여행 계획에 속한 도시의 수

        parents = new int[N];
        for(int i = 0; i < N; i++)
            parents[i] = i;

        StringTokenizer st;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                if(Integer.parseInt(st.nextToken()) == 1 && i < j){
                    union(i, j);
                }
            }
        }

        // 여행 계획에 속한 도시의 부모가 일치하는지 확인
        st = new StringTokenizer(br.readLine());
        int parent = find(Integer.parseInt(st.nextToken()) - 1);
        for(int i = 1; i < M; i++){
            if(parent != find(Integer.parseInt(st.nextToken()) - 1)){
                System.out.println("NO");
                return;
            }
        }

        System.out.println("YES");

    }
    static int find(int x){
        if(x == parents[x]){
            return x;
        }

        return parents[x] = find(parents[x]);
    }

    static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a != b){
            if(a < b){
                parents[b] = a;
            } else {
                parents[a] = b;
            }
        }
    }

}
