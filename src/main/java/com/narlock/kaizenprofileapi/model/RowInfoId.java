package com.narlock.kaizenprofileapi.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RowInfoId implements Serializable {
    private Integer profileId;
    private Integer rowIndex;
}