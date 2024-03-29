package ru.projectraid.messages.commands.admin;

import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.exceptions.UserMismatchException;
import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.util.List;

public class SetPermissions extends ACommand {

    @Override
    public String getCommandName() {
        return "Права";
    }

    @Override
    public String getCommandDescription() {
        return "Повысить игрока до должности";
    }

    @Override
    public int getPermissionsLevel() {
        return 3;
    }

    @Override
    public Keyboard getKeyboard(List<Button> buttons, boolean inline) {
        return super.getKeyboard(buttons, inline);
    }

    @Override
    public void action( User user, Message message, String... args) throws IncorrectArgumentException {
        if(args.length<=2)
            throw new IncorrectArgumentException(this.getCommandName(), "Установить ID Значение");
        else {
            try {
                if(user.getUserType().permissionsId >= Integer.parseInt(args[2])) {
                    User userTo = Database.getUser(Integer.parseInt(args[1]), true);
                    userTo.setUserType(UserType.getUserTypeById(Integer.parseInt(args[2])));
                }
            } catch (UserMismatchException e) {
                Bot.getInstance.sendMsgToUser(user, e.getMessage());
            } catch (NumberFormatException e) {
                throw new IncorrectArgumentException(this.getCommandName(), "Установить ID Значение");
            }
        }
    }
}
