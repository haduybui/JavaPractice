package com.home.duy.practice;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
//import org.mockito.Matchers;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.*;
//import static org.mockito.Matchers.any;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


@RunWith(PowerMockRunner.class)
@PrepareForTest({ClassWithStaticMethod.class, SubjectUnderTesting.class})
public class TestHamcrestAny {

    @Test
    public void test1() {
        // When
        PowerMockito.mockStatic(ClassWithStaticMethod.class);
        BDDMockito.given(ClassWithStaticMethod.duyName()).willReturn("THIS IS SPARTA");

        // Thi mock only uses mockito any()
        when(ClassWithStaticMethod.justForHamcrest(any(Integer.class))).thenReturn(5);

        // Mockito mock
        SubjectUnderTesting sut = new SubjectUnderTesting();
        String actualDuyNameOutput = sut.getDuyName();
        assertEquals("THIS IS SPARTA", actualDuyNameOutput);
        //Ham crest is only for this kind of situation - Matchers.any(String.class)
        assertThat(actualDuyNameOutput, Matchers.is(Matchers.any(String.class)));

        // PowerMock mock
        String actualThaoNameOutput = ClassWithStaticMethod.thaoName("asdasda");
        assertEquals("Hahaha, this is no power mock",actualThaoNameOutput);

        // Hamcrest Matchers

    }
}
