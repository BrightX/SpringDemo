package com.bright;

import com.bright.pojo.Transcripts;
import com.deepoove.poi.XWPFTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DocxApplication {
    public static void main(String[] args) throws IOException {
        Transcripts data = new Transcripts();
        data.setSchool("红星小学");
        data.setClazz("四年级1班");
        data.setName("张三");
        data.setAge(10);
        data.setChinese(87.5);
        data.setMath(96.0);
        data.setEnglish(82.0);

        // http://deepoove.com/poi-tl/
        InputStream inputStream = DocxApplication.class.getResourceAsStream("/模板.docx");
        assert inputStream != null;
        XWPFTemplate template = XWPFTemplate.compile(inputStream);
        template.render(data);
        template.writeAndClose(new FileOutputStream("out/output.docx"));
    }
}
