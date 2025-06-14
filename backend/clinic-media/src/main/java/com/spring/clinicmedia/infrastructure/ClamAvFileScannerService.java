package com.spring.clinicmedia.infrastructure;

import com.spring.clinicmedia.domain.exception.VirusDetectedException;
import com.spring.clinicmedia.domain.port.FileScanner;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ClamAvFileScannerService implements FileScanner {

    private final static String HOST = "127.0.0.1";
    private final static int PORT = 3310;
    private final static String OPERATION_CLAIM = "zINSTREAM\0";

    @Override
    public void scan(byte[] fileBytes) throws IOException {
        try (
                Socket socket = new Socket(HOST, PORT);
                OutputStream out = socket.getOutputStream();
                InputStream in = socket.getInputStream()
        ) {

            // Send INSTREAM command with null terminator
            out.write(OPERATION_CLAIM.getBytes(StandardCharsets.US_ASCII));
            out.flush();

            // Send the file in chunks of max 1024 bytes
            int offset = 0;
            while (offset < fileBytes.length) {
                int chunkSize = Math.min(1024, fileBytes.length - offset);

                // Write chunk size as 4-byte big-endian integer
                byte[] sizeBytes = ByteBuffer.
                        allocate(4)
                        .putInt(chunkSize).array();
                out.write(sizeBytes);

                // Write chunk data
                out.write(fileBytes, offset, chunkSize);
                out.flush();

                offset += chunkSize;
            }

            // Send zero-length chunk to mark EOF (End Of File)
            out.write(new byte[]{0, 0, 0, 0});
            out.flush();

            // Read response line
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(in, StandardCharsets.US_ASCII));

            String response = reader.readLine();
            if (response == null) {
                throw new IOException("No response received from ClamAV");
            }

            String cleanResult = response.replace("stream: ", "").trim();

            if (cleanResult.equalsIgnoreCase("ok")) {
                throw new VirusDetectedException("Malicious content detected — upload rejected.");
            }
        } catch (IOException e) {
            throw new IOException("Error communicating with ClamAV", e);
        }
    }

}

