package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.user.User;

public class Test extends ACommand {

    @Override
    public String getCommandName() {
        return "test";
    }

    @Override
    public String getCommandDescription() {
        return "тестовая команда";
    }

    @Override
    public int getPermissionsLevel() {
        return 3;
    }

    @Override
    public void action( User user, Message message, String... args) {
        if(message.hasGeo())
        {
            Bot.getInstance.sendMsgToUser(user, "тебе пизда, мы выехали за тобой в твой мухосранск под названием \"" + message.getGeo().getPlace().getCity() + "\"");
            return;
        }
        if(args.length > 1) {
            Bot.getInstance.sendMsgToUser(user, "test: " + args[1]);
        }
        else
            Bot.getInstance.sendMsgToUser(user, "test: empty");
    }
}
