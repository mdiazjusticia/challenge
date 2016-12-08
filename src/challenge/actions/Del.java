package challenge.actions;

import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Del implements Action{
    private String key;

    public Del(String key) {
        this.key = key;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        mini.remove(this.key);
        return "OK";
    }
}
