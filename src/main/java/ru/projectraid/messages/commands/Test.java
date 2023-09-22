package ru.projectraid.messages.commands;

import ru.projectraid.Bot;
import ru.projectraid.user.User;

public class Test extends ACommand {

    @Override
    public String getCommandName() {
        return "test";
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
    public void action(User user, String... args) {
        if(args.length > 1)
            Bot.getInstance.sendMsgToUser(user, "test: " + args[1]);
        else
            Bot.getInstance.sendMsgToUser(user, "test: empty");
    }
}
