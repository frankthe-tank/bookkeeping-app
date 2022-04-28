package LocalDatabase;

public class Calender {


    private String c_name;
    private String c_date;

    public Calender(String c_name, String c_date) {
        this.c_name = c_name;
        this.c_date = c_date;
    }

    public Calender() {
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }

    @Override
    public String toString() {
        return "Calender{" +
                "c_name='" + c_name + '\'' +
                ", c_date='" + c_date + '\'' +
                '}';


    }
}
