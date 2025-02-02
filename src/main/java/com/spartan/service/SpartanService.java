package com.spartan.service;

import com.spartan.dto.SpartanDTO;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface SpartanService {

    SpartanDTO createSpartan(SpartanDTO spartanDTO);

    SpartanDTO readSpartanByUuid(UUID uuid);

    List<SpartanDTO> readAllSpartans();

    List<SpartanDTO> readAllSpartansBasedOnSearchCriteria(String nameContains, String gender);

    SpartanDTO updateSpartan(SpartanDTO spartanDTO, UUID uuid);

    SpartanDTO updateSpartanPartially(UUID uuid, Map<String, Object> fieldsToUpdate);

    void deleteSpartanByUuid(UUID uuid);

}
