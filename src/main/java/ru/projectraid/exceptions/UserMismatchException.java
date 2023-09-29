package ru.projectraid.exceptions;

public class UserMismatchException extends Throwable {
    /**
     * Используется в рамках базы данных.
     * Выкидывается в случае того, если пользователь с уникальным идетификатором не был найден.
     * @param userId идентификатор, по которому не был найден пользователь
     */
    public UserMismatchException(int userId) {
        super("Пользовател с указанным идентификатором не найден: " + userId );
    }

    @Override
    public String getMessage () {
        return super.getMessage();
    }

}
