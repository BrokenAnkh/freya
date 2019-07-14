package freya.component.lika.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.ss.util.NumberToTextConverter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExcelValidUtil {
    private static final String DATE_FORMAT_STR = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT_STR);

    private static final int DEFAULT_COLUMN_RANG = 200;

    /**
     * 添加约束的方法
     */

    public static DataValidationConstraint createConstraint(DataValidationHelper helper, int validationType, int operateType, String... options) {
        DataValidationConstraint constraint = null;

        String[] params = parseConstraintOptions(options);
        switch (validationType) {
            case DataValidationConstraint.ValidationType.LIST:
                if (params.length == 2 && params[1] == null) {
                    constraint = helper.createFormulaListConstraint(params[0]);
                } else {
                    constraint = helper.createExplicitListConstraint(params);
                }
                break;
            case DataValidationConstraint.ValidationType.DATE:
                constraint = helper.createDateConstraint(operateType, options[0], options[1], DATE_FORMAT_STR);
                break;
            case DataValidationConstraint.ValidationType.TEXT_LENGTH:
                constraint = helper.createTextLengthConstraint(operateType, options[0], options[1]);
                break;
            case DataValidationConstraint.ValidationType.INTEGER:
                constraint = helper.createIntegerConstraint(operateType, options[0], options[1]);
                break;
            case DataValidationConstraint.ValidationType.DECIMAL:
                constraint = helper.createDecimalConstraint(operateType, options[0], options[1]);
                break;
            case DataValidationConstraint.ValidationType.TIME:
                constraint = helper.createTimeConstraint(operateType, options[0], options[1]);
                break;
            case DataValidationConstraint.ValidationType.FORMULA:
                constraint = helper.createCustomConstraint(options[0]);
                break;
            case DataValidationConstraint.ValidationType.ANY:
            default:
                break;
        }
        return constraint;
    }

    public static CellRangeAddressList createRangeAddressList(Cell cell) {
        return createRangeAddressList(cell, DEFAULT_COLUMN_RANG);
    }

    public static CellRangeAddressList createRangeAddressList(Cell cell, int rang) {
        return new CellRangeAddressList(cell.getRowIndex(), cell.getRowIndex() + rang, cell.getColumnIndex(), cell.getColumnIndex());
    }


    public static String[] parseConstraintOptions(String... options) {
        final int defaultOptionsNum = 2;
        String[] result = new String[defaultOptionsNum > options.length ? defaultOptionsNum : options.length];
        for (int i = 0; i < options.length; i++) {
            result[i] = options[i];
        }
        return result;
    }

    public static void appendValidationData(DataValidation validation, Sheet sheet) {
        validation.createErrorBox("数据不正确", "请重新输入");
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        sheet.addValidationData(validation);
    }


    /**
     * 解析方法
     */

    public static String parseValue(Cell cell) {
        String value;
        switch (cell.getCellType()) {
            case BLANK:
                value = "";
                break;
            case BOOLEAN:
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                Workbook wb = cell.getSheet().getWorkbook();
                CreationHelper crateHelper = wb.getCreationHelper();
                FormulaEvaluator evaluator = crateHelper.createFormulaEvaluator();
                value = parseValue(evaluator.evaluateInCell(cell));
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    value = DATE_FORMAT.format(date);
                } else {
                    value = NumberToTextConverter.toText(cell.getNumericCellValue());
                }
                break;
            case STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case ERROR:
            default:
                value = null;
                break;
        }

        return value;
    }
}
