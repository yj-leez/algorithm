package algorithm.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class B7785 {
    static List<String> enter;
    static List<String> leave;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        enter = new ArrayList<>();
        leave = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] str = br.readLine().split(" ");
            if (str[1].equals("enter")){
                enter.add(str[0]);
            } else if(str[1].equals("leave")){
                leave.add(str[0]);
            }
        }

        Collections.sort(enter);
        Collections.sort(leave);

        List<String> ans = new ArrayList<>();
        if (getTotalCnt(enter.get(0)) == 1){
            ans.add(enter.get(0));
        }
        for (int i = 1; i < enter.size(); i++) {
            if (!enter.get(i).equals(enter.get(i - 1))){
                if (getTotalCnt(enter.get(i)) == 1){
                    ans.add(enter.get(i));
                }
            }
        }

        Collections.sort(ans, Collections.reverseOrder());

        for (String s: ans) {
            System.out.println(s);
        }

    }

    static int getTotalCnt(String name){
        // enter에서 개수 구하기 - 제일 왼쪽 인덱스
        int st = 0;
        int en = enter.size();

        while(st < en){
            int mid = (st + en)/ 2;

            if (name.compareTo(enter.get(mid)) <= 0){
                en = mid;
            } else if (name.compareTo(enter.get(mid)) > 0){
                st = mid + 1;
            }
        }

        int enterCnt = st;

        // enter에서 개수 구하기 - 제일 오른쪽 인덱스
        st = 0;
        en = enter.size();

        while(st < en){
            int mid = (st + en)/ 2;

            if (name.compareTo(enter.get(mid)) >= 0){
                st = mid + 1;
            } else if (name.compareTo(enter.get(mid)) < 0){
                en = mid;
            }
        }

        enterCnt = st - enterCnt;

        // enter에서 개수 구하기 - 제일 왼쪽 인덱스
        st = 0;
        en = leave.size();

        while(st < en){
            int mid = (st + en)/ 2;

            if (name.compareTo(leave.get(mid)) <= 0){
                en = mid;
            } else if (name.compareTo(leave.get(mid)) > 0){
                st = mid + 1;
            }
        }

        int leaveCnt = st;

        // enter에서 개수 구하기 - 제일 오른쪽 인덱스
        st = 0;
        en = leave.size();

        while(st < en){
            int mid = (st + en)/ 2;

            if (name.compareTo(leave.get(mid)) >= 0){
                st = mid + 1;
            } else if (name.compareTo(leave.get(mid)) < 0){
                en = mid;
            }
        }

        leaveCnt = st - leaveCnt;

        return enterCnt - leaveCnt;
    }
}
