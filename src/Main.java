import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //--------------------- CHANGE emailLinkedInAccount AND passwordLinkedInAccount UP TO YOUR USER LINKEDIN ACCOUNT ---------------------
        String[] jobsSearchTitles=new String[]{"java","backend developer","junior backend developer","junior java developer","junior software engineer","junior developer"};
        String emailLinkedInAccount = "User_Email_Linkedin";
        String passwordLinkedInAccount = "Password_User_LinkedIn";
        String location = "Israel";

        //--------------------- CHANGE THE REGEXES UP TO YOUR JOB SEARCH, GOOD LUCK! :) ---------------------

        //insert keyword that must appear in the job full deatails. If not, the job not suitable and won't appear in excel file results
        Pattern titleJobPattern = Pattern.compile("\\bjava\\b", Pattern.CASE_INSENSITIVE);

        Pattern unsuitableRangeExperiencePattern = Pattern.compile("[4-9][+]|[4-9] [+]");
        Pattern unsuitableExperiencePattern = Pattern.compile("[4-9] years|four years" +
                "|five years|six years|seven years|eight years", Pattern.CASE_INSENSITIVE);

        Pattern unsuitableCitiesPattern = Pattern.compile("Haifa|Northern|Southern|Yokneam|Beersheba|Ashdod|Sederot|Nes Ziyyona", Pattern.CASE_INSENSITIVE);

        Pattern unsuitableKeyWordsPatternTitle = Pattern.compile
                        ("Quality|Automation|QA|Verification|Unity|testing|Test|senior|SW|flutter|\\bData\\b|Devops|Dev Ops|DevSecOps|NPI" +
                       "|Agency|FinOps|Frontend|Front End|fullstack|full stack|Full-Stack|Full - Stack|Full- Stack" +
                       "|Front-End|Layout|Implementation Engineer|Graphics|SRE|Network Engineer|Hardware Engineer" +
                       "|Business|Augmented Reality|Data Scientist|Research|Researcher|Investigator|Analysis|Analyst" +
                       "|Mobile|\\bApplication\\b|Web Developer|Android|Javascript|Blockchain|c#|C\\+\\+|Performance Engineer" +
                       "|Angular|Vue.js|Node js|nodejs|php|Laravel|wpf|React|Optimizations|Sales|Go|\\bSite\\b|Onsite Engineer|Technician" +
                       "|Technical|Mechanical|Electrical|Electronic|DFT|ETCS|Support|System|Production|Presale|\\bDatabase\\b|\\bEnvironmental\\b" +
                       "|Founding|Contractor|Information Security|Solutions|.NET Developer|.NET|Net Software|\\bMonitoring\\b|\\bDrone\\b|\\bBiomedical\\b" +
                       "|Product|Value stream|Solidity Developer|Project Engineer|Embedded|Robotics Engineer|\\bUI\\b|\\bDSP\\b|\\bATE\\b" +
                       "|Desk|Student|Expert|Lead|ios|Customer|FPGA|Oracle|Rotation|Tier|IP|IT Engineer|IT Security|\\bHW\\b|\\bPropulsion\\b" +
                       "|Integration|Professional|Advanced Software|Specialist|Manager|\\bsenior\\b|\\bVP\\b|DWH Developer|Word press" +
                       "|Motion|Machine Learning Engineer|Game Developer|Core|Infrastructure|getREEF|GIS|ADR|Package Engineer" +
                       "|Bioinformatics|OS|Node.js|Dev-Ops|BSP|macOS|UAV|Firmware|MLops|SOC|V&V|Vision|Kernel|Design|Deep Learning Engineer" +
                       "|Response|Machine|Water|FS|Reliability|Scala|Process|Industrial|RPG|Field|Head|Validation|STA Engineer|Safety Engineer" +
                       "|Magento|Aerodynamics|C developer|Pre-Sale|Integrity|DBA|Materials|Priority|Network|Manufacturing|AWS Developer" +
                       "|Control|Platform|CSV|Optical|Consultant|Escalation|Analog|SecOps|Component|Wordpress|Cloud|Linux Developer|SaaS Security" +
                       "|CloudOps|Solution|Facilities|Release|Infra|NOC|Frequency|Procurement|SQL|Administrator|Web|Terraform Developer" +
                       "|\\bML\\b|MBR|Hardware|Structural|ETL|\\bBI\\b|Civil|Install|Tools|Architect|ModelOps|QMS|PLC|Kotlin developer"+
                       "|Director|Chemical|Ruby|\\bRF\\b|Analytics|\\bCTO\\b|\\bIT\\b|Framework|Haifa|Caesarea|Yokneam|Security Engineer" +
                       "|Virtualization|Scientist|\\bPHY\\b|Delivery|Digital Engineer|Shopify|\\bFW\\b|\\bSAS\\b|\\bASIC\\b|\\bDrupal\\b" +
                       "|Sustaining|\\bSr\\b|Sr.|Device|ABAP|\\bSDK\\b|Co-Founder|President|Pilot|Chief|Integrator|\\bBiotech\\b|\\bRPA\\b" +
                       "|Sharepoint|Plastic|Forward Deployed|Azure|Ember.js|Human|Coach|Operating Engineer|Operation|Dynamics Developer" +
                        "|VLSI|Ethereum|Emulator|Cluster|SAP|Staff|Service|Simulation|Elixir|Configuration|Maintenance|\\bAudio\\b|\\bAccount\\b"
                        ,Pattern.CASE_INSENSITIVE);

        Pattern[] patterns=new Pattern[5];
        patterns[0]= titleJobPattern;
        patterns[1]= unsuitableRangeExperiencePattern;
        patterns[2]= unsuitableExperiencePattern;
        patterns[3]= unsuitableCitiesPattern;
        patterns[4]= unsuitableKeyWordsPatternTitle;

        String jobTitle;

        for(int i=0; i<jobsSearchTitles.length; i++) {
            jobTitle = jobsSearchTitles[i];
            JobSearchFilter searchFilters = new JobSearchFilter(jobTitle, location, DatePosted.PAST_MONTH,ExperienceLevel.ENTRY_LEVEL, JobType.FULL_TIME, OnSiteOrRemote.ON_SITE,"", SortBy.MOST_RECENT);
            Scraper.startScraper(emailLinkedInAccount, passwordLinkedInAccount, searchFilters, patterns);
        }
    }
}
