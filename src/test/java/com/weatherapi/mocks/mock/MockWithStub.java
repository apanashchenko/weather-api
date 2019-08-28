package com.weatherapi.mocks.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

/**
 * Created by alpa on 2019-08-22
 */
public class MockWithStub {

    @Mock
    private List<String> mockedList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getByIndexTestWhen() {
        String item = "blabla";
        when(mockedList.get(0)).thenReturn(item);

        assertThat(mockedList.get(0)).isEqualTo(item);
    }

    @Test
    public void getByIndexTestDoReturn() {
        String item = "blabla";
        doReturn(item).when(mockedList).get(0);

        assertThat(mockedList.get(0)).isEqualTo(item);
    }

    @Test
    public void getByIndexTestDoReturnFailed() {
        doReturn(10).when(mockedList).get(0);

        assertThatThrownBy(() -> mockedList.get(0))
                .isInstanceOf(ClassCastException.class)
                .hasMessage("java.lang.Integer cannot be cast to java.lang.String");
    }

    @Test
    public void getSizeTest() {
        int value = 100;
        when(mockedList.size()).thenReturn(value);

        assertThat(mockedList.size()).isEqualTo(value);
    }

    @Test
    public void removeItemTest() {
        String item = "blabla";
        when(mockedList.remove(item)).thenReturn(true);

        assertThat(mockedList.remove(item)).isTrue();

        String removeItem = "remove";
        assertThat(mockedList.remove(removeItem)).isFalse();

        when(mockedList.remove(removeItem)).thenReturn(true);

        assertThat(mockedList.remove(removeItem)).isTrue();
    }

    @Test
    public void containsItemTest() {
        String item = "trololo";
        when(mockedList.contains(item)).thenReturn(true);

        assertThat(mockedList.contains(item)).isTrue();

        mockedList.add("contains");
        assertThat(mockedList.contains("contains")).isFalse();
    }
}
