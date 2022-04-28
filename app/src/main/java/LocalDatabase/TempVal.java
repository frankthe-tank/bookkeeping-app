package LocalDatabase;

public class TempVal {
    private String username;
    private Byte is_acc;
    private int bsn_id;
    private int employee_id;
    private int invoice_id;
    private int vendor_id;

    public TempVal(String username, Byte is_acc, int bsn_id, int employee_id, int invoice_id, int vendor_id){
        this.username = username;
        this.is_acc = is_acc;
        this.bsn_id = bsn_id;
        this.employee_id = employee_id;
        this.invoice_id = invoice_id;
        this.vendor_id = vendor_id;
    }
    public TempVal(){

    }

    @Override
    public String toString() {
        return "TempVal{" +
                "username='" + username + '\'' +
                ", is_acc=" + is_acc +
                ", bsn_id=" + bsn_id +
                ", employee_id=" + employee_id +
                ", invoice_id=" + invoice_id +
                ", vendor_id=" + vendor_id +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Byte getIs_acc() { return is_acc;}

    public void setIs_acc(Byte is_acc) { this.is_acc = is_acc;}

    public int getBsn_id() {
        return bsn_id;
    }

    public void setBsn_id(int bsn_id) {
        this.bsn_id = bsn_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) { this.invoice_id = invoice_id; }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }
}
