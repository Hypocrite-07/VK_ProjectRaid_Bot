package ru.projectraid.messages;

import ru.projectraid.exceptions.IllegalAccess;
import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.util.ArrayList;
import java.util.List;


public class MessageHandler {

    private static List<ACommand> commands = new ArrayList<>();

    public static void addCommand(ACommand command) {
        if(existCommand(command.getCommandName()))
            return;
        System.out.println("Command " + command.getCommandName() + " was initialized");
        commands.add(command);
    }

    public static boolean useCommand(User user, String commandName) throws IllegalAccess {
        if(existCommand(commandName))
        {
            ACommand command = getCommand(commandName);
            if(user.canUseCommand(command))
            {
                command.action(user);
                return true;
            }
            else
                throw new IllegalAccess(UserType.getUserTypeById(command.getPermissionsLevel()));
        }

        return false;
    }

    private static boolean existCommand(String commandName) {
        for (ACommand command : commands) {
            if(command.getCommandName().equalsIgnoreCase(commandName))
                return true;
        }
        return false;
    }

    private static ACommand getCommand(String commandName) {
        for (ACommand command : commands) {
            if(command.getCommandName().toLowerCase().equals(commandName.toLowerCase()))
                return command;
        }
        return null;
    }

    public static List<ACommand> getCommandsList() {
        return commands;
    }
}
