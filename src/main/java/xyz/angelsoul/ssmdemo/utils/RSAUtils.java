package xyz.angelsoul.ssmdemo.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class RSAUtils {
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDg6pZm2SmddLm1BKyeXhTDkaVg" +
            "nW4fxP6YHQckIu2ghVY6D1NURco7fhYorNG4PCpU0UUK/uRwEoyvIIyNxNf4TYQl" +
            "thx6jN8jayF3kZn3skk2fAK7XSseGlMhC2pPdc5tgsnvvJYkOdIl4MIt9GOv7TjA" +
            "wDrDWG0ohmcBeSGSlQIDAQAB";

    private static String privateKey = "MIICXQIBAAKBgQDg6pZm2SmddLm1BKyeXhTDkaVgnW4fxP6YHQckIu2ghVY6D1NU" +
            "Rco7fhYorNG4PCpU0UUK/uRwEoyvIIyNxNf4TYQlthx6jN8jayF3kZn3skk2fAK7" +
            "XSseGlMhC2pPdc5tgsnvvJYkOdIl4MIt9GOv7TjAwDrDWG0ohmcBeSGSlQIDAQAB" +
            "AoGBAIFPfh8tdZaYWHixckku0ANMhmdQu9K6IKH19ngZOMOURWnmC8OkyEuCB11l" +
            "VsH0nWXcVzSmLVId6BJ7xTNw27VLYqzEMrKS7qlV1J06Qx+PNdEByGgdsUe2zjdA" +
            "jWh8WCPrimCH9drmVj774xw6e9edFXX6TD/qMAkgg73fVwA1AkEA9gpi/GdtWL/9" +
            "UQCA8LUWwqpjGK3s7EXGt2rgSj1ZfVvxm9xw5rLPLk2nxp61UDnQHNgAxclsqA6F" +
            "R4aoQcNVRwJBAOoFTLQgQmXvdw8PsSpB7UrfCV04bzLgGaj/PocUlvKwL/xF737G" +
            "yKXJVh37w9Q7eH6aHPx5AEbh+ZODVu7QN0MCQQC9lK9FEWS+F+FWwu6XitGo2aVV" +
            "5N5ECFfgCKgeTDtiTrBSin8sI+dkJE38y1mmIYXsU7v0qwngTKABxw5q7doNAkBy" +
            "XbyqmslYP7sTpTuTxzxMRsuwvw+48UQZDwRjx3AtkmUhCJyOfF86hjaL2KpPYfD1" +
            "DiP0Tr4P5geuS5WEiEU5AkA0KLz0X46TzwGstecX2oYvkO0xRh7huEf99vH71enm" +
            "vODLahyEpblMOKM77OnS6kXLX0z6jKrvUL5Ht6raK8SL";

//    private static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    private static final String RSA_ALGORITHM_NOPADDING = "RSA";

    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;
//    private static String data = "test123"; //$NON-NLS-1$

    public RSAUtils() {
    }

    //    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException, InvalidAlgorithmParameterException {
////        String test = testEncrypt(publicKey,data);
////        System.out.println("encrypt: " + test);
//        String test = "O7PX39Doy+AxSnAqyIrKEDy7YrGwFZb3oTB2saPpSjYOB3bY1hsA7kaeMgRlSYKb8MmkTiB7NDqGge+9E7klIXDqVV+K2LBMFjlsSS7603neiIdcS9KhtgPykLmqKfhZ0yI1wdJv3cb1W5LD/GNsY1EYmE57zIAL1LZo+/7res4=";
////        String test = "NNy1UX0yxDHgXzU8/UaGbA3Wer5ZJjglcT8tQ2gj3NND/dr6dw6HTsa2ybyT+2Z1K43QskH82oWhPVY078IbHHe37niUsIzj136a65oMjAsEyjutDbe1KZ/YqARIZhHFiHa4FotUJviyVYRIhlgzXNPwN/L8+4NoRx0iylC24rU=";
////        String test = "wvLddmMJNvBIJXDRV3NPHTgys6soYXzHFJyzJC/LRA/TQ7xBwIaNOUlgHHeV7TOXOgmq3a+ec0pwqE/oQIfgvdyRJh4ilRck1r1C8UggsUk0ZweNPWW+Keixh2iXZmdcYqH4YFiXLwzp+KOJwtGZNyVcM0ullyvSjPJbYKB+r4A=";
//        String testDecrypt = testDecrypt(privateKey, test);
////        String testDecrypt = decryptRSA(privateKey, test);
////        String testDecrypt = decryptRSADefault(privateKey, test);
//
//        System.out.println(testDecrypt);
//    }


    /**
     * 加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws InvalidKeyException
     * @throws IOException
     */
    public static String RSAEncrypt(String data) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, IOException{
        byte[] decode = Base64.getDecoder().decode(publicKey);
//        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
        KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM_NOPADDING);
        PublicKey generatePublic = kf.generatePublic(x509EncodedKeySpec);
        Cipher ci = Cipher.getInstance(RSA_ALGORITHM_NOPADDING);
        ci.init(Cipher.ENCRYPT_MODE, generatePublic);

        byte[] bytes = data.getBytes();
        int inputLen = bytes.length;
        int offLen = 0;//偏移量
        int i = 0;
        ByteArrayOutputStream bops = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte [] cache;
            if(inputLen - offLen > 117){
                cache = ci.doFinal(bytes, offLen, MAX_ENCRYPT_BLOCK);
            }else{
                cache = ci.doFinal(bytes, offLen,inputLen - offLen);
            }
            bops.write(cache);
            i++;
            offLen = 117 * i;
        }
        bops.close();
        byte[] encryptedData = bops.toByteArray();
        String encodeToString = Base64.getEncoder().encodeToString(encryptedData);
        return encodeToString;
    }




    /**
     * 解密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws NoSuchPaddingException
     * @throws InvalidKeySpecException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     * @throws IOException
     */
    public static String RSADecrypt(String data) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, InvalidKeySpecException, IllegalBlockSizeException, BadPaddingException, IOException{
        byte[] decode = Base64.getDecoder().decode(privateKey);
//        byte[] decode = key.getBytes();

        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decode);
//        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decode);
        KeyFactory kf = KeyFactory.getInstance(RSA_ALGORITHM_NOPADDING);
        PrivateKey privateKey = kf.generatePrivate(pkcs8EncodedKeySpec);
        Cipher ci = Cipher.getInstance(RSA_ALGORITHM_NOPADDING);
        ci.init(Cipher.DECRYPT_MODE,privateKey);

        byte[] bytes = Base64.getDecoder().decode(data);
        int inputLen = bytes.length;
        int offLen = 0;
        int i = 0;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while(inputLen - offLen > 0){
            byte[] cache;
            if(inputLen - offLen > 128){
                cache = ci.doFinal(bytes,offLen,MAX_DECRYPT_BLOCK);
            }else{
                cache = ci.doFinal(bytes,offLen,inputLen - offLen);
            }
            byteArrayOutputStream.write(cache);
            i++;
            offLen = 128 * i;

        }
        byteArrayOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return new String(byteArray);
    }
}
