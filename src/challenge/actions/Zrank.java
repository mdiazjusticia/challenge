package challenge.actions;

import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Zrank implements Action{
    private String key;
    private String member;

    public Zrank(String key, String member) {
        this.key = key;
        this.member = member;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        return mini.zrank(this.key, this.member) + "";
    }
}
