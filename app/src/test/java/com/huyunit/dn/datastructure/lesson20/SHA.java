package com.huyunit.dn.datastructure.lesson20;

import org.apache.commons.codec.digest.Sha2Crypt;
import org.junit.Test;

/**
 * Created by Jett on 2019/1/14.
 */

public class SHA {
    @Test
    public void test(){
        String result= Sha2Crypt.sha256Crypt("jett".getBytes());
        System.out.println(result);
    }
}
