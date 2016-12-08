package challenge.actions;

import challenge.Member;
import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Zcard implements Action{
    private String key;


    public Zcard(String key) {
        this.key = key;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        return mini.zcard(this.key) + "";
    }
}
