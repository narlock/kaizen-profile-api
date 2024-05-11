package com.narlock.kaizenprofileapi.repository;

import com.narlock.kaizenprofileapi.model.RowInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RowInfoRepository extends JpaRepository<RowInfo, Integer> {
    RowInfo save(RowInfo entry);

    List<RowInfo> findByProfileId(Integer profileId);
}
