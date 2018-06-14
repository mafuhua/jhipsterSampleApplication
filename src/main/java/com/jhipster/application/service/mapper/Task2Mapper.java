package com.jhipster.application.service.mapper;

import com.jhipster.application.domain.*;
import com.jhipster.application.service.dto.Task2DTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Task2 and its DTO Task2DTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface Task2Mapper extends EntityMapper<Task2DTO, Task2> {



    default Task2 fromId(Long id) {
        if (id == null) {
            return null;
        }
        Task2 task2 = new Task2();
        task2.setId(id);
        return task2;
    }
}
