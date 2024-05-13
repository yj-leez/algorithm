package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B2295 {
    /**
     * x번째 수 + y번째 수 + z번째 수 = k번째 수
     * (x번째 수 + y번째 수)의 조합이 가능한 배열을 먼저 만든다.
     * 그리고 k번째 수 - z번째의 수가 이 배열에 존재하는지 확인한다.
     */

    static int[] sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        sum = new int[n * (n + 1) / 2];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                sum[idx++] = arr[i] + arr[j];
            }
        }

        Arrays.sort(arr);
        Arrays.sort(sum);

        int answer = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = i; j >= 0 ; j--) {
                int target = arr[i] - arr[j];
                if (find(target)) answer = Math.max(answer, arr[i]);
            }
        }

        System.out.println(answer);

    }

    static boolean find(int target){
        int st = 0;
        int en = sum.length - 1;

        while(st <= en){
            int mid = (st + en)/2;

            if(sum[mid] == target){
                return true;
            } else if(sum[mid] > target){
                en = mid - 1;
            } else if (sum[mid] < target){
                st = mid + 1;
            }
        }

        return false;
    }

}
