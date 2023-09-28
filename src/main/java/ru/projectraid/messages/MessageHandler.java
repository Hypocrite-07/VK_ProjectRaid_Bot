package ru.projectraid.messages;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.exceptions.IllegalAccess;
import ru.projectraid.exceptions.IncorrectArgument;
import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.util.ArrayList;
import java.util.List;

public class MessageHandler {

    private static final List<ACommand> commands = new ArrayList<>();

    /**
     * Используется для добавления комманды в лист, при условии, что отсутствует команда с именем,
     * которое используется в свойствах передаваемой через параметры команды.
     * @param command команда, которую мы хотим добавить
     */
    public static void addCommand(ACommand command) {
        if(existCommand(command.getCommandName())) return;
        System.out.println("Команда \"" + command.getCommandName() + "\" была добавлена в список.");
        commands.add(command);
    }

    /**
     * Данный метод позволяет нам использовать действие команды, когда её вызвал игрок.
     * @param user пользователь вызвавший команду
     * @param message сообщение, которое отправил пользователь
     * @return успешное использование команды
     * @throws IllegalAccess выкидывает исключение при условии того, что у {@link User} {@code StatusID} в {@link UserType} меньше, чем необходимо для использования команды
     * @throws IncorrectArgument выкидывает исключение при условии того, что у вызванной команды была некорректно пройдена проверка на аргументы
     */
    public static boolean useCommand( User user, Message message) throws IllegalAccess, IncorrectArgument {
        String[] args = message.getText().split(" ");
        if(existCommand(args[0]))
        {
            ACommand command = getCommand(args[0]);
            assert command != null;
            if(user.canUseCommand(command))
            {
                command.action(user, message, args);
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
            if(command.getCommandName().equalsIgnoreCase(commandName))
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
