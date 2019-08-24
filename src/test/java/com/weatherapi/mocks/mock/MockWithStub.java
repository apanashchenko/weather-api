package com.weatherapi.mocks.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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
    public void getByIndexTest() {
        String item = "blabla";
        when(mockedList.get(0)).thenReturn(item);
        assertThat(mockedList.get(0)).isEqualTo(item);
    }

    @Test
    public void removeItemTest() {
        String item = "blabla";
        when(mockedList.remove(item)).thenReturn(true);
        assertThat(mockedList.remove(item)).isTrue();

        assertThat(mockedList.remove("remove item")).isTrue();
    }

    @Test
    public void containsItemTest() {
        String item = "blabla";
        when(mockedList.contains(item)).thenReturn(true);
        assertThat(mockedList.contains(item)).isTrue();

        assertThat(mockedList.contains("contains item")).isTrue();
    }
}
