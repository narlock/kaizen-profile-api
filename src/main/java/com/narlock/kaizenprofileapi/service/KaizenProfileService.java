package com.narlock.kaizenprofileapi.service;

import com.narlock.kaizenprofileapi.model.*;
import com.narlock.kaizenprofileapi.model.error.NotFoundException;
import com.narlock.kaizenprofileapi.repository.HealthRepository;
import com.narlock.kaizenprofileapi.repository.KaizenProfileRepository;

import java.util.List;
import java.util.Optional;

import com.narlock.kaizenprofileapi.repository.RowInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KaizenProfileService {

  private final KaizenProfileRepository kaizenProfileRepository;
  private final HealthRepository healthRepository;
  private final RowInfoRepository rowInfoRepository;


  public ProfileResponse getKaizenProfileById(Integer id) {
    return ProfileResponse.builder()
            .profile(getProfileById(id))
            .health(getHealthByProfileId(id))
            .build();
  }

  public Profile getProfileById(Integer id) {
    Optional<Profile> profileOptional = kaizenProfileRepository.findById(id);
    if (profileOptional.isPresent()) {
      return profileOptional.get();
    } else {
      throw new NotFoundException("Entry not found for id " + id);
    }
  }

  public Health getHealthByProfileId(Integer profileId) {
    Optional<Health> healthOptional = healthRepository.findById(profileId);
    if (healthOptional.isPresent()) {
      return healthOptional.get();
    } else {
      throw new NotFoundException("Entry not found for profileId " + profileId);
    }
  }

  public ProfileResponse createProfile(ProfileRequest request) {
    Profile responseProfile = kaizenProfileRepository.save(request.getProfile());
    healthRepository.saveHealth(responseProfile.getId(),
            request.getHeight(), request.getWeight(), request.getGoalWeight(), request.getGoalWater());
    Health responseHealth = getHealthByProfileId(responseProfile.getId());

    return ProfileResponse.builder()
            .profile(responseProfile)
            .health(responseHealth)
            .build();
  }

  public ProfileResponse updateProfile(ProfileRequest request) {
    Profile responseProfile = kaizenProfileRepository.save(request.getProfile());
    healthRepository.updateHealth(responseProfile.getId(),
            request.getHeight(), request.getWeight(), request.getGoalWeight(), request.getGoalWater());
    Health responseHealth = getHealthByProfileId(responseProfile.getId());

    return ProfileResponse.builder()
            .profile(responseProfile)
            .health(responseHealth)
            .build();
  }

  public void deleteProfileById(Integer id) {
    healthRepository.deleteById(id);
    kaizenProfileRepository.deleteById(id);
  }

  public List<RowInfo> saveRowInfo(Integer id, RowInfoRequest request) {
    rowInfoRepository.saveRowInfo(id, request.getRowIndex(), request.getWidgets());
    return getRowInfoById(id);
  }

  public List<RowInfo> getRowInfoById(Integer id) {
    return rowInfoRepository.findByProfileIdNative(id);
  }

  public List<RowInfo> updateRowInfo(Integer id, RowInfoRequest request) {
    rowInfoRepository.updateRowInfo(id, request.getRowIndex(), request.getWidgets());
    return getRowInfoById(id);
  }

  public void deleteRowInfo(Integer id, Integer rowIndex) {
    rowInfoRepository.delete(RowInfo.builder().profileId(id).rowIndex(rowIndex).build());
  }
}
