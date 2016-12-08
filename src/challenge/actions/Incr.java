package challenge.actions;


import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Incr implements Action {

    private String key;

    public Incr(String key) {
        this.key = key;
    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
        String value = mini.get(this.key);
        if (value != null) {
            try {
                Integer ivalue = Integer.parseInt(value) + 1;
                value = ivalue + "";
                mini.put(this.key, ivalue + "");
            }
            catch (NumberFormatException nfe) {
                value = "ERROR";
            }
        }
        else {
            value = "0";
            mini.put(this.key, value + "");
        }
        return value;
    }
}
