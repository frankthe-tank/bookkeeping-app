package LocalDatabase;

public class Invoice {
    private int invoice_id;
    private int bsn_id;
    private String gL;
    private String vendor_id;
    private byte is_tax_deductible;
    private String invoice_num;
    private String item;
    private String amount;
    private String i_date;
    private String payMethod;

    public Invoice(int invoice_id,int bsn_id, String gL, String vendor_id,byte is_tax_deductible,
                   String invoice_num,String item, String amount,String i_date,String payMethod){
        this.invoice_id = invoice_id;
        this.bsn_id = bsn_id;
        this.gL = gL;
        this.vendor_id = vendor_id;
        this.is_tax_deductible = is_tax_deductible;
        this.invoice_num = invoice_num;
        this.item = item;
        this.amount = amount;
        this.i_date = i_date;
        this.payMethod = payMethod;
    }

    public Invoice(){

    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id=" + invoice_id +
                ", bsn_id=" + bsn_id +
                ", vendor_id=" + vendor_id +
                ", is_tax_deductible=" + is_tax_deductible +
                ", invoice_num='" + invoice_num + '\'' +
                ", item='" + item + '\'' +
                ", amount='" + amount + '\'' +
                ", i_date='" + i_date + '\'' +
                ", payMethod='" + payMethod + '\'' +
                '}';
    }

    public String getPayMethod() { return payMethod; }

    public void setPayMethod(String payMethod) { this.payMethod = payMethod; }

    public int getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(int invoice_id) {
        this.invoice_id = invoice_id;
    }

    public int getBsn_id() {
        return bsn_id;
    }

    public void setBsn_id(int bsn_id) {
        this.bsn_id = bsn_id;
    }

    public String getgL() {
        return gL;
    }

    public void setgL(String gL) {
        this.gL = gL;
    }

    public String getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(String vendor_id) {
        this.vendor_id = vendor_id;
    }

    public byte getIs_tax_deductible() {
        return is_tax_deductible;
    }

    public void setIs_tax_deductible(byte is_tax_deductible) {
        this.is_tax_deductible = is_tax_deductible;
    }

    public String getInvoice_num() {
        return invoice_num;
    }

    public void setInvoice_num(String invoice_num) {
        this.invoice_num = invoice_num;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }


    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getI_date() {
        return i_date;
    }

    public void setI_date(String i_date) {
        this.i_date = i_date;
    }
}
