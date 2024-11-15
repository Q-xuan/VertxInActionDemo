package deploy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Deployer extends AbstractVerticle {

    private final Logger log = LoggerFactory.getLogger(Deployer.class);

    @Override
    public void start() {
        long delay = 1000;
        for (int i = 0; i < 50; i++) {
            vertx.setTimer(delay, id -> deploy());
            delay = delay + 1000;
        }
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Deployer());
    }

    private void deploy() {
        vertx.deployVerticle(new EmptyVerticle(), ar -> {
            if (ar.succeeded()) {
                String id = ar.result();
                log.info("Successfully deploy {}", id);
                vertx.setTimer(5000, tid -> unDeployLater(id));
            }
        });
    }

    private void unDeployLater(String id) {
        vertx.undeploy(id, ar -> {
            if (ar.succeeded()) {
                log.info("{} was unDeployed", id);
            } else {
                log.error("{} could not be unDeployed", id);
            }
        });
    }

}
