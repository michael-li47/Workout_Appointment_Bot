import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class main {
    public static void main(String[] args) throws InterruptedException {
        //Setting the driver path
        System.setProperty("webdriver.chrome.driver", "D:\\Downloads\\chromedriver_win32 (3)\\chromedriver.exe");
        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(15000, TimeUnit.MILLISECONDS);
        driver.manage().window().setPosition(new Point(-1000, 0));
        driver.manage().window().maximize();

        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.setTime(date);
        String dayOfWeek = utils.getDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));
        List<String> restDaysList = Arrays.asList(constants.restDays);

        if (!(restDaysList.contains(dayOfWeek))) {
            utils.NavigateToURL(driver, URLS.LOGIN_PAGE);
            utils.Login(driver);
            utils.NavigateToURL(driver, URLS.BOOKING_PAGE);
            driver.findElement(By.cssSelector(Selectors.FITNESS_CENTRE)).click();
            Appointment appointment = new Appointment();
            WebElement child = utils.getAppointmentRow(driver, appointment).get(0);
            if (child == null) {
                utils.closeDriver(driver);
                System.exit(1);
            }
            WebElement row = child.findElement(By.xpath("./.."));
            utils.bookAppointment(driver, row);
        }
        utils.closeDriver(driver);
    }

}
