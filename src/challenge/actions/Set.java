package challenge.actions;

import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Set implements Action{
    private String key;
    private String value;
    private Integer seconds;


    public Set(String key, String value, Integer n) {
        this.key = key;
        this.value = value;
        this.seconds = n;
    }

    public Set(String key, Integer value, Integer n) {
        this.key = key;
        this.value = value + "";
        this.seconds = n;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        mini.put(this.key, this.value, this.seconds);
        return "OK";
    }
}
