import client.Client;
import client.ClientTwo;
import container.IoCOne;
import services.NewService;
import services.NewServiceOneImpl;
import services.Service;
import services.ServiceOneImpl;

public class Main {

    public static void main(String[] args) {
        IoCOne container = new IoCOne();
        container.register(Service.class, ServiceOneImpl.class);
        container.register(NewService.class, NewServiceOneImpl.class);

        ClientTwo resolve = (ClientTwo) container.resolve(ClientTwo.class);
    }
}
