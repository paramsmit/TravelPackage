package storage;

import data.Activity;

import java.util.HashMap;
import java.util.Map;

public class ActivityStorage {
    final static Map<String, Activity> idToActivity = new HashMap<>();

    public static Activity get(String id){
        if(idToActivity.containsKey(id)){
            return idToActivity.get(id);
        }
        throw new RuntimeException("record not found");
    }

    public static void save(Activity activity){
        idToActivity.put(activity.getId(),activity);
    }
}
