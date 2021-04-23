package com.huyunit.dn.datastructure.lesson20;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

import javax.crypto.Cipher;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

/**
 * Created by Jett on 2019/1/14.
 */

public class RSA {

    public static String ALGORITHM="RSA";

    //指定key的位数   N的位数
    public static int KEYSIZE=1024;//65536

    //指定公钥的存放文件
    public static String PUBLIC_KEY_FILE="public_key.dat";

    //指定私钥的存放文件
    public static String PRIVATE_KEY_FILE="private_key.dat";

    /**
     * 生成密钥对   公(e,n)  私(d,n)
     */
    public static void generateKeyPair() throws Exception{
        SecureRandom sr=new SecureRandom();
        //需要一个KeyPairGenerator对象
        KeyPairGenerator keyPairGenerator=KeyPairGenerator.getInstance(ALGORITHM);

        keyPairGenerator.initialize(KEYSIZE,sr);
        //生成密钥对
        KeyPair keyPair=keyPairGenerator.generateKeyPair();

        //得到公钥
        Key publicKey=keyPair.getPublic();
        //得到私钥
        Key privateKey=keyPair.getPrivate();

        //可以写入文件后，这两个文件分别放到服务器和客户端
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE));
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
        objectOutputStream1.writeObject(publicKey);
        objectOutputStream2.writeObject(privateKey);
        objectOutputStream2.close();
        objectOutputStream1.close();


    }
    /**
     * 加密
     */
    public static String encrypt(String source) throws Exception{
        generateKeyPair();
        //取出公钥
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        Key key=(Key)ois.readObject();
        ois.close();
        //开始用公钥
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE,key);
        byte[] b=source.getBytes();
        byte[] b1=cipher.doFinal(b);
        //用base64进行编码  二进制与字符串转换的一种方式
        BASE64Encoder encoder=new BASE64Encoder();
        return encoder.encode(b1);
    }

    /**
     * 解密
     * @throws Exception
     */
    @Test
    public void test() throws Exception{
        //客户端用公钥加密
        String content="jett";
        String password=encrypt(content);
        System.out.println("密文:"+password);
        //到了服务器后，用私钥解密
        String target=decrypt(password);
        System.out.println("明文:"+target);
    }
    public static String decrypt(String cryptText) throws Exception{
        //读文件,取到私钥
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
        Key key=(Key)ois.readObject();
        ois.close();
        //解密
        Cipher cipher=Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE,key);
        BASE64Decoder decoder=new BASE64Decoder();
        byte[] b1=decoder.decodeBuffer(cryptText);
        byte[] b2=cipher.doFinal(b1);
        return new String(b2);
    }


}













