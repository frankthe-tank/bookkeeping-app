package LocalDatabase;

public class Login {
    private String username;
    private String password;
    private Byte is_acc;

    public Login(String username, String password,Byte is_acc) {
        this.username = username;
        this.password = password;
        this.is_acc = is_acc;
    }

    public Login() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte getIs_acc(){return is_acc;}

    public void setIs_acc(Byte is_acc){this.is_acc = is_acc;}

    @Override
    public String toString() {
        return "Login{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", is_acc=" + is_acc +
                '}';
    }
}
