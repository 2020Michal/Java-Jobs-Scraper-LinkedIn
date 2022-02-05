import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.LinkedList;
import java.util.regex.Pattern;

public class Scraper {

    private static WebDriver driver;
    private static Browser browser;

    private static void configBrowser() {
        try {
            String osName = System.getProperty("os.name").toUpperCase();
            if (osName.contains("WINDOWS")) {
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
            } else {
                System.setProperty("webdriver.chrome.driver", "chromedriver");
            }

            // chrome window configurations
            ChromeOptions browserConfig = new ChromeOptions();
            browserConfig.addArguments("start-maximized");
            browserConfig.addArguments("--incognito");
            browserConfig.addArguments("--max_old_space_size=4096");
            browserConfig.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(browserConfig);
            browser = new Browser();
        }

        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean startScraper(String emailLinkedInAccount, String passwordLinkedInAccount,JobSearchFilter searchFilters, Pattern[] patterns) {
        configBrowser();
        boolean isSuccessfull = false;
        boolean loginStatus = browser.login(emailLinkedInAccount, passwordLinkedInAccount, driver); // login to linkedin account
        LinkedList<Job> jobsList=new LinkedList<>();

        if (loginStatus) {
            boolean isJobsFound = browser.performSearchWithFilters(searchFilters);
            if(isJobsFound) { //if the results search are greater than 0
                jobsList.addAll(browser.analyzeJobsResult(patterns));
            }
            if(jobsList.size() > 0){
                isSuccessfull = ExcelSheetManager.exportToExcel(jobsList);
            }
        }
        shutDownChrome();
        return isSuccessfull;
    }

    private static void shutDownChrome() {
        try {
            driver.quit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //change query param in url, in order move to next page of search job results in LinkedIn
    public static String appendUri(String uri, String appendQuery) {

        String changedUrl;
        if(uri.contains("start=")){
            changedUrl = uri.replaceAll("start=\\d+", appendQuery);
        }
        else{
            changedUrl = uri+ "&" + appendQuery;
        }
        return changedUrl;
    }
}
