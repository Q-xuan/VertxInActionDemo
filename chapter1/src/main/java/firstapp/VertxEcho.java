package firstapp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

public class VertxEcho {

    private static int numberOfConnections = 0;

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.createNetServer()
                .connectHandler(VertxEcho::handleNewClient)
                .listen(3000);

        vertx.setPeriodic(5000, id -> System.out.println(howMany()));

        vertx.createHttpServer()
                .requestHandler(req -> req.response().end(howMany()))
                .listen(8080);

    }

    private static String howMany() {
        return "We now have" + numberOfConnections + " connections";
    }

    private static void handleNewClient(NetSocket netSocket) {
        numberOfConnections++;
        netSocket.handler(b -> {
            netSocket.write(b);
            if (b.toString().endsWith("/quit\n")) {
                netSocket.close();
            }
        });
        netSocket.closeHandler(v -> numberOfConnections--);
    }
}
