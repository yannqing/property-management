package com.qcx.property.config;

import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBody;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordTemplateProcessor {

    public static void processTemplate(String inputPath, String outputPath, Map<String, String> variables) {
        try (FileInputStream fis = new FileInputStream(inputPath);
             XWPFDocument document = new XWPFDocument(fis);
             FileOutputStream fos = new FileOutputStream(outputPath)) {

            // 处理段落中的变量
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                replaceVariablesInParagraph(paragraph, variables);
            }

            // 处理表格中的变量
            for (XWPFTable table : document.getTables()) {
                for (XWPFTableRow row : table.getRows()) {
                    for (XWPFTableCell cell : row.getTableCells()) {
                        for (XWPFParagraph paragraph : cell.getParagraphs()) {
                            replaceVariablesInParagraph(paragraph, variables);
                        }
                    }
                }
            }

            // 处理页眉中的变量
            for (XWPFHeader header : document.getHeaderList()) {
                for (XWPFParagraph paragraph : header.getParagraphs()) {
                    replaceVariablesInParagraph(paragraph, variables);
                }
            }

            // 处理页脚中的变量
            for (XWPFFooter footer : document.getFooterList()) {
                for (XWPFParagraph paragraph : footer.getParagraphs()) {
                    replaceVariablesInParagraph(paragraph, variables);
                }
            }

            document.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void replaceVariablesInParagraph(XWPFParagraph paragraph, Map<String, String> variables) {
        String text = paragraph.getText();
        if (text != null && text.contains("${")) {
            // 替换整个段落中的变量
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                String key = "${" + entry.getKey() + "}";
                if (text.contains(key)) {
                    text = text.replace(key, entry.getValue());
                }
            }
            
            // 清除原有段落内容
            for (int i = paragraph.getRuns().size() - 1; i >= 0; i--) {
                paragraph.removeRun(i);
            }
            
            // 添加新内容
            paragraph.createRun().setText(text);
        }
    }

    public static void main(String[] args) {
        // 示例用法
        Map<String, String> variables = new HashMap<>();
        variables.put("param1", "替换值1");
        variables.put("param2", "替换值2");
        variables.put("param3", "替换值3");
        
        String inputFile = "./sql/content.docx";
        String outputFile = "./sql/output.docx";
        
        processTemplate(inputFile, outputFile, variables);
        
        System.out.println("Word文档处理完成，已生成: " + outputFile);
    }
}