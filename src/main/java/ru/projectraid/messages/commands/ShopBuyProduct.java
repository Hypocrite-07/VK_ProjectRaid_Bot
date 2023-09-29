package ru.projectraid.messages.commands;

import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.Bot;
import ru.projectraid.activitys_shop.Shop;
import ru.projectraid.activitys_shop.products.AProduct;
import ru.projectraid.user.User;

public class ShopBuyProduct extends ACommand {

    @Override
    public String getCommandName() {
        return "купить";
    }

    @Override
    public String getCommandDescription() {
        return "Купить продукт в магазине.\nПример команды: Купить ЗБТ";
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
    public void action( User user, Message message, String... args) {
        if(args.length <= 1) return;

        Bot.getInstance.sendMsgToUser(user, String.valueOf(args.length));

        AProduct product = Shop.getProduct(args[1]);
        if(product == null)
            Bot.getInstance.sendMsgToUser(user, "Продукт не найден");
        else
            Shop.buyProduct(product, user);
    }
}
