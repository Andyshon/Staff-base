public class FileNamesCreator {

    FileNamesCreator(){
        getFileName();
    }

    public String getFileName(){
        java.util.Date todayYear = new java.util.Date();
        todayYear.getYear();
        String todYear = String.valueOf(todayYear);
        String str = todYear.substring(todYear.length() - 4, todYear.length());
        int yearNew = Integer.parseInt(str);

        String todDay = String.valueOf(todayYear);
        String str2 = todDay.substring(8, 10);
        int todayDay = Integer.parseInt(str2);
        String todayDatStr="";
        if (todayDay < 10)
            todayDatStr = "0"+todayDay;
        else
            todayDatStr = Integer.toString(todayDay);

        java.util.Date todayMonth = new java.util.Date();
        todayMonth.getDate();
        String todMonth = String.valueOf(todayMonth);
        String str3 = todMonth.substring(4, 7);
        int monthNew = 0;
        monthNew = getMonth(str3, monthNew);
        String monthNewStr="";
        if (monthNew < 10)
            monthNewStr = "0"+monthNew;
        else
            monthNewStr = Integer.toString(monthNew);

        String fileNameDate = todayDatStr + "-" + monthNewStr + "-" + Integer.toString(yearNew);
        System.out.println("FileNameDate = " + fileNameDate);

        return fileNameDate;
    }

    // Получаем текущий месяц в числовой системе счисления
    private int getMonth(String str, int month){
        if (str.equals("Jan"))
            month = 1;
        else if (str.equals("Feb"))
            month = 2;
        else if (str.equals("Mar"))
            month = 3;
        else if (str.equals("Apr"))
            month = 4;
        else if (str.equals("May"))
            month = 5;
        else if (str.equals("Jun"))
            month = 6;
        else if (str.equals("Jul"))
            month = 7;
        else if (str.equals("Aug"))
            month = 8;
        else if (str.equals("Sep"))
            month = 9;
        else if (str.equals("Oct"))
            month = 10;
        else if (str.equals("Nov"))
            month = 11;
        else if (str.equals("Dec"))
            month = 12;
        return month;
    }
}