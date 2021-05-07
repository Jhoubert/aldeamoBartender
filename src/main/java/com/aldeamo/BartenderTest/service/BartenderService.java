package com.aldeamo.BartenderTest.service;

import com.aldeamo.BartenderTest.entity.BartenderEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BartenderService {
    private final Logger logger = LogManager.getLogger(BartenderService.class);
    @Autowired BartenderRepository repository;
    public Optional<BartenderEntity> findById(Long id) {
        try {
            return repository.findById(id);
        } catch (Exception e) {
            logger.error("ERROR:", e);
            return Optional.empty();
        }
    }
}