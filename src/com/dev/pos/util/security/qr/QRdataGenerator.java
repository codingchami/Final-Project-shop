package com.dev.pos.util.security.qr;

import java.util.Date;
import java.util.Random;

public class QRdataGenerator {

    private final static String DATA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%&*";
    
    public static String generate(int length){

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(DATA.charAt(
                    new Random().nextInt(DATA.length())
            ));
        }
        return sb.toString();
    }
}
