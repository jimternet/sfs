package com.noofinc.dsm.webapi.client.filestation.background;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noofinc.dsm.webapi.client.core.AbstractDsmServiceImpl;
import com.noofinc.dsm.webapi.client.core.DsmWebApiResponseError;
import com.noofinc.dsm.webapi.client.core.DsmWebapiRequest;
import com.noofinc.dsm.webapi.client.core.DsmWebapiResponse;
import com.noofinc.dsm.webapi.client.filestation.common.PaginationAndSorting;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BackgroundTaskServiceImpl extends AbstractDsmServiceImpl implements BackgroundTaskService {

    // API Infos
    private static final String API_ID = "SYNO.FileStation.BackgroundTask";
    private static final String API_VERSION = "3";

    // Methods
    private static final String METHOD_LIST = "list";

    // Parameters
    private static final String PARAMETER_OFFSET = "offset";
    private static final String PARAMETER_LIMIT = "limit";
    private static final String PARAMETER_API_FILTER = "api_filter";

    public BackgroundTaskServiceImpl() { super(API_ID); }

    @Override
    public BackgroundTask.TaskList list() {
        return list(new PaginationAndSorting(0, 0, PaginationAndSorting.Sort.NAME, PaginationAndSorting.SortDirection.ASC), Optional.<String>empty());
    }

    @Override
    public BackgroundTask.TaskList list(PaginationAndSorting paginationAndSorting, Optional<String> apiFilter) {
        DsmWebapiRequest request = new DsmWebapiRequest(getApiId(), API_VERSION, getApiInfo().getPath(), METHOD_LIST)
                .parameter(PARAMETER_OFFSET, Integer.toString(paginationAndSorting.getOffset()))
                .parameter(PARAMETER_LIMIT, Integer.toString(paginationAndSorting.getLimit()))
                ;

        if (apiFilter != null && apiFilter.isPresent()) {
            request.parameter(PARAMETER_API_FILTER, apiFilter);
        }
        BackgroundTaskListResponse response = getDsmWebapiClient().call(request, BackgroundTaskListResponse.class);
        return response.getData();

    }

    private static class BackgroundTaskListResponse extends DsmWebapiResponse<BackgroundTask.TaskList> {

        @JsonCreator
        public BackgroundTaskListResponse(
                @JsonProperty("success") boolean success,
                @JsonProperty("data") BackgroundTask.TaskList data,
                @JsonProperty("error") DsmWebApiResponseError error) {
            super(success, data, error);
        }
    }
}
