package io.github.feiyizhan;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.TrueTypeCollection;
import com.itextpdf.io.font.constants.FontStyles;
import com.itextpdf.layout.font.FontProvider;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 徐明龙 XuMingLong 2020-08-04
 */
public class Html2PdfTests {

    @Test
    public void test() throws IOException {
        // IO
        File htmlSource = new File("D:\\email.html");
        File pdfDest = new File("D:\\output.pdf");
        // pdfHTML specific code
//        FontProgram fontProgram =  FontProgramFactory.createRegisteredFont("D:\\font\\msyh.ttc");
//        System.out.println(fontProgram.getFontNames());
//        System.out.println(FontProgramFactory.getRegisteredFonts());
//        System.out.println(FontProgramFactory.getRegisteredFontFamilies());
//        System.out.println(FontProgramFactory.registerFontDirectory("D:\\\\font"));
//        System.out.println(FontProgramFactory.getRegisteredFonts());
//        System.out.println(FontProgramFactory.getRegisteredFontFamilies());
        ConverterProperties converterProperties = new ConverterProperties();

//        FontSelector fontSelector = new FontSelector();
        FontProvider fontProvider = addFont_4();
//        fontProvider.getFontSet().addFont("D:\\\\font\\\\msyh.ttc");
//        FontProgramFactory.createFont("D:\\font\\msyh.ttc",1,true);
//        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyh.ttc",1,true));
//        fontProvider.addFont(FontProgramFactory.createFont("microsoft yahei bold",true));
//        fontProvider.addFont(FontProgramFactory.createRegisteredFont("microsoft yahei", FontStyles.NORMAL,true));
//        fontProvider.addFont(FontProgramFactory.createRegisteredFont("微软雅黑", FontStyles.NORMAL,true));
        System.out.println(FontProgramFactory.getRegisteredFontFamilies());
//        for(String r:FontProgramFactory.getRegisteredFontFamilies()){
//            fontProvider.addFont(FontProgramFactory.createFont(r,true));
//        }
//        FontProgramFactory.getRegisteredFonts().forEach(r->{
//
//        });

//        fontProvider.addStandardPdfFonts();

//        fontProvider.addDirectory("D:\\\\font");
//        fontProvider.addFont("D:\\font\\msyh.ttc,0");
//        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyh.ttc",true));
//        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyh.ttc,0",true));
        fontProvider.getFontSet().getFonts().forEach(System.out::println);
//        fontProvider.getFontSet().addFont(FontInfo.create())
//        fontProvider.addSystemFonts();
//        fontProvider.addFont("D:\\font\\msyh.ttc");
//        fontProvider.addSystemFonts()
        converterProperties.setFontProvider(fontProvider);
        HtmlConverter.convertToPdf(new FileInputStream(htmlSource),
            new FileOutputStream(pdfDest), converterProperties);
    }

    /**
     * 增加ttc字体的方法
     * @author 徐明龙 XuMingLong 2020-08-04
     * @return com.itextpdf.layout.font.FontProvider
     */
    private FontProvider addFont_1(){
        FontProvider fontProvider = new FontProvider();
        /**
         *  ttc字体是一个集合，一个字体文件里包含了多个字体
         *  fontPath 格式为：文件名路径,集合的下标（0开始）
         */
        //增加微软雅黑普通字体
        fontProvider.addFont("D:\\font\\msyh.ttc,0");
        fontProvider.addFont("D:\\font\\msyh.ttc,1");
        //增加微软雅黑加粗字体
        fontProvider.addFont("D:\\font\\msyhbd.ttc,0");
        fontProvider.addFont("D:\\font\\msyhbd.ttc,1");
        //增加微软雅黑Light字体
        fontProvider.addFont("D:\\font\\msyhl.ttc,0");
        fontProvider.addFont("D:\\font\\msyhl.ttc,1");
        return fontProvider;
    }


