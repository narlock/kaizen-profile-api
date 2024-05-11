package com.narlock.kaizenprofileapi.controller;

import com.narlock.kaizenprofileapi.model.Profile;
import com.narlock.kaizenprofileapi.model.ProfileRequest;
import com.narlock.kaizenprofileapi.model.ProfileResponse;
import com.narlock.kaizenprofileapi.model.error.BadRequestException;
import com.narlock.kaizenprofileapi.service.KaizenProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class KaizenProfileController {

  private final KaizenProfileService kaizenProfileService;

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ProfileResponse getProfileById(@PathVariable("id") Integer id) {
    return kaizenProfileService.getKaizenProfileById(id);
  }

  /**
   * Creates a Profile and Health entry for a Kaizen Profile
   * @param request
   * @return
   */
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ProfileResponse createProfile(@Valid @RequestBody ProfileRequest request) {
    if(request.getProfileId() != null) {
      throw new BadRequestException("Profile ID must be null when creating a new profile");
    }
    return kaizenProfileService.createProfile(request);
  }

  /**
   * Updates a Profile and Health entry for a Kaizen Profile
   * @param request
   * @return
   */
  @PutMapping
  @ResponseStatus(HttpStatus.OK)
  public ProfileResponse updateProfile(@Valid @RequestBody ProfileRequest request) {
    if(request.getProfileId() == null) {
      throw new BadRequestException("Profile ID must NOT be null when updating an existing profile");
    }
    return kaizenProfileService.updateProfile(request);
  }
}
