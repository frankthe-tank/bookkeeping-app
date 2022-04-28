package LocalDatabase;

public class Payroll {
    private int payroll_id;
    private int employee_id;
    private String check_num;
    private String paid_on;;
    private String pay_end;
    private String hours_work;
    private String total_pay;
    private String net_pay;

    public Payroll(int payroll_id, int employee_id, String check_num, String paid_on,
                   String pay_end , String hours_work, String total_pay, String net_pay) {
        this.payroll_id = payroll_id;
        this.employee_id = employee_id;
        this.check_num = check_num;
        this.paid_on = paid_on;
        this.pay_end = pay_end;
        this.hours_work = hours_work;
        this.total_pay = total_pay;
        this.net_pay = net_pay;
    }

    public Payroll() {
    }

    @Override
    public String toString() {
        return "payroll{" +
                "payroll_id=" + payroll_id +
                ", employee_id=" + employee_id +
                ", check_num='" + check_num + '\'' +
                ", paid_on='" + paid_on + '\'' +
                ", pay_end='" + pay_end + '\'' +
                ", hours_work=" + hours_work +
                ", total_pay=" + total_pay +
                ", net_pay=" + net_pay +
                '}';
    }

    public int getPayroll_id() {
        return payroll_id;
    }

    public void setPayroll_id(int payroll_id) {
        this.payroll_id = payroll_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getCheck_num() {
        return check_num;
    }

    public void setCheck_num(String check_num) {
        this.check_num = check_num;
    }

    public String getPaid_on() {
        return paid_on;
    }

    public void setPaid_on(String paid_on) {
        this.paid_on = paid_on;
    }

    public String getPay_end() {
        return pay_end;
    }

    public void setPay_end(String pay_end) {
        this.pay_end = pay_end;
    }

    public String getHours_work() {
        return hours_work;
    }

    public void setHours_work(String hours_work) {
        this.hours_work = hours_work;
    }

    public String getTotal_pay() {
        return total_pay;
    }

    public void setTotal_pay(String total_pay) {
        this.total_pay = total_pay;
    }

    public String getNet_pay() {
        return net_pay;
    }

    public void setNet_pay(String net_pay) {
        this.net_pay = net_pay;
    }
}