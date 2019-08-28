package com.weatherapi.mocks.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * Created by alpa on 2019-08-22
 */
public class SpyListExample {

    @Spy
    private List<String> spyList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAndGetByIndexTest() {
        spyList.add("234");
        assertThat(spyList.get(0)).isEqualTo("234");
    }

    @Test
    public void getByNegativeIndexTest() {
        assertThatThrownBy(() -> spyList.get(-35))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("-35");
    }
}
