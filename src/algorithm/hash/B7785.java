package algorithm.hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class B7785 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            if (str[1].equals("enter")){
                set.add(str[0]);
            } else if (str[1].equals("leave")){
                set.remove(str[0]);
            }
        }

        String[] arr = set.toArray(new String[0]);
        Arrays.sort(arr, Collections.reverseOrder());

        for (String s: arr){
            System.out.println(s);
        }
    }
}
