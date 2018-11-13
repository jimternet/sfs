package com.noofinc.dsm.webapi.client.filestation.background;

import com.noofinc.dsm.webapi.client.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class BackgroundTaskServiceTest extends AbstractTest {

    @Autowired
    BackgroundTaskService backgroundTaskService;


    @Test
    public void testList() {
        BackgroundTask.TaskList list = backgroundTaskService.list();
        //some should exist due to prior tests
        assert list.getElements().size() > 0;
    }

}
