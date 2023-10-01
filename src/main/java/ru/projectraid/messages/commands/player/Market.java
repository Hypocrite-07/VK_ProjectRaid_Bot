package ru.projectraid.messages.commands.player;

import api.longpoll.bots.model.objects.additional.Keyboard;
import api.longpoll.bots.model.objects.additional.buttons.Button;
import api.longpoll.bots.model.objects.additional.buttons.TextButton;
import api.longpoll.bots.model.objects.basic.Message;
import com.google.gson.JsonObject;
import ru.projectraid.Bot;
import ru.projectraid.activitys_shop.products.AProduct;
import ru.projectraid.messages.commands.ACommand;
import ru.projectraid.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Market extends ACommand {

    @Override
    public String getCommandName() {
        return "Магазин";
    }

    @Override
    public String getCommandDescription() {
        return "Просмотр доступных продуктов";
    }

    @Override
    public int getPermissionsLevel() {
        return 0;
    }

    @Override
    public boolean isVisibleOnStartPage() {
        return true;
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
        for (AProduct product : ru.projectraid.activitys_shop.Shop.getProductList())
        {
            buttons.add(new TextButton(Button.Color.SECONDARY, new TextButton.Action(
                    "Купить " + product.getNameProduct()
            )));
        }

        Bot.getInstance.sendButtons(user, "Список продуктов:", getKeyboard(buttons, true));

    }
}
