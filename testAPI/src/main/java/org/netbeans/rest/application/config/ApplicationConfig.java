package org.netbeans.rest.application.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    // Empty class - used only to activate JAX-RS!
}
