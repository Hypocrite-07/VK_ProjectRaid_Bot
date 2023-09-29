package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.user.User;

import java.lang.reflect.Member;

public class Profile extends ACommand {

    @Override
    public String getCommandName() {
        return "Профиль";
    }

    @Override
    public String getCommandDescription() {
        return "Выводит данные профиля";
    }

    @Override
    public int getPermissionsLevel() {
        return 0;
    }

    @Override
    public void action( User user, Message message, String... args) {
        Bot.getInstance.sendMsgToUser(user, user.toString());
    }
}
