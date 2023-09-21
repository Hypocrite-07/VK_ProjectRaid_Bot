package ru.projectraid.messages.commands;

import ru.projectraid.Bot;
import ru.projectraid.user.User;

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
        return 1;
    }

    @Override
    public void action(User user) {
        Bot.getInstance.sendMsgToUser(user, user.toString());
    }
}
