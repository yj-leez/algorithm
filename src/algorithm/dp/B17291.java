package algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * bugs[i]: 그 해에 생존한 벌레의 수
 * 만약 홀수년도에 탄생한 개체는 3번 분열하여 삭제하고 싶다면 bugs[i - 3] - bugs[i - 4]가 아닌
 * bugs[i - 4]를 삭제해야한다. (그 전 년도의 2배가 태어나기 때문)
 */
public class B17291 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] bugs = new int[Math.max(N + 1, 5)];
        bugs[1] = 1; bugs[2] = 2; bugs[3] = 4; bugs[4] = 7;
        for(int i = 5; i <= N; i++){
            bugs[i] = bugs[i - 1] * 2;

            if(i - 3 > 0 && (i - 3) % 2 == 1){
                bugs[i] -= bugs[i - 4];
            }
            if(i - 4 > 0 && (i - 4) % 2 == 0){
                bugs[i] -= bugs[i - 5];
            }
        }

        System.out.println(bugs[N]);
    }
}
