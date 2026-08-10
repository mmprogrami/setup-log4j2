package nl.mmprogrami.log4j2.test;

import java.io.IOException;
import java.util.logging.LogManager;

import java.util.logging.Logger;

import org.apache.logging.log4j.jul.Log4jBridgeHandler;
import org.junit.platform.launcher.*;


/**
 * /logging.properties is automatically picked up in servlet environment.
 * <p>
 * This makes it work in jupiter tests too.
 *
 */
public class Jul2Log4j2 implements LauncherSessionListener {

    @Override
    public void launcherSessionOpened(LauncherSession launcherSession) {
        try {
            Log4jBridgeHandler.install(true, null, true);
            LogManager.getLogManager().readConfiguration(getClass().getResourceAsStream("/logging.properties"));//, (s) -> (ss, m) -> {return m;});
            Logger.getLogger(Jul2Log4j2.class.getName()).fine("Logging properties loaded");
        } catch (IOException ignore) {

        } catch (Exception e) {
            System.err.println("Failed to load logging properties: " + e.getMessage());
        }
    }


}
