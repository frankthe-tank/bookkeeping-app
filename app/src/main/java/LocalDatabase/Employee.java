package LocalDatabase;

import com.google.gson.annotations.SerializedName;

public class Employee {
    private int employee_id;
    private int bsn_id;
    private String f_name;
    private String m_name;
    private String l_name;
    private String address;
    private String state;
    private String city;
    private String zip;
    private String phone;
    private String ssn;
    private int allowances;
    private String p_rotation;
    private boolean is_married;
    private boolean active;

    public Employee(int employee_id, int bsn_id, String f_name, String m_name, String l_name, String address,
                    String state, String city, String zip, String phone, String ssn,
                    int allowances, String p_rotation, boolean is_married, boolean active) {
        this.employee_id = employee_id;
        this.bsn_id = bsn_id;
        this.f_name = f_name;
        this.m_name = m_name;
        this.l_name = l_name;
        this.address = address;
        this.state = state;
        this.city = city;
        this.zip = zip;
        this.phone = phone;
        this.ssn = ssn;
        this.allowances = allowances;
        this.p_rotation = p_rotation;
        this.is_married = is_married;
        this.active = active;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employee_id=" + employee_id +
                ", bsn_id=" + bsn_id +
                ", f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", address='" + address + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", ssn='" + ssn + '\'' +
                ", allowances=" + allowances +
                ", p_rotation='" + p_rotation + '\'' +
                ", is_married=" + is_married +
                ", active=" + active +
                '}';
    }

    public String getM_name() { return m_name; }

    public void setM_name(String m_name) { this.m_name = m_name;}

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getBsn_id() {
        return bsn_id;
    }

    public void setBsn_id(int bsn_id) {
        this.bsn_id = bsn_id;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }

    public int getAllowances() {
        return allowances;
    }

    public void setAllowances(int allowances) {
        this.allowances = allowances;
    }

    public String getP_rotation() {
        return p_rotation;
    }

    public void setP_rotation(String p_rotation) {
        this.p_rotation = p_rotation;
    }

    public boolean isIs_married() {
        return is_married;
    }

    public void setIs_married(boolean is_married) {
        this.is_married = is_married;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}