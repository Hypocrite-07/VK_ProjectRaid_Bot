package ru.projectraid.user;

public enum UserType {
    GUEST(0, "Гость"), AUTHORIZED(1, "Игрок"), ADMIN(2, "Администратор");

    public String statusName;
    public int permissionsId;

    UserType(int statusId, String statusName) {
        this.permissionsId = statusId;
        this.statusName = statusName;
    }

    public static UserType getUserTypeById(int id) {
        switch (id) {
            case 1:
                return UserType.AUTHORIZED;
            case 2:
                return UserType.ADMIN;
            default:
                return UserType.GUEST;
        }
    }
}
