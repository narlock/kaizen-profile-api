package com.narlock.kaizenprofileapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileRequest {
    private Integer profileId;

    @NotEmpty(message = "Name must not be empty")
    private String username;

    @NotNull(message = "Age must not be null")
    @Min(value = 1, message = "Age must be greater than or equal to 1")
    private Integer age;


    @NotNull(message = "Birthdate must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    private String imageUrl;
    private Integer xp;
    private Integer numRows;

    @Size(min = 4, max = 4, message = "PIN must be exactly 4 digits")
    private String pin;

    @NotNull(message = "Height must not be empty")
    private Double height;

    @NotNull(message = "Weight must not be empty")
    private Double weight;

    private Double goalWeight;
    private Double goalWater;

    @JsonIgnore
    public Profile getProfile() {
        Profile profile = new Profile();
        profile.setId(profileId);
        profile.setUsername(username);
        profile.setAge(age);
        profile.setBirthDate(birthDate);
        profile.setImageUrl(imageUrl);
        profile.setXp(xp);
        profile.setNumRows(numRows);
        profile.setPin(pin);
        return profile;
    }

    @JsonIgnore
    public Health getHealth() {
        Health health = new Health();
        health.setProfileId(profileId);
        health.setHeight(height);
        health.setWeight(weight);
        health.setGoalWater(goalWater);
        health.setGoalWeight(goalWeight);
        return health;
    }
}
