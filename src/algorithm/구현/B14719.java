package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14719 {
    /**
     * 엣지케이스 고려 못했음
     * 빗물을 담는 오른쪽을 고려할 때 맨 처음에는 i의 블록 수 > i+1의 블록수일때 까지 반복한 후 오른쪽을 정했는데,
     * 그렇게 되면 i의 블록 수 > i + 2의 블록 수인 경우에 담을 수 있는 빗물을 고려하지 못하게 되었다. 예시)30103
     * 그래서 빗물을 담은 왼쪽 인덱스가 i라면 i보다 오른쪽의 수 중 가장 큰 블록을 골라서 계산했더니,
     * 예시)10324인 경우에서 실패하게 되었다.
     *
     * 따라서 점차 오른쪽으로 가면서 가장 긴 블록의 인덱스를 구하되, 만약 긴 블록이 왼쪽 블록보다 높다면 멈추는 방식으로 양 쪽을 구하였다.
     */
    static int H;
    static int W;
    static int[] block;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        block = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        int ans = 0;
        while(idx < W - 1){

            if (block[idx] <= block[idx + 1]){
                idx++;
                continue;
            }

            // 빗물이 고일 수 있는 오른쪽 블록 인덱스 구하기
            int lastIdx = idx + 1;
            for(int i = idx + 1; i < W; i++){
                // 오른쪽으로 가면서 가장 긴 블록의 위치를 찾는다.
                if (block[i] > block[lastIdx]){
                    lastIdx = i;
                }
                // 현재 가장 긴 블록이 왼쪽 블록보다 높다면 빗물은 이를 넘어설 수 없으므로 break한 후 빗물의 양을 구한다.
                if (block[idx] <= block[i]) break;
            }

            if (lastIdx == idx + 1 && block[lastIdx] == 0){
                break;
            }

            ans += measureRain(idx, lastIdx);
            idx = lastIdx;

        }

        System.out.println(ans);
    }

    static int measureRain(int st, int en){
        int min = block[st] > block[en]? block[en]: block[st];
        int sum = 0;
        for (int i = st + 1; i < en; i++) {
            sum += min - block[i];
        }

        return sum;
    }
}
class Solution {
    /**
     * 빗물이 차는 한 구간을 구하는 것이 아니라, 한 인덱스별로 찰 수 있는 빗물의 양을 구해서 더한다.
     */
    static int H;
    static int W;
    static int[] block;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        block = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            block[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;
        for (int i = 1; i < W - 1; i++) {

            int left = block[0];
            int right = block[W - 1];
            for(int j = 1; j < i; j++){
                if (block[j] > left) left = block[j];
            }
            for (int j = W - 2; j > i; j--) {
                if (block[j] > right) right = block[j];
            }

            if (block[i] < left && block[i] < right){
                ans += (left < right? left: right) - block[i];
            }
        }

        System.out.println(ans);

    }

}