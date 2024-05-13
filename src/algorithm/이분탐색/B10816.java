package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B10816 {
    /**
     * 이분탐색에서는 무한 루프를 조심해야하는데,
     * st가 en보다 하나 작을 때를 고려해보면 좋다.
     */

    /**
     * 삽입했을 때 정렬이 깨지지 않는 가장 오른쪽 인덱스 - 가장 왼쪽 인덱스로 풀이하였다.
     */

    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int target = Integer.parseInt(st.nextToken());
            int ans = getUpperIndex(target) - getLowerIndex(target);
            sb.append(ans + " ");
        }

        System.out.println(sb);
    }

    static int getLowerIndex(int target){
        int st = 0;
        int en = arr.length;
        int mid = 0;

        while(st < en){
            mid = (st + en)/2;

            if(arr[mid] < target){
                st = mid + 1;
            } else if(arr[mid] >= target){
                en = mid;
            }
        }
        return st;
    }


    static int getUpperIndex(int target){
        int st = 0;
        int en = arr.length;
        int mid = 0;

        while(st < en){
            mid = (st + en)/2;

            if(arr[mid] <= target){
                st = mid + 1;
            } else if(arr[mid] > target){
                en = mid;
            }
        }
        return st;
    }

}
