package com.spring.clinicmedia.infrastructure;

import com.spring.clinicmedia.domain.port.SaveFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class SaveFileAdapter implements SaveFile {

    private static final String PATH_SAVE = "";

    @Override
    public String save(MultipartFile medicalRecordFile, String fileName) throws IOException {
        String storageDir = ""; // Example path on server
        File destFile = new File(storageDir + fileName);

        // Make sure the directory exists

        // Save the file
        medicalRecordFile.transferTo(destFile);
        return destFile.getAbsolutePath();
    }
}
