package com.shtd.keycloak;

import com.alibaba.fastjson.JSON;
import com.shtd.keycloak.dto.RealmDTO;
import com.shtd.keycloak.dto.UserDTO;
import com.shtd.keycloak.util.ExcelUtils;
import org.apache.poi.ss.usermodel.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class ImportUser {
    public static final String REALM_NAME = "odms";
    
    public static void main(String[] args) {
        String filePath = args[0];

        // 获取Excel
        Workbook workbook = ExcelUtils.getWorkBook(filePath);
        if (workbook == null) {
            System.out.println("未读取到Excel文件");
        }

        // 解析Excel
        List<UserDTO> users = null;
        try {
            users = ExcelUtils.analysisWorkBook(workbook);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return;
        }
        if ( users == null|| users.isEmpty()) {
            System.out.println("未解析到用户");
        }

        RealmDTO realmDTO = new RealmDTO();
        realmDTO.setReaml(REALM_NAME);
        realmDTO.setUsers(users);

        // 输出json文件
        String jsonString = JSON.toJSONString(realmDTO, true);
        try {
            String outputFilePath = filePath.substring(0, filePath.lastIndexOf(".")) + ".json";
            Files.write(Paths.get(outputFilePath), jsonString.getBytes());
            System.out.println("已在同级目录生成json文件");
        } catch (IOException e) {
            System.out.println("生成json文件失败：" + e.getMessage());
        }
    }

}
