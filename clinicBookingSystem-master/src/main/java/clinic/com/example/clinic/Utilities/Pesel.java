package clinic.com.example.clinic.Utilities;

import java.time.LocalDate;
import java.time.Period;

public class Pesel {
    private String pesel;
    private Integer pYear;
    private Integer pMonth;
    private Integer pDay;

    public Pesel(String pesel) {

        this.pesel = pesel;
        this.pYear = Integer.parseInt(pesel.substring(0, 2));
        this.pMonth = Integer.parseInt(pesel.substring(2, 4));
        this.pDay = Integer.parseInt(pesel.substring(4, 6));

    }

    public char getSex() {
        int sexNo = Integer.parseInt(pesel.substring(9, 10));
        if (sexNo % 2 == 0) {
            return 'F';
        } else return 'M';
    }

    public Integer getAge() {

        if (checkDate() == true) {
            LocalDate datePesel = getDateOfBirth();
            LocalDate currDate = LocalDate.now();
            return Period.between(datePesel, currDate).getYears() > 0 ? Period.between(datePesel, currDate).getYears() : 0;
        } else return null;
    }


    public LocalDate getDateOfBirth() {

        if (checkDate() == true) {
            return LocalDate.of(getYear(), getMonth(), pDay);
        } else return null;
    }

    private int getYear() {

        if (pMonth >= 01 && pMonth <= 12) {
            return pYear + 1900;
        } else if (pMonth >= 21 && pMonth <= 32) {
            return pYear + 2000;
        } else if (pMonth >= 41 && pMonth <= 52) {
            return pYear + 2100;
        } else if (pMonth >= 61 && pMonth <= 72) {
            return pYear + 2200;
        } else {
            return pYear + 1800;
        }
    }


    private int getMonth() {
        if (pMonth >= 01 && pMonth <= 12) {
            return pMonth;
        } else if (pMonth >= 21 && pMonth <= 32) {
            return pMonth - 20;
        } else if (pMonth >= 41 && pMonth <= 52) {
            return pMonth - 40;
        } else if (pMonth >= 61 && pMonth <= 72) {
            return pMonth - 60;
        } else {
            return pMonth - 80;
        }
    }

    private boolean checkChecksum() {
        int a = Integer.parseInt(pesel.substring(0, 1));
        int b = Integer.parseInt(pesel.substring(1, 2));
        int c = Integer.parseInt(pesel.substring(2, 3));
        int d = Integer.parseInt(pesel.substring(3, 4));
        int e = Integer.parseInt(pesel.substring(4, 5));
        int f = Integer.parseInt(pesel.substring(5, 6));
        int g = Integer.parseInt(pesel.substring(6, 7));
        int h = Integer.parseInt(pesel.substring(7, 8));
        int i = Integer.parseInt(pesel.substring(8, 9));
        int j = Integer.parseInt(pesel.substring(9, 10));
        int k = Integer.parseInt(pesel.substring(10, 11));

        return (1 * a + 3 * b + 7 * c + 9 * d + 1 * e + 3 * f + 7 * g +
                9 * h + 1 * i + 3 * j + 1 * k) % 10 == 0 ? true : false;
    }


    private boolean checkDate() {
        int month = getMonth();
        int year = getYear();

        if (((month == 2) && (isLeapYear(year) == false && pDay <= 28)) || ((month == 2) && (isLeapYear(year) == true && pDay == 29))) {
            return true;
        } else if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && pDay <= 31) {
            return true;
        } else if ((month == 4 || month == 6 || month == 9 || month == 11) && pDay <= 30) {
            return true;
        } else return false;
    }


    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0) ? true : false;

    }

    public boolean validate() {
        return (checkDate() && checkChecksum()) == true ? true : false;

    }
}
