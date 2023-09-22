package ru.projectraid.exceptions;

public class IncorrectArgument extends Throwable{
    public IncorrectArgument(String command, String msg) {
        super("При использовании команды \"" + command + "\" " + msg);
    }

    public IncorrectArgument(String command) {
        super("При использовании команды \"" + command + "\" " + "произошла ошибки в указании аргументов!");
    }
}
