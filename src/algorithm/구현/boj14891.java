package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj14891 {

    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        arr = new int[4][8];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            String[] tmp = br.readLine().split("");
            for (int j = 0; j < 8; j++) {
                arr[i][j] = Integer.parseInt(tmp[j]);
            }
        }

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            // 바로 왼쪽 체크
            if(idx - 1 >= 0 && arr[idx-1][2] != arr[idx][6]){
                move(idx - 1, 0 - direction);
                // 한 칸 떨어진 왼쪽 체크
                if(direction == 1 && idx - 2 >= 0 && arr[idx-2][2] != arr[idx-1][5]) {
                    move(idx - 2, direction);

                    // 두 칸 떨어진 왼쪽 체크
                    if(idx - 3 >= 0 && arr[idx-3][2] != arr[idx-2][7]) move(idx - 3, 0 - direction);
                }
                else if (direction == -1 && idx - 2 >= 0 && arr[idx - 2][2] != arr[idx - 1][7]){
                    move(idx - 2, direction);

                    // 두 칸 떨어진 왼쪽 체크
                    if(idx - 3 >= 0 && arr[idx-3][2] != arr[idx-2][5]) move(idx - 3, 0 - direction);
                }

            }

            // 바로 오른쪽 체크
            if(idx + 1 < 4 && arr[idx][2] != arr[idx+1][6]){
                move(idx + 1, 0 - direction);

                if (direction == -1 && idx + 2 < 4 && arr[idx+1][3] != arr[idx+2][6]) {
                    move(idx + 2, direction);

                    // 두 칸 떨어진 오른쪽 체크
                    if(idx + 3 < 4 && arr[idx+2][1] != arr[idx+3][6]) move(idx + 3, 0 - direction);
                }
                else if (direction == 1 && idx + 2 < 4 && arr[idx+1][1] != arr[idx+2][6]) {
                    move(idx + 2, direction);

                    // 두 칸 떨어진 오른쪽 체크
                    if(idx + 3 < 4 && arr[idx+2][3] != arr[idx+3][6]) move(idx + 3, 0 - direction);
                }
            }

            //  톱니바퀴 돌리기
            move(idx, direction);
        }

        int answer = 0;
        if(arr[0][0] == 1) answer += 1;
        if(arr[1][0] == 1) answer += 2;
        if(arr[2][0] == 1) answer += 4;
        if(arr[3][0] == 1) answer += 8;

        System.out.println(answer);

    }

    public static void move(int idx, int direction){
        if(direction == 1){
            int tmp = arr[idx][0];
            arr[idx][0] = arr[idx][7];
            arr[idx][7] = arr[idx][6];
            arr[idx][6] = arr[idx][5];
            arr[idx][5] = arr[idx][4];
            arr[idx][4] = arr[idx][3];
            arr[idx][3] = arr[idx][2];
            arr[idx][2] = arr[idx][1];
            arr[idx][1] = tmp;
        } else if(direction == -1){
            int tmp = arr[idx][0];
            arr[idx][0] = arr[idx][1];
            arr[idx][1] = arr[idx][2];
            arr[idx][2] = arr[idx][3];
            arr[idx][3] = arr[idx][4];
            arr[idx][4] = arr[idx][5];
            arr[idx][5] = arr[idx][6];
            arr[idx][6] = arr[idx][7];
            arr[idx][7] = tmp;
        }
    }
}
