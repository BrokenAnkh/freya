package freya.component.lika.util;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;

/**
 * excel读写工具类
 */
public class ExcelConfigUtil {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    /**
     * 创建方法
     */
    public static Workbook crateWorkBook(byte[] fileData) throws IOException, EncryptedDocumentException {
        ByteArrayInputStream in = new ByteArrayInputStream(fileData);
        return WorkbookFactory.create(in);
    }

    public static Workbook crateWorkBook(File file) throws IOException, EncryptedDocumentException {
        if (file == null || !file.isFile()) {
            return null;
        }
        String fileName = file.getName();
        return WorkbookFactory.create(new FileInputStream(file));
    }

    public static CellStyle createBorderedStyle(Workbook wb) {
        CellStyle style = wb.createCellStyle();
        baseConfig(style);
        return style;
    }

    /**
     * 配置方法
     */
    public static void baseConfig(Sheet sheet) {
        if (sheet != null) {
            // sheet 基本配置
            sheet.setDisplayGridlines(false);
            sheet.setPrintGridlines(false);//设置不打印网格线
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);
            sheet.setAutobreaks(true);//Sheet页自适应页面大小
            // 打印配置
            PrintSetup print = sheet.getPrintSetup();//得到打印对象
            print.setLandscape(false);//true，则表示页面方向为横向；否则为纵向
            print.setScale((short) 100);//缩放比例80%(设置为0-100之间的值)
            print.setFitWidth((short) 1);//设置页宽
            print.setFitHeight((short) 1);//设置页高
            print.setPaperSize(PrintSetup.A4_PAPERSIZE);//纸张设置
            print.setUsePage(true);//设置打印起始页码不使用"自动"
            print.setPageStart((short) 6);//设置打印起始页码
            print.setNoColor(true);//值为true时，表示单色打印
            print.setDraft(false);//值为true时，表示用草稿品质打印
            print.setLeftToRight(true);//true表示“先行后列”；false表示“先列后行”
            print.setNotes(true);//设置打印批注
        }
    }

    public static void baseConfig(CellStyle style) {
        if (style != null) {
            style.setBorderRight(BorderStyle.THIN);
            style.setRightBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderBottom(BorderStyle.THIN);
            style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderLeft(BorderStyle.THIN);
            style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            style.setBorderTop(BorderStyle.THIN);
            style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        }
    }
}