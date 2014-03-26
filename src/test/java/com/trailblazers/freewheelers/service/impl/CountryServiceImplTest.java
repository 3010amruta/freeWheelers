package com.trailblazers.freewheelers.service.impl;

import com.trailblazers.freewheelers.mappers.CountryMapper;
import com.trailblazers.freewheelers.model.Country;
import com.trailblazers.freewheelers.service.CountryService;
import org.apache.ibatis.session.SqlSession;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.thoughtworks.selenium.SeleneseTestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by abhishep on 3/25/14.
 */
public class CountryServiceImplTest {

    private CountryServiceImpl countryService;
    private SqlSession sqlSession;
    private CountryMapper countryMapperMock;

    @Before
    public void setUp() throws Exception {
        this.sqlSession = mock(SqlSession.class);
        this.countryMapperMock = mock(CountryMapper.class);
        when(sqlSession.getMapper(CountryMapper.class)).thenReturn(countryMapperMock);
        this.countryService = new CountryServiceImpl(sqlSession);
    }

    @Test
    public void shouldSetCountryMapperAsMapper() throws Exception {
        verify(sqlSession).getMapper(CountryMapper.class);
    }

    @Test
    public void shouldReturnListOfCountries() throws Exception {
        countryService.findAll();
        verify(countryMapperMock).findAll();
    }

    @Test
    public void shouldReturnCountry() throws Exception {
        countryService.get(1);
        verify(countryMapperMock).getById(1);
    }
}
