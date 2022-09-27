public class GregorianDate {
    /*private static int month;*/
    private int year;
    private int month;
    private int day;
    private final int[] normalDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final int[] leapDays = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public GregorianDate(){
        this.year = 1970;
        this.month = 1;
        this.day = 1;

        long currentTimeMillis = java.lang.System.currentTimeMillis();
        int currentTimeDays = (int) ((currentTimeMillis / 1000 / 60 / 60 - 8) / 24);
        addDays(currentTimeDays);
    }

    public GregorianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getMonthName(){
        String[] names = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return names[month-1];
    }

    public int getMonth() {
        return month;
    }

    public int getYear(){
        return year;
    }

    public int getDayOfMonth(){
        return day;
    }

    public boolean isLeapYear(){
        return 0 == year % 4;
    }

    public void printShortDate(){
        System.out.print(month + "/" + day + "/" + year);
    }

    public void printLongDate(){
        System.out.print(getMonthName() + " " + getDayOfMonth() + ", " + getYear());
    }

    private int getNumberOfDaysInMonth(int year, int month){
        if (isLeapYear(year)){
            return leapDays[month-1]; /*import java.util.Date;*/

        }
        else{
            return normalDays[month-1];
        }
    }

    public void addDays(int days){
        for (int i=0; i < days; i++){
            day += 1;
            if (day > getNumberOfDaysInMonth(year, month)) {
                day = 1;
                month += 1;
                if (month == 13) {
                    month = 1;
                    year += 1;
                }
            }
        }
    }

    public void subtractDays(int days){
        for (int i=0; i < days; i++){
            day -= 1;
            if (day == 0) {
                month -= 1;
                if (month == -1) {
                    month = 11;
                    year -= 1;
                }
                else if(month == 0){
                    month = 12;
                    year -= 1;
                }
                day = getNumberOfDaysInMonth(year, month);
            }
        }
    }

    private boolean isLeapYear(int year){
        return 0 == year % 4;
    }

    private int getNumberOfDaysInYear(int year){
        int sum = 0;
        if (isLeapYear(year)) {
            for(int i : leapDays) {
                sum += i;
            }
        }
        else {
            for(int i : normalDays) {
                sum += i;
            }
        }
        return sum;
    }

    private String getMonthName(int month){
        String[] names = {"January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"};
        return names[month -1];
    }


}