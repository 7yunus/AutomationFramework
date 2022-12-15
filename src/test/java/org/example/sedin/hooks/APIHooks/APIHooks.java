package org.example.sedin.hooks.APIHooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.sedin.configuration.DriverSetup;

import java.io.IOException;

public class APIHooks extends DriverSetup {

    private static final Logger LOG = LogManager.getLogger(DriverSetup.class);

    @Before
    public void setUp() throws IOException {
    }

    @After
    public void takeScreenshot(Scenario scenario) {
    }
}
