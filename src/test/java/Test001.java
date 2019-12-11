import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Keys;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Testszenario für BMI Rechner")
public class Test001 {

    FirefoxDriver driver;

    @BeforeEach
    public void openPage() {
        //Firefox's geckodriver *requires* you to specify its location.
        System.setProperty("webdriver.gecko.driver", ".\\lib\\geckodriver.exe");
        // Aufruf der Seite
        driver=new FirefoxDriver();
        driver.get("https://60tools.com/de/tool/bmi-calculator");
        driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
    }

    @Test
    @DisplayName("Test für BMI Techner mit Testmethode")
    public void test_Oversized2() {
        testen_BMI("180", "200", "20", "male", 2, "45");
    }

    @Test
    @DisplayName("Test für BMI Techner ohne Testmethode")
    public void test_Oversized() {

        // Eingabe der Daten in die Textfelder
        driver.findElement(By.name("weight")).sendKeys("180");
        driver.findElement(By.name("size")).sendKeys("200");
        driver.findElement(By.name("age")).sendKeys("20");

        // Auswahl des Geschlechts
        WebElement gender = driver.findElement(By.name("sex"));
        gender.sendKeys("Männlich");
        gender.sendKeys(Keys.RETURN);

        // Starten der Berechnung per Klick auf den Button
        WebElement button = driver.findElement(By.xpath("//*[@id=\"toolForm\"]/table/tbody/tr[5]/td[2]/input[2]"));
        button.click();

        // Vergleich des Ergebnisses
        String str2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]")).getText().substring(0,2);
        System.out.println("str2: " + str2.substring(0,2));
        assertTrue(str2.contains("45"));
    }

    @Test
    @DisplayName("Test für BMI Techner mit Untergewicht")
    public void test_Undersized() {

        // Eingabe der Daten in die Textfelder
        driver.findElement(By.name("weight")).sendKeys("50");
        driver.findElement(By.name("size")).sendKeys("200");
        driver.findElement(By.name("age")).sendKeys("20");

        // Auswahl des Geschlechts
        WebElement gender = driver.findElement(By.name("sex"));
        gender.sendKeys("Männlich");
        gender.sendKeys(Keys.RETURN);

        // Starten der Berechnung per Klick auf den Button
        WebElement button = driver.findElement(By.xpath("//*[@id=\"toolForm\"]/table/tbody/tr[5]/td[2]/input[2]"));
        button.click();

        // Vergleich des Ergebnisses
        String str2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]")).getText().substring(0,4);
        System.out.println("str2: " + str2.substring(0,4));
        assertTrue(str2.contains("12.5"));

    }


    @AfterEach
    public void closePage() {
        driver.close();
    }

    private void testen_BMI(String weight, String size, String age, String sex, int sub, String check ) {

        // Eingabe der Daten in die Textfelder
        driver.findElement(By.name("weight")).sendKeys(weight);
        driver.findElement(By.name("size")).sendKeys(size);
        driver.findElement(By.name("age")).sendKeys(age);

        // Auswahl des Geschlechts
        WebElement gender = driver.findElement(By.name("sex"));
        if (sex.contains("male")) gender.sendKeys("Männlich");
        if (sex.contains("female")) gender.sendKeys("Weiblich");
        gender.sendKeys(Keys.RETURN);

        // Starten der Berechnung per Klick auf den Button
        WebElement button = driver.findElement(By.xpath("//*[@id=\"toolForm\"]/table/tbody/tr[5]/td[2]/input[2]"));
        button.click();

        // Vergleich des Ergebnisses
        String str2 = driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]")).getText().substring(0,sub);
        System.out.println("str2: " + str2.substring(0,sub));
        assertTrue(str2.contains(check));
    }


}
