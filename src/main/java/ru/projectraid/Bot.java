package ru.projectraid;

import api.longpoll.bots.LongPollBot;
import api.longpoll.bots.exceptions.VkApiException;
import api.longpoll.bots.model.events.likes.Like;
import api.longpoll.bots.model.events.messages.MessageNew;
import api.longpoll.bots.model.events.wall.comments.WallReply;
import api.longpoll.bots.model.events.wall.comments.WallReplyDelete;
import api.longpoll.bots.model.objects.basic.Message;

import ru.projectraid.activitys_shop.ActivityTable;
import ru.projectraid.database.Database;
import ru.projectraid.exceptions.IllegalAccessException;
import ru.projectraid.exceptions.IncorrectArgumentException;
import ru.projectraid.exceptions.UserMismatchException;
import ru.projectraid.messages.MessageHandler;
import ru.projectraid.user.User;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Bot extends LongPollBot {

    public static final Logger logger = Logger.getLogger(Bot.class.getName());
    public static Bot getInstance;
    private final String vkToken;
    private final int groupId = -222574479;

    public Bot(String vkToken) {
        this.vkToken = vkToken;
        getInstance = this;
        logger.log(Level.INFO, "Бот был проинициализирован");
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
            User user = null;
            try {
                user = Database.getUser(message.getFromId(), false);
                if(MessageHandler.useCommand(user, message)) {}
                else sendMsgToUser(user, "Данной команды нет в списке!");
            } catch (IllegalAccessException | IncorrectArgumentException e)
            {
                sendMsgToUser(user, e.getMessage());
            } catch (UserMismatchException ignored) {}
        }
    }

    @Override
    public void onLikeAdd(Like like) {
        logger.log(Level.INFO, like.toString());
        User user = null;
        try {
            user = Database.getUser(like.getLikerId(), true);
        } catch (UserMismatchException e) {
            logger.log(Level.WARNING, "Пользователь с идентификатором " + like.getLikerId() + " не был инициализирован в боте.");
            return;
        }
        if(like.getObjectOwnerId() == groupId) user.addActivities(ActivityTable.LIKE_EVENT.activitiesCount, ActivityTable.LIKE_EVENT, like);
    }

    @Override
    public void onLikeRemove(Like like) {
        User user = null;
        try {
            user = Database.getUser(like.getLikerId(), true);
        } catch (UserMismatchException e) {
            logger.log(Level.WARNING, "Пользователь с идентификатором " + like.getLikerId() + " не был инициализирован в боте.");
            return;
        }
        if(like.getObjectOwnerId() == groupId) user.addActivities(-ActivityTable.LIKE_EVENT.activitiesCount, ActivityTable.LIKE_EVENT, like);
    }

    @Override
    public void onWallReplyNew(WallReply wallReply) {
        logger.log(Level.INFO, wallReply.toString());
        User user = null;
        try {
            user = Database.getUser(wallReply.getFromId(), true);
        } catch (UserMismatchException e) {
            logger.log(Level.WARNING, "Пользователь с идентификатором " + wallReply.getFromId() + " не был инициализирован в боте.");
            return;
        }
        int postId = wallReply.getPostId();
        if(!user.isReplyPost(postId)) {
            user.addReply(postId);
            user.addActivities(ActivityTable.COMMENT_EVENT.activitiesCount, ActivityTable.COMMENT_EVENT, wallReply);
        }
    }

    @Override
    public void onWallReplyDelete(WallReplyDelete wallReplyDelete) {
        User user = null;
        try {
            user = Database.getUser(wallReplyDelete.getDeleterId(), true);
        } catch (UserMismatchException e) {
            logger.log(Level.WARNING, "Пользователь с идентификатором " + wallReplyDelete.getDeleterId() + " не был инициализирован в боте.");
            return;
        }
        user.removeReplay(wallReplyDelete.getPostId());
        user.addActivities(-ActivityTable.COMMENT_EVENT.activitiesCount, ActivityTable.COMMENT_EVENT, wallReplyDelete);
    }

    @Override
    public void onWallReplyRestore(WallReply wallReply) {
        onWallReplyNew(wallReply);
    }

    @Override
    public String getAccessToken() {
        return vkToken;
    }

}
