
 enum DatePosted { PAST_MONTH, PAST_WEEK, PAST_24_HOURS}
 enum ExperienceLevel { INTERNSHIP, ENTRY_LEVEL, ASSOCIATE,MID_SENIOR_LEVEL,DIRECTOR, EXECUTIVE }
 enum JobType { FULL_TIME, PART_TIME, CONTRACT, TEMPORARY, VOLUNTEER, INTERNSHIP }
 enum OnSiteOrRemote { ON_SITE, HYBRID, REMOTE }
 enum SortBy {MOST_RECENT, MOST_RELEVANT}

public class JobSearchFilter {

    private String jobTitle;
    private String location;
    private String companyName;
    private DatePosted datePosted;
    private ExperienceLevel experienceLevel;
    private JobType jobType;
    private OnSiteOrRemote onSiteOrRemote;
    private SortBy sortBy;
    private String easyApply;

    public JobSearchFilter(String jobTitle, String location, String companyName,
                           DatePosted datePosted,  ExperienceLevel experienceLevel,  JobType jobType, OnSiteOrRemote onSiteOrRemote, String easyApply) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.companyName = companyName;
        this.datePosted = datePosted;
        this.experienceLevel = experienceLevel;
        this.jobType = jobType;
        this.onSiteOrRemote = onSiteOrRemote;
        this.easyApply = easyApply;
    }
    public JobSearchFilter(String jobTitle, String location, String companyName,
                           DatePosted datePosted,  ExperienceLevel experienceLevel,  JobType jobType, OnSiteOrRemote onSiteOrRemote) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.companyName = companyName;
        this.datePosted = datePosted;
        this.experienceLevel = experienceLevel;
        this.jobType = jobType;
        this.onSiteOrRemote = onSiteOrRemote;
        this.easyApply = easyApply;
    }

    public JobSearchFilter(String jobTitle, String location,
                           DatePosted datePosted,  ExperienceLevel experienceLevel,  JobType jobType,OnSiteOrRemote onSiteOrRemote,String easyApply, SortBy sortBy) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.datePosted = datePosted;
        this.experienceLevel = experienceLevel;
        this.jobType = jobType;
        this.onSiteOrRemote = onSiteOrRemote;
        this.easyApply = easyApply;
        this.sortBy = sortBy;
    }

    public JobSearchFilter(String jobTitle, String location, DatePosted datePosted) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.datePosted = datePosted;
    }

    public JobSearchFilter(String jobTitle, String location) {
        this.jobTitle = jobTitle;
        this.location = location;
    }

    public JobSearchFilter(String jobTitle, String location, SortBy sortBy) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.sortBy = sortBy;
    }

    public JobSearchFilter(String jobTitle, String location, ExperienceLevel experienceLevel) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.experienceLevel = experienceLevel;
    }

    public JobSearchFilter(String jobTitle, String location,DatePosted datePosted, SortBy sortBy ) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.experienceLevel = experienceLevel;
        this.datePosted = datePosted;
        this.sortBy = sortBy;
    }

    public JobSearchFilter(String jobTitle, String location, ExperienceLevel experienceLevel, SortBy sortBy) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.experienceLevel = experienceLevel;
        this.sortBy = sortBy;
    }

    public JobSearchFilter(String jobTitle, String location,DatePosted datePosted, ExperienceLevel experienceLevel, SortBy sortBy ) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.experienceLevel = experienceLevel;
        this.datePosted = datePosted;
        this.sortBy = sortBy;
    }

    public JobSearchFilter(String jobTitle, String location,DatePosted datePosted, ExperienceLevel experienceLevel,OnSiteOrRemote onSiteOrRemote, SortBy sortBy ) {
        this.jobTitle = jobTitle;
        this.location = location;
        this.experienceLevel = experienceLevel;
        this.datePosted = datePosted;
        this.onSiteOrRemote = onSiteOrRemote;
        this.sortBy = sortBy;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLocation() {
        return location;
    }

    public String getCompanyName() {
        return companyName;
    }

    public DatePosted getDatePosted() {
        return datePosted;
    }

    public ExperienceLevel getExperienceLevel() {
        return experienceLevel;
    }

    public JobType getJobType() {
        return jobType;
    }

    public OnSiteOrRemote getOnSiteOrRemote() {
        return onSiteOrRemote;
    }

    public SortBy getSortBy() {
        return sortBy;
    }

    public String getEasyApply() {
        return easyApply;
    }
}
