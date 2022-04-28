package LocalDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public DataBaseHelper(@Nullable Context context) {
        super(context, "bk_main.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatment = "CREATE TABLE login(username TEXT PRIMARY KEY, password TEXT, is_acc BYTE)";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE bsn_id(bsn_id INTEGER PRIMARY KEY AUTOINCREMENT, c_username TEXT, a_username TEXT,"+
                                "FOREIGN KEY(c_username)"+
                                    "REFERENCES client_login(c_username),"+
                                "FOREIGN KEY(a_username)"+
                                    "REFERENCES accountant_login(a_username))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE employee(employee_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, f_name TEXT,"+
                                "m_name TEXT, l_name TEXT, address TEXT, state TEXT, city TEXT, zip TEXT, phone TEXT, ssn TEXT, "+
                                "allowances INTEGER, p_rotation TEXT, is_married BOOL, active BOOL,"+
                                "FOREIGN KEY(bsn_id)" +
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE payroll(payroll_id INTEGER PRIMARY KEY AUTOINCREMENT, employee_id INTEGER, check_num TEXT,"+
                                "paid_on TEXT, pay_end TEXT, hours_work TEXT, total_pay TEXT, net_pay TEXT,"+
                                "FOREIGN KEY(employee_id)"+
                                    "REFERENCES employee(employee_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE vendor(vendor_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, vendor_name TEXT, vendor_type TEXT, acc_num TEXT,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE invoice(invoice_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, gL TEXT, vendor_id TEXT,is_tax_deductible BYTE,"+
                                "invoice_num TEXT, item TEXT, amount TEXT, i_date TEXT, pay_method TEXT,"+
                                "FOREIGN KEY(bsn_id)"+
                                    "REFERENCES bsn_id(bsn_id),"+
                                "FOREIGN KEY(vendor_id)"+
                                    "REFERENCES vendor(vendor_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE chat(chat_id INTEGER PRIMARY KEY AUTOINCREMENT, bsn_id INTEGER, is_acc BOOL, message TEXT, time TEXT," +
                                "FOREIGN KEY(bsn_id)" +
                                    "REFERENCES bsn_id(bsn_id))";
        db.execSQL(createTableStatment);

        createTableStatment = "CREATE TABLE temp_val(username TEXT PRIMARY KEY, is_acc BOOL, bsn_id INTEGER, employee_id INTEGER, invoice_id INTEGER, vendor_id INTEGER," +
                                "FOREIGN KEY(bsn_id)" +
                                    "REFERENCES bsn_id(bsn_id)," +
                                "FOREIGN KEY(employee_id)" +
                                    "REFERENCES employee(employee_id)," +
                                "FOREIGN KEY(invoice_id)" +
                                    "REFERENCES invoice(invoice_id)," +
                                "FOREIGN KEY(vendor_id)" +
                                    "REFERENCES vendor(vendor_id))";
        db.execSQL(createTableStatment);

        //////////////////////////////calender creating a Table/////////////////////////////////
        createTableStatment = "CREATE TABLE calender(calender_id INTEGER PRIMARY KEY AUTOINCREMENT,bsn_id INTEGER, name TEXT, date TEXT,"+
                "FOREIGN KEY(bsn_id)" +
                "REFERENCES bsn_id(bsn_id))"; //please take a look at this part
        db.execSQL(createTableStatment);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean addLogin(Login login){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", login.getUsername());
        cv.put("password", login.getPassword());
        cv.put("is_acc", login.getIs_acc());
        long insert = db.insert("login", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addPayroll(Payroll payroll){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("employee_id", payroll.getEmployee_id());
        cv.put("check_num",payroll.getCheck_num());
        cv.put("paid_on",payroll.getPaid_on());
        cv.put("pay_end",payroll.getPay_end());
        cv.put("hours_work",payroll.getHours_work());
        cv.put("total_pay",payroll.getTotal_pay());
        cv.put("net_pay",payroll.getNet_pay());
        long insert = db.insert("payroll", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addEmployee(Employee employee){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bsn_id",employee.getBsn_id());
        cv.put("f_name", employee.getF_name());
        cv.put("m_name", employee.getM_name());
        cv.put("l_name", employee.getL_name());
        cv.put("address", employee.getAddress());
        cv.put("state", employee.getState());
        cv.put("city", employee.getCity());
        cv.put("zip", employee.getZip());
        cv.put("phone", employee.getPhone());
        cv.put("ssn", employee.getSsn());
        cv.put("allowances", employee.getAllowances());
        cv.put("p_rotation", employee.getP_rotation());
        cv.put("is_married", employee.isIs_married());
        cv.put("active", employee.isActive());
        long insert = db.insert("employee", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    public boolean addInvoice(Invoice invoice){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("bsn_id",invoice.getBsn_id());
        cv.put("vendor_id",invoice.getVendor_id());
        cv.put("gL", invoice.getgL());
        cv.put("is_tax_deductible", invoice.getIs_tax_deductible());
        cv.put("invoice_num",invoice.getInvoice_num());
        cv.put("item",invoice.getItem());
        cv.put("amount",invoice.getAmount());
        cv.put("i_date", invoice.getI_date());
        cv.put("pay_method",invoice.getPayMethod());
        long insert = db.insert("invoice", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public boolean addTempVal(TempVal tempVal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username", tempVal.getUsername());
        cv.put("is_acc", tempVal.getIs_acc());
        cv.put("bsn_id", tempVal.getBsn_id());
        cv.put("employee_id", tempVal.getEmployee_id());
        cv.put("invoice_id", tempVal.getInvoice_id());
        cv.put("vendor_id", tempVal.getVendor_id());
        long insert = db.insert("temp_val", null, cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean editTempValS(String value){
        //String queryString = "UPDATE temp_val SET username = '" + value +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("username",value);
        long test = db.update("temp_val",cv,null,null);
        return true;
    }

    public Boolean editTempValB(Byte value){
        //String queryString = "UPDATE temp_val SET username = " + value;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("is_acc",value);
        long test = db.update("temp_val",cv,null,null);
        return true;
    }
    public Boolean editTempValI(String column, int value){
        //String queryString = "UPDATE temp_val SET " + column " = '" + value +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(column,value);
        long test = db.update("temp_val",cv,null,null);
        return true;
    }

    public Boolean editInvoice(int invoice_id, String gL, String vendor_id, byte is_tax_deductible,
                               String invoice_num, String item, String amount, String i_date, String pay_method){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("gL",gL);
        cv.put("vendor_id",vendor_id);
        cv.put("is_tax_deductible",is_tax_deductible);
        cv.put("invoice_num",invoice_num);
        cv.put("item", item);
        cv.put("amount", amount);
        cv.put("i_date",i_date);
        cv.put("pay_method", pay_method);
        long test = db.update("invoice",cv,"invoice_id = " + invoice_id,null);
        return true;
    }

    public Boolean editEmployee(int employee_id, String f_name, String m_name,
                                String l_name, String address, String state,
                                String city, String zip, String phone,
                                String ssn, int allowances,String p_rotation,
                                boolean is_married){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("f_name",f_name);
        cv.put("m_name",m_name);
        cv.put("l_name",l_name);
        cv.put("address",address);
        cv.put("state",state);
        cv.put("city",city);
        cv.put("zip",zip);
        cv.put("phone",phone);
        cv.put("ssn",ssn);
        cv.put("allowances",allowances);
        cv.put("p_rotation",p_rotation);
        cv.put("is_married",is_married);
        long test = db.update("employee",cv,"employee_id = " + employee_id,null);
        return true;
    }

    public TempVal getTempVal() {
        String queryString = "SELECT * FROM temp_val";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            String username = cursor.getString(0);
            byte is_acc = (byte) cursor.getInt(1);
            int bsn_id = cursor.getInt(2);
            int employee_id = cursor.getInt(3);
            int invoice_id = cursor.getInt(4);
            int vendor_id = cursor.getInt(5);

            TempVal tempVal = new TempVal(username, is_acc, bsn_id, employee_id, invoice_id, vendor_id);
            cursor.close();
            db.close();
            return tempVal;
        }else{
            cursor.close();
            db.close();
            return new TempVal("error", (byte) 0,000,000,000,000);

        }
    }

    public Boolean isTempVal(){
        String queryString = "Select * FROM temp_val";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        //cursor.getCount();
        if(cursor.getCount() == 0){
            return false;
        }else{
            return true;
        }
    }
    //////////////////////////////////////////
    //add calender
    public boolean addCalender(Calender calender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("c_name", calender.getC_name());
        cv.put("c_date", calender.getC_date());

        long insert = db.insert("calender", null,cv);
        db.close();
        if(insert == -1){
            return false;
        }else{
            return true;
        }
    }
    ////////////////////////////////////////////////

    public Boolean isAvailable(String username){
        String queryString = "SELECT count(username) FROM login WHERE username = '" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        if(cursor.getInt(0) == 1){
            cursor.close();
            db.close();
            return false;
        }else{
            cursor.close();
            db.close();
            return true;
        }
    }
    public Login getLogin(String username){
        String queryString = "SELECT * FROM login WHERE username = '" + username + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
        String usernamee = cursor.getString(0);
        String password = cursor.getString(1);
        byte is_acc = (byte) cursor.getInt(2);
        Login login = new Login(usernamee, password, is_acc);
        cursor.close();
        db.close();
        return login;

    }

    public List<Login> getAllLogin(){
        List<Login> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM login";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                String username= cursor.getString(0);
                String password = cursor.getString(1);
                byte is_acc = (byte) cursor.getInt(2);

                Login newLogin = new Login(username, password, is_acc);
                returnList.add(newLogin);
            }while(cursor.moveToNext());
        }
        else{
            //failure. do not add anything to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Employee> getAllEmployee(){
        List<Employee> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM employee";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int employee_id=cursor.getInt(0);
                int bsn_id=cursor.getInt(1);
                String f_name=cursor.getString(2);
                String m_name=cursor.getString(3);
                String l_name=cursor.getString(4);
                String address=cursor.getString(5);
                String state=cursor.getString(6);
                String city= cursor.getString(7);
                String zip= cursor.getString(8);
                String phone= cursor.getString(9);
                String ssn= cursor.getString(10);
                int allowances= cursor.getInt(11);
                String p_rotation = cursor.getString(12);
                boolean is_married=cursor.getInt(13)== 1? true:false;
                boolean active=cursor.getInt(14)==1?true:false;

                Employee newEmployee = new Employee(employee_id,bsn_id,f_name,m_name,l_name,address,state,
                        city,zip,phone,ssn,allowances,p_rotation,is_married,active);
                returnList.add(newEmployee);
            }while(cursor.moveToNext());
        }
        else{
            //failure. do not add anything to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }

    public List<Invoice> getAllInvoice(){
        List<Invoice> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM invoice";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int invoice_id=cursor.getInt(0);
                int bsn_id=cursor.getInt(1);
                String gL=cursor.getString(2);
                String vendor_id=cursor.getString(3);
                byte is_tax_deductible=(byte) cursor.getInt(4);
                String invoice_num=cursor.getString(5);
                String item=cursor.getString(6);
                String amount= cursor.getString(7);
                String i_date= cursor.getString(8);
                String pay_method = cursor.getString(9);


                Invoice newInvoice = new Invoice(invoice_id,bsn_id,gL,vendor_id,is_tax_deductible,invoice_num,item,
                        amount,i_date,pay_method);
                returnList.add(newInvoice);
            }while(cursor.moveToNext());
        }
        else{
            //failure. do not add anything to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }
    //////////////////////////Calender//////////////////////////////////


    public List<Calender> getAllCalender(){
        List<Calender> returnList= new ArrayList<>();
        String queryString = "SELECT * FROM calender";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);
        if(cursor.moveToFirst()){
            do{
                int calender_id=cursor.getInt(0);
                int bsn_id=cursor.getInt(1);
                String c_name= cursor.getString(2);
                String c_date= cursor.getString(3);



                Calender newCalender = new Calender(c_name,c_date);
                returnList.add(newCalender);
            }while(cursor.moveToNext());
        }
        else{
            //failure. do not add anything to list.
        }
        cursor.close();
        db.close();
        return returnList;
    }



    ///////////////////////////////Calender/////////////////////////////


    public Employee getEmployee(int id) {
        String queryString = "SELECT * FROM employee WHERE employee_id = " + id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {int employee_id=cursor.getInt(0);
            int bsn_id=cursor.getInt(1);
            String f_name=cursor.getString(2);
            String m_name=cursor.getString(3);
            String l_name=cursor.getString(4);
            String address=cursor.getString(5);
            String state=cursor.getString(6);
            String city= cursor.getString(7);
            String zip= cursor.getString(8);
            String phone= cursor.getString(9);
            String ssn= cursor.getString(10);
            int allowances= cursor.getInt(11);
            String p_rotation = cursor.getString(12);
            boolean is_married=cursor.getInt(13)== 1? true:false;
            boolean active=cursor.getInt(14)==1?true:false;

            Employee newEmployee = new Employee(employee_id,bsn_id,f_name,m_name,l_name,address,state,
                    city,zip,phone,ssn,allowances,p_rotation,is_married,active);
            cursor.close();
            db.close();
            return newEmployee;

        }
        else{
            int employee_id = 666;
            int bsn_id=666;
            String f_name="error";
            String m_name = "error";
            String l_name="error";
            String address="error";
            String state="error";
            String city= "error";
            String zip= "error";
            String phone= "error";
            String ssn= "error";
            int allowances= 0;
            String p_rotation = "error";
            boolean is_married=false;
            boolean active=false;

            Employee newEmployee = new Employee(employee_id,bsn_id,f_name,m_name,l_name,address,state,
                    city,zip,phone,ssn,allowances,p_rotation,is_married,active);
            cursor.close();
            db.close();
            return newEmployee;
        }

    }

    //public int getInvoiceId(String num){

    //}

    public Invoice getInvoice(int id) {
        String queryString = "SELECT * FROM invoice WHERE invoice_id = " + id;
        //String queryString = "SELECT * FROM invoice WHERE invoice_id = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {int invoice_id=cursor.getInt(0);
            int bsn_id=cursor.getInt(1);
            String gL=cursor.getString(2);
            String vendor_id=cursor.getString(3);
            byte is_tax_deductible=(byte) cursor.getInt(4);
            String invoice_num=cursor.getString(5);
            String item=cursor.getString(6);
            String amount= cursor.getString(7);
            String i_date= cursor.getString(8);
            String pay_method = cursor.getString(9);

            Invoice newInvoice = new Invoice(invoice_id,bsn_id,gL,vendor_id,is_tax_deductible,invoice_num,item,
                    amount,i_date,pay_method);
            cursor.close();
            db.close();
            return newInvoice;

        }
        else{
            int invoice_id = 666;
            int bsn_id=666;
            String gL="666";
            String vendor_id="666";
            byte is_tax_deductible = 0;
            String invoice_num="error";
            String item="error";
            String amount= "error";
            String i_date= "error";
            String pay_method = "cash";

            Invoice newInvoice = new Invoice(invoice_id,bsn_id,gL,vendor_id,is_tax_deductible,invoice_num,item,
                    amount,i_date,pay_method);
            cursor.close();
            db.close();
            return newInvoice;
        }
        ///////////////////////////calender////////////////////////////////////////
        /*public Calender getCalender(int id) {
            String queryString = "SELECT * FROM calender WHERE calender_id = " + id;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(queryString, null);
            if (cursor.moveToFirst()) {int calender_id=cursor.getInt(0);
                String c_name= cursor.getString(1);
                String c_date= cursor.getString(2);


                Calender newCalender = new Calender(c_name,c_date,);
                cursor.close();
                db.close();
                return newCalender;

            }
            else{

                String c_name= "error";
                String c_date= "error";


                Calender newCalender = new Calender(c_name,c_date);
                cursor.close();
                db.close();
                return newCalender;
            }

        */

    }
}

