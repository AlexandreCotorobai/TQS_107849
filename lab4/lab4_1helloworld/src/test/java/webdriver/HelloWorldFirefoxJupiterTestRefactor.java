package webdriver;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import static org.slf4j.LoggerFactory.getLogger;
import static java.lang.invoke.MethodHandles.lookup;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.seljup.DockerBrowser;
import io.github.bonigarcia.seljup.SeleniumJupiter;
import static io.github.bonigarcia.seljup.BrowserType.CHROME;
/**
 * Unit test for simple App.
 */
@ExtendWith(SeleniumJupiter.class)
class HelloWorldFirefoxJupiterTestRefactor {

    static final Logger log = getLogger(lookup().lookupClass());

    @Test
    void testRef(FirefoxDriver driver) {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @Test
    void testRef2(@DockerBrowser(type = CHROME) WebDriver driver) {
        // Exercise
        String sutUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(sutUrl);
        String title = driver.getTitle();
        log.debug("The title of {} is {}", sutUrl, title);

        // Verify
        assertThat(title).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

}
