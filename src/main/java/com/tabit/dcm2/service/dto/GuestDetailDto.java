package com.tabit.dcm2.service.dto;

import java.time.LocalDate;
import java.util.List;

public class GuestDetailDto {

    private StayDto stayDto;
    private List<StaySummary> staysHistory;

    private class StaySummary{

        private Long id;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;

        public StaySummary(Long id, LocalDate checkInDate, LocalDate checkOutDate) {
            this.id = id;
            this.checkInDate = checkInDate;
            this.checkOutDate = checkOutDate;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public LocalDate getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
        }

        public LocalDate getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
        }
    }

    public StayDto getStayDto() {
        return stayDto;
    }

    public void setStayDto(StayDto stayDto) {
        this.stayDto = stayDto;
    }

    public List<StaySummary> getStaysHistory() {
        return staysHistory;
    }

    public void setStaysHistory(List<StaySummary> staysHistory) {
        this.staysHistory = staysHistory;
    }
}
