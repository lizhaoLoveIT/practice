package cn.lizhaoloveit.domain;

/**
 * DESCRIPTION:
 * Author: ammar
 * Date:   2020-07-15
 * Time:   18:24
 */
//public class CInit {
//    static {
//        i = 0; // 给变量赋值可以编译通过
//        System.out.println(i); // 编译报错，非法向前引用
//    }
//    static int i = 1;   // 变量声明
//}
class SuperClass {
    static {
        System.out.println("super init");
    }
    public static int value = 123;
    public static final String DATA = "hello world";
}

class SubClass extends SuperClass {
    static {
        System.out.println("sub init");
    }
}

public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(SuperClass.DATA);
    }
}
