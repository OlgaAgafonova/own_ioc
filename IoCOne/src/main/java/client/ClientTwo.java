package client;

import annotation.UseIoCOne;
import com.sun.istack.internal.NotNull;
import services.NewService;
import services.Service;

public class ClientTwo {

    private Client client;

    public ClientTwo() {
    }

    @UseIoCOne
    public ClientTwo(Client client) {
        client.useServices();
        System.out.println("Создан ClientTwo");
    }

    public void printMessage(String message) {
        System.out.printf("Client: %s%n", message);
    }
}
