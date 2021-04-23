package com.huyunit.dn.datastructure.lesson18;

import org.junit.Test;

import java.util.Base64;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void main() {
        String p = "Ajds@560656";
        String baseString = Base64.getEncoder().encodeToString(p.getBytes());
        System.out.println("baseString = " + baseString);
    }
}