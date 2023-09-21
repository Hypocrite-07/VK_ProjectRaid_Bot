> # VK Project Raid Activity Bot
> Разработчики __[Hypocrite07](https://t.me/hypocrite07)__ и __[Higirayn](https://t.me/higirayn)__.
> 
> Специально для проекта ____[ProjectRaid](https://vk.com/deathraid)____
> 
> Версия: 1.0
## Описание:
Данный бот был написан для отслеживания активностей в ВК группе __[ProjectRaid](https://vk.com/deathraid)__.
####
Подробное ТЗ можно найти тут: [*ТЫК*](https://docs.google.com/document/d/1FbEJBzpL2xOeX8F21zyrGTMMFqFeScGjnMgNVsLwYBA/edit?usp=sharing)
## Используемый SDK:
> [Java VK Bots Long Poll API](https://github.com/yvasyliev/java-vk-bots-long-poll-api)

### Требования:
    - Java 8 или выше
    - Gradle 8.0 или иной

### Как добавлять команды?
```java
public class Main {
    public static void main ( String[] args ) {
        initCommands();
        //...
    }
    private static void initCommands() {
        MessageHandler.addCommand(new Profile());
        MessageHandler.addCommand(new Help());
    }
}
```

### Примеры команд
```java
public class Profile extends ACommand {

    // Устанавливаем имя команды, с помощью которого они и будет вызываться
    @Override
    public String getCommandName() {
        return "Профиль";
    }

    // Описание команды. Выводится в выводе команды "Помощь"
    @Override
    public String getCommandDescription() {
        return "Выводит данные профиля";
    }

    //Уровень доступа, с которого можно использовать данную команду
    @Override
    public int getPermissionsLevel() {
        return 1;
    }

    //Действие, которое будет происходить в случае выполнения всех условий по вызову команды
    @Override
    public void action(User user) {
        Bot.getInstance.sendMsgToUser(user, user.toString());
    }
}
```

Уровни доступа команд на момент `Version 1.0` :

>Гость - 0
> 
>Пользователь - 1
> 
>Администратор - 2
> 
>Разработчик - 3
> 
>~~Консоль - 4~~ __SOON__

