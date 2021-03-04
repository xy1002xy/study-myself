package com.study.myself.xycommon.utils;

/**
 * @program: xy-parent
 * @description: base64工具类
 * @author: wxy
 * @create: 2021-03-04 17:59
 **/
public class Base64SizeUtils {

    /**
     * 精确计算base64字符串文件大小（单位：B）
     *
     * @param base64String 字节
     * @return 计算后得到的文件流大小，单位为字节
     */
    public static double base64FileSize(String base64String) {
        //检测是否含有base64,文件头)
        if (base64String.lastIndexOf(",") > 0) {
            base64String = base64String.substring(base64String.lastIndexOf(",") + 1);
        }
        //获取base64字符串长度(不含data:audio/wav;base64,文件头)
        int size0 = base64String.length();
        //获取字符串的尾巴的最后10个字符，用于判断尾巴是否有等号，正常生成的base64文件'等号'不会超过4个
        String tail = base64String.substring(size0 - 10);
        //找到等号，把等号也去掉,(等号其实是空的意思,不能算在文件大小里面)
        int equalIndex = tail.indexOf("=");
        if (equalIndex > 0) {
            size0 = size0 - (10 - equalIndex);
        }
        //计算后得到的文件流大小，单位为字节
        return size0 - ((double)size0 / 8) * 2;
    }
}
