package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14503 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int cx = Integer.parseInt(st.nextToken());
        int cy = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());
        // 아래 배열과 인덱스 맞춰주기
        if(direction == 1) direction = 3;
        else if (direction == 3) direction = 1;

        // 순서대로 북, 서, 남, 동
        int[] x = {-1, 0, 1, 0};
        int[] y = {0, -1, 0, 1};

        int[][] room = new int[N][M]; // 0: 청소 안 됨, 1: 벽, 2: 청소 완료
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                room[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = 0;
        boolean flag;
        while(true){
            if(room[cx][cy] == 0){
                room[cx][cy] = 2;
                answer++;
            }

            flag = false;
            int dx = cx + (x[direction] == 0? 0: (x[direction] == -1? 1: -1)); // 후진할 위치
            int dy = cy + (y[direction] == 0? 0: (y[direction] == -1? 1: -1));

            // 주변에 청소되지 않은 빈 칸이 있나 확인
            int tmp = direction + 1;
            for (int i = 0; i < 4; i++) {
                if(tmp > 3) tmp = 0;
                if(cx + x[tmp] >=  0 && cx + x[tmp] < N && cy + y[tmp] >= 0 && cy + y[tmp] < M){
                    if(room[cx + x[tmp]][cy + y[tmp]] == 0){
                        flag = true;
                        direction = tmp;
                        break;
                    }
                }
                tmp++;
            }


            if(flag){
                // 청소되지 않은 빈 칸이 있는 경우
                cx = cx + x[direction];
                cy = cy + y[direction];
            } else if (dx >= 0 && dx < N && dy >= 0 && dy < M && room[dx][dy] != 1) {
                // 청소되지 않은 빈 칸이 없지만 한 칸 후진할 수 있는 경우
                cx = dx;
                cy = dy;
            } else {
                // 청소되지 않은 빈 칸도 없고 한 칸 후진도 불가한 경우
                break;
            }

        }

        System.out.println(answer);

    }
}