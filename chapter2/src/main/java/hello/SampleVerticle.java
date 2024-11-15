package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SampleVerticle extends AbstractVerticle {

    private final Logger log = LoggerFactory.getLogger(SampleVerticle.class);

    @Override
    public void start() throws Exception {
        log.info("n = {}", config().getInteger("n", -1));
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        for (int n = 0; n < 4; n++) {
            JsonObject conf = new JsonObject().put("n", n);
            DeploymentOptions opts = new DeploymentOptions()
                    .setConfig(conf)
                    .setInstances(n);
            vertx.deployVerticle("hello.SampleVerticle", opts);
        }
    }
}
