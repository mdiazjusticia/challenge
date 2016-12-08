package challenge;

import java.util.*;

/**
 * Created by mario on 08/12/2016.
 */
public class MiniRedis {
    private static MiniRedis instance = null;
    private HashMap<String, String> store;
    private HashMap<String, Long> lifeTime;
    private HashMap<String, TreeSet<Member>> scores;

    private MiniRedis() {
        store = new HashMap<String, String>();
        lifeTime = new HashMap<String, Long>();
        scores = new HashMap<String, TreeSet<Member>>();
    }

    public static MiniRedis getInstance () {
        if (instance == null) {
            instance = new MiniRedis();
        }
        return instance;
    }

    public String get (String s) {
        String value = null;
        synchronized (store) {
            value = store.get(s);
            if (s != null && expired(s)) {
                remove(s);
                return null;
            }
        }
        return value;
    }

    public void put (String key, String value) {
        synchronized (store) {
            store.put(key, value);
        }
    }

    public void put (String key, String value, int seconds) {
        synchronized (store) {
            store.put(key, value);
            if (seconds > 0) {
                synchronized (lifeTime) {
                    lifeTime.put(key, System.currentTimeMillis() + seconds * 1000);
                }
            }
        }
    }

    private boolean expired (String s) {
        synchronized (lifeTime) {
            Long value = lifeTime.get(s);
            if (value != null && System.currentTimeMillis() > value) {
                return true;
            }
        }
        return false;
    }

    public void remove(String key) {
        synchronized (lifeTime) {
            lifeTime.remove(key);
        }
        synchronized (store) {
            store.remove(key);
        }
    }

    public int dbSize() {
        return store.size();
    }

    public void zadd (String key, Member member) {
        synchronized (scores) {
            TreeSet<Member> members = scores.get(key);
            if (members == null) {
                scores.put(key, new TreeSet<Member>());
            }
            scores.get(key).add(member);
        }
    }

    public int zcard (String key) {
        int result = 0;
        synchronized (scores) {
            TreeSet<Member> members = scores.get(key);
            if (members != null) {
                result = members.size();
            }
        }
        return result;
    }

    public Integer zrank (String key, String member) {
        Integer result = null;
        synchronized (scores) {
            TreeSet<Member> members = scores.get(key);
            if (members != null) {
                Iterator<Member> it = members.iterator();
                int i = 0;
                while (it.hasNext() && result == null) {
                    Member m = it.next();
                    if (m.getValue().equals(member)) {
                        result = i;
                    }
                    i++;
                }
            }
        }
        return result;
    }

    public ArrayList<String> zrange (String key, int start, int stop) {
        ArrayList<String> result = new ArrayList<>();
        synchronized (scores) {
            TreeSet<Member> members = scores.get(key);
            if(members != null) {
                Iterator<Member> it = members.iterator();
                int i = 0;
                while (it.hasNext() && i <= stop) {
                    Member m = it.next();
                    if (i >= start) {
                        result.add(m.getValue());
                    }
                    i++;
                }
            }
        }

        return result;
    }
}
