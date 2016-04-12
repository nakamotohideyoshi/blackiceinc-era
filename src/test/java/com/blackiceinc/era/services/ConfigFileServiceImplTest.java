package com.blackiceinc.era.services;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static org.junit.Assert.assertEquals;

public class ConfigFileServiceImplTest {

    private ConfigFileService configFileService;


    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);


    }

    @Test
    public void decimalTest(){
        double n1 = 1500000000001d;
        double n2 = 400000000000d;
        double n3 = 0.500001d;
        double n4 = 0.2d;
        double n5 = 0.25d;

        NumberFormat f = NumberFormat.getInstance();
        f.setGroupingUsed(false);

        assertEquals("1500000000001", new BigDecimal(Double.toString(n1)).toPlainString());
        assertEquals("400000000000", new BigDecimal(Double.toString(n2)).toPlainString());
        assertEquals("0.500001", new BigDecimal(Double.toString(n3)).toPlainString());
        assertEquals("0.2", new BigDecimal(Double.toString(n4)).toPlainString());
        assertEquals("0.25", new BigDecimal(Double.toString(n5)).toPlainString());
    }

}