package algorithm.자료구조;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B1764 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        List<String> ans = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            String tmp = br.readLine();
            if (set.contains(tmp)) ans.add(tmp);
        }

        Collections.sort(ans);
        System.out.println(ans.size());
        for(String s: ans){
            System.out.println(s);
        }
    }
}
