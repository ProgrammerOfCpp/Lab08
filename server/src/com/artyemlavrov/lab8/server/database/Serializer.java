package com.artyemlavrov.lab8.server.database;

import java.io.*;

public class Serializer {
    public static byte[] serialize(Serializable o){
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        try {

            ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
            objectStream.writeObject(o);
            objectStream.flush();
            return byteStream.toByteArray();


        } catch (IOException ex) {
            ex.printStackTrace();
            return new byte[]{};
        }
    }

    public static Object deserialize(byte[] bytes) {
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream inputStream = new ObjectInputStream(byteArrayInputStream);
            return inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return new Object();
        }
    }
}
