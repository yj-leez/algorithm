package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class B22233 {
    /**
     * 메모장의 키워드들을 담는 set, 블로그들의 키워드들을 담는 set을 선언한 후
     * 기존의 글과 중복되지 않은 키워드이고 메모장에 있는 키워드라면 cnt(메모장에 있는 키워드 수)를 감소시켰다.
     * 다른 방법으로는 Map<String, Integer>을 선언하여 Integer가 1일 때만 cnt를 감소시키는 방법도 있고, 이것이 메모리 측면에서 더 효율적일 것 같다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Set<String> memo = new HashSet<>();
        for (int i = 0; i < N; i++) {
            memo.add(br.readLine());
        }

        Set<String> blog = new HashSet<>();
        int cnt = N;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            String[] keywords = br.readLine().split(",");
            for (int j = 0; j < keywords.length; j++) {
                if (blog.contains(keywords[j])) continue;

                // 기존의 글과 중복되지 않은 키워드이고 메모장에 있는 키워드라면
                if (memo.contains(keywords[j])){
                    cnt--;
                    blog.add(keywords[j]);
                }
            }
            sb.append(cnt + "\n");
        }

        System.out.println(sb);
    }
}
