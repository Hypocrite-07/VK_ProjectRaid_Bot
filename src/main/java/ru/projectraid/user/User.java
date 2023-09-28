package ru.projectraid.user;

import api.longpoll.bots.model.events.Update;
import ru.projectraid.Bot;
import ru.projectraid.activitys_shop.ActivityTable;
import ru.projectraid.messages.commands.ACommand;

import java.util.ArrayList;

import static ru.projectraid.database.Database.update;

public class User {
    private final int uniqueId;
    private ArrayList<Integer> replyPost;
    private int activities;

    public UserType getUserType() {
        return userType;
    }

    private UserType userType;

    /**
     * Конструктор пользователя
     * @param uniqueId уникальный идентификатор пользователя в ВК
     * @param userType правовой доступ пользователя
     */

    public User(int uniqueId, UserType userType) {
        this.uniqueId = uniqueId;
        this.userType = userType;
        this.replyPost = new ArrayList();
    }

    /**
     *
     * @return уникальный идентификатор пользователя в ВК
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     * Проверка игрока на доступ к команде
     * @param command комманда, доступ к которой мы хотим проверить
     * @return {@code true} при условии того, что наш правовой статус позволяет использовать комманду
     */
    public boolean canUseCommand(ACommand command) {
        return this.userType.permissionsId >= command.getPermissionsLevel();
    }

    public int getActivities() {
        return activities;
    }

    public void addActivities(int activities, ActivityTable activityType, Update.Object _object) {
        this.activities += activities;
        if(activities > 0)
            Bot.getInstance.sendMsgToUser(this, "На ваш счёт зачислен " + activityType.activitiesCount + "\nТекущий счёт:" + getActivities() + "\n" + _object.toString());
        else
            Bot.getInstance.sendMsgToUser(this, "С вашего счёта вычеслено " + activityType.activitiesCount + "\nТекущий счёт:" + getActivities() + "\n" + _object.toString());
        update();
    }

    public void setActivities(int setActivities) {
        this.activities = setActivities;
    }

    public void addReply(int postId) {
        replyPost.add(postId);
    }

    public void removeReplay(int postId) {
        replyPost.remove((Object) postId);
    }

    public boolean isReplyPost(int postId) {
        return replyPost.contains(postId);
    }

    @Override
    public String toString() {
        return "Профиль" +
                "\nИдентификатор: " + uniqueId +
                "\nСтатус: " + userType.statusName +
                "\nБаллы: " + activities;
    }

}