    /**
     * 增加ttc字体的方法2
     * @author 徐明龙 XuMingLong 2020-08-04
     * @return com.itextpdf.layout.font.FontProvider
     */
    private FontProvider addFont_2() throws IOException {
        FontProvider fontProvider = new FontProvider();
        //使用TrueTypeCollection创建字体
        TrueTypeCollection ttc = new TrueTypeCollection("D:\\font\\msyh.ttc");
        for (int i = 0; i < ttc.getTTCSize(); i++) {
            fontProvider.addFont(ttc.getFontByTccIndex(i));
        }
        ttc = new TrueTypeCollection("D:\\font\\msyhbd.ttc");
        for (int i = 0; i < ttc.getTTCSize(); i++) {
            fontProvider.addFont(ttc.getFontByTccIndex(i));
        }
        ttc = new TrueTypeCollection("D:\\font\\msyhl.ttc");
        for (int i = 0; i < ttc.getTTCSize(); i++) {
            fontProvider.addFont(ttc.getFontByTccIndex(i));
        }
        return fontProvider;
    }

    /**
     * 增加ttc字体的方法3
     * @author 徐明龙 XuMingLong 2020-08-04
     * @return com.itextpdf.layout.font.FontProvider
     */
    private FontProvider addFont_3() throws IOException {
        FontProvider fontProvider = new FontProvider();
        //使用FontProgramFactory创建字体
        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyh.ttc",0,true));
        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyh.ttc",1,true));
        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyhbd.ttc",0,true));
        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyhbd.ttc",1,true));
        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyhl.ttc",0,true));
        fontProvider.addFont(FontProgramFactory.createFont("D:\\font\\msyhl.ttc",1,true));
        return fontProvider;
    }

    /**
     * 增加字体的方法3
     * @see com.itextpdf.io.font.FontProgramDescriptorFactory
     * @author 徐明龙 XuMingLong 2020-08-04
     * @return com.itextpdf.layout.font.FontProvider
     */
    private FontProvider addFont_4() throws IOException {
        FontProvider fontProvider = new FontProvider();
        //先扫描字体目录，注册扫描到的字体
        /**
         * FontProgramDescriptorFactory.fetchDescriptor(String fontName) 方法解析出字体的名称和path的映射关系
         *
         */
        FontProgramFactory.clearRegisteredFonts();
        FontProgramFactory.clearRegisteredFontFamilies();
        FontProgramFactory.registerFontDirectory("D:\\\\font");
        /**
         * D:\font\msyh.ttc 微软雅黑的名称，
         * fullName: 1) Microsoft YaHei  2)微软雅黑
         * familyName: 1) Microsoft YaHei  2)微软雅黑
         * fontName: MicrosoftYaHei
         * fontStretch: Normal
         * 最终支持的fontName:
         * 1)microsoftyahei -> D:\font\msyh.ttc,0
         * 2)微软雅黑 -> D:\font\msyh.ttc,0
         * 3)microsoft yahei -> D:\font\msyh.ttc,0
         * 4)microsoftyaheiui -> D:\font\msyh.ttc,1
         * 4)microsoftyaheiui -> D:\font\msyh.ttc,1
         */
        //使用FontProgramFactory创建已注册的字体，fontName为注册的字体名称，可以为字体的任意有效的名称
        fontProvider.addFont(FontProgramFactory.createRegisteredFont("微软雅黑", FontStyles.NORMAL,true));
        fontProvider.addFont(FontProgramFactory.createRegisteredFont("Microsoft YaHei Bold",FontStyles.BOLD,true));
        fontProvider.addFont(FontProgramFactory.createRegisteredFont("Microsoft YaHei Light",FontStyles.NORMAL,true));
        fontProvider.addFont(FontProgramFactory.createRegisteredFont("Microsoft YaHei UI", FontStyles.NORMAL,true));
        fontProvider.addFont(FontProgramFactory.createRegisteredFont("Microsoft YaHei UI Bold",FontStyles.BOLD,true));
        fontProvider.addFont(FontProgramFactory.createRegisteredFont("Microsoft YaHei UI Light",FontStyles.NORMAL,true));


        return fontProvider;
    }
}
