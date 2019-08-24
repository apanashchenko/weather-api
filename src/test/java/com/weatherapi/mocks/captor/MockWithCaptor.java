package com.weatherapi.mocks.captor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Created by alpa on 2019-08-22
 */
public class MockWithCaptor {

    @Mock
    private List<String> mockedList;

    @Captor
    private ArgumentCaptor argCaptor;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void addItemTestSuccess() {
        String item = "blabla";
        mockedList.add(item);
        verify(mockedList).add((String) argCaptor.capture());

        assertThat(argCaptor.getValue()).isEqualTo(item);
    }

    @Test
    public void addItemTestFail() {
        String item = "blabla";
        mockedList.add(item);
        verify(mockedList).add((String) argCaptor.capture());

        assertThat(argCaptor.getValue()).isEqualTo("trololo");
    }
}
