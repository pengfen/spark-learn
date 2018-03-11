package hadoop.mr_seri;

import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.hadoop.io.Text;

import java.io.DataOutputStream;
import java.io.ObjectOutputStream;

/**
 * java的序列化是一个重量级序列化框架(Serializable) 一个对象被序列化后 会附带很多额外的信息(各种校验信息 header 继承体系 ...) 不便于在网络中高效传输
 *
 * hadoop自己开发了一套序列化机制(Writable) 精简　高效
 */
public class TestSeri {
    public static void main(String[] args) throws Exception {
        //定义两个ByteArrayOutputStream，用来接收不同序列化机制的序列化结果
        ByteArrayOutputStream ba = new ByteArrayOutputStream();
        ByteArrayOutputStream ba2 = new ByteArrayOutputStream();

        //定义两个DataOutputStream，用于将普通对象进行jdk标准序列化
        DataOutputStream dout = new DataOutputStream(ba);
        DataOutputStream dout2 = new DataOutputStream(ba2);
        ObjectOutputStream obout = new ObjectOutputStream(dout2);
        //定义两个bean，作为序列化的源对象
        //ItemBeanSer itemBeanSer = new ItemBeanSer(1000L, 89.9f);
        //ItemBean itemBean = new ItemBean(1000L, 89.9f);

        //用于比较String类型和Text类型的序列化差别
        Text atext = new Text("a");
        // atext.write(dout);
        //itemBean.write(dout);

        byte[] byteArray = ba.toByteArray();

        //比较序列化结果
        System.out.println(byteArray.length);
        for (byte b : byteArray) {

            System.out.print(b);
            System.out.print(":");
        }

        System.out.println("-----------------------");

        String astr = "a";
        // dout2.writeUTF(astr);
        //obout.writeObject(itemBeanSer);

        byte[] byteArray2 = ba2.toByteArray();
        System.out.println(byteArray2.length);
        for (byte b : byteArray2) {
            System.out.print(b);
            System.out.print(":");
        }
    }
}