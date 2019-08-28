package com.weatherapi.mocks.spy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

/**
 * Created by alpa on 2019-08-22
 */
public class SpyWithStub {

    @Spy
    private List<String> spyList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByIndexEqualToTest() {
        String item = "trololo";
        doReturn(item).when(spyList).get(1);

        assertThat(spyList.get(1)).isEqualTo(item);
    }

    @Test
    public void getByIndexNotEqualToTest() {
        String item = "trololo";
        doReturn(item).when(spyList).get(1);

        assertThat(spyList.get(1)).isNotEqualTo("blabla");
    }

    @Test
    public void containsItemTest() {
        String item = "trololo";
        doReturn(true).when(spyList).contains(item);

        assertThat(spyList.contains(item)).isTrue();
    }

    @Test
    public void notContainsItemTes() {
        String item = "trololo";
        doReturn(true).when(spyList).contains(item);

        assertThat(spyList.contains("blabla")).isFalse();
    }

    @Test
    public void addItemTest() {
        String item = "trololo";
        doAnswer(invocation -> null).when(spyList).add(1, item);

        spyList.add(1, item);
    }

    @Test
    public void addItemNotStubbedTest() {
        String item = "trololo";

        assertThatThrownBy(() -> spyList.add(1, item))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index: 1, Size: 0");
        ;
    }
}
