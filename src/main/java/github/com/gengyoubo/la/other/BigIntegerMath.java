package github.com.gengyoubo.la.other;

import org.jetbrains.annotations.NotNull;

import java.math.BigInteger;

public class BigIntegerMath {
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a和b的和。
     */
    public static BigInteger plus(@NotNull BigInteger a,@NotNull BigInteger b) {
        return a.add(b);
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a和b的差
     */
    public static BigInteger minus(@NotNull BigInteger a,@NotNull BigInteger b) {
        return a.subtract(b);
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a*b
     */
    public static BigInteger multiply(@NotNull BigInteger a,@NotNull BigInteger b) {
        return a.multiply(b);
    }
    /**
     * @param a 非空的数
     * @param b 非空的数，但是不能为0
     * @return a/b
     */
    public static BigInteger divide(@NotNull BigInteger a,@NotNull BigInteger b) {
        if(equals(b,BigInteger.ZERO)) {
            return null;
        }else {
            return a.divide(b);
        }
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a/b的余数
     */
    public static BigInteger modulus(@NotNull BigInteger a,@NotNull BigInteger b) {
        if(equals(b,BigInteger.ZERO)) {
            return null;
        }else {
            return a.mod(b);
        }
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return 如果相等，返回true
     */
    public static boolean equals(@NotNull BigInteger a,@NotNull BigInteger b) {
        return a.compareTo(b) == 0;
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a>b如果是真返回true
     */
    public static boolean MoreThan(@NotNull BigInteger a,@NotNull BigInteger b) {
        return a.compareTo(b) > 0;
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a<b如果是真返回true
     */
    public static boolean LessThan(@NotNull BigInteger a,@NotNull BigInteger b){
        return a.compareTo(b) < 0;
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a >= b 如果是真，返回true
     */
    public static boolean moreOrEquals(@NotNull BigInteger a, @NotNull BigInteger b) {
        return a.compareTo(b) >= 0;
    }
    /**
     * @param a 非空的数
     * @param b 非空的数
     * @return a <= b 如果是真，返回true
     */
    public static boolean lessOrEquals(@NotNull BigInteger a, @NotNull BigInteger b) {
        return a.compareTo(b) <= 0;
    }
}
