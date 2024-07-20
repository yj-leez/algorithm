package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B16235 {

    static class Sod{
        private int nutrition;
        private List<Integer> trees;
        private int nutritionOfDeadTree;
        private int multipleOf5;

        public Sod(){
            nutrition = 5;
            trees = new ArrayList<>();
            nutritionOfDeadTree = 0;
        }
    }

    static int N;
    static int M;
    static Sod[][] sods;
    static int[][] A;

    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        sods = new Sod[N][N];
        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                sods[i][j] = new Sod();
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            sods[r][c].trees.add(Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            spring();
            summer();
            autumn();
            winter();
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += sods[i][j].trees.size();
            }
        }

        System.out.println(ans);


    }

    static void spring(){
        // 나이가 어린 나무부터 나이만큼 땅의 양분을 가져가고 나이를 +1한다. 만약 땅의 양분이 0이 되었다면, 그 이후의 나무는 죽는다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Sod sod = sods[i][j];

                Collections.sort(sod.trees);
                int size = sod.trees.size();
                int cutIndex = -1; // 땅의 양분이 모자라는 트리의 인덱스
                for (int k = 0; k < size; k++) {
                    sod.nutrition -= sod.trees.get(k); // 나이만큼 뺀다


                    if (sod.nutrition < 0){
                        sod.nutrition += sod.trees.get(k); // 원래 있던 양분만큼 되돌리기
                        cutIndex = k; // 죽기 시작한 나무의 인덱스 체크
                        break;
                    }

                    int age = sod.trees.get(k) + 1;
                    sod.trees.set(k, age); // 양분을 먹었다면 나이 증가
                    if (age % 5 == 0) sod.multipleOf5++;
                }

                if (cutIndex != -1){
                    for (int k = cutIndex; k < size; k++) {
                        sod.nutritionOfDeadTree += sod.trees.get(cutIndex)/2; // 죽은 나무의 영양분 모아놓기
                        sod.trees.remove(cutIndex); // 죽은 나무 제거
                    }
                }

            }
        }

    }

    static void summer(){
        // 봄에 죽은 나무가 양분으로 변하게 된다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Sod sod = sods[i][j];
                if (sod.nutritionOfDeadTree == 0){
                    continue;
                }

                sod.nutrition += sod.nutritionOfDeadTree;
                sod.nutritionOfDeadTree = 0;
            }
        }
    }

    static void autumn(){
        // 번식 조건을 만족하는 나무 주위에 나이가 1인 나무들이 생긴다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                Sod sod = sods[i][j];

                if (sod.multipleOf5 == 0){
                    continue;
                }

                for (int k = 0; k < 8; k++) {
                    if (i + dx[k] < 0 || i + dx[k] >= N || j + dy[k] < 0 || j + dy[k] >= N){
                        continue;
                    }

                    for (int l = 0; l < sod.multipleOf5; l++) {
                        sods[i + dx[k]][j + dy[k]].trees.add(1);
                    }
                }

                sod.multipleOf5 = 0;
            }
        }
    }

    static void winter(){
        // 땅을 돌아다니면서 땅에 양분을 추가한다.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sods[i][j].nutrition += A[i][j];
            }
        }

    }
}


