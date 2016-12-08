package challenge.actions;

import challenge.MiniRedis;

/**
 * Created by mario on 08/12/2016.
 */
public class Dbsize implements Action{

    public Dbsize() {

    }

    public String execute () {
        MiniRedis mini = MiniRedis.getInstance();
         return mini.dbSize() + "";
    }
}
