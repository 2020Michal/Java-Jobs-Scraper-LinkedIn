# Java-Jobs-Scraper-LinkedIn
I had a lot of fun with this program. This program uses Selenium to go to Linkedin site. The program will scrape up all the job data (title, company, location, number of employees, on-site/remote, link) and places it into an excel file. Selenium will then grab all the data and place everything it found into an excel file using the function exportToExcel() with a parameter of all the jobs scraped earlier. If the file excel already exists, it will insert only unique jobs. And then boom, there should be a new excel file in there for you to click. Once that has opened, you can see that it includes the titles, company, location, and links all in one place. Note, in order for the Linkedin function to work, you must pass in the Username and Password of your linkedin profile. This is due to Linkedin's login authentication. Another note, for this specific program, I initiated regexes of job search. Those parameters can be changed to a person's liking. 
