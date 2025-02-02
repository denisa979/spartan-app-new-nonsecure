package com.spartan.service.impl;

import com.spartan.dto.SpartanDTO;
import com.spartan.entity.Spartan;
import com.spartan.enums.Gender;
import com.spartan.exception.FieldValidationException;
import com.spartan.exception.SpartanAlreadyExistsException;
import com.spartan.exception.SpartanNotFoundException;
import com.spartan.repository.SpartanRepository;
import com.spartan.service.SpartanService;
import com.spartan.util.MapperUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SpartanServiceImpl implements SpartanService {

    private final SpartanRepository spartanRepository;
    private final MapperUtil mapperUtil;
    private final Validator validator;

    public SpartanServiceImpl(SpartanRepository spartanRepository, MapperUtil mapperUtil, Validator validator) {
        this.spartanRepository = spartanRepository;
        this.mapperUtil = mapperUtil;
        this.validator = validator;
    }

    @Override
    public SpartanDTO createSpartan(SpartanDTO spartanDTO) {

        Optional<Spartan> foundSpartan = spartanRepository.findByUuidAndIsDeleted(spartanDTO.getUuid(), false);

        if (foundSpartan.isPresent()) {
            throw new SpartanAlreadyExistsException("Spartan with this ID number already exists.");
        }

        Spartan spartanToSave = mapperUtil.convert(spartanDTO, new Spartan());
        spartanToSave.setIsDeleted(false);
        spartanToSave.setUuid(UUID.randomUUID());

        Spartan savedSpartan = spartanRepository.save(spartanToSave);

        return mapperUtil.convert(savedSpartan, new SpartanDTO());

    }

    @Override
    public SpartanDTO readSpartanByUuid(UUID uuid) {
        Spartan foundSpartan = spartanRepository.findByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() -> new SpartanNotFoundException("Spartan with the given ID number could not be found."));
        return mapperUtil.convert(foundSpartan, new SpartanDTO());
    }

    @Override
    public List<SpartanDTO> readAllSpartans() {
        return spartanRepository.findAllByIsDeletedOrderByIdAsc(false).stream()
                .map(eachSpartan -> mapperUtil.convert(eachSpartan, new SpartanDTO()))
                .toList();
    }

    @Override
    public List<SpartanDTO> readAllSpartansBasedOnSearchCriteria(String nameContains, String gender) {

        StringBuilder errorMessage = new StringBuilder();

        if (nameContains != null && nameContains.isBlank()) {
            errorMessage.append("name")
                    .append(":")
                    .append("The 'name contains' can not include only spaces, if provided.")
                    .append("\n");
        }

        if (gender != null && (gender.isEmpty() || (!gender.equalsIgnoreCase("Male") && !gender.equalsIgnoreCase("Female")))) {
            errorMessage.append("gender")
                    .append(":")
                    .append("The gender can only have 'Male' or 'Female' values, if provided.")
                    .append("\n");
        }

        if (!errorMessage.isEmpty()) {
            throw new FieldValidationException(errorMessage.toString());
        }

        if (gender != null) {
            gender = gender.toUpperCase();
        }

        return spartanRepository.findAllByNameContainsAndGender(nameContains, gender).stream()
                .map(eachSpartan -> mapperUtil.convert(eachSpartan, new SpartanDTO()))
                .toList();

    }

    @Override
    public SpartanDTO updateSpartan(SpartanDTO spartanDTO, UUID uuid) {

        Spartan foundSpartan = spartanRepository.findByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() -> new SpartanNotFoundException("Spartan with the given ID number could not be found."));

        Spartan spartanToUpdate = mapperUtil.convert(spartanDTO, new Spartan());
        spartanToUpdate.setUuid(foundSpartan.getUuid());
        spartanToUpdate.setId(foundSpartan.getId());

        Spartan updatedSpartan = spartanRepository.save(spartanToUpdate);

        return mapperUtil.convert(updatedSpartan, new SpartanDTO());

    }

    @Override
    public SpartanDTO updateSpartanPartially(UUID uuid, Map<String, Object> fieldsToUpdate) {

        Spartan foundSpartan = spartanRepository.findByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() -> new SpartanNotFoundException("Spartan with the given ID number could not be found."));

        SpartanDTO spartanToUpdate = new SpartanDTO();

        spartanToUpdate.setUuid(foundSpartan.getUuid());

        StringBuilder errorMessage = new StringBuilder();

        if (fieldsToUpdate.containsKey("name")) {
            spartanToUpdate.setName((String) fieldsToUpdate.get("name"));
            validateField(spartanToUpdate, "name", errorMessage);
        } else {
            spartanToUpdate.setName(foundSpartan.getName());
        }

        if (fieldsToUpdate.containsKey("gender")) {
            String gender = (String) fieldsToUpdate.get("gender");
            if (gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female")) {
                spartanToUpdate.setGender(Gender.valueOf(gender.toUpperCase()));
            } else {
                spartanToUpdate.setGender(null);
            }
            validateField(spartanToUpdate, "gender", errorMessage);
        } else {
            spartanToUpdate.setGender(foundSpartan.getGender());
        }

        if (fieldsToUpdate.containsKey("phone")) {
            spartanToUpdate.setPhone((String) fieldsToUpdate.get("phone"));
            validateField(spartanToUpdate, "phone", errorMessage);
        } else {
            spartanToUpdate.setPhone(foundSpartan.getPhone());
        }

        if (!errorMessage.isEmpty()) {
            throw new FieldValidationException(errorMessage.toString());
        }

        return updateSpartan(spartanToUpdate, uuid);

    }

    @Override
    public void deleteSpartanByUuid(UUID uuid) {
        Spartan foundSpartan = spartanRepository.findByUuidAndIsDeleted(uuid, false)
                .orElseThrow(() -> new SpartanNotFoundException("Spartan with the given ID number could not be found."));
        foundSpartan.setIsDeleted(true);
        spartanRepository.save(foundSpartan);
    }

    private void validateField(SpartanDTO spartanDTO, String fieldName, StringBuilder errorMessage) {

        Set<ConstraintViolation<Object>> violations = validator.validateProperty(spartanDTO, fieldName);

        if (!violations.isEmpty()) {

            for (ConstraintViolation<Object> violation : violations) {

                errorMessage.append(fieldName)
                        .append(": ")
                        .append(violation.getMessage())
                        .append("\n");
            }

        }

    }

}
