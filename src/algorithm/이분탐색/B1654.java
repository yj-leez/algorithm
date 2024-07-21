package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1654 {
    /**
     * parametric search 문제는
     * 최적화 문제를 결정 문제로 바꿀 수 있는지 고려해보고
     * 그 결정 문제로 얻어 낸 함수가 증가 혹은 감소 함수인지 따져봐야한다.
     *
     * 문제에서 최대, 최소 이야기가 있고 범위가 무지막지하게 크거나
     * 시간복잡도에서 로그로 떨구면 될 것 같을 것 같을 때 고려해볼 수 있다.
     *
     * 랜선의 길이는 2^31 - 1보다 작거나 같은 자연수이므로 int가 아닌 long의 값을 가져야한다.
     */

    static int[] cables;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken()); // 이미 가지고 있는 랜선의 개수
        int N = Integer.parseInt(st.nextToken()); // 필요한 랜선의 개수

        cables = new int[K];
        long hi = 0;
        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
            hi = Math.max(hi, cables[i]);
        }

        if (cal(hi) >= N){
            System.out.println(hi);
            return;
        }

        long lo = 1;
        while(lo < hi - 1){
            long mid = (lo + hi) / 2;

            if (cal(mid) >= N) lo = mid;
            else hi = mid;
        }

        System.out.println(lo);

    }
    static int cal(long length){
        int cnt = 0;
        for(int cable: cables){
            cnt += cable / length;
        }
        return cnt;
    }
}
