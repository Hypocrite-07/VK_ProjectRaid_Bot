package ru.projectraid.database;

import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private static HashMap<Integer, User> users;
    private static ArrayList<Integer> developers;
    public Database() {
        users = new HashMap<>();
        developers = new ArrayList<>();
        initDevelopers();
    }

    private void initDevelopers () {
        developers.add(281130380);
        developers.add(454783070);
    }

    public static void addUser(int uniqueId) {
        if(existUser(uniqueId))
            return;
        if(isDeveloper(uniqueId))
            users.put(uniqueId, new User(uniqueId, UserType.DEVELOPER));
        else
            users.put(uniqueId, new User(uniqueId, UserType.GUEST));
    }

    public static User getUser(int uniqueId) {
        return users.get(uniqueId);
    }

    public static boolean existUser(int uniqueId) {
        return users.containsKey(uniqueId);
    }

    public static boolean isDeveloper ( int uniqueId) {
        return developers.contains(uniqueId);
    }
}
