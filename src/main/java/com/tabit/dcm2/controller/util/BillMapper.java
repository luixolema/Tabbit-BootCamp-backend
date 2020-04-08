package com.tabit.dcm2.controller.util;

import com.tabit.dcm2.entity.Activity;
import com.tabit.dcm2.entity.Bill;
import com.tabit.dcm2.entity.Stay;
import com.tabit.dcm2.service.dto.ActivityDto;
import com.tabit.dcm2.service.dto.BillDto;

import java.util.List;
import java.util.stream.Collectors;

public class BillMapper {

    public static BillDto billToBillDto(Bill bill) {
        BillDto billDto = new BillDto();
        Stay stay = bill.getStay();

        billDto.setCode(bill.getCode());

        billDto.setFirstName(stay.getFirstName());
        billDto.setLastName(stay.getLastName());
        billDto.setLastName(stay.getLastName());
        billDto.setStreet(stay.getStreet());
        billDto.setPostcode(stay.getPostcode());
        billDto.setCity(stay.getCity());
        billDto.setCountry(stay.getCountry());
        billDto.setCountry(stay.getCountry());

        billDto.setArriveDate(stay.getArriveDate());
        billDto.setLeaveDate(stay.getLeaveDate());
        billDto.setCheckInDate(stay.getCheckInDate());
        billDto.setCheckOutDate(stay.getCheckOutDate());
        billDto.setBoxNumber(stay.getBoxNumber());

        List<ActivityDto> activityDtoList = stay.getActivities().stream().map(ActivityMapper::activityToActivityDto).collect(Collectors.toList());
        billDto.setActivities(activityDtoList);

        Double total = stay.getActivities().stream().mapToDouble(Activity::getPrice).sum();
        billDto.setTotal(total);

        return billDto;
    }
}
