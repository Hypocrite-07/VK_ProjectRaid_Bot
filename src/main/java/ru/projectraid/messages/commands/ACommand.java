package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.JsonObject;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.user.User;

import java.util.Arrays;
import java.util.List;

public abstract class ACommand {

    /**
     * Используется для получения имени команды, через которое она и взызывается
     * @return название/имя команды
     */
    public String getCommandName() {
        return null;
    }

    public boolean isVisibleOnStartPage() {
        return false;
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

    public boolean getSplitArgs() { return true; }

    public Keyboard getKeyboard(List<Button> buttons, boolean inline) {
        return null;
    }

    public Keyboard getKeyboard() {
        return null;
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
