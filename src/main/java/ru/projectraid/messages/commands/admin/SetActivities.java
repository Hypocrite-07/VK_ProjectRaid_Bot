package ru.projectraid.messages.commands.admin;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.exceptions.UserMismatchException;
import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;

public class SetActivities extends ACommand {

    @Override
    public String getCommandName() {
        return "Установить";
    }

    @Override
    public String getCommandDescription() {
        return "Устанавливает значение активностей игроку";
    }

    @Override
    public int getPermissionsLevel() {
        return 2;
    }

    @Override
    public void action( User user, Message message, String... args) throws IncorrectArgumentException {
        if(args.length<=2)
            throw new IncorrectArgumentException(this.getCommandName(), "Установить ID Значение");
        else {
            try {
                User userTo = Database.getUser(Integer.parseInt(args[1]), true);
                userTo.setActivities(Integer.parseInt(args[2]));

            } catch (UserMismatchException e) {
                Bot.getInstance.sendMsgToUser(user, e.getMessage());
            } catch (NumberFormatException e) {
                throw new IncorrectArgumentException(this.getCommandName(), "Установить ID Значение");
            }
        }
    }
}
