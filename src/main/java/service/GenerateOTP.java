package service;

import java.util.Random;

public class GenerateOTP {
    public static String getOtp(){
        Random r = new Random();
        return String.format("%04d",r.nextInt(10000));
    }

}
