package ru.projectraid.activitys_shop.products;

import ru.projectraid.Bot;
import ru.projectraid.user.User;
import ru.projectraid.user.UserType;
import ru.projectraid.utils.RandomStringGenerator;

import java.util.UUID;

public class BetaTestProduct extends  AProduct {

    @Override
    public String getNameProduct() {
        return "ЗБТ";
    }

    @Override
    public String getProductDescription() {
        return "После покупки предоставляется ключ для участия в ЗБТ.";
    }

    @Override
    public int getPermissionLevel() {
        return UserType.AUTHORIZED.permissionsId;
    }

    @Override
    public int getPrice() {
        return 1;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public void action(User user) {
        Bot.getInstance.sendMsgToUser(user, RandomStringGenerator.generateString());
    }

}
