package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B21608 {
    static class Student{
        private int index;
        private int[] favoriteFriends; // 좋아하는 친구들
        private int[] location; // 선정된 위치
        public Student(int index, int favoriteFriend1, int favoriteFriend2, int favoriteFriend3, int favoriteFriend4){
            this.index = index;
            this.favoriteFriends = new int[]{favoriteFriend1, favoriteFriend2, favoriteFriend3, favoriteFriend4};
            location = new int[2];
        }
    }

    static int N;
    static Student[] students;
    static int[][] classRoom;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        students = new Student[N * N + 1]; // 1 ~ N * N번 인덱스
        classRoom = new int[N][N];
        
        StringTokenizer st;
        for (int i = 1; i <= N * N; i++) {
            st = new StringTokenizer(br.readLine());

            Student student = new Student(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            students[student.index] = student;

            findSeat(student.index);
        }

        int answer = 0;
        for (int i = 1; i <= N * N; i++) {
            //  1 ~ N*N번의 학생들의 만족도 구하기
            Student student = students[i];

            int x = student.location[0];
            int y = student.location[1];

            int cnt = 0;
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || nx >= N|| ny < 0|| ny >= N) continue;

                for (int j = 0; j < 4; j++) {
                    if (classRoom[nx][ny] == student.favoriteFriends[j]){
                        cnt++;
                        break;
                    }
                }
            }

            if (cnt > 0) {
                answer += (int) Math.pow(10, cnt - 1);  // 만족도 계산
            }
        }

        System.out.println(answer);
    }

    static void findSeat(int index){
        Student student = students[index];

        int favoriteFriendMax = -1;
        int availableSeatMax = -1;
        int[] willSit = new int[2];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (classRoom[x][y] > 0) continue;

                int favoriteFriendCnt = 0;
                int availableSeatCnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || nx >= N|| ny < 0|| ny >= N) continue;

                    // 빈 자리인지 확인
                    if (classRoom[nx][ny] == 0) availableSeatCnt++;

                    // 좋아하는 학생인지 확인
                    for (int j = 0; j < 4; j++) {
                        if (classRoom[nx][ny] == student.favoriteFriends[j]){
                            favoriteFriendCnt++;
                            break;
                        }
                    }
                }

                if (favoriteFriendCnt > favoriteFriendMax ||
                        (favoriteFriendCnt == favoriteFriendMax && availableSeatCnt > availableSeatMax)){
                    favoriteFriendMax = favoriteFriendCnt;
                    availableSeatMax = availableSeatCnt;
                    willSit[0] = x; willSit[1] = y;
                }
            }
        }

        classRoom[willSit[0]][willSit[1]] = index;
        students[index].location = willSit;
    }
}
