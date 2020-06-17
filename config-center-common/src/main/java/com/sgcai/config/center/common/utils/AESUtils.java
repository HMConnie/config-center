package com.sgcai.config.center.common.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

public abstract class AESUtils {

    private static final String AES_ALGORITHM="AES";

    private static final String DEFAULT_ENCODING="UTF-8";

    private static final String CIPHER_TYPE="AES/CBC/PKCS5Padding";

    private static final String IV_KEY="0102030405060708";

    public static String encrypt(String sSrc, String sKey) throws Exception {
        byte[] raw=sKey.getBytes(DEFAULT_ENCODING);
        SecretKeySpec skeySpec=new SecretKeySpec(raw, AES_ALGORITHM);
        Cipher cipher=Cipher.getInstance(CIPHER_TYPE);
        IvParameterSpec iv=new IvParameterSpec(IV_KEY.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted=cipher.doFinal(sSrc.getBytes());
        return byte2hex(encrypted).toLowerCase();
    }

    public static String decrypt(String sSrc, String sKey) throws Exception {
        byte[] raw=sKey.getBytes(DEFAULT_ENCODING);
        SecretKeySpec skeySpec=new SecretKeySpec(raw, AES_ALGORITHM);
        Cipher cipher=Cipher.getInstance(CIPHER_TYPE);
        IvParameterSpec iv=new IvParameterSpec(IV_KEY.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] encrypted1=hex2byte(sSrc);
        byte[] original=cipher.doFinal(encrypted1);
        return new String(original, DEFAULT_ENCODING);
    }

    private static byte[] hex2byte(String strhex) {
        if (strhex == null) {
            return null;
        }
        int l=strhex.length();
        if (l % 2 == 1) {
            return null;
        }
        byte[] b=new byte[l / 2];
        for (int i=0; i != l / 2; i++) {
            b[i]=(byte)Integer.parseInt(strhex.substring(i * 2, i * 2 + 2), 16);
        }
        return b;
    }

    private static String byte2hex(byte[] b) {
        String hs="";
        String stmp="";
        for (int n=0; n < b.length; n++) {
            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs=hs + "0" + stmp;
            } else {
                hs=hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) throws Exception {
        
        System.out.println("*************************第一层加密**************************");
        /*
         * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
         */
         //String merchantNo = "110";
        //String cKey="1234567890123456";
        
        String merchantNo = "5231985101";
        String cKey="65543m2154yy2304";
        // 需要加密的字串
        String cSrc=
                //"{merchantOrderNo:\"2016080311301\"}";
                "{amount:1 ,merchantOrderNo:\"20160816164130111\" , describe:\"您已成功消费1蓝钻\" , message:\"您已成功消费1蓝钻\" ,openId:\"271638406\" ,sceneId:\"2001\"}";
        System.out.println(cSrc);
        // 加密
        long lStart=System.currentTimeMillis();
        String enString=encrypt(cSrc, cKey);
        //String enString="0fcae1640f11a27dfadac3121255e8fbfc0ebdbb54e555bbea02b3c2be82a3604ecd096f89158f7e12e1f992ac9f76beb1ff8608f5422469ec81fd41184cf89d111827ecf1339dcc97f911a2d4ead3d2b790547143b494d01db9454546223e258b5261cbc40c4faac6d1e0f4f1e56b5b220805533e94cee1118c4a7aaad9b3ae12a819112ce66921019234182420afd3c94f97ce357575a087aeb02097493383b8a442d59f1d5fb3142343ffc368ee6b";
        System.out.println("加密后的字串是：" + enString);

        long lUseTime=System.currentTimeMillis() - lStart;
        System.out.println("加密耗时：" + lUseTime + "毫秒");
        // 解密
        lStart=System.currentTimeMillis();
        String DeString=decrypt(enString, cKey);
        System.out.println("解密后的字串是：" + DeString);
        lUseTime=System.currentTimeMillis() - lStart;
        System.out.println("解密耗时：" + lUseTime + "毫秒");
        
        System.out.println("*************************第二层加密**************************");
        String signInfo = enString;
        String sign = DigestUtils.md5Hex(merchantNo+"."+signInfo);
        System.out.println("merchantNo:"+merchantNo);
        System.out.println("signInfo:"+signInfo);
        System.out.println("sign:"+sign);
        
        System.out.println("#########################第一层解密###########################");
        String sign1 = DigestUtils.md5Hex(merchantNo+"."+enString);
        System.out.println("验签sign:"+sign1);
        if(!sign.equals(sign1)){
            System.out.println("第一层验签失败");
            return;
        }
        System.out.println("第一层验签成功");
        System.out.println("#########################第二层解密###########################");
        String data = decrypt(signInfo, cKey);
        System.out.println("解密的数据："+data);
    }

}
