package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B2230 {
    /**
     * 이분탐색으로 풀이
     * N개의 정수로 이루어진 수열(arr[i])에서 arr[i] - M인 값이 존재하는지 이분탐색으로 확인, 없다면 값을 하나씩 감소 -> 시간 초과
     * N개의 정수로 이루어진 수열(arr[i])에서 arr[i] - M보다 작은 값이 존재하는지 이분탐색으로 확인 -> 성공
     */
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int idx = getLowerIndex(arr[i] - m);
            idx = arr[idx] == arr[i] - m? idx: idx - 1;
            if (idx == -1) continue;

            ans = Math.min(ans, arr[i] - arr[idx]);
        }

        System.out.println(ans);

    }

    static int getLowerIndex(int target){
        int st = 0;
        int en = arr.length;

        while(st < en){
            int mid = (st + en)/2;

            if(arr[mid] >= target){
                en = mid;
            } else if (arr[mid] < target) {
                st = mid + 1;
            }
        }
        return st;
    }
}
