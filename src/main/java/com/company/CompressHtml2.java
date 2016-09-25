package com.company;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * Created by Administrator on 2016/8/6.
 */
public class CompressHtml2 {

    public static void main(String[] args) throws Exception {
        String html = Files.toString(new File("C:\\Users\\Administrator\\Desktop\\testCompress.html"), Charsets.UTF_8);
        System.out.println(html.length());

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

        String gzipBase64Str = compress(compressHtml);
        System.out.println("gzip压缩后的字符串：");
        System.out.println(gzipBase64Str);
        System.out.println(gzipBase64Str.length());

        String ungzipHtmlStr = decompressToString(gzipBase64Str);
        System.out.println("解压后的字符串:");
        System.out.println(ungzipHtmlStr);
        System.out.println(ungzipHtmlStr.length());

        System.out.println("解压后是否与压缩前相同：");
        System.out.println(ungzipHtmlStr.equals(compressHtml));

}

    private static String compress(String stringToCompress) throws UnsupportedEncodingException
    {

        byte[] input = stringToCompress.getBytes("UTF-8");
        Deflater compressor = new Deflater();
        compressor.setLevel(Deflater.BEST_COMPRESSION);
        compressor.setInput(input);
        compressor.finish();

        ByteArrayOutputStream bos = new ByteArrayOutputStream(input.length);

        byte[] buf = new byte[1024];
        while (!compressor.finished()) {
            int count = compressor.deflate(buf);
            bos.write(buf, 0, count);
        }
        try {
            bos.close();
        } catch (IOException e) {
        }

        // Get the compressed data
        byte[] compressedData = bos.toByteArray();
        return Base64.encodeBase64String(compressedData);
    }

    private static String decompressToString(String base64String) throws IOException, DataFormatException
    {
        byte[] compressedData = Base64.decodeBase64(base64String);

        Inflater inflater = new Inflater();
        inflater.setInput(compressedData);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(compressedData.length);
        byte[] buffer = new byte[1024];
        while (!inflater.finished()) {
            int count = inflater.inflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        outputStream.close();
        byte[] output = outputStream.toByteArray();
        return new String(output, "UTF-8");
    }

}
