package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B18870 {

    static int[] noDuplicated;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] tmp = new int[n]; // 정렬 용도
        int[] arr = new int[n]; // 계산 용도
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            tmp[i] = arr[i];
        }

        // 정렬
        Arrays.sort(tmp);

        // 중복 제거
        noDuplicated = new int[n]; // 좌표 압축 적용 용도
        noDuplicated[0] = tmp[0];
        idx = 1;
        for (int i = 1; i < n; i++) {
            if(tmp[i] != noDuplicated[idx - 1]){
                noDuplicated[idx++] = tmp[i];
            }
        }

        // 이분 탐색
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tmp.length; i++) {
            sb.append(binarySearch(arr[i]) + " ");
        }

        System.out.println(sb);
    }

    static int binarySearch(int target){
        int st = 0;
        int en = idx;
        int mid;

        while (st < en){
            mid = (st + en)/2;

            if(noDuplicated[mid] < target){
                st = mid + 1;
            } else if(noDuplicated[mid] >= target){
                en = mid;
            }
        }

        return st;
    }
}
