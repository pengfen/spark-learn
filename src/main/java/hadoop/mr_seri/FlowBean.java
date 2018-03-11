package hadoop.mr_seri;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

/**
 * 自定义对象实现MR中的序列化接口
 *
 * 如果需要将自定义的bean放在key中传输　则还需要实现comparable接口　因为mapreduce框架中的shuffle过程一定会对key进行排序
 * 此时 自定义的bean实现的接口应该是
 * public class FlowBean implements WritableComparable<FlowBean>
 *
 * 需要自己实现的方法是
 * readFields
 * write
 * compareTo
 */
public class FlowBean implements Writable{

    private long upFlow;
    private long dFlow;
    private long sumFlow;

    //反序列化时，需要反射调用空参构造函数，所以要显示定义一个
    public FlowBean(){}

    public FlowBean(long upFlow, long dFlow) {
        this.upFlow = upFlow;
        this.dFlow = dFlow;
        this.sumFlow = upFlow + dFlow;
    }

    // 排序时添加
    public void set(long upFlow, long dFlow) {
        this.upFlow = upFlow;
        this.dFlow = dFlow;
        this.sumFlow = upFlow + dFlow;
    }

    public long getUpFlow() {
        return upFlow;
    }
    public void setUpFlow(long upFlow) {
        this.upFlow = upFlow;
    }
    public long getdFlow() {
        return dFlow;
    }
    public void setdFlow(long dFlow) {
        this.dFlow = dFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(long sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     * 序列化方法
     */
    public void write(DataOutput out) throws IOException {
        out.writeLong(upFlow);
        out.writeLong(dFlow);
        // 可以考虑不序列化总流量　因为总流量是可以通过上行流量和下行流量计算出来的
        out.writeLong(sumFlow);
    }

    /**
     * 反序列化方法
     * 注意：反序列化的顺序跟序列化的顺序完全一致
     * 反序列化时 从流中读取到的各个字段的顺序应该与序列化时写出去的顺序保持一致
     */
    public void readFields(DataInput in) throws IOException {
        upFlow = in.readLong();
        dFlow = in.readLong();
        sumFlow = in.readLong();
    }

    public String toString() {
        return upFlow + "\t" + dFlow + "\t" + sumFlow;
    }

    public int compareTo(FlowBean o) {
        // 实现执照sumflow的大小倒序排序
        return sumFlow > o.getSumFlow() ? -1 : 1;
    }
}
