package com.example.demo.domain.common.log;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface LogEntryRepository extends CrudRepository<LogEntry, UUID>{
}
