package com.leaves.smalltiger.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
@Slf4j
public class RegularExpression {
//    默认头像路径
    public static final String AVATAR_PATH= "http://q1ziatbln.bkt.clouddn.com/6bae3f7b8e29444db08d0b137c76f925.jpg";
//    验证手机号的正则表达式
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
//    验证邮箱的正则表达式
//    public static final String  REGEX_EMAIL = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
    public static final String  REGEX_EMAIL = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

    /**
     * 正则验证邮箱账号
     * @param email
     * @return
     */
    public static boolean orEmail(String email) {
        if (email == null || "".equals(email)) {
            return false;
        }
        return email.matches(REGEX_EMAIL);
    }

    /**
     * 正则验证手机号
     * @param phone
     * @return
     */
    public static boolean orPhone(String phone) {
        if (phone == null || "".equals(phone)) {
            return false;
        }
        return phone.matches(REGEX_MOBILE);

    }

/*    *//**
     * 随机生成昵称
     * @return
     *//*
    public static String randomNickname(){
//        随机生成大写的字母
        String str = "";
       *//* for (int i = 0; i < 2; i++){
            str = str + (char)(Math.random()*26+'A');
        }
        log.info(".....随机生成的字母....." + str);*//*
//        随机生成数字
        String randomNumber = String.valueOf(new Random().nextInt(99999999));
        log.info(".....随机生成的数字....." + randomNumber);
        str +=  randomNumber;
        log.info(".....随机生成的昵称....." + str);
        return str;
    }*/

    /**
     * 随机生成昵称
     * @return
     */
    public static String randomNickname(){
//        随机生成大写的字母
        String str = "";
        for (int i = 0; i < 2; i++){
            str = str + (char)(Math.random()*26+'A');
        }
        log.info(".....随机生成的字母....." + str);
//        随机生成数字
        String randomNumber = String.valueOf(new Random().nextInt(99999999));
        log.info(".....随机生成的数字....." + randomNumber);
        str +=  randomNumber;
        log.info(".....随机生成的昵称....." + str);
        return str;
    }

    public static int randomId() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            if (i == 0 && 8 > 1) {
                str.append(new Random().nextInt(9) + 1);
            }else {
                str.append(new Random().nextInt(10));
            }
        }
        log.info(".....随机生成的Id....." + str);
        return Integer.valueOf(str.toString());
    }

//    public static void main(String[] args) {
//        int num = getNum();
//        log.info(num+"====");
//    }
}
