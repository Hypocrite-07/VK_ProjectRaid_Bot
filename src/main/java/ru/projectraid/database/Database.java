package ru.projectraid.database;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.projectraid.database.json.JsonUserConfig;
import ru.projectraid.exceptions.UserMismatchException;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class Database {

    public static HashMap<Integer, User> users = new HashMap<>();
    private static ArrayList<Integer> developers = new ArrayList<>();

    /**
     * Конструктор, инициализуирует всех администраторов
     */
    public Database() {
        initDevelopers();
    }

    /**
      * Помогает считывать информацию с JSON файла. Парсит данные и заменяет их, как только программа была открыта.
     */
    public static void loadUsersFromJsonFile(){
        try (FileReader fileReader = new FileReader(JsonUserConfig.userDatabaseFilepath)) {
            Type type = new TypeToken<HashMap<Integer, JsonObject>>(){}.getType();
            HashMap<Integer, JsonObject> userJsonMap = JsonUserConfig.gson.fromJson(fileReader, type);
            if(userJsonMap == null) return;
            // Преобразование JSON объектов в пользователей и установка уровня "прав"
            for (Integer uniqueId : userJsonMap.keySet()) {
                JsonObject userJson = userJsonMap.get(uniqueId);
                int userTypeIndex = userJson.get("userType").getAsInt();
                UserType userType = UserType.getUserTypeById(userTypeIndex);
                int activities = userJson.get("activities").getAsInt(); // Получаем activities из JSON
                // Создание пользователя и добавление в HashMap
                users.put(uniqueId, new User(uniqueId, userType));
                users.get(uniqueId).setActivities(activities);
            }

            System.out.println(users);
        } catch (IOException e) {
            System.out.println(JsonUserConfig.userDatabaseFilepath + " не был найден. Будет создан новый!");
        }
    }

    /**
     * Добавляет определённых пользователей в массив {@link #developers}
     */
    public static void initDevelopers () {
        developers.add(281130380);
        developers.add(454783070);
    }


    /**
     * Данный метод обновляет информацию о игроке, либо заного добавляет после перезапуска бота
     * @throws IOException выкидывает исключение, если не может найти/обновить json конфиг
     */
    public static void update() {
        try (FileWriter fileWriter = new FileWriter(JsonUserConfig.userDatabaseFilepath)) {
            // Конвертация пользователей в JSON объекты для сохранения
            Type type = new TypeToken<HashMap<Integer, JsonObject>>(){}.getType();
            HashMap<Integer, JsonObject> userJsonMap = new HashMap<>();
            for (Integer uniqueId : users.keySet()) {
                User user = users.get(uniqueId);
                JsonObject userJson = new JsonObject();
                userJson.addProperty("uniqueId", user.getUniqueId());
                userJson.addProperty("userType", user.getUserType().permissionsId);
                userJson.addProperty("activities", user.getActivities()); // Добавляем activities в JSON

                userJsonMap.put(uniqueId, userJson);
            }

            String json = JsonUserConfig.gson.toJson(userJsonMap, type);
            fileWriter.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

        update();
    }

    /**
     * Получает пользователя из ключевого массива {@link #users} по идентификационному ключу
     * @param uniqueId уникальный идентификатор пользователя в ВК
     * @return возвращает пользователя из ключевого массива {@link #users}
     */
    public static User getUser(int uniqueId, boolean mustExist) throws UserMismatchException {
        User user = users.get(uniqueId);
        if(users.get(uniqueId) == null)
        {
            if(mustExist){
                throw new UserMismatchException(uniqueId);
            }
            addUser(uniqueId);
            return getUser(uniqueId, mustExist);
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
