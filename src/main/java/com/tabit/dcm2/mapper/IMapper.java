package com.tabit.dcm2.mapper;

import java.util.List;

public interface IMapper<T1,T2> {
    T2 map(T1 source);

    List<T2> map(List<T1> source);
}
