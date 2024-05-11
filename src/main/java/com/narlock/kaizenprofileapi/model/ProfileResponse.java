package com.narlock.kaizenprofileapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfileResponse {
    private Profile profile;
    private Health health;
    private List<RowInfo> rowInfoList;
}
