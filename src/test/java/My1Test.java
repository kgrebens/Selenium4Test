import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class My1Test {

    ChromeDriver driver;

    @BeforeEach
    public void starten() {
        String pathToChromeDriver = ".\\libs\\chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", pathToChromeDriver);
        driver = new ChromeDriver();
        // Aufruf der Webseite
        driver.get("https://60tools.com/de/tool/bmi-calculator");
    }

    @Test
    @DisplayName("Test für BMI Techner mit Übergewicht")
    public void testenUebergewicht() {
        // IN Felder was eingeben
        driver.findElement(By.name("weight")).sendKeys("200");
        driver.findElement(By.name("size")).sendKeys("200");
        driver.findElement(By.name("age")).sendKeys("100");

        // EIngabe männlich
        driver.findElement(By.name("sex")).sendKeys("Männlich");

        driver.findElement(By.xpath("//*[@id=\"toolForm\"]/table/tbody/tr[5]/td[2]/input[2]")).click();
        String text = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/span[1]")).getText();
        System.out.println("text: " + text);
        Assertions.assertTrue(text.contains("Übergewicht"));
    }

    @Test
    @DisplayName("Test für BMI Techner mit Untergewicht")
    public void testenUntergewicht() {

        // IN Felder was eingeben
        driver.findElement(By.name("weight")).sendKeys("50");
        driver.findElement(By.name("size")).sendKeys("180");
        driver.findElement(By.name("age")).sendKeys("20");

        // EIngabe männlich
        driver.findElement(By.name("sex")).sendKeys("Männlich");

        driver.findElement(By.xpath("//*[@id=\"toolForm\"]/table/tbody/tr[5]/td[2]/input[2]")).click();
        String text = driver.findElement(By.xpath("//*[@id=\"content\"]/div[4]/span[1]")).getText();
        System.out.println("text: " + text);
        Assertions.assertTrue(text.contains("Untergewicht"));
    }


    @AfterEach
    public void closePage() {
        driver.close();
    }

    @Test
    @DisplayName("Test für BMI Techner mit Testmethode")
    public void test_Oversized2() {
        testen_BMI("180", "200", "20", "male", 2, "45");
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

