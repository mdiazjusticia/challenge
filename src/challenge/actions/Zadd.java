package challenge.actions;

import challenge.Member;
import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Zadd implements Action{
    private String key;
    private Integer score;
    private String member;


    public Zadd(String key, Integer score, String member) {
        this.key = key;
        this.score = score;
        this.member = member;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        Member m = new Member(this.member, this.score);
        mini.zadd(this.key, m);
        return "OK";
    }
}
