package ru.projectraid;

import api.longpoll.bots.exceptions.VkApiException;
import ru.projectraid.activitys_shop.Shop;
import ru.projectraid.database.Database;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.messages.commands.*;
import ru.projectraid.messages.commands.admin.Send;
import ru.projectraid.messages.commands.admin.SetActivities;
import ru.projectraid.messages.commands.admin.SetPermissions;
import ru.projectraid.messages.commands.player.*;

import java.io.IOException;

public class Main {

    public static void main ( String[] args ) throws IOException, VkApiException {
        new Shop();

        Database.loadUsersFromJsonFile();
        Database.initDevelopers();

        initCommands();

        Bot bot = new Bot("vk1.a.umOj-hRMmmIlipTzs74Su3wTxeq9NPS6vz1-fWKCxcTbSO7JhHiQnwvNTzjcLr-eDj1tK7eJeMB3L5n30nhVyc8-tBLmwRZePnvcDAlGzqPfkJNSGHUJZmdHTM32huxRka95m53QXKxc4-4N2aNGuetoXJs1Bx54wozXr_m4mI66PF_IL_kOFcms-K5cnlYdQ6oXDjAeHNRkmoxsUSF0ew");

        bot.startPolling();
   }

    private static void initCommands() {
        MessageHandler.addCommand(new Profile());
        MessageHandler.addCommand(new ShopBuyProduct());
        MessageHandler.addCommand(new Help());
        MessageHandler.addCommand(new SetActivities());
        MessageHandler.addCommand(new SetPermissions());
        MessageHandler.addCommand(new Report());
        MessageHandler.addCommand(new Send());
        MessageHandler.addCommand(new Market());
        MessageHandler.addCommand(new Start());
    }

    
}