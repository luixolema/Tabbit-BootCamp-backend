package com.tabit.dcm2.service.dto;

import com.tabit.dcm2.commons.AbstractBean;
import com.tabit.dcm2.commons.AbstractNonNullValidatingBeanBuilder;

public class ContextInfoDto extends AbstractBean {
    private String userName;
    private String diveCenterName;

    public String getUserName() {
        return userName;
    }

    public String getDiveCenterName() {
        return diveCenterName;
    }

    public static class Builder extends AbstractNonNullValidatingBeanBuilder<ContextInfoDto, ContextInfoDto.Builder> {

        public Builder() {
            super(new ContextInfoDto());
        }

        public ContextInfoDto.Builder withFirstName(String userName) {
            bean.userName = userName;
            return this;
        }

        public ContextInfoDto.Builder withDiveCenterName(String diveCenterName) {
            bean.diveCenterName = diveCenterName;
            return this;
        }
    }
}
