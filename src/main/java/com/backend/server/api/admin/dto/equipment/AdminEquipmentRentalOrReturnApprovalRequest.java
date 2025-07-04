package com.backend.server.api.admin.dto.equipment;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminEquipmentRentalOrReturnApprovalRequest {
    private List<Long> ids;
} 