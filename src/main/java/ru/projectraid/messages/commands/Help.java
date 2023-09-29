package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

public class Help extends ACommand{

    @Override
    public String getCommandName () {
        return "Помощь";
    }

    @Override
    public String getCommandDescription () {
        return "Выдаёт список команд, которые возможно использовать";
    }

    @Override
    public int getPermissionsLevel () {
        return 0;
    }

    @Override
    public void action ( User user, Message message,  String... args) throws IncorrectArgumentException {
        if(args.length > 1)
            throw new IncorrectArgumentException(getCommandName(), "был найден лишний аргумент!");
        StringBuilder commandslist = new StringBuilder("В данный момент вы можете использовать следующие команды: \n\n");
        for (ACommand command: MessageHandler.getCommandsList()) {
            if(user.canUseCommand(command))
                commandslist.append(command.getCommandName() + " - " + command.getCommandDescription() + ";\n Уровень доступа: " + UserType.getUserTypeById(command.getPermissionsLevel()).statusName + "\n\n");
        }
        String response = commandslist.toString();

        Bot.getInstance.sendMsgToUser(user, response);
    }
}
