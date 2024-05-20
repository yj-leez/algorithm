package algorithm.hash;

import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> map = new HashMap<>(n);
        String[] arr = new String[n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            map.put(s, i);
            arr[i] = s;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            String s = br.readLine();

            if (Character.isDigit(s.charAt(0))){
                sb.append(arr[Integer.parseInt(s) - 1] + "\n");
            } else {
                sb.append((map.get(s) + 1) + "\n");
            }
        }

        System.out.println(sb);
    }
}
