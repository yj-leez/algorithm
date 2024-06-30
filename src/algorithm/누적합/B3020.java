package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B3020 {
    /**
     * 구간마다 N미터 거리를 다 탐색하며 돈다면 최악의 경우 N * H = 100,000,000,000이므로 시간 초과가 난다.
     * 만약 네 번째 구간의 석순이 3개라면, 세 번째 구간의 석순은 네 번째 구간의 석순 개수 + 3m의 석순 개수이다.
     * 그리고 각 구간의 석순과 종유석을 합하면 총 장애물의 개수이다.
     * 이렇게 누적합의 성질을 이용하면 최악의 경우 시간복잡도도 3 * H + N이므로 괜찮다.
     *
     * N * H의 시간복잡도가 걸리는 탐색하는 로직을 이분탐색으로 줄일 수도 있다고 한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] fromFloor = new int[H + 1]; // 0 ~ H
        int[] fromCeiling = new int[H + 1]; // 0 ~ H

        for(int i = 0; i < N; i++){
            if (i % 2 == 0) fromFloor[Integer.parseInt(br.readLine())]++;
            else fromCeiling[Integer.parseInt(br.readLine())]++;
        }

        for (int i = H - 1; i > 0; i--){
            fromFloor[i] += fromFloor[i + 1];
            fromCeiling[i] += fromCeiling[i + 1];
        }

        int[] obstacleCnt = new int[H + 1];
        int i = 1;
        int j = H;
        int min = Integer.MAX_VALUE;
        while(i <= H){
            obstacleCnt[i] = fromFloor[i] + fromCeiling[j];
            if (obstacleCnt[i] < min) min = obstacleCnt[i];
            i++;
            j--;
        }


        int ans = 0;
        for(int k = 1; k <= H; k++){
            if (min == obstacleCnt[k]) ans++;
        }

        System.out.println(min + " " + ans);

    }
}
