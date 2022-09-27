public class JulianDate extends Date {
    public JulianDate(){
        this.year = 1;
        this.month = 1;
        this.day = 1;

        addDays(719164);
        long currentTimeMillis = java.lang.System.currentTimeMillis();
        int currentTimeDays = (int) ((currentTimeMillis / 1000 / 60 / 60 - 8) / 24);
        addDays(currentTimeDays);
    }

    public JulianDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public boolean isLeapYear(){
        return 0 == year % 4;
    }

    public boolean isLeapYear(int year){
        return 0 == year % 4;
    }
}