package ru.projectraid.exceptions;

import ru.projectraid.user.UserType;

public class IllegalAccessException extends Throwable {
    /**
     * Используется в рамках обработчика команд.
     * Выкидывается в случае того, если кто-то неправомерно хочет использовать какую-то команду
     * @param type правовой статус игрока
     */
    public IllegalAccessException(UserType type ) {
        super("Для доступа к команде необходимо иметь уровень: ".concat(type.statusName));
    }

    @Override
    public String getMessage () {
        return super.getMessage();
    }

}
