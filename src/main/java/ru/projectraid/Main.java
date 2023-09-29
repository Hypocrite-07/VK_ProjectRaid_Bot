package ru.projectraid;

import api.longpoll.bots.exceptions.VkApiException;
import ru.projectraid.database.Database;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.messages.commands.Help;
import ru.projectraid.messages.commands.Profile;
import ru.projectraid.messages.commands.SetActivities;
import ru.projectraid.messages.commands.Test;

import java.io.IOException;

public class Main {

    public static void main ( String[] args ) throws IOException, VkApiException {

        Database.loadUsersFromJsonFile();
        Database.initDevelopers();

        initCommands();
        Bot bot = new Bot("vk1.a.umOj-hRMmmIlipTzs74Su3wTxeq9NPS6vz1-fWKCxcTbSO7JhHiQnwvNTzjcLr-eDj1tK7eJeMB3L5n30nhVyc8-tBLmwRZePnvcDAlGzqPfkJNSGHUJZmdHTM32huxRka95m53QXKxc4-4N2aNGuetoXJs1Bx54wozXr_m4mI66PF_IL_kOFcms-K5cnlYdQ6oXDjAeHNRkmoxsUSF0ew");

        bot.startPolling();

   }

    private static void initCommands() {
        MessageHandler.addCommand(new Profile());
        MessageHandler.addCommand(new Help());
        MessageHandler.addCommand(new Test());
        MessageHandler.addCommand(new SetActivities());
    }
    
}