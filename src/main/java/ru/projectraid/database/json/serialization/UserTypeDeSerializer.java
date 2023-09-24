package ru.projectraid.database.json.serialization;

import com.google.gson.*;
import ru.projectraid.user.UserType;

import java.lang.reflect.Type;

public class UserTypeDeSerializer implements JsonDeserializer<UserType> {

    @Override
    public UserType deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        if (json.isJsonPrimitive()) {
            int permissionsId = json.getAsInt();
            return UserType.getUserTypeById(permissionsId);
        }

        return null;
    }
}
