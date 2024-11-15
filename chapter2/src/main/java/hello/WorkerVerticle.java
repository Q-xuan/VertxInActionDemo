package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.ThreadingModel;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerVerticle extends AbstractVerticle {

    private final Logger log = LoggerFactory.getLogger(WorkerVerticle.class);

    @Override
    public void start() throws Exception {
        vertx.setPeriodic(10_000, id -> {
            try {
                log.info("Zzz...");
                Thread.sleep(8000);
                log.info("Up !");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        DeploymentOptions opts = new DeploymentOptions()
                .setInstances(2)
                .setThreadingModel(ThreadingModel.WORKER);
        vertx.deployVerticle(WorkerVerticle.class, opts);
    }
}
