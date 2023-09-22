package ru.projectraid;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.likes.Like;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.objects.basic.Message;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IllegalAccess;
import ru.projectraid.exceptions.IncorrectArgument;
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

    /**
     * Позволяет отправить текстовое сообщение в ЛС пользователю от имени группы
     * @param user пользователь, которому мы отправляем
     * @param text текст, который мы отправляем
     */
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

    /**
     * Ивент, который срабатывает при получении сообщения от пользователя.
     * Сначала мы добавляем пользователя в массив сохранённых пользователей(если он уже есть там, то данное действие игнорируется),
     * а после мы получаем данного пользователя и обрабатываем сообщение через {@link MessageHandler}.
     * @param messageNew event object.
     */
    @Override
    public void onMessageNew(MessageNew messageNew) {
        Message message = messageNew.getMessage();
        if (message.hasText()) {
            String response;
            Database.addUser(message.getFromId());
            User user = Database.getUser(message.getFromId());

            try {
                if(MessageHandler.useCommand(user, message.getText())) {}
                else sendMsgToUser(user, "Данной команды нет в списке!");
            } catch (IllegalAccess | IncorrectArgument e)
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
