import java.util.*;

class File implements Comparable<File> {
    
    String head;
    String number;
    String tail;
    int idx;
    
    public File(String head, String number, String tail, int idx) {
        this.head = head;
        this.number = number;
        this.tail = tail;
        this.idx = idx;
    }
    
    public int compareTo(File o) {
    int headComparison = this.head.compareToIgnoreCase(o.head);
    if (headComparison == 0) {
        int numberComparison = Integer.compare(Integer.parseInt(this.number), Integer.parseInt(o.number));
        if (numberComparison == 0) {
            return Integer.compare(this.idx, o.idx);
        }
        return numberComparison;
    }
    return headComparison;
}
}

class Solution {
    public String[] solution(String[] files) {
        PriorityQueue<File> PQ = new PriorityQueue<>();

        for (int i = 0; i < files.length; i++) {
            String file = files[i];
            int numberStart = 0;
            int numberEnd = file.length();

            for (int j = 0; j < file.length(); j++) {
                if (Character.isDigit(file.charAt(j))) {
                    numberStart = j;
                    break;
                }
            }

            for (int j = numberStart; j < file.length(); j++) {
                if (!Character.isDigit(file.charAt(j))) {
                    numberEnd = j;
                    break;
                }
            }

            String head = file.substring(0, numberStart);
            String number = file.substring(numberStart, numberEnd);
            String tail = file.substring(numberEnd);

            PQ.offer(new File(head, number, tail,i));
        }

        String[] answer = new String[PQ.size()];

        int cnt = 0;
        while (!PQ.isEmpty()) {
            File f = PQ.poll();
            answer[cnt++] = f.head + f.number + f.tail;
        }

        return answer;
    }
}
