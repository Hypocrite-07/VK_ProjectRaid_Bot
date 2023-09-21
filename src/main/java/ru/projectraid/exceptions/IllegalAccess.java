package ru.projectraid.exceptions;

import ru.projectraid.user.UserType;

public class IllegalAccess extends Throwable {
    public IllegalAccess ( UserType type ) {
        super("Для доступа к команде необходимо иметь уровень: " + type.statusName);
    }

    @Override
    public String getMessage () {
        return super.getMessage();
    }
}
