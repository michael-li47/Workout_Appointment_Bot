
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class utils {
    public static void NavigateToURL(WebDriver driver, String url) {
        driver.get(constants.URL + url);
    }

    public static String getDayOfWeek(int value) {
        String day = "";
        switch (value) {
            case 1:
                day = "Sunday";
                break;
            case 2:
                day = "Monday";
                break;
            case 3:
                day = "Tuesday";
                break;
            case 4:
                day = "Wednesday";
                break;
            case 5:
                day = "Thursday";
                break;
            case 6:
                day = "Friday";
                break;
            case 7:
                day = "Saturday";
                break;
        }
        return day;
    }

    public static void Login(WebDriver driver) {
        driver.findElement(By.cssSelector(Selectors.EMAIL_INPUT)).sendKeys(constants.email);
        driver.findElement(By.cssSelector(Selectors.PASSWORD_INPUT)).sendKeys(constants.password);
        driver.findElement(By.cssSelector(Selectors.LOGIN_BUTTON)).click();
    }

    public static List<WebElement> getAppointmentRow(WebDriver driver, Appointment appointment) {
        WebElement table = driver.findElement(By.cssSelector(Selectors.BOOKING_TABLE));
        return table.findElements(By.tagName("tr"))
                .stream()
                .skip(1)
                .map(tr -> tr.findElements(By.tagName("td")))
                .filter(tds -> tds.get(1).getText().equals(appointment.getDate()) && tds.get(2).getText().equals(appointment.getTime()))
                .findFirst()
                .orElse(null);
    }

    public static void bookAppointment(WebDriver driver, WebElement row) throws InterruptedException {
        row.click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector(Selectors.BOOK_NOW_BUTTON)).click();
        List<WebElement> submitBtn = driver.findElements(By.cssSelector(Selectors.SUBMIT_BUTTON));
        if (submitBtn.isEmpty()) {
            closeDriver(driver);
            System.exit(2);
        }
        submitBtn.get(0).click();
        System.out.println("Booked");
    }

    public static void closeDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}