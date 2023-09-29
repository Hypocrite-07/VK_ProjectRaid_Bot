package ru.projectraid.exceptions;

public class IncorrectArgumentException extends Throwable{
    public IncorrectArgumentException(String command, String msg) {
        super("При использовании команды \"" + command + "\" произошло исключение: " + msg);
    }

    public IncorrectArgumentException(String command) {
        super("При использовании команды \"" + command + "\" " + "произошла ошибки в указании аргументов!");
    }
}
