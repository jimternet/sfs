package com.noofinc.dsm.webapi.client.filestation.background;


import com.noofinc.dsm.webapi.client.filestation.common.PaginationAndSorting;

import java.util.List;
import java.util.Optional;

public interface BackgroundTaskService {
    BackgroundTask.TaskList list();
    BackgroundTask.TaskList list(PaginationAndSorting paginationAndSorting, Optional<String> apiFilter);
    void clear(List<String> taskIds);
    void clear();

}
