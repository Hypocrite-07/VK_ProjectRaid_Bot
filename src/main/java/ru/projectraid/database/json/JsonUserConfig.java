package ru.projectraid.database.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.projectraid.database.json.serialization.UserTypeDeSerializer;
import ru.projectraid.database.json.serialization.UserTypeSerializer;
import ru.projectraid.user.UserType;

public class JsonUserConfig {
    public static final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .create();
    public static String userDatabaseFilepath = "database/UsersDB.json";
    public static Gson jsonUserBuilder = new GsonBuilder()
            .registerTypeAdapter(UserType.class, new UserTypeSerializer())
            .setPrettyPrinting()
            .create();

    public static Gson jsonUserBuilderDeserializer = new GsonBuilder()
            .registerTypeAdapter(UserType.class, new UserTypeDeSerializer())
            .setPrettyPrinting()
            .create();

}
