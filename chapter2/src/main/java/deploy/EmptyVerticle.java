package deploy;

import io.vertx.core.AbstractVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmptyVerticle extends AbstractVerticle {

    private final Logger log = LoggerFactory.getLogger(EmptyVerticle.class);


    @Override
    public void start() throws Exception {
        log.info("Start");
    }

    @Override
    public void stop() throws Exception {
        log.info("Stop");
    }
}
