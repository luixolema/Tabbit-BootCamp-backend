package com.tabit.dcm2.controller.util;

import com.tabit.dcm2.entity.Activity;
import com.tabit.dcm2.service.dto.ActivityDto;

public class ActivityMapper {

    public static ActivityDto activityToActivityDto(Activity activity) {
        ActivityDto activityDto = new ActivityDto();

        activityDto.setDate(activity.getDate());
        activityDto.setId(activity.getId());
        activityDto.setPos(activity.getPos());
        activityDto.setPrice(activity.getPrice());
        activityDto.setType(activity.getType());

        return activityDto;
    }
}
