package ru.projectraid;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.likes.Like;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IllegalAccess;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.user.User;

import java.util.List;
import java.util.Random;

public class Bot extends LongPollBot {

    public static Bot getInstance;
    private String vkToken;

    public Bot(String vkToken) {
        this.vkToken = vkToken;
        getInstance = this;
    }

    public void sendMsgToUser(User user, String text) {
        try {
            vk.messages.send()
                    .setPeerId(user.getUniqueId())
                    .setMessage(text)
                    .execute();
        } catch (VkApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        if (message.hasText()) {
            String response;
            Database.addUser(message.getFromId());
            User user = Database.getUser(message.getFromId());

            try {
                if(MessageHandler.useCommand(user, message.getText()))
                    System.out.println(user.getUniqueId() + " used command " + message.getText());
                else
                    sendMsgToUser(user, "Incorrect command");
            } catch (IllegalAccess e)
            {
                sendMsgToUser(user, e.getMessage());
            }
        }
    }

    @Override
    public void onLikeAdd(Like like) {
//        try {
//
//        } catch (VkApiException e) {
//
//        }
    }

    @Override
    public String getAccessToken() {
        return vkToken;
    }

}
