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

    /**
     * Добавляет определённых пользователей в массив {@link #developers}
     */
    private void initDevelopers () {
        developers.add(281130380);
        developers.add(454783070);
    }

    /**
     * Добавляет пользователя в ключевой массив данных {@link #users} с уровнем доступа {@code GUEST(0)},
     * в ином случае, если данный пользователь находится в заранее проинициализрованном массиве {@link #developers}, то он будет добавлен с уровнем доступа {@code DEVELOPER(3)}.
     * Всё это возможно в случае, если данного пользователя ещё нет в ключевом массиве {@link #users}.
     * В таком исходе событий сработает {@code return;}
     * @param uniqueId уникальный идентификатор пользователя в ВК
     */
    public static void addUser(int uniqueId) {
        if(existUser(uniqueId))
            return;
        if(isDeveloper(uniqueId))
            users.put(uniqueId, new User(uniqueId, UserType.DEVELOPER));
        else
            users.put(uniqueId, new User(uniqueId, UserType.GUEST));
    }

    /**
     * Получает пользователя из ключевого массива {@link #users} по идентификационному ключу
     * @param uniqueId уникальный идентификатор пользователя в ВК
     * @return возвращает пользователя из ключевого массива {@link #users}
     */
    public static User getUser(int uniqueId) {
        User user = users.get(uniqueId);
        if(users.get(uniqueId) == null)
        {
            addUser(uniqueId);
            return getUser(uniqueId);
        }
        return user;
    }

    /**
     * Проверяет существование пользователя в ключевом массиве {@link #users}
     * @param uniqueId уникальный идентификатор пользователя в ВК
     * @return возвращает {@link Boolean} is True при условии того, что пользователь существует в ключевом массиве {@link #users}
     */
    public static boolean existUser(int uniqueId) {
        return users.containsKey(uniqueId);
    }

    /**
     * Проверяет существование пользователя в массиве {@link #developers}
     * @param uniqueId уникальный идентификатор пользователя в ВК
     * @return возвращает {@link Boolean} is True при условии того, что пользователь существует в ключевом массиве {@link #developers}
     */
    public static boolean isDeveloper ( int uniqueId) {
        return developers.contains(uniqueId);
    }
}
