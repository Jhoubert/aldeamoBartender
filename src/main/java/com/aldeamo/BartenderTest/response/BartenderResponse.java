package com.aldeamo.BartenderTest.response;

import com.aldeamo.BartenderTest.entity.BartenderEntity;
import com.aldeamo.BartenderTest.service.BartenderService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BartenderResponse {
    private final Logger logger = LogManager.getLogger(BartenderService.class);
    private long id;
    private String status = "";
    private String result = "";
    private long execution_time = 0;

    public BartenderResponse(){}

    public BartenderResponse(BartenderEntity bartender){
        this.id = bartender.getId();
    }

    public void setStatus(String status){
        this.status = status;
    }

    public void setExecution_time(long time){
        this.execution_time = time;
    }

    public void setResult(String result){
        this.result = result;

    }


    @JsonProperty("status")
    public String getStatus(){
        return this.status;
    }

    @JsonProperty("time")
    public String getExecution_time(){
        return this.execution_time+" ms";
    }

    @JsonProperty("result")
    public String getResult(){
        return this.result;

    }

}
