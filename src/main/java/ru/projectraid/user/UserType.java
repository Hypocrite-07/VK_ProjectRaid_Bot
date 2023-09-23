package ru.projectraid.user;

public enum UserType {

    GUEST(0, "Гость"),
    AUTHORIZED(1, "Игрок"),
    ADMIN(2, "Администратор"),
    DEVELOPER(3, "Разработчик");

    public final String statusName;
    public final int permissionsId;

    UserType(int statusId, String statusName) {
        this.permissionsId = statusId;
        this.statusName = statusName;
    }

    /**
     * Используется для получения {@link UserType} по {@link #permissionsId}
     * @param id идентификатор правового статуса
     * @return {@link UserType}, который имеет указанный в аргументах идентификатор правового статуса
     */
    public static UserType getUserTypeById(int id) {
        switch (id) {
            case 1:
                return UserType.AUTHORIZED;
            case 2:
                return UserType.ADMIN;
            case 3:
                return UserType.DEVELOPER;
            default:
                return UserType.GUEST;
        }
    }
}
