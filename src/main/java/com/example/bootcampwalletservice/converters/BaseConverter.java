package com.example.bootcampwalletservice.converters;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

public interface BaseConverter<E,D> {

    @Mapping(source = "createDate",target = "createDate")
    @Mapping(source = "lastModifiedDate",target = "lastModifiedDate")
    @Mapping(source = "version",target = "version")
    E convertEntity(D dto);
    @Mapping(source = "createDate",target = "createDate")
    @Mapping(source = "lastModifiedDate",target = "lastModifiedDate")
    @Mapping(source = "version",target = "version")
    D convertDto(E entity);
    List<E> convertEntities(List<D> dtoes);
    List<D> convertDtoes(List<E> entities);
}
