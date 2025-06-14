package com.spring.clinicmedia.infrastructure;

import com.spring.clinicmedia.domain.port.SaveFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SaveFileAdapter implements SaveFile {

    private static final String PATH_SAVE = "./uploads/";

    @Override
    public String save(MultipartFile medicalRecordFile, String fileName) throws IOException {
        try {
            File directory = new File(PATH_SAVE);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    throw new IOException("Could not create directory: " + PATH_SAVE);
                }
            }
            File destFile = new File(directory, fileName);
            medicalRecordFile.transferTo(destFile);
            return destFile.getAbsolutePath();

        } catch (Exception e) {
            throw new IOException("Failed to save file: " + e.getMessage(), e);
        }
    }
}
