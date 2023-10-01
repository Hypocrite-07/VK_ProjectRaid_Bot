package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.activitys_shop.products.AProduct;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Start extends ACommand {

    @Override
    public String getCommandName() {
        return "Начать";
    }

    @Override
    public String getCommandDescription() {
        return "Просмотр доступных комманд";
    }

    @Override
    public int getPermissionsLevel() {
        return 0;
    }

    @Override
    public boolean getSplitArgs() {
        return true;
    }

    @Override
    public Keyboard getKeyboard(List<Button> buttons, boolean inline) {
        return new Keyboard(Arrays.asList(buttons)).setInline(inline);
    }

    @Override
    public void action( User user, Message message, String... args) {
        List<Button> buttons = new ArrayList<>();

        for (ACommand com: MessageHandler.getCommandsList()) {
            if(com.isVisibleOnStartPage())
                buttons.add(new TextButton(Button.Color.SECONDARY, new TextButton.Action(
                    com.getCommandName()
               )));
        }
        Bot.getInstance.sendButtons(user, "Список команд:", getKeyboard(buttons, false));

    }
}
