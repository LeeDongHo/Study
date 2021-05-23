package psg_1;

import java.util.*;

public class Dictionary {

    class Word implements Comparable<Word> {
        private String str;
        private int index;

        public Word(String str, int index) {
            this.str = str;
            this.index = index;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(Word obj) {
            return str.compareTo(obj.getStr());
        }
    }

    private StringTokenizer tokenizer;

    private ArrayList<Word> words;
    private ArrayList<String> means;

    public Dictionary() {
        words = new ArrayList<>();
        means = new ArrayList<>();
    }

    private void setWord(String word, int meanIndex) {
        words.add(new Word(word,meanIndex));
        // 정렬
        Collections.sort(words);
    }

    private int setMean(String mean) {
        means.add(mean);
        return means.size()-1;
    }

    private int findWord(String word) {
        int answer = -1;
        int mid = 0;
        int left = 0, right = words.size()-1;

        while(left <= right) {
            mid = (left+right)/2;
            int result = word.compareTo(words.get(mid).getStr());

            if(result == 0) {
                answer = mid;
                break;
            }
            else if(result < 0)
                left = mid+1;
            else
                right = mid-1;

        }
        return answer;
    }

    public void insert(String str) {
        String[] result = str.split(String.valueOf('/'));
        setWord(result[0],setMean(result[1]));
    }

    public String search(String word) {
        int index = findWord(word);
        if(index == -1)
            return "존재하지 않는 단어 입니다.";
        return means.get(words.get(index).getIndex());
    }

    public boolean remove(String word) {
        int index = findWord(word);
        if(index == -1)
            return false;
        means.remove(words.get(index).getIndex());
        words.remove(index);
        return true;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Dictionary dic = new Dictionary();

        while(true) {

            System.out.print("메뉴 선택: ");
            int menu = scan.nextInt();
            String input="";
            switch(menu) {
                case 1:
                    System.out.print("단어 입력: ");
                    input = scan.next();
                    dic.insert(input);
                    break;
                case 2:
                    System.out.print("단어 입력: ");
                    input = scan.next();
                    System.out.println(input + "뜻 : " + dic.search(input));
                    break;
                case 3:
                    System.out.print("단어 입력: ");
                    input = scan.next();
                    if(dic.remove(input))
                        System.out.println("삭제 완료");
                    else
                        System.out.println("존재 하지 않습니다.");
                    break;

                default:
                    return;
            }
        }

    }
}

/*
1. Dictionary 클래스 설계
2. Vector, ArrayList 클래스를 사용하여 Dictionary 클래스 구현
    - 단어 추가 기능 : 단어와 뜻을 입력 받아라 ('/' 구분자 사용) & 알파벳순 정렬
    - 단어 검색 기능 : 사전 구성 후 단어 입력하면 뜻 반환
    - 단어 삭제 기능 : 해당 단어 찾아서 삭제하는 기능
 */