package com.noofinc.dsm.webapi.client.filestation.delete;

import com.noofinc.dsm.webapi.client.AbstractTest;
import com.noofinc.dsm.webapi.client.filestation.exception.FileNotFoundException;
import com.noofinc.dsm.webapi.client.filestation.filelist.FileListService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.Assert.*;

public class DeleteServiceTest extends AbstractTest {

    @Autowired
    private FileListService fileListService;

    @Autowired
    private DeleteServiceImpl deleteService;

    @Test
    public void testSynchronousDelete() {
        deleteService.synchronousDelete("/noofinc-ws-it/test-1", true, Optional.empty());
        assertNull(fileListService.getFile("/noofinc-ws-it/test-1"));
    }

    @Test
    public void testDeleteNonRecursive() {
        deleteService.synchronousDelete("/noofinc-ws-it/test-1", false, Optional.empty());
        assertNotNull(fileListService.getFile("/noofinc-ws-it/test-1"));
    }

    @Test(expected = FileNotFoundException.class)
    public void testNotExistingShare() {
        deleteService.synchronousDelete("/not-existing/test-brol", false, Optional.empty());
    }

    @Test
    public void testNotExistingFile() {
        deleteService.synchronousDelete("/noofinc-ws-it/test-brol", false, Optional.empty());
    }
}
