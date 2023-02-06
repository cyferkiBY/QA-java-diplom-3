import com.codeborne.selenide.Configuration;
import org.junit.Before;

public class BaseTest {
    @Before
    public void configureDriver() {
        Configuration.browserSize = "1920x1080";
    }
}