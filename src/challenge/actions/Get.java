package challenge.actions;


import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Get implements Action {

    private String key;

    public Get(String key) {
        this.key = key;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        return mini.get(this.key);
    }
}
