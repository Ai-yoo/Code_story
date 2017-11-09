package com_basedao;


import java.util.List;

public interface BaseDao<T> {

    void update(T t, String password, int id);

    void insert(T t);

    void delete(T t, int id);

    List selectAll(T t);

    List selectOne(T t,int id);
}
