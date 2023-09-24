package ru.projectraid.database.json.serialization;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import ru.projectraid.user.UserType;

import java.lang.reflect.Type;

public class UserTypeSerializer implements JsonSerializer<UserType> {

    @Override
    public JsonElement serialize(UserType userType, Type type, JsonSerializationContext jsonSerializationContext) {
        return new JsonPrimitive(userType.permissionsId);
    }

}
