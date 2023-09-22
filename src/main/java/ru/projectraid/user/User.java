package ru.projectraid.user;

import ru.projectraid.market.wallet.Wallet;
import ru.projectraid.messages.commands.ACommand;

public class User {
    private final int uniqueId;
    private final Wallet wallet;

    private UserType userType;

    /**
     * Конструктор пользователя
     * @param uniqueId уникальный идентификатор пользователя в ВК
     * @param userType правовой доступ пользователя
     */
    public User(int uniqueId, UserType userType) {
        this.uniqueId = uniqueId;
        this.wallet = new Wallet();
        this.userType = userType;
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

    @Override
    public String toString() {
        return "Профиль" +
                "\nИдентификатор: " + uniqueId +
                "\nСтатус: " + userType.statusName +
                "\nБаланс: " + wallet.getActivities();
    }

}
