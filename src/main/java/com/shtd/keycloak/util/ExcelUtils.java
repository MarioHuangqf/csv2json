package com.shtd.keycloak.util;

import com.shtd.keycloak.dto.UserDTO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExcelUtils {

    // 读取Excel
    public static Workbook getWorkBook(String filePath) {
        File file = new File(filePath);
        FileInputStream inputStream;
        Workbook workbook = null;
        String fileSuffix = filePath.substring(filePath.lastIndexOf("."));

        try {
            inputStream = new FileInputStream(file);
            if (".xls".equals(fileSuffix)) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (".xlsx".equals(fileSuffix)) {
                workbook = new XSSFWorkbook(inputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }


    // 解析Excel
    public static List<UserDTO> analysisWorkBook(Workbook workbook) throws Exception{
        List<UserDTO> users = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0);
        int totalRowNum = sheet.getLastRowNum();
        Row row = null;
        for (int i=1; i <= totalRowNum; i++) {
            row = sheet.getRow(i);
            UserDTO user = new UserDTO();
            user.setCreatedTimestamp(System.currentTimeMillis());
            String phoneNum = getCellStringVal(row.getCell(4));
            if (!judgePhoneNum(phoneNum)) {
                throw new Exception("手机号格式不正确："+phoneNum);
            }
            user.setUsername(getCellStringVal(row.getCell(4)));
            user.setEnabled(true);
            user.setTotp(false);
            user.setEmailVerified(false);
            user.setEmail(getCellStringVal(row.getCell(5)));
            user.setFirstName(getCellStringVal(row.getCell(1)));
            user.setLastName(getCellStringVal(row.getCell(0)));
            Map<String, List> clientRoles = new HashMap();
            List<String> roles = new ArrayList<>();
            roles.add("manage-account");
            clientRoles.put("account", roles);
            user.setClientRoles(clientRoles);
            user.setNotBefore(0);
            List<String> groups = new ArrayList<>();
            String groupsData = getCellStringVal(row.getCell(6));
            if (!groupsData.isEmpty()) {
                 List groupsList = Arrays.asList(groupsData.split(" "));
                 user.setGroups(groupsList);
            }
            Map<String, String> map = new HashMap<>();
            map.put("locale", "zh-CN");
            map.put("sex", getCellStringVal(row.getCell(2)));
            map.put("address", getCellStringVal(row.getCell(3)));
            user.setAttributes(map);
            users.add(user);
        }

        return users;
    }
    private static String getCellStringVal(Cell cell) {
        CellType cellType = cell.getCellType();
        switch (cellType) {
            // 科学计数
            case NUMERIC:
                return new DecimalFormat("0").format(cell.getNumericCellValue());
            // 字符串
            case STRING:
                return cell.getStringCellValue();
            // 布尔值
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            // 公式
            case FORMULA:
                return cell.getCellFormula();
            // 空值
            case BLANK:
                return "";
            // 错误
            case ERROR:
                return "";
            default:
                return "";
        }
    }

    private static Boolean judgePhoneNum(String phoneNum) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phoneNum.length() != 11) {
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNum);
        return m.matches();
    }
}