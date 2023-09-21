package ru.projectraid;

import api.longpoll.bots.exceptions.VkApiException;
import ru.projectraid.market.Shop;
import ru.projectraid.database.Database;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.messages.commands.Help;
import ru.projectraid.messages.commands.Profile;

public class Main {
    public static void main ( String[] args ) {
        Database db = new Database();
        Shop shop = new Shop();
        initCommands();


        Bot bot = new Bot("vk1.a.umOj-hRMmmIlipTzs74Su3wTxeq9NPS6vz1-fWKCxcTbSO7JhHiQnwvNTzjcLr-eDj1tK7eJeMB3L5n30nhVyc8-tBLmwRZePnvcDAlGzqPfkJNSGHUJZmdHTM32huxRka95m53QXKxc4-4N2aNGuetoXJs1Bx54wozXr_m4mI66PF_IL_kOFcms-K5cnlYdQ6oXDjAeHNRkmoxsUSF0ew");

        try {
            bot.startPolling();
        } catch (VkApiException e) {
            throw new RuntimeException(e);
        }

    }

    private static void initCommands() {
        MessageHandler.addCommand(new Profile());
        MessageHandler.addCommand(new Help());
    }
}