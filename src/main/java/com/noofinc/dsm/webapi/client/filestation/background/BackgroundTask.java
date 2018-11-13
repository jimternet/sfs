package com.noofinc.dsm.webapi.client.filestation.background;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.noofinc.dsm.webapi.client.filestation.common.PaginatedList;
import com.noofinc.dsm.webapi.client.filestation.favorite.FavoriteProperties;

import java.util.List;
import java.util.Map;


public class BackgroundTask {

    String api;
    Integer version;
    String method;
    String taskId;
    Boolean finished;
    //Map<String, Object> params;
    String path;
    String processingPath;
    Integer progress;
    Integer total;


    @JsonCreator
    public BackgroundTask(
            @JsonProperty("api") String api,
            @JsonProperty("version") Integer version,
            @JsonProperty("method") String method,
            @JsonProperty("taskid") String taskId,
            @JsonProperty("finished") Boolean finished,
            //@JsonProperty("params") Map<String, Object> params,
            @JsonProperty("path") String path,
            @JsonProperty("processing_path") String processingPath,
            @JsonProperty("progress") Integer progress,
            @JsonProperty("total") Integer total
    ) {
        this.api = api;
        this.version = version;
        this.method = method;
        this.taskId = taskId;
        this.finished = finished;
        //this.params = params;
        this.path = path;
        this.processingPath = processingPath;
        this.progress = progress;
        this.total = total;
    }


    public static class TaskList extends PaginatedList<BackgroundTask> {

        public TaskList(@JsonProperty("total") int total,
                            @JsonProperty("offset") int offset,
                            @JsonProperty("tasks") List<BackgroundTask> tasks
        ) {
            super(total, offset, tasks);
        }
    }
}
