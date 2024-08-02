package algorithm.문자열;

import java.util.*;

public class P방금그곡 {
    static class Music{
        private String title;
        private char[] sheet;

        public Music(String title, List<Character> list){

            char[] charArray = new char[list.size()];
            for (int i = 0; i < list.size(); i++) {
                charArray[i] = list.get(i);
            }
            this.title = title;
            this.sheet = charArray;
        }
    }
    public String solution(String m, String[] musicinfos) {
        // 제목과 악보 정보
        List<Music> musics = new ArrayList<>();

        // 악보 정보 하나의 String으로 문자열 처리
        for (int i = 0; i < musicinfos.length; i++) {
            String[] tmp = musicinfos[i].split(",");
            String[] start = tmp[0].split(":");
            String[] end = tmp[1].split(":");

            int time = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));
            List<Character> sheet = new ArrayList<>();
            tmp[3] = tmp[3].replace("C#", "c")
                    .replace("D#", "d")
                    .replace("F#", "f")
                    .replace("G#", "g")
                    .replace("A#", "a")
                    .replace("B#", "b");

            for (int j = 0; j < time; j++) {
                sheet.add(tmp[3].charAt(j % tmp[3].length()));
            }

            musics.add(new Music(tmp[2], sheet));
        }

        // 타깃 악보 문자열 처리
        m = m.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");


        // 일치하는 악보 찾기
        String answer = "(None)";
        int sheetLen = 0;
        for (Music music : musics) {
            for (int i = 0; i < music.sheet.length; i++) {
                if (music.sheet[i] == m.charAt(0)) {
                    int cnt = 1;
                    while (true) {
                        if (i + cnt >= music.sheet.length || cnt >= m.length()) break;
                        if (music.sheet[i + cnt] != m.charAt(cnt)) {
                            break;
                        }
                        cnt++;
                    }

                    if (cnt == m.length() && sheetLen < music.sheet.length) {
                        answer = music.title;
                        sheetLen = music.sheet.length;
                        break;
                    }
                }
            }
        }

        return answer;
    }
}
class Solution{
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int time = 0;

        m = m.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a")
                .replace("B#", "b");

        for(int i = 0; i < musicinfos.length; i++){
            String[] info = musicinfos[i].split(",");
            String[] start = info[0].split(":");
            String[] end = info[1].split(":");

            int t = (Integer.parseInt(end[0]) * 60 + Integer.parseInt(end[1])) - (Integer.parseInt(start[0]) * 60 + Integer.parseInt(start[1]));
            if(t > time){
                info[3] = info[3].replace("C#", "c")
                        .replace("D#", "d")
                        .replace("F#", "f")
                        .replace("G#", "g")
                        .replace("A#", "a")
                        .replace("B#", "b");

                StringBuilder sb = new StringBuilder();
                for(int j = 0; j < t; j++){
                    sb.append(info[3].charAt((j % info[3].length())));
                }

                // String 메서드 indexOf는 String 객체에서 m 문자열의 첫 번째 발생 위치를 반환, 만약 없다면 -1 반환
                if(sb.toString().indexOf(m) >= 0){
                    answer = info[2];
                    time = t;
                }
            }
        }
        return answer;
    }
}
