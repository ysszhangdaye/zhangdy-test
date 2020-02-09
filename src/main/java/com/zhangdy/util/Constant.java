package com.zhangdy.util;

import com.google.common.collect.ImmutableList;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

public interface Constant {

    /*** 静态常量 */
    String ZERO = "0";
    String SHA1 = "SHA-1";
    String MD5 = "MD5";

    BigDecimal ZERO_DECIMAL = new BigDecimal(ZERO);
    BigDecimal ONE = new BigDecimal("1");

    long SERIAL_VERSION_UID = -3597809070303833860L;
    SecureRandom random = new SecureRandom();
    Lock LOCK = new ReentrantLock();
    int DEFAULT_PAGE_SIZE = 10;
    Pattern KVP_PATTERN = Pattern.compile("([_.a-zA-Z0-9][-_.a-zA-Z0-9]*)[=](.*)");
    Pattern INT_PATTERN = Pattern.compile("^\\d+$");
    Pattern FLOAT_PATTERN = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
    String SUCCEED = "succeed";
    String OK = "ok";
    String ERROR = "error";
    String FAILURE = "failure";
    String COMMAND = "command";
    char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
    String GBK = "GBK";

    String DEFAULT_ENCODING = "UTF-8";

    String[] padding = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          "};

    ImmutableList<Character> CHAR_NUMBER_SEQUENCE = ImmutableList.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    ImmutableList<Character> CHARS_EQUENCE = ImmutableList.of('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W');

    ImmutableList<Character> NUMBER_SEQUENCE = ImmutableList.of('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');

    int YEAR = Calendar.getInstance().get(Calendar.YEAR);


}
