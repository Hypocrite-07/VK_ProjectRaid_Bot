package ru.projectraid.messages;

import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;

import java.util.ArrayList;


public class MessageHandler {
    public static ArrayList<? extends ACommand> commands;

    public static boolean useCommand(User user, String commandName) {
        if(existCommand(commandName))
        {
            ACommand command = getCommand(commandName);
            if(user.canUseCommand(command))
            {
                command.action(user);
                return true;
            }
        }

        return false;
    }

    public static boolean existCommand(String commandName) {
        for (ACommand command : commands) {
            if(command.getCommandName().toLowerCase().equals(commandName.toLowerCase()))
                return true;
        }
        return false;
    }

    public static ACommand getCommand(String commandName) {
        for (ACommand command : commands) {
            if(command.getCommandName().toLowerCase().equals(commandName.toLowerCase()))
                return command;
        }
        return null;
    }
}
