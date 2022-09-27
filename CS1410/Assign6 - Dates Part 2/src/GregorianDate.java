public class GregorianDate extends Date {
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

    public boolean isLeapYear(){
        if(year %4 ==0){
            if(year %400 ==0){
                return true;
            }
            else return year % 100 != 0;
        }
        else{
            return false;
        }
        //return (year%4==0 && year%100!=0) && year%400==0;
    }

    public boolean isLeapYear(int year){
        if(year %4 ==0){
            if(year %400 ==0){
                return true;
            }
            else return year % 100 != 0;
        }
        else{
            return false;
        }
        //return (year%4==0 && year%100!=0) && year%400==0;
    }
}