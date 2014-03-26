package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.AccountMapper;
import com.trailblazers.freewheelers.mappers.CountryMapper;
import com.trailblazers.freewheelers.mappers.MyBatisUtil;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.service.CountryService;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;


public class CountryServiceImpl implements CountryService {


    private final CountryMapper countryMapper;
    public CountryServiceImpl() {
        this(MyBatisUtil.getSqlSessionFactory().openSession());
    }

    public CountryServiceImpl(SqlSession sqlSession) {
        this.countryMapper = sqlSession.getMapper(CountryMapper.class);
    }

    @Override
    public List<Country> findAll() {
        return countryMapper.findAll();
    }

    @Override
    public Country get(long country_id) {
        return countryMapper.getById(country_id);
    }

}
