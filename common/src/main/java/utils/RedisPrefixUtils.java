package utils;

/**
 * @author ：53527
 * @title ：RedisPrefixUtils
 * @date ：Created in 2023/5/11 10:10
 * @description：<TODO description class purpose>
 */
public class RedisPrefixUtils {

    private static final String VERIFICATIONCODEREF="passenger-verification-code-";
    private static final  String TOKENREF="token-";
    /**
     * 根据手机号生成redis的key
     * @param passengerPhone 手机号
     * */
    public static String generatorKeyPhone(String passengerPhone){
        return new StringBuilder(VERIFICATIONCODEREF).append(passengerPhone).toString();
    }
   /***
    *
    * 根据手机号和身份标识生成token key
    * @param String phone 手机号
    * @param String identity id身份
    * @param String tokenType token类型
    * @return {@link String}
    * @throws
    * @author 53527
    * @date 2023/5/11 13:38
    */
    public static String generatorTokenKey(String phone,String identity,String tokenType){
        return new StringBuilder(TOKENREF).append(phone).append("-").append(identity).append("-").append(tokenType).toString();
    }
}
