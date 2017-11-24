package rpc.server;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectSerializerUtils {

    /**
     * 将Object转码为byte[]
     * @param obj   对象
     * @return  byte[]
     */
    public static byte[] serializer(Object obj){
        if (obj == null) return null;
        ObjectOutputStream oos = null;

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.flush();
            return baos.toByteArray();

        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (oos != null) {
                try {
                    //关闭流
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 将byte[] 转码为 Object
     * @param bytes byte[]
     * @return  Object对象
     */
    public static Object deSerializer(byte[] bytes){
        if (bytes == null || bytes.length == 0) return null;
        try {
            ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);

            return ois.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}