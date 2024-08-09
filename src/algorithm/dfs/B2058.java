package algorithm.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다음 N개의 줄에는 원자의 각 에너지 상태에서의 에너지가 주어진다.
 * 물론 두 상태에서의 에너지가 같은 경우는 없다.
 * 다음 M개의 줄에는 양성자가 가질 수 있는 에너지가 주어진다.
 *
 * 출력: 컨테이너 안의 원자들의 에너지들의 총 합의 최대
 */
public class B2058 {

    static int M;
    static boolean[] atoms;
    static int[] protons;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        atoms = new boolean[1_000_001]; // 원자가 가질 수 있는 에너지 값
        protons = new int[M]; // 더할 수 있는 양성자의 에너지 값

        int start = 0;
        for(int i = 0; i < N; i++){
            int n = Integer.parseInt(br.readLine());
            atoms[n] = true;

            if(i == 0) start = n;
        }

        for (int i = 0; i < M; i++)
            protons[i] = Integer.parseInt(br.readLine());

        visited = new boolean[1_000_001];
        int[] result = dfs(start);

        System.out.println(Math.max(result[0], result[1]));

    }

    static int[] dfs(int now){
        // now: 현재 상태
        visited[now] = true;
        int[] result = new int[2]; // [0] : 자기 자신을 포함, [1] : 자기 자신을 포함하지 않음
        result[0] += now;

        for(int i = 0; i < M; i++){
            for(int n : new int[]{now + protons[i], now - protons[i]}){
                if(n >= 0 && n < 1_000_001 && atoms[n] && !visited[n]){
                    int[] tmp = dfs(n); // 자식 결과 반환
                    result[0] += tmp[1];
                    result[1] += Math.max(tmp[0], tmp[1]);
                }
            }
        }

        return result;
    }
}
