package com.company;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import org.apache.commons.codec.binary.Base64;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by Administrator on 2016/8/6.
 */
public class CompressHtml {

    public static void main(String[] args) throws Exception {
        String html = Files.toString(new File("C:\\Users\\Administrator\\Desktop\\testCompress.html"), Charsets.UTF_8);
        System.out.println(html.length());

        //jsoup去除class
        Document doc = Jsoup.parse(html);
        doc.getAllElements().removeAttr("class");


        String compressHtml = compressHtml(doc.toString());

        String gzipBase64Str = Base64.encodeBase64String(gzip(compressHtml));
        System.out.println("gzip压缩后的字符串：");
        System.out.println(gzipBase64Str);
        System.out.println(gzipBase64Str.length());


        //读取出文章后
        if(!isHtmlString(gzipBase64Str)){
        String ungzipHtmlStr = ungzip(Base64.decodeBase64(gzipBase64Str));
        System.out.println("解压后的字符串:");
        System.out.println(ungzipHtmlStr);
        System.out.println(ungzipHtmlStr.length());

        System.out.println("解压后是否与压缩前相同：");
            System.out.println(ungzipHtmlStr.equals(compressHtml));
        }

    }

    private static boolean isHtmlString(String source) {
        //for check base64:
        //^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$
        return Pattern.compile("<[a-z][\\s\\S]*>").matcher(source).matches();
    }

    private static String compressHtml(String html) {
        HtmlCompressor compressor = new HtmlCompressor();

        compressor.setEnabled(true);                   //if false all compression is off (default is true)
        compressor.setCompressCss(true);

        compressor.setCompressJavaScript(true);
        compressor.setRemoveComments(true);            //if false keeps HTML comments (default is true)
        compressor.setRemoveMultiSpaces(true);         //if false keeps multiple whitespace characters (default is true)
        compressor.setRemoveIntertagSpaces(true);      //removes iter-tag whitespace characters
        compressor.setRemoveQuotes(true);              //removes unnecessary tag attribute quotes
        compressor.setSimpleDoctype(true);             //simplify existing doctype
        compressor.setRemoveScriptAttributes(true);    //remove optional attributes from script tags
        compressor.setRemoveStyleAttributes(true);     //remove optional attributes from style tags
        compressor.setRemoveLinkAttributes(true);      //remove optional attributes from link tags
        compressor.setRemoveFormAttributes(true);      //remove optional attributes from form tags
        compressor.setRemoveInputAttributes(true);     //remove optional attributes from input tags
        compressor.setSimpleBooleanAttributes(true);   //remove values from boolean tag attributes
        compressor.setRemoveJavaScriptProtocol(true);  //remove "javascript:" from inline event handlers
        compressor.setRemoveHttpProtocol(true);        //replace "http://" with "//" inside tag attributes
        compressor.setRemoveHttpsProtocol(true);       //replace "https://" with "//" inside tag attributes
        compressor.setPreserveLineBreaks(true);        //preserves original line breaks

        String compressHtml = compressor.compress(html);
        System.out.println("压缩前的字符串");
        System.out.println(compressHtml);
        System.out.println(compressHtml.length());
        return compressHtml;
    }

    private static String ungzip(byte[] bytes) throws Exception {
        InputStreamReader isr = new InputStreamReader(new GZIPInputStream(new ByteArrayInputStream(bytes)), StandardCharsets.UTF_8);
        StringWriter sw = new StringWriter();
        char[] chars = new char[1024];
        for (int len; (len = isr.read(chars)) > 0; ) {
            sw.write(chars, 0, len);
        }
        return sw.toString();
    }

    private static byte[] gzip(String s) throws Exception {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzip = new GZIPOutputStream(bos);
        OutputStreamWriter osw = new OutputStreamWriter(gzip, StandardCharsets.UTF_8);
        osw.write(s);
        osw.close();
        return bos.toByteArray();
    }

}
