package client;

import annotation.UseIoCOne;
import com.sun.istack.internal.NotNull;
import services.NewService;
import services.Service;

public class Client {
    private String name;
    private Service service;
    private NewService newService;

    @UseIoCOne
    public Client(@NotNull Service service, @NotNull NewService newService) {
        this.service = service;
        this.newService = newService;
        System.out.println("Создан Client");
    }

    public void printMessage(String message) {
        System.out.printf("Client: %s, %s%n", name, message);
    }

    public void useServices() {
        service.doSomething();
        newService.doSomethingNew();
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }
}
