package ru.projectraid.messages;

import ru.projectraid.exceptions.IllegalAccess;
import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.util.ArrayList;
import java.util.List;


public class MessageHandler {

    private static List<ACommand> commands = new ArrayList<>();

    /**
     * Используется для добавления комманды в лист, при условии, что отсутствует команда с именем,
     * которое используется в свойствах передаваемой через параметры команды.
     * @param command команда, которую мы хотим добавить
     */
    public static void addCommand(ACommand command) {
        if(existCommand(command.getCommandName()))
            return;
        System.out.println("Комманда \"" + command.getCommandName() + "\" была добавлена в список.");
        commands.add(command);
    }

    /**
     * Данный метод позволяет нам использовать действие команды, когда её вызвал игрок.
     * @param user игрок вызвавший команду
     * @param commandName имя команды
     * @return успешное использование команды
     * @throws IllegalAccess выкидывает исключение при условии того, что у {@link User} {@code StatusID} в {@link UserType} меньше, чем необходимо для использования команды
     */
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

    /**
     * Проверяет наличие команды в списке проинициализированных комманд {@link #commands}
     * @param commandName имя команды
     * @return {@code true} при условии нахождении команды в списке
     */
    private static boolean existCommand(String commandName) {
        for (ACommand command : commands) {
            if(command.getCommandName().equalsIgnoreCase(commandName))
                return true;
        }
        return false;
    }

    /**
     * Полуечает команду по её {@code commandName}
     * @param commandName имя команды
     * @return возрвщает саму команду {@link ACommand}
     */
    private static ACommand getCommand(String commandName) {
        for (ACommand command : commands) {
            if(command.getCommandName().toLowerCase().equals(commandName.toLowerCase()))
                return command;
        }
        return null;
    }

    /**
     *
     * @return возвращает список команд из {@link #commands}
     */
    public static List<ACommand> getCommandsList() {
        return commands;
    }
}
