import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.util.*;


public class ExcelSheetManager {

    public static boolean exportToExcel(LinkedList<Job> foundedJobsList) {
        boolean isExportToExcelSucceed = false;
        boolean isExcelExists = false;
        // below code will create workbook > spreadsheet > row;
        String filePath = System.getProperty("user.dir");

        String filename = "\\jobs.xlsx";
        try {
            File fileToRead = new File(filePath + filename);
            XSSFWorkbook workbook;
            XSSFSheet sheet;
            if(fileToRead.exists()) {
                isExcelExists=true;
                workbook = new XSSFWorkbook(new FileInputStream(fileToRead));
                sheet = workbook.getSheet("My Jobs");
            }
            else{
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("My Jobs");
            }

            int rowCounter = 0;
            Map<String, Object[]> data = new TreeMap<>();
            rowCounter++;
            Object[] rowTitles = new Object[]{"Name", "Title", "Location", "Num Of Employees", "Status", "Remote","Job ID", "Job URL"};
            // titles for excel sheet
            data.put(Integer.toString(rowCounter), rowTitles);

            if(isExcelExists) {
                int j;
                int i = 0;
                String jobId = " ";

                //read data of existing sheet excel
                for (Row row : sheet) {
                    j = 0;
                    if (i == 0) { //skip the titles first line
                        i++;
                        continue;
                    }
                    Object[] myRow = new Object[rowTitles.length];
                    for (Cell cell : row) {
                        int jobIdIndex = Arrays.asList(rowTitles).indexOf("Job ID");
                        if (j == jobIdIndex) {
                            DecimalFormat df = new DecimalFormat("#");
                            df.setMaximumFractionDigits(10);
                            jobId = df.format(Double.valueOf(cell.toString()));
                            myRow[j] = jobId;
                            j++;
                        } else {
                            myRow[j] = cell.toString();
                            j++;
                        }
                    }
                    rowCounter++;
                    data.put(jobId, myRow);
                }
            }

            // preparing object for excel file- insert to data only if doesn't exist
            //if isExcelExists is false= the excel is new, than write the found jobs anyway
            for (Job job : foundedJobsList) {
                if (!isExcelExists || !data.keySet().contains(job.getCurrentJobId())) {
                    Object[] currentJob = new Object[]{ job.getCompany(), job.getTitle(), job.getLocation(), job.getNumOfEmployees(),"New", job.getRemoteOrHybrid(), job.getCurrentJobId(), job.getLink()};
                    data.put(job.getCurrentJobId(),currentJob);
                    rowCounter++;
                    System.out.println(job.getTitle() + " " + job.getCompany() + " new");
                }
                else{
                    System.out.println(job.getTitle() + " " + job.getCompany() + " already exists");
                }
            }

            // Writing data to excel file
            XSSFRow row;
            Set<String> keyid = data.keySet();
            int rowid = 0;

            for (String key : keyid) {
                row = sheet.createRow(rowid++);
                Object[] objectArr = data.get(key);
                int cellid = 0;
                //set font
                XSSFFont font= workbook.createFont();
                font.setFontHeightInPoints((short)11);
                font.setFontName("Calibri");
                font.setBold(false);
                font.setColor(IndexedColors.BLACK.getIndex());
                //set color to all cells in the row
                short bg;

                int statusIndex=Arrays.asList(rowTitles).indexOf("Status");

                String status=objectArr[statusIndex].toString();
                switch (status){
                    case "Sent":
                        bg = IndexedColors.LIGHT_GREEN.getIndex();
                        break;
                    case "No":
                        bg = IndexedColors.CORAL.getIndex();
                        break;
                    case "Got Call":
                        bg = IndexedColors.LEMON_CHIFFON.getIndex();
                        break;
                    case "Not Relevant":
                        bg = IndexedColors.GREY_50_PERCENT.getIndex();
                        font.setColor(IndexedColors.WHITE.getIndex());
                        font.setBold(true);
                        break;
                    default:// case "New"
                        bg = IndexedColors.WHITE.getIndex();
                        break;
                }

                CellStyle style;
                style=workbook.createCellStyle();
                style.setFillBackgroundColor(bg);
                style.setFillForegroundColor(bg);

                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                style.setFont(font);
                int columnIndex=0;
                int jobUrlIndex=Arrays.asList(rowTitles).indexOf("Job URL");

                for (Object obj : objectArr) {
                    Cell cell = row.createCell(cellid++);
                    if(obj!=null){
                        if(columnIndex!=jobUrlIndex || key.equals("1")) { //if it's not job url column, or it's first row-titles
                            cell.setCellValue((String) obj);
                            cell.setCellStyle(style);
                        }
                        else { //Cell Style and font for job url
                            CellStyle hlink_style = workbook.createCellStyle();
                            Font hlink_font = workbook.createFont();
                            hlink_font.setUnderline(Font.U_SINGLE);
                            hlink_font.setColor(HSSFColor.HSSFColorPredefined.BLUE.getIndex());
                            hlink_style.setFont(hlink_font);

                            Hyperlink link = workbook.getCreationHelper().createHyperlink(HyperlinkType.URL);
                            link.setAddress((String) obj);
                            cell.setCellValue((String) obj);
                            cell.setHyperlink(link);
                            cell.setCellStyle(hlink_style);
                        }
                    }
                    columnIndex++;
                }
            }

            File file = new File(filePath + filename);
            FileOutputStream out = new FileOutputStream(file);
            workbook.write(out);
            out.close();
            isExportToExcelSucceed = true;
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return isExportToExcelSucceed;
    }

}

