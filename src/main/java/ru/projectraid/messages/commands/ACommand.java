package ru.projectraid.messages.commands;

import ru.projectraid.user.User;

public abstract class ACommand {

    public String getCommandName() {
        return null;
    }

    public String getCommandDescription() {
        return null;
    }

    /**
     * Permissions check in {@link ru.projectraid.user.UserType}
     * @return permission level that can use command
     */
    public int getPermissionsLevel() {
        return 2;
    }

    public void action(User user) {

    }
}
