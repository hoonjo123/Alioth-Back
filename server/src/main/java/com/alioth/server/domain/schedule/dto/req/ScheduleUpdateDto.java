package com.alioth.server.domain.schedule.dto.req;

import com.alioth.server.domain.schedule.domain.ScheduleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ScheduleUpdateDto(
        Long scheduleId,
        @NotNull(message="시작시간을 입력해주세요.")
        LocalDateTime scheduleStartTime,
        LocalDateTime scheduleEndTime,
        @NotNull(message="일정 상세 정보를 입력해주세요.")
        String scheduleNote,
        ScheduleType scheduleType,
        String allDay
){}
