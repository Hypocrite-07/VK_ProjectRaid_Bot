package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.user.User;

public abstract class ACommand {

    /**
     * Используется для получения имени команды, через которое она и взызывается
     * @return название/имя команды
     */
    public String getCommandName() {
        return null;
    }

    /**
     * @return Описание данной команды
     */
    public String getCommandDescription() {
        return null;
    }

    /**
     * Права доступа смотреть в {@link ru.projectraid.user.UserType}
     * @return {@link Integer} значение уровня доступа, от которого уже можно использовать команду
     */
    public int getPermissionsLevel() {
        return 3;
    }

    /**
     * Данный метод используется для реализации действия команды
     * @param user игрок, который вызвал команду
     * @param message само сообщение из ВК с хранимыми внутри данными
     * @param args массив слов из сообщения, args[0] это сам commandName, а остальное аргументы
     */
    public void action( User user, Message message, String... args) throws IncorrectArgumentException {

    }
}
