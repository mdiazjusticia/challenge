package challenge.actions;

import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Zrange implements Action{
    private String key;
    private int start;
    private int stop;

    public Zrange(String key, int start, int stop) {
        this.key = key;
        this.start = start;
        this.stop = stop;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        return mini.zrange(this.key, this.start, this.stop) + "";
    }
}
