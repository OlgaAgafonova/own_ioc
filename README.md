# Собственный IoC-container

## Первый способ реализации (Модуль IoCOne)

Используется метод вставки инъекций в конструктор.
Чтобы определить в какой конструктор надо делать инъекции, используется пользовательская анотация (```@UseIoCOne```).
Можно использовать и собственную анотацию. Для этого надо изменить в интерфейсе ```IoCInterface``` значение поля ```IOC_ANNOTATION```.
 
Рекурсивно создаются все необходимые параметры для объекта, который требуется пользователю.

**Регистрация реализаций интерфейса:**

>``` 
>IoCOne container = new IoCOne();
>
>container.register(Интерфейс.class, РеализацияИнтерфейса.class);
> 
>container.register(Service.class, ServiceOneImpl.class);
>container.register(NewService.class, NewServiceOneImpl.class);
> ```

**Создание объекта:**
>```
>ClientTwo resolve = (ClientTwo) container.resolve(ClientTwo.class);
>```

![iocOne](https://github.com/OlgaAgafonova/own_ioc/raw/master/IoCOne/src/main/resources/iocOne.jpg "Диаграмма классов")