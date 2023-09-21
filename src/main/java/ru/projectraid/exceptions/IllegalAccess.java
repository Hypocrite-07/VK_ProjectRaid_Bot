package ru.projectraid.exceptions;

import ru.projectraid.user.UserType;

public class IllegalAccess extends Throwable {
    /**
     * Используется в рамках обработчика команд.
     * Выкидывается в случае того, если кто-то неправомерно хочет использовать какую-то команду
     * @param type правовой статус игрока
     */
    public IllegalAccess ( UserType type ) {
        super("Для доступа к команде необходимо иметь уровень: " + type.statusName);
    }

    @Override
    public String getMessage () {
        return super.getMessage();
    }
}
