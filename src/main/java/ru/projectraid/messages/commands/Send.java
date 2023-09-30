package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.exceptions.UserMismatchException;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

public class Send extends ACommand {

    @Override
    public String getCommandName() {
        return "Ответить";
    }

    @Override
    public String getCommandDescription() {
        return "Отправить сообщение / ответить на репорт";
    }

    @Override
    public int getPermissionsLevel() {
        return 3;
    }

    @Override
    public void action( User user, Message message, String... args) throws IncorrectArgumentException {
        if(args.length<=2)
            throw new IncorrectArgumentException(this.getCommandName(), "Установить ID Значение");
        else {
            try {
                    User userTo = Database.getUser(Integer.parseInt(args[1]), true);
                    String sendMessages = message.getText().replace(args[0], "").replace(String.valueOf(Integer.parseInt(args[1])), "");
                    Bot.getInstance.sendMsgToUser(userTo, "Вам было доставлено сообщение: \n" + sendMessages + "\n Хорошего дня!");

                    Bot.getInstance.sendMsgToUser(user, "Сообщение было доставлено игроку с ID:" + userTo.getUniqueId() + "\n" + sendMessages);
            } catch (UserMismatchException e) {
                Bot.getInstance.sendMsgToUser(user, e.getMessage());
            }
        }
    }
}
