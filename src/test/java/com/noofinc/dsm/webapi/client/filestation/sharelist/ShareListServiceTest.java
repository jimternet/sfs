package com.noofinc.dsm.webapi.client.filestation.sharelist;

import com.noofinc.dsm.webapi.client.AbstractTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ShareListServiceTest extends AbstractTest {

    @Autowired
    private ShareListService shareListService;

    @Test
    public void testList() throws Exception {
        List<Share> list = shareListService.list();
        Assert.assertEquals(2, list.size());
        List<String> shareNames = list.stream().map(Share::getName).collect(Collectors.toList());
        //Arbitrary share set up on the DSM server set up as a precondition
        Assert.assertTrue(shareNames.contains("noofinc-test-share"));
        //Arbitrary share set up on the DSM server set up as a precondition
        Assert.assertTrue(shareNames.contains("noofinc-ws-it"));
    }

}
