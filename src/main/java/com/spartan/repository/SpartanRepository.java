package com.spartan.repository;

import com.spartan.entity.Spartan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpartanRepository extends JpaRepository<Spartan, Long> {

    Optional<Spartan> findByUuidAndIsDeleted(UUID id, Boolean isDeleted);

    List<Spartan> findAllByIsDeletedOrderByIdAsc(Boolean isDeleted);

    @Query(value = "SELECT * FROM spartans WHERE " +
            "(COALESCE(:nameContaining, '') = '' OR name LIKE %:nameContaining%) " +
            "AND (COALESCE(:gender, '') = '' OR gender = :gender)", nativeQuery = true)
    List<Spartan> findAllByNameContainsAndGender(@Param("nameContaining") String nameContaining,
                                                 @Param("gender") String gender);


}
