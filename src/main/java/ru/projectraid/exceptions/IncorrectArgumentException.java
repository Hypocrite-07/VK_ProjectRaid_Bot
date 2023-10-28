package ru.projectraid.exceptions;

public class IncorrectArgumentException extends Throwable{
    public IncorrectArgumentException(String command, String msg) {
        super("При использовании команды \"" .concat( command).concat( "\" произошло исключение: ").concat( msg));
    }

    public IncorrectArgumentException(String command) {
        super("При использовании команды \"" .concat( command).concat ("\" ").concat("произошла ошибки в указании аргументов!"));
    }
}
