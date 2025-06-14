package com.spring.clinicmedia.domain.port;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface SaveFile {


    String save(MultipartFile medicalRecordFile,String fileName) throws IOException;

}
