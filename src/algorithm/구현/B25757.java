package algorithm.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class B25757 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int gamePeople = 0;

        switch(st.nextToken().charAt(0)){
            case 'Y':
                gamePeople = 1;
                break;
            case 'F':
                gamePeople = 2;
                break;
            case 'O':
                gamePeople = 3;
                break;
        }

        Set<String> set = new HashSet<>();
        int cnt = 0;
        int gamePlayedCnt = 0;
        for(int i = 0; i < N; i++){
            String nickName = br.readLine();
            if(!set.contains(nickName)){
                cnt++;
                set.add(nickName);
                if(cnt == gamePeople){
                    gamePlayedCnt++;
                    cnt = 0;
                }
            }
        }

        System.out.println(gamePlayedCnt);
    }
}
