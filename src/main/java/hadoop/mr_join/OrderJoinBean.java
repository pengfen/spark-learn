package hadoop.mr_join;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class OrderJoinBean implements Writable {

    private int order_id;
    private String dateString;
    private String p_id;
    private int amount;
    private String pname;

    public OrderJoinBean() {
    }

    public void set(int order_id, String dateString, String p_id, int amount, String pname) {
        this.order_id = order_id;
        this.dateString = dateString;
        this.p_id = p_id;
        this.amount = amount;
        this.pname = pname;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getP_id() {
        return p_id;
    }

    public void setP_id(String p_id) {
        this.p_id = p_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    /**
     * private int order_id; private String dateString; private int p_id;
     * private int amount; private String pname; private int category_id;
     * private float price;
     */
    public void write(DataOutput out) throws IOException {
        out.writeInt(order_id);
        out.writeUTF(dateString);
        out.writeUTF(p_id);
        out.writeInt(amount);
        out.writeUTF(pname);
    }

    public void readFields(DataInput in) throws IOException {
        this.order_id = in.readInt();
        this.dateString = in.readUTF();
        this.p_id = in.readUTF();
        this.amount = in.readInt();
        this.pname = in.readUTF();
    }

    @Override
    public String toString() {
        return "order_id=" + order_id + ", dateString=" + dateString + ", p_id=" + p_id + ", amount=" + amount + ", pname=" + pname;
    }

}
