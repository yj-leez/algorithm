package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17143 {
    static class Shark {
        private int r; // 0 ~ R-1
        private int c; // 0 ~ C - 1
        private int s;
        private int d; // 0 ~ 3
        private int z;
        private boolean dead;

        public Shark(int r, int c, int s, int d, int z) {
            this.r = r - 1;
            this.c = c - 1;
            this.s = s;
            this.d = d - 1;
            this.z = z;
            dead = false;
        }
    }

    static int R;
    static int C;
    static int M;

    // 위, 아래, 오른쪽, 왼쪽
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static Shark[] sharks;
    static int[][] sea;
    static int ans;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        sharks = new Shark[M + 1]; // 1 ~ M까지 존재
        sea = new int[R][C];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            sharks[i + 1] = new Shark(r, c,
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

            sea[r - 1][c - 1] = i + 1;
        }

        ans = 0;
        for (int i = 0; i < C; i++) {
            catchShark(i);
            moving();
        }

        System.out.println(ans);
    }

    static void catchShark(int c) {
        for (int i = 0; i < R; i++) {
            if (sea[i][c] != 0) {
                int idx = sea[i][c];
                // 상어 잡아서 점수 획득
                ans += sharks[idx].z;
                // 상어 죽음 처리
                sharks[idx].dead = true;
                break;
            }
        }
    }

    static void moving() {
        // 바다 초기화
        sea = new int[R][C];

        // 차례대로 움직이기
        for (int i = 1; i <= M; i++) {
            if (sharks[i].dead) continue;

            // 속도만큼 움직이기
            for (int j = 0; j < sharks[i].s; j++) {
                int r = sharks[i].r + dr[sharks[i].d];
                int c = sharks[i].c + dc[sharks[i].d];

                if (r < 0 || r >= R || c < 0 || c >= C) {
                    switch (sharks[i].d) {
                        case 0:
                            sharks[i].d = 1;
                            break;
                        case 1:
                            sharks[i].d = 0;
                            break;
                        case 2:
                            sharks[i].d = 3;
                            break;
                        case 3:
                            sharks[i].d = 2;
                            break;
                    }
                    r = sharks[i].r + dr[sharks[i].d];
                    c = sharks[i].c + dc[sharks[i].d];
                }

                sharks[i].r = r;
                sharks[i].c = c;
            }

            if (sea[sharks[i].r][sharks[i].c] == 0) {
                sea[sharks[i].r][sharks[i].c] = i;
            } else {
                if (fight(sea[sharks[i].r][sharks[i].c], i)) {
                    sea[sharks[i].r][sharks[i].c] = i;
                }
            }

        }
    }

    static boolean fight(int a, int b) {
        if (sharks[a].z > sharks[b].z) {
            sharks[b].dead = true;
            return false;
        } else {
            sharks[a].dead = true;
            return true;
        }
    }
}
