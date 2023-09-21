package ru.projectraid.messages.commands;

import ru.projectraid.user.User;

public abstract class ACommand {

    public String getCommandName() {
        return null;
    }

    public String getCommandDescription() {
        return null;
    }

    public int getPermissionsLevel() {
        return 2;
    }

    public void action(User user) {

    }
}
