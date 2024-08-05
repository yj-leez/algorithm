package algorithm.slidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 부분 배열의 크기는 정해져 있으므로 슬라이딩 윈도우로 중복되는 접시가 존재하는지 확인한다.
 */
public class B2531 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 접시의 수
        int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
        int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] dishes = new int[N];
        for(int i = 0; i < N; i++){
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // 초밥 종류를 세기 위한 배열
        int[] eaten = new int[d + 1];
        int currentCnt = 0;
        int maxCnt = 0;

        // 초기 윈도우 설정
        for (int i = 0; i < k; i++) {
            if (eaten[dishes[i]] == 0) {
                currentCnt++;
            }
            eaten[dishes[i]]++;
        }

        maxCnt = currentCnt + (eaten[c] == 0? 1: 0);

        // 슬라이딩 윈도우를 오른쪽으로 이동하며 검사
        for (int i = 1; i < N; i++) {
            int start = i - 1;
            int end = (i + k - 1) % N;

            // 윈도우에 새 초밥 추가
            if (eaten[dishes[end]] == 0) {
                currentCnt++;
            }
            eaten[dishes[end]]++;

            // 윈도우에서 초밥 제거
            eaten[dishes[start]]--;
            if (eaten[dishes[start]] == 0) {
                currentCnt--;
            }

            // 최대 초밥 종류 수 업데이트
            maxCnt = Math.max(maxCnt, currentCnt + (eaten[c] == 0 ? 1 : 0));
        }

        System.out.println(maxCnt);
    }
}
