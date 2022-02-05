import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public class Browser {

    private static WebDriver driver;

    public boolean login(String emailLinkedInAccount, String passwordLinkedInAccount, WebDriver myDriver) {
        driver = myDriver;
        boolean isSuccessfull = false;

        try {
            driver.get("https://www.linkedin.com/login");
            // wait until login page is loaded and fetch email field
            WebElement emailField = new WebDriverWait(driver, 60).until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[contains(@id,'username')]")));
            // fetch password field
            WebElement passwordField = driver.findElement(By.xpath("//input[contains(@id,'password')]"));
            // insert username and password
            emailField.sendKeys(emailLinkedInAccount);
            passwordField.sendKeys(passwordLinkedInAccount);
            // find login button and perform click
            WebElement loginBtn = driver.findElement(By.xpath("//button[contains(@type,'submit') and text()='Sign in']"));
            loginBtn.click();
            isSuccessfull = true;
            // wait until home page is visible
            Thread.sleep(3000);
        }

        catch (Exception ex ){
            ex.printStackTrace();
        }
        return isSuccessfull;
    }

    public boolean performSearchWithFilters(JobSearchFilter jobSearchFilter){
        boolean isJobsFound = false;
        try {
            driver.findElement(By.xpath("//a[@href='/jobs/']")).click();

            Thread.sleep(1000);

            //Everything is dynamically changing so this will grab the id.
            String job_id = driver.findElement(By.xpath("//input[@class='jobs-search-box__text-input jobs-search-box__keyboard-text-input']")).getAttribute("id");
            Thread.sleep(2000);
            //Set job title
            driver.findElement(By.id(job_id)).sendKeys(jobSearchFilter.getJobTitle());

            int ember = Integer.parseInt(job_id.replaceAll("\\D", "")); // Takes out the id number from the job_id calculated earlier
            // The id number for both input fields are the same, taking advantage of this fact.

            //move cursor
            WebElement locationInput = driver.findElements(By.id("jobs-search-box-location-id-ember" + ember)).get(0);
            Actions builder = new Actions(driver);
            builder.moveToElement(locationInput).build();

            //Set job location
            locationInput.sendKeys(jobSearchFilter.getLocation());

            Thread.sleep(2000);

            //find search button and perform click
            driver.findElement(By.className("jobs-search-box__submit-button")).click();
            Thread.sleep(2000);

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            int counterFilterButtons = 0;//access to the list of filter buttons by this index

            //Date Posted filter
            // enum DatePosted {ANY_TIME, PAST_MONTH, PAST_WEEK, PAST_24_HOURS}
            if (jobSearchFilter.getDatePosted() != null) {
                DatePosted datePostedFilter = jobSearchFilter.getDatePosted();
                driver.findElements(By.xpath("//button[@class='artdeco-pill artdeco-pill--slate artdeco-pill--choice artdeco-pill--2 search-reusables__filter-pill-button\n" +
                        "     reusable-search-filter-trigger-and-dropdown__trigger']")).get(counterFilterButtons).click();
                Thread.sleep(1000);
                WebElement datePostedRadioButton;

                switch (datePostedFilter) {
                    case PAST_MONTH:
                        datePostedRadioButton = driver.findElement(By.id("timePostedRange-r2592000"));
                        break;
                    case PAST_WEEK:
                        datePostedRadioButton = driver.findElement(By.id("timePostedRange-r604800"));
                        break;
                    default: //case PAST_24_HOURS:
                        datePostedRadioButton = driver.findElement(By.id("timePostedRange-r86400"));
                }

                executor.executeScript("arguments[0].click();", datePostedRadioButton);
                Thread.sleep(1000);
                //show results button
                driver.findElements(By.xpath("//button[@class= 'artdeco-button artdeco-button--2 artdeco-button--primary ember-view ml2']")).get(0).click();
                Thread.sleep(2000);
            } else {
                counterFilterButtons++;
            }

            //Experience Level filter
            // enum ExperienceLevel { INTERNSHIP, ENTRY_LEVEL, ASSOCIATE ,MID_SENIOR_LEVEL ,DIRECTOR, EXECUTIVE }
            if (jobSearchFilter.getExperienceLevel() != null) {
                ExperienceLevel experienceLevelFilter = jobSearchFilter.getExperienceLevel();
                driver.findElements(By.xpath("//button[@class='artdeco-pill artdeco-pill--slate artdeco-pill--choice artdeco-pill--2 search-reusables__filter-pill-button\n" +
                        "     reusable-search-filter-trigger-and-dropdown__trigger']")).get(counterFilterButtons).click();
                Thread.sleep(1000);
                WebElement experienceLevelRadioButton;

                switch (experienceLevelFilter) {
                    case INTERNSHIP:
                        experienceLevelRadioButton = driver.findElement(By.id("experience-1"));
                        break;
                    case ENTRY_LEVEL:
                        experienceLevelRadioButton = driver.findElement(By.id("experience-2"));
                        break;
                    case ASSOCIATE:
                        experienceLevelRadioButton = driver.findElement(By.id("experience-3"));
                        break;
                    case MID_SENIOR_LEVEL:
                        experienceLevelRadioButton = driver.findElement(By.id("experience-4"));
                        break;
                    case DIRECTOR:
                        experienceLevelRadioButton = driver.findElement(By.id("experience-5"));
                        break;
                    default://  case EXECUTIVE:
                        experienceLevelRadioButton = driver.findElement(By.id("experience-6"));
                }

                executor.executeScript("arguments[0].click();", experienceLevelRadioButton);
                Thread.sleep(1000);
                //show results button
                driver.findElements(By.xpath("//button[@class= 'artdeco-button artdeco-button--2 artdeco-button--primary ember-view ml2']")).get(1).click();
                Thread.sleep(2000);
            } else {
                counterFilterButtons++;
            }

            //String companyName = jobSearchFilter.getCompanyName();
            //Company Name filter
            // String Company Name------ FAIL:(
            if(jobSearchFilter.getCompanyName() != null){
                //                String companyNameFilter = jobSearchFilter.getCompanyName();
                //
                //                this.driver.findElements(By.xpath("//button[@class= 'artdeco-pill artdeco-pill--slate artdeco-pill--2 artdeco-pill--choice ember-view search-reusables__filter-pill-button']")).get(counterFilterButtons).click();
                //                Thread.sleep(1000);
                //                WebElement companyNameInputButton;
                //
                //                companyNameInputButton = this.driver.findElement(By.xpath("//fieldset/div/div/div/input"));
                //
                //                // insert username and password
                //                companyNameInputButton.sendKeys(companyNameFilter);
                //             //??   this.driver.findElements(By.name("company-filter-value")).get(0);
                //                // name- company-filter-value
                //                // class- search-reusables__collection-values-item
                //                Thread.sleep(1000);
                //                //show results button
                //                this.driver.findElements(By.xpath("//button[@class= 'artdeco-button artdeco-button--2 artdeco-button--primary ember-view ml2']")).get(4).click();
                //                Thread.sleep(2000);
            }
            else{
                counterFilterButtons++;
            }

            //Job Type filter
            // enum JobType { FULL_TIME, PART_TIME, CONTRACT, TEMPORARY, VOLUNTEER, INTERNSHIP }
            if (jobSearchFilter.getJobType() != null) {
                JobType jobTypeFilter = jobSearchFilter.getJobType();
                driver.findElements(By.xpath("//button[@class='artdeco-pill artdeco-pill--slate artdeco-pill--choice artdeco-pill--2 search-reusables__filter-pill-button\n" +
                        "     reusable-search-filter-trigger-and-dropdown__trigger']")).get(counterFilterButtons).click();

                Thread.sleep(1000);
                WebElement jobTypeRadioButton;

                switch (jobTypeFilter) {
                    case FULL_TIME:
                        jobTypeRadioButton = driver.findElement(By.id("jobType-F"));
                        break;
                    case PART_TIME:
                        jobTypeRadioButton = driver.findElement(By.id("jobType-P"));
                        break;
                    case CONTRACT:
                        jobTypeRadioButton = driver.findElement(By.id("jobType-C"));
                        break;
                    case TEMPORARY:
                        jobTypeRadioButton = driver.findElement(By.id("jobType-T"));
                        break;
                    case VOLUNTEER:
                        jobTypeRadioButton = driver.findElement(By.id("jobType-V"));
                        break;
                    default://case INTERNSHIP:
                        jobTypeRadioButton = driver.findElement(By.id("jobType-I"));
                }

                executor.executeScript("arguments[0].click();", jobTypeRadioButton);
                Thread.sleep(1000);
                //show results button
                driver.findElements(By.xpath("//button[@class= 'artdeco-button artdeco-button--2 artdeco-button--primary ember-view ml2']")).get(3).click();
                Thread.sleep(2000);
            } else {
                counterFilterButtons++;
            }

            //On Site Or Remote filter
            //enum OnSiteOrRemote { ON_SITE, HYBRID, REMOTE }
            if (jobSearchFilter.getOnSiteOrRemote() != null) {
                OnSiteOrRemote onSiteOrRemoteFilter = jobSearchFilter.getOnSiteOrRemote();
                driver.findElements(By.xpath("//button[@class='artdeco-pill artdeco-pill--slate artdeco-pill--choice artdeco-pill--2 search-reusables__filter-pill-button\n" +
                        "     reusable-search-filter-trigger-and-dropdown__trigger']")).get(counterFilterButtons).click();
                Thread.sleep(1000);
                WebElement onSiteOrRemoteRadioButton;

                switch (onSiteOrRemoteFilter) {
                    case ON_SITE:
                        onSiteOrRemoteRadioButton = driver.findElement(By.id("workplaceType-1"));
                        break;
                    case HYBRID:
                        onSiteOrRemoteRadioButton = driver.findElement(By.id("workplaceType-3"));
                        break;
                    default://case "REMOTE":
                        onSiteOrRemoteRadioButton = driver.findElement(By.id("workplaceType-2"));
                }

                executor.executeScript("arguments[0].click();", onSiteOrRemoteRadioButton);
                Thread.sleep(1000);
                //show results button
                driver.findElements(By.xpath("//button[@class= 'artdeco-button artdeco-button--2 artdeco-button--primary ember-view ml2']")).get(4).click();
                Thread.sleep(2000);
            } else {
                counterFilterButtons++;
            }

            //Easy Apply filter
            if (jobSearchFilter.getEasyApply() != null && !jobSearchFilter.getEasyApply().equals("")) {
                driver.findElement(By.xpath("//button[@class= 'artdeco-pill artdeco-pill--slate artdeco-pill--2 artdeco-pill--choice ember-view search-reusables__filter-pill-button']")).click();
                Thread.sleep(1000);
            } else {
                counterFilterButtons++;
            }

            //sort by filter
            //enum SortBy {MOST_RECENT, MOST_RELEVANT}
            if (jobSearchFilter.getSortBy() != null) {
                SortBy sortBy = jobSearchFilter.getSortBy();
                driver.findElement(By.xpath("//button[@class= 'artdeco-pill artdeco-pill--slate artdeco-pill--choice artdeco-pill--2 search-reusables__filter-pill-button\n" +
                        "     search-reusables__filter-pill-button']")).click();
                Thread.sleep(1000);
                WebElement sortByRadioButton;

                switch (sortBy) {
                    case MOST_RECENT:
                        sortByRadioButton = driver.findElement(By.id("advanced-filter-sortBy-DD"));
                        break;
                    default: //case "MOST_RELEVANT":
                        sortByRadioButton = driver.findElement(By.id("advanced-filter-sortBy-R"));
                        break;
                }

                executor.executeScript("arguments[0].click();", sortByRadioButton);
                Thread.sleep(1000);
                //show results button
                driver.findElement(By.xpath("//button[contains(@class, 'reusable-search-filters-buttons search-reusables__secondary-filters-show-results-button')]")).click();
                Thread.sleep(2000);
            } else {
                counterFilterButtons++;
            }

            //element of total number of search result - if it appears, then we found results for the search
            if(driver.findElement(By.xpath("//small[contains(@class, 'jobs-search-results-list__text')]")) != null) {
                isJobsFound = true;
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        return isJobsFound;
    }

    public LinkedList<Job> analyzeJobsResult(Pattern[] patterns){

        LinkedList<Job> jobsList = new LinkedList<>();
        Pattern titleJobPattern = patterns[0];
        Pattern unsuitableRangeExperiencePattern = patterns[1];
        Pattern unsuitableExperiencePattern = patterns[2];
        Pattern unsuitableCitiesPattern = patterns[3];
        Pattern unsuitableKeyWordsPatternTitle = patterns[4];

        int i;
        String jobTitle=" ";
        String jobCompany= " ";
        String jobLocation= " ";
        String jobNumOfEmployees= " ";
        String jobRemote= " ";

        String searchJobsUrl = driver.getCurrentUrl();
        String resultJobUrl;
        int numOfPagesResults = 0;
        int queryParamNextPage = 0;
        int numofJobsResults = 0;

        try{
            String numStringJobsResults = driver.findElement(By.xpath(
                    "//small[contains(@class, 'jobs-search-results-list__text')]")).getText().split(" ")[0];
            //removing "," from the String
            numofJobsResults = Integer.parseInt(numStringJobsResults.replaceAll(",", ""));

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            for (int j = 0; j < 330; j++) { //All jobs elements aren't in DOM yet so this allows for all to load.
                executor.executeScript("document.getElementsByClassName('jobs-search-results')[0].scrollBy(0,10)");
                Thread.sleep(10);
            }

            List<WebElement> jobsCards = driver.findElements(By.xpath("//div[contains(@class, 'job-card-container--clickable')]"));

            WebElement jobsSection = driver.findElement(By.xpath("//section[contains(@class, 'jobs-search__left-rail')]"));
            List<WebElement> jobsLinksInCurrPage = jobsSection.findElements(By.xpath(
                    "//a[contains(@class, 'disabled ember-view job-card-container__link job-card-list__title')]"));

            if(numofJobsResults > 25) { //if numofJobsResults <= 25 then there is only one page, and then numPagesSection doesn't exists.
                List<WebElement> numPagesSection = driver.findElements(By.xpath(
                        "//li[contains(@class, 'artdeco-pagination__indicator artdeco-pagination__indicator--number ember-view')]"));
                numOfPagesResults = Integer.parseInt(numPagesSection.get(numPagesSection.size() - 1).getAttribute("data-test-pagination-page-btn"));
            }
            else{
                if(numOfPagesResults==0)
                {
                    numOfPagesResults = 1;
                }
            }
            int k=0;
            while (numOfPagesResults > 0) {
                i=0;
                for (WebElement jobCard : jobsCards) {
                    k++;
                    WebElement job = jobsLinksInCurrPage.get(i);
                    resultJobUrl = job.getAttribute("href");
                    if(resultJobUrl==null){
                        resultJobUrl=driver.getCurrentUrl();
                    }
                    jobTitle = job.getText();
                    boolean matcherFalse4 = unsuitableKeyWordsPatternTitle.matcher(jobTitle).find();

                    if(!matcherFalse4){
                        System.out.println(jobTitle +" -I clicked here! ");
                        jobCard.click();
                        Thread.sleep(2000);

                        String jobDetailsText = driver.findElement(By.xpath("//div[contains(@class, 'jobs-description-content__text')]")).getText();
                        boolean matcherTrue1 = titleJobPattern.matcher(jobDetailsText).find();
                        boolean matcherTrue2 = titleJobPattern.matcher(jobTitle).find();
                        boolean matcherFalse1 = unsuitableRangeExperiencePattern.matcher(jobDetailsText).find();
                        boolean matcherFalse2 = unsuitableExperiencePattern.matcher(jobDetailsText).find();

                        if ((matcherTrue1 || matcherTrue2) && !matcherFalse1 && !matcherFalse2) {

                            WebElement jobHeaderSection = driver.findElement(By.className("jobs-unified-top-card__content--two-pane"));
                            jobTitle = jobHeaderSection.findElement(By.xpath("//h2[@class= 't-24 t-bold']")).getText();

                            List<WebElement> jobDetails = jobHeaderSection.findElement(By.xpath("//*[@class= 'jobs-unified-top-card__subtitle-primary-grouping mr2 t-black']")).findElements(By.tagName("span"));
                            jobCompany = jobDetails.get(0).getText();
                            jobLocation = jobDetails.get(1).getText();
                            boolean matcherFalse3 = unsuitableCitiesPattern.matcher(jobLocation).find();

                            //Check if location is suitable
                            if (!matcherFalse3)
                            {
                                if (jobDetails.size() > 2) {
                                    jobRemote = jobDetails.get(2).getText();
                                }

                                jobNumOfEmployees = jobHeaderSection.findElements(By.xpath("//li[@class='jobs-unified-top-card__job-insight']")).get(1).getText();

                                String currentJobId=" ";
                                String url = driver.getCurrentUrl();

                                if(url.contains("currentJobId=")){
                                    currentJobId = url.split("currentJobId=")[1].split("&")[0];
                                }
                                Job jobInfo = new Job(currentJobId, jobTitle, jobCompany, jobLocation, resultJobUrl, jobRemote, jobNumOfEmployees);
                                jobsList.add(jobInfo);
                            }
                        }
                    }
                    else{
//                        System.out.println(jobTitle +" title not suitable");
                    }
                    i++;
                }

                numOfPagesResults--;
                if (numOfPagesResults > 0) {
                    //move to next page by query param "start"
                    queryParamNextPage += 25;
                    searchJobsUrl = Scraper.appendUri(searchJobsUrl, "start=" + queryParamNextPage);
                    driver.get(searchJobsUrl);
                    Thread.sleep(1000);

                    for (int j = 0; j < 330; j++) {
                        executor.executeScript("document.getElementsByClassName('jobs-search-results')[0].scrollBy(0,10)");
                        Thread.sleep(10);
                    }

                    jobsCards = driver.findElements(By.xpath("//div[contains(@class, 'job-card-container--clickable')]"));
                    jobsSection = driver.findElement(By.xpath("//section[contains(@class, 'jobs-search__left-rail')]"));
                    jobsLinksInCurrPage = jobsSection.findElements(By.xpath(
                            ".//a[contains(@class, 'disabled ember-view job-card-container__link job-card-list__title')]"));
                    if(jobsCards.size()==0){
                        System.out.println("search only got to " + k + " out of" +numofJobsResults);
                        break;
                    }
                }
            }
        }

        catch (Exception e){
            e.printStackTrace();
        }
        return jobsList;
    }
}
