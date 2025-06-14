package com.spring.clinicmedia.domain.port;

import java.io.IOException;

public interface FileScanner {

    void scan(byte[] fileBytes) throws IOException;
}
