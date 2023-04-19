package com.lg.common.utils;

import com.antherd.smcrypto.sm2.Keypair;
import com.antherd.smcrypto.sm2.Point;
import com.antherd.smcrypto.sm2.SignatureOptions;
import com.antherd.smcrypto.sm2.Sm2;
import com.antherd.smcrypto.sm4.Sm4;

import java.util.*;

/**
 * @author liuga
 * @since 2023-02-24 15:25
 * Description:国密算法sm2、sm3和sm4的java版
 * 此项目还提供js版本 见：<a href="https://github.com/antherd/sm-crypto">...</a>
 */
public class SmCryptoUtil {

    public static SignatureOptions signatureOptions2 = new SignatureOptions();

    static {
        Queue<Point> pointPool = new LinkedList<>(Arrays.asList(Sm2.getPoint(), Sm2.getPoint(), Sm2.getPoint(), Sm2.getPoint()));
        // 传入事先已生成好的椭圆曲线点，可加快签名速度
        signatureOptions2.setPointPool(pointPool);
    }

    /**
     * 公钥
     */
    public static final String PUBLIC_KEY = "0468a0666f7242ceb7f5f89f1a4fc599bfed6f22266ad676c7c34fb77561e8b6c8446be55ff45100964b52bfeb38e093ebbedb25bcd1a6867fd64b408fd06cc42d";

    /**
     * 私钥
     */
    public static final String PRIVATE_KEY = "a872ddb635fc71b6327ad2f3c201c40341968ffb17d595394295469d4d08d1f7";

    /**
     * sm4 key 16 进制字符串，要求为 128 比特
     */
    public static final String SM4_KEY = "cda9e23b7654a10feb89503c14f2d678";

    /**
     * sm2 加密
     *
     * @param msg 加密数据
     * @return 加密结果
     */
    public static String sm2Encrypt(String msg) {

        return Sm2.doEncrypt(msg, PUBLIC_KEY);
    }

    /**
     * sm2 解密
     *
     * @param encryptData 加密字符串
     * @return 解密结果
     */
    public static String sm2Decrypt(String encryptData) {
        return Sm2.doDecrypt(encryptData, PRIVATE_KEY);
    }


    public static String sm2Decrypt(String encryptData,String privateKey ) {
        return Sm2.doDecrypt(encryptData, privateKey);
    }
    /**
     * 签名验签  加密
     *
     * @param msg 原始字符串
     * @return 加密字符串
     */
    public static String sm2Signature(String msg) {

        return Sm2.doSignature(msg, PRIVATE_KEY, signatureOptions2);
    }

    /**
     * 签名验签 校验 校验很慢  >5s
     * 推荐使用再次签名校验
     *
     * @param msg         原始字符串
     * @param encryptData 加密字符串
     * @return 校验结果
     */
    public static boolean sm2VerifySignature(String msg, String encryptData) {
        return Sm2.doVerifySignature(msg, encryptData, PUBLIC_KEY);
    }

    /**
     * sm4 加密 默认使用 pkcs#5 填充，输出16进制字符串
     *
     * @param msg 加密数据
     * @return 加密结果
     */
    public static String sm4Encrypt(String msg) {
        return Sm4.encrypt(msg, SM4_KEY);
    }

    /**
     * sm4 解密
     * 默认使用 pkcs#5 填充，输出 utf8 字符串
     *
     * @param encryptData 加密字符串
     * @return 解密结果
     */
    public static String sm4Decrypt(String encryptData) {
        return Sm4.decrypt(encryptData, SM4_KEY);
    }


    /**
     * 生成一对sm2 密钥对
     *
     * @return sm2密钥对 map
     */
    public static Map<String, Object> getSm2KeyPair() {
        Keypair keypair = Sm2.generateKeyPairHex();
        // 公钥
        String privateKey = keypair.getPrivateKey();
        // 私钥
        String publicKey = keypair.getPublicKey();
        Map<String, Object> res = new HashMap<>();
        res.put("privateKey", privateKey);
        res.put("publicKey", publicKey);
        return res;
    }

    public static void main(String[] args) {

    }
}
