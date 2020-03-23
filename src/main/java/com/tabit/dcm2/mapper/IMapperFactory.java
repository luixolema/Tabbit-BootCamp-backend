package com.tabit.dcm2.mapper;

public interface IMapperFactory {
    IMapper getMapper(Class source, Class target) throws Exception;
}
