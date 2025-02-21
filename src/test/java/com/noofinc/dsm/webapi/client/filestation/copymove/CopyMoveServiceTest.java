package com.noofinc.dsm.webapi.client.filestation.copymove;

import com.noofinc.dsm.webapi.client.AbstractTest;
import com.noofinc.dsm.webapi.client.filestation.common.OverwriteBehavior;
import com.noofinc.dsm.webapi.client.filestation.exception.FileNotFoundException;
import com.noofinc.dsm.webapi.client.filestation.filelist.FileListService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CopyMoveServiceTest extends AbstractTest {

    @Autowired
    private CopyMoveService copyMoveService;

    @Autowired
    private FileListService fileListService;

    @Test
    public void testSynchronousMoveFile() {
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1/test-text-file2.txt"));
        copyMoveService.synchronousMove("/noofinc-ws-it/test-1/test-text-file2.txt", "/noofinc-ws-it/test-2", OverwriteBehavior.OVERWRITE);
        Assert.assertNull(fileListService.getFile("/noofinc-ws-it/test-1/test-text-file2.txt"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-2/test-text-file2.txt"));
    }

    @Test
    public void testSynchronousCopyFile() {
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1/test-text-file2.txt"));
        copyMoveService.synchronousCopy("/noofinc-ws-it/test-1/test-text-file2.txt", "/noofinc-ws-it/test-2", OverwriteBehavior.OVERWRITE);
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1/test-text-file2.txt"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-2/test-text-file2.txt"));
    }

    @Test
    public void testSynchronousMoveDirectory() {
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1"));
        copyMoveService.synchronousMove("/noofinc-ws-it/test-1", "/noofinc-ws-it/test-2", OverwriteBehavior.OVERWRITE);
        Assert.assertNull(fileListService.getFile("/noofinc-ws-it/test-1"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-2/test-1"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-2/test-1/test-text-file2.txt"));
    }

    @Test
    public void testSynchronousCopyDirectory() {
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1"));
        copyMoveService.synchronousCopy("/noofinc-ws-it/test-1", "/noofinc-ws-it/test-2", OverwriteBehavior.OVERWRITE);
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1/test-text-file2.txt"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-2/test-1"));
        Assert.assertNotNull(fileListService.getFile("/noofinc-ws-it/test-2/test-1/test-text-file2.txt"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testCopyDestinationNotExisting() {
        copyMoveService.synchronousCopy("/noofinc-ws-it/test-1/test-text-file2.txt", "/not-existing/brol2", OverwriteBehavior.SKIP);
    }

    @Test(expected = FileNotFoundException.class)
    public void testCopySourceNotExisting() {
        copyMoveService.synchronousCopy("/file-not-existing/file-not-existing.txt", "/noofinc-ws-it/test-1", OverwriteBehavior.SKIP);
    }

    @Test(expected = FileNotFoundException.class)
    public void testMoveDestinationNotExisting() {
        copyMoveService.synchronousMove("/noofinc-ws-it/test-1/test-text-file2.txt", "/not-existing/brol2", OverwriteBehavior.SKIP);
    }

    @Test(expected = FileNotFoundException.class)
    public void testMoveSourceNotExisting() {
        copyMoveService.synchronousMove("/file-not-existing/file-not-existing.txt", "/noofinc-ws-it/test-1", OverwriteBehavior.SKIP);
    }
}
