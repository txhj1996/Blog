package com.lrm.service;

import com.lrm.po.Type;


import java.util.List;

/**
 * Created by limi on 2017/10/16.
 */
public interface TypeService {

    void saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String name);

//    Page<Type> listType(Pageable pageable);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);

    void updateType(Long id,Type type);

    void deleteType(Long id);
}
