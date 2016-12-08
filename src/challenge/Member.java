package challenge;

import java.util.Comparator;

/**
 * Created by mario on 08/12/2016.
 */
public class Member implements Comparable<Member>{
    private String value;
    private int score;

    public Member(String value, int score) {
        this.score = score;
        this.value = value;
    }

    public int compareTo (Member m) {
        if(this.score < m.score) return -1;
        else if (this.score > m.score) return 1;
        else return 0;
    }

    public String getValue () {
        return value;
    }
}
