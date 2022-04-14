package org.acme;

import io.micrometer.core.instrument.MeterRegistry;
import org.jboss.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/data")
public class DataResource {

    private static final Logger LOGGER = Logger.getLogger(DataResource.class);
    private final MeterRegistry registry;

    DataResource(MeterRegistry registry) {
        this.registry = registry;
    }

    @GET
    @Path("{timeout}/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public DataObject get(int timeout, int size)  {
        registry.counter("data.requests").increment();
        registry.counter("data.size").increment(size + 55);
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            LOGGER.errorf(e.getMessage());
        }
        return DataObject.generate(size);
    }
}