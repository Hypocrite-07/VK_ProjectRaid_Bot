package ru.projectraid.database;

import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    private static HashMap<Integer, User> users;
    private static ArrayList<Integer> admins;
    public Database() {
        users = new HashMap<>();
        admins = new ArrayList<>();
        initAdmins();
    }

    private void initAdmins() {
        admins.add(281130380);
        admins.add(454783070);
    }

    public static void addUser(int uniqueId) {
        if(existUser(uniqueId))
            return;
        if(isAdmin(uniqueId))
            users.put(uniqueId, new User(uniqueId, UserType.ADMIN));
        else
            users.put(uniqueId, new User(uniqueId, UserType.GUEST));
    }

    public static User getUser(int uniqueId) {
        return users.get(uniqueId);
    }

    public static boolean existUser(int uniqueId) {
        return users.containsKey(uniqueId);
    }

    public static boolean isAdmin(int uniqueId) {
        return admins.contains(uniqueId);
    }
}
