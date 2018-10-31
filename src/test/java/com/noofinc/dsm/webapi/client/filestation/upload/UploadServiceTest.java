package com.noofinc.dsm.webapi.client.filestation.upload;

import com.noofinc.dsm.webapi.client.AbstractTest;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

public class UploadServiceTest extends AbstractTest {

    @Autowired
    private UploadService uploadService;

    @Test
    public void testUpload() throws Exception {
        UploadRequest uploadRequest = UploadRequest.createBuilder("/noofinc-ws-it/createFiles" + System.currentTimeMillis(), "test-file.txt", "this is a test file with strsing content\nHelloWorld!\n".getBytes("UTF-8"))
                .createParents(true)
                .creationTime(LocalDateTime.of(1984, 3, 9, 10, 0))
                .lastAccessTime(LocalDateTime.of(1984, 3, 9, 10, 0))
                .lastModificationTime(LocalDateTime.of(1984, 3, 9, 10, 0))
                .build();
        uploadService.uploadFile(uploadRequest);

    }

    @Test
    public void testFtpUpload() {
        String fileName = "uploadTestFileFtp.txt";
        uploadService.uploadFtpFile("//noofinc-ws-it/", fileName, "Upload testFtpUpload file contents".getBytes());


        File f = new File(getShareMountPoint() + "/" + fileName);
        int seconds = 0;
        //check for existence up to 10 sec
        while (!f.exists()  && seconds < 10) {
            try { Thread.sleep(1000L); } catch (InterruptedException e) { }
            seconds = seconds++;
        }
        assert f.exists() && !f.isDirectory();
    }

    @Test
    public void testUploadFileWithFtpFailOver() {
        String fileName = "uploadTestFileFtpFailover.txt";
        uploadService.uploadFileWithFtpFailOver("//noofinc-ws-it/", fileName, "Upload testUploadFileWithFtpFailOver file contents".getBytes());


        File f = new File(getShareMountPoint() + "/" + fileName);
        int seconds = 0;
        //check for existence up to 10 sec
        while (!f.exists()  && seconds < 10) {
            try { Thread.sleep(1000L); } catch (InterruptedException e) { }
            seconds = seconds++;
        }
        assert f.exists() && !f.isDirectory();
    }
}
