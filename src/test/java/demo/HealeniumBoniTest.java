package demo;

import io.github.bonigarcia.wdm.WebDriverManager;
import com.epam.healenium.SelfHealingDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class HealeniumBoniTest {

    @Test
    public void testFormularioConHealenium() throws InterruptedException {

        // Configuración del Driver
        WebDriverManager.chromedriver().setup();

        // SUSTITUCIÓN DEL DRIVER: Creamos el delegado y lo envolvemos en Healenium
        WebDriver delegate = new ChromeDriver();
        SelfHealingDriver driver = SelfHealingDriver.create(delegate);

        // Maximizamos para evitar errores de clic (viewport)
        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // Abrir la página web
            driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
            System.out.println("Página abierta correctamente con Healenium: " + driver.getTitle());

            // Agregar info en Text input
            WebElement textInput = driver.findElement(By.id("my-text-id"));
            textInput.sendKeys("Ingeniería en Software UABC");
            System.out.println("Informacion en Text input agregada");

            // Agregar info en Password
            WebElement passwordInput = driver.findElement(By.name("my-password"));
            passwordInput.sendKeys("password1423");
            System.out.println("Informacion en Password agregada");

            // Agregar info en Textarea
            WebElement textArea = driver.findElement(By.name("my-textarea"));
            textArea.sendKeys("Esta es una prueba de automatizacion");
            System.out.println("Informacion en Textarea agregada");

            // Seleccionar una opción en Dropdown
            WebElement dropdownElement = driver.findElement(By.name("my-select"));
            Select dropdown = new Select(dropdownElement);
            dropdown.selectByValue("2");
            System.out.println("Opción de dropdown seleccionada.");

            // Interactuar con Checkbox
            WebElement checkbox = driver.findElement(By.id("my-check-1"));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            System.out.println("Interaccion con checkbox");

            // Interactuar con Radio button
            WebElement radioButton = driver.findElement(By.id("my-radio-2"));
            radioButton.click();
            System.out.println("Interaccion con radio button");

            // Intereactuar con Date picker
            WebElement datePicker = driver.findElement(By.name("my-date"));
            datePicker.sendKeys("03/28/2026");
            System.out.println("Interaccion con Date picker");

            Thread.sleep(3000);

            // Presionar el botón Submit
            WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));
            submitButton.click();
            System.out.println("Boton submit presionado");

            // Esperamos a que se muestre la pagina de confirmacion
            WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("display-6")));

            // Confirmacion "Form submitted"
            if (header.isDisplayed()) {
                System.out.println("¡PRUEBA EXITOSA!");
                System.out.println("Confirmación visual: " + header.getText());
            }

        } catch (Exception e) {
            System.out.println("Error durante la ejecución: " + e.getMessage());
        } finally {
            System.out.println("Cerrando navegador...");
            driver.quit();
        }
    }
}