package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B17779 {
    static int N;
    static int[][] people;
    static boolean[][] site5;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        people = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                people[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = 40000;
        for (int x = 0; x < N - 2; x++) {
            for (int y = 1; y < N - 1; y++) {
                for (int d1 = 1; d1 <= y ; d1++) {
                    for (int d2 = 1; d2 < N - x - d1; d2++) {
                        if (isValid(x, y, d1, d2)){
                            int sum5 = sum5(x, y, d1, d2);
                            int[] tmp = {
                                    sum1(x, y, d1, d2),
                                    sum2(x, y, d1, d2),
                                    sum3(x, y, d1, d2),
                                    sum4(x, y, d1, d2),
                                    sum5
                            };

                            int max = tmp[0];
                            int min = tmp[0];

                            for (int i = 1; i < tmp.length; i++) {
                                max = Math.max(max, tmp[i]);
                                min = Math.min(min, tmp[i]);
                            }

                            if (result > max - min) result = max - min;
                        }
                    }
                }
            }
        }
        System.out.println(result);
    }

    static boolean isValid(int x, int y, int d1, int d2){
        if (x + d1 + d2 < N && y + d2 < N && 0 <= y - d1) return true;
        return false;
    }

    static int sum1(int x, int y, int d1, int d2){
        int sum = 0;
        for (int r = 0; r < x + d1; r++) {
            for (int c = 0; c <= y; c++) {
                if (!site5[r][c]) sum += people[r][c];
            }
        }
        return sum;
    }

    static int sum2(int x, int y, int d1, int d2){
        int sum = 0;
        for (int r = 0; r <= x + d2; r++) {
            for (int c = y + 1; c < N; c++) {
                if (!site5[r][c]) sum += people[r][c];
            }
        }
        return sum;
    }
    
    static int sum3(int x, int y, int d1, int d2){
        int sum = 0;
        for (int r = x + d1; r < N; r++) {
            for (int c = 0; c < y - d1 + d2; c++) {
                if (!site5[r][c]) sum += people[r][c];
            }
        }
        return sum;
    }
    static int sum4(int x, int y, int d1, int d2){
        int sum = 0;
        for (int r = x + d2 + 1; r < N; r++) {
            for (int c = y - d1 + d2; c < N; c++) {
                if (!site5[r][c]) sum += people[r][c];
            }
        }
        return sum;
    }
    static int sum5(int x, int y, int d1, int d2){
        site5 = new boolean[N][N];
        int sum = 0;

        int dl = 0;
        int dr = 0;
        boolean flagl = false, flagr = false; // true일때 감소
        for (int r = x; r <= x + d1 + d2; r++) {
            for (int c = y - dl; c <= y + dr; c++) {
                site5[r][c] = true;
                sum += people[r][c];
            }
            if (dl < d1 && !flagl) {
                dl++;
            } else {
                flagl = true;
                dl--;
            }
            if (dr < d2 && !flagr) {
                dr++;
            } else {
                flagr = true;
                dr--;
            }
        }
        return sum;
    }
}
