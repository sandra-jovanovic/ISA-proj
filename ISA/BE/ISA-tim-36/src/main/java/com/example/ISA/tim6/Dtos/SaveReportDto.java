package com.example.ISA.tim6.Dtos;

import java.util.List;

public record SaveReportDto(Long id,
                            boolean conditionsNotFulfilled,
                            boolean didNotShow,
                            String details,
                            List<SaveEquipmentDto> spentEquipment,

                            List<SaveBloodTypesDto> spentBloodTypes) {
}
