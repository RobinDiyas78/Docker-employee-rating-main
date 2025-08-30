package com.EmployeeRating.ServiceImplementation;

import com.EmployeeRating.Entity.User;
import com.EmployeeRating.Repository.UserRepository;
import com.EmployeeRating.Service.ExcelUploadService;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void uploadExcel(MultipartFile file) {
        try (InputStream is = file.getInputStream()) {
            Workbook workbook = WorkbookFactory.create(is);
            Sheet sheet = workbook.getSheetAt(0);

            // Skip header (row 0)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                String empId = getCellValue(row.getCell(1)); // Emp ID
                String name  = getCellValue(row.getCell(2)); // Name
                String role  = getCellValue(row.getCell(3)); // Role

                if (empId == null || empId.isEmpty()) continue; // skip empty rows

                // Create new User
                User user = new User();
                user.setEmployeeId(empId);            // 👈 login ID
                user.setEmployeeName(name);           // 👈 employee name
                user.setPassword("Rumango@123");  // default password
                user.setEmployeeRole(role);

                userRepository.save(user);
            }

            workbook.close();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage(), e);
        }
    }

    // ✅ Safe cell reader
    private String getCellValue(Cell cell) {
        if (cell == null) return null;

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf((long) cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
                return null;
            default:
                return null;
        }
    }
}
