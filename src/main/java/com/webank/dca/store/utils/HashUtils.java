package com.webank.dca.store.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author aaronchu
 * @Description
 * @date 2022/04/28
 */
public class HashUtils {


    public static String sha1(File file) throws NoSuchAlgorithmException, IOException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");

        try(InputStream fis = new FileInputStream(file)){
            int n = 0;
            byte[] buffer = new byte[8192];

            while(n != -1){
                n = fis.read(buffer);
                if (n > 0) {
                    digest.update(buffer, 0, n);
                }
            }
        } catch (IOException e) {
            throw e;
        }
        return byte2hexLower(digest.digest());
    }

    private static String byte2hexLower(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs;
    }

}