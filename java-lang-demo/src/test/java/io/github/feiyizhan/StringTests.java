package io.github.feiyizhan;

import org.apache.commons.codec.binary.Hex;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

/**
 * @author 徐明龙 XuMingLong 2020-03-31
 */

public class StringTests {

    @Test
    public void testFind(){
        //String str1="abc123abc123abc1234abcd12345";
        String str1 ="abc123\uD840\uDC17\uD840\uDC16abc123\uD840\uDC17\uD840\uDC16123\uD840\uDC17dddd";
        String str2 = "\uD840\uDC17\uD840\uDC16";
        String str3 = "我想静静";
        System.out.println(str1);
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<str1.length();){
            if(i+str2.length() > str1.length() ){
                sb.append(str1.substring(i,str1.length()));
                break;
            }
            String subStr1 = str1.substring(i,i+str2.length());
            boolean isFind = true;
            for(int j=0;j<str2.length();j++){
                String tmp1 = subStr1.substring(j,j+1);
                String tmp2 = str2.substring(j,j+1);
                if(!tmp1.equals(tmp2)){
                    isFind = false;
                    break;
                }
            }
            if(isFind){
                sb.append(str3);
                i = i+str2.length();
            }else{
                sb.append(str1.substring(i,i+1));
                i++;
            }
        }

        System.out.println(sb.toString());
    }

    @Test
    public void test() throws UnsupportedEncodingException {
        String str = "abc123\uD840\uDC17\uD840\uDC16abc123\uD840\uDC17\uD840\uDC16123\uD840\uDC17dddd";
        System.out.println("原文："+str);
        System.out.println("char长度："+str.length());
        System.out.println("实际字符个数："+str.codePoints().toArray().length);
        printInfo(str,"utf-8");
        printInfo(str,"GBK");
        printInfo(str,"ISO-8859-1");

    }

    @Test
    public void test2() throws UnsupportedEncodingException {
        String str = "我是静静";
        System.out.println("原文："+str);
        System.out.println("char长度："+str.length());
        System.out.println("实际字符个数："+str.codePoints().toArray().length);
        printInfo(str,"UTF-8");
        printInfo(str,"GBK");
        printInfo(str,"ISO-8859-1");
        printInfo(str,"BIG5");
        printInfo(str,"GB2312");
        printInfo(str,"ISO-8859-2");
        printInfo(str,"ISO-8859-3");
        printInfo(str,"ISO-8859-4");
        printInfo(str,"ISO-8859-5");
        printInfo(str,"ISO-8859-6");
        printInfo(str,"ISO-8859-7");
        printInfo(str,"ISO-8859-8");
        printInfo(str,"ISO-8859-9");

        String iso88591 = new String(str.getBytes("UTF-8"),"ISO-8859-1");
        System.out.println(iso88591);
        String utf8 = new String(iso88591.getBytes("ISO-8859-1"),"UTF-8");
        System.out.println(utf8);





    }


    private void printInfo(String str, String charset) throws UnsupportedEncodingException {
        System.out.println(String.format("转换为%s编码======begin======",charset));
        //char[] arr = new String(str.getBytes("UTF-8"),charset).toCharArray();
        String newStr = new String(str.getBytes(charset),charset);
        System.out.println(String.format("新编码的值:【%s】",newStr));
        System.out.println("新编码char长度："+newStr.length());
        System.out.println("实际字符个数："+newStr.codePoints().toArray().length);
        long[] arr = newStr.chars().asLongStream().toArray();
        System.out.println("char数组的长度:"+arr.length);

        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]);
            System.out.print(" , ");
        }
        System.out.println();
        System.out.println(String.format("转换为%s编码======end======",charset));

    }

    @Test
    public void test_5_hex(){
        String string = "abcd";
        byte[] hash = string.getBytes(StandardCharsets.UTF_8);
        System.out.println(DatatypeConverter.printHexBinary(hash));
        System.out.println(Hex.encodeHexString(hash));

    }
}
