package com.aldeamo.BartenderTest.controller;

import com.aldeamo.BartenderTest.exceptions.IdNotFound;
import com.aldeamo.BartenderTest.response.BartenderResponse;
import com.aldeamo.BartenderTest.service.BartenderService;
import com.aldeamo.BartenderTest.util.Constants;
import com.aldeamo.BartenderTest.ws.IBartenderWS;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/bartender")
public class BartenderController {
    private final Logger logger = LogManager.getLogger(BartenderService.class);
    @Autowired IBartenderWS service;
    @GetMapping("solve/{q}/{id}")
    public ResponseEntity<BartenderResponse> getBartender(@PathVariable int q, @PathVariable int id){
        BartenderResponse response = null;
        try {

            logger.info(String.format(Constants.LOG_SOLVING, q, id));

            response = service.execute(q, id);

            logger.info(String.format(Constants.LOG_SOLVED, q, id, response.getResult(), response.getExecution_time()));

            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (IdNotFound e){
            logger.error("ERROR:", e);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            logger.error("ERROR:", e);
            return new ResponseEntity<>(response, HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
}
