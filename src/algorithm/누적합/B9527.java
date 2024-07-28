package algorithm.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B9527 {
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());


        arr = new long[55];
        arr[0] = 1;
        for(int i = 1; i < 55; i++){
            arr[i] = (arr[i - 1] << 1) + (1L << i);
        }

        System.out.println(calculate(b) - calculate(a - 1));

    }

    static long calculate(long N){
        long count = N & 1; // 가장 낮은 비트 더하고 시작
        int size = (int) (Math.log(N)/Math.log(2)); // 몇 개의 비트가 필요한지

        for(int i = size; i > 0; i--) {
            // n번째 비트가 1이라면 n-1번째 비트가 최초로 1인 숫자보다 작은 누적합 더해주기
            if((N & (1L << i)) != 0L) {
                count += arr[i - 1] + (N - (1L << i) + 1);
                N -= (1L << i);	//비트 이동시키기
            }
        }
        return count;	//1의 개수 반환
    }


}
