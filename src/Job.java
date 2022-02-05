import org.openqa.selenium.By;

public class Job {
    private String currentJobId;
    private String title;
    private String company;
    private String location;
    private String link ;
    private String remoteOrHybrid;
    private String numOfEmployees;

    public Job(String currentJobId, String title, String company, String location, String link, String remoteOrHybrid, String numOfEmployees) {
        this.currentJobId = currentJobId;
        this.title = title;
        this.company = company;
        this.location = location;
        this.link = link;
        this.remoteOrHybrid = remoteOrHybrid;
        this.numOfEmployees = numOfEmployees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrentJobId() {
        return currentJobId;
    }

    public void setCurrentJobId(String currentJobId) {
        this.currentJobId = currentJobId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getRemoteOrHybrid() {
        return remoteOrHybrid;
    }

    public void setRemoteOrHybrid(String remoteOrHybrid) {
        this.remoteOrHybrid = remoteOrHybrid;
    }

    public String getNumOfEmployees() {
        return numOfEmployees;
    }

    public void setNumOfEmployees(String numOfEmployees) {
        this.numOfEmployees = numOfEmployees;
    }
}
