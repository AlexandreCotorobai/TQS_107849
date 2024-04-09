package tqs.hw1.bustickets.BDDWithSeleniumTests;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("tqs/hw1/bustickets/BDDWithSeleniumTests")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "tqs.hw1.bustickets.BDDWithSeleniumTests")
public class TicketPurchaseTest {
}
