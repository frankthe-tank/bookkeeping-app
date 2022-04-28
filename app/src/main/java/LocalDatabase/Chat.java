package LocalDatabase;

public class Chat {
    private int chat_id;
    private int bsn_id;
    private boolean is_acc;
    private String message;
    private String time;

    public Chat(int chat_id,int bsn_id,boolean is_acc,String message, String time){
        this.chat_id = chat_id;
        this.bsn_id = bsn_id;
        this.is_acc = is_acc;
        this.message = message;
        this.time = time;
    }

    public Chat(){

    }

    @Override
    public String toString() {
        return "Chat{" +
                "chat_id=" + chat_id +
                ", bsn_id=" + bsn_id +
                ", is_acc=" + is_acc +
                ", message='" + message + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public int getBsn_id() {
        return bsn_id;
    }

    public void setBsn_id(int bsn_id) {
        this.bsn_id = bsn_id;
    }

    public boolean isIs_acc() {
        return is_acc;
    }

    public void setIs_acc(boolean is_acc) {
        this.is_acc = is_acc;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime(){ return time; }

    public void setTime(String time) { this.time = time; }
}
