package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

public class Report extends ACommand{

    @Override
    public String getCommandName () {
        return "Report";
    }

    @Override
    public String getCommandDescription () {
        return "Оставьте отзыв или сообщение о какой-то некорректной ситуации, чтоб связать с админами.";
    }

    @Override
    public int getPermissionsLevel () {
        return 0;
    }

    @Override
    public boolean getSplitArgs() {
        return false;
    }

    @Override
    public void action ( User user, Message message, String... args) throws IncorrectArgumentException {

        String response = args[0];

        for (User userTo: Database.getUsersWithPermissions(2)) {
            Bot.getInstance.sendMsgToUser(userTo, "Пользователь " + user.getUniqueId() + " оставил репорт.\nСодержание:\n\n" + response);
        }

        Bot.getInstance.sendMsgToUser(user, "Репорт был доставлен.\nОжидайте ответа!");
    }
}
