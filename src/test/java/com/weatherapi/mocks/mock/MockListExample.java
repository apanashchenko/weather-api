package com.weatherapi.mocks.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by alpa on 2019-08-22
 */
public class MockListExample {

    @Mock
    private List<String> mockedList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addAndGetByIndexTest() {
        mockedList.add("234");
        assertThat(mockedList.get(0)).isNull();
    }

    @Test
    public void getByNegativeIndexTest() {
        assertThat(mockedList.get(-35)).isNull();
    }

    @Test
    public void getSizeTest() {
        mockedList.add("1");
        mockedList.add("2");
        mockedList.add("3");
        assertThat(mockedList.size()).isEqualTo(0);
    }

    @Test
    public void getByAnyIndexTest() {
        assertThat(mockedList.get(5)).isNull();
        assertThat(mockedList.get(100)).isNull();
        assertThat(mockedList.get(9379992)).isNull();
    }

    @Test
    public void removeItemTest() {
        assertThat(mockedList.remove(1)).isNull();
        assertThat(mockedList.remove("kill me")).isFalse();
    }

    @Test
    public void containsItemTest() {
        assertThat(mockedList.contains("blabla")).isFalse();
    }
}
