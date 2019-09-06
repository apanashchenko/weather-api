package com.weatherapi.mocks.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doAnswer;

/**
 * Created by alpa on 2019-08-22
 */
public class MockListExample {

    @Mock
    private List<String> mockedList;

    private List<String> realList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        realList = new ArrayList<>();
    }

    @AfterEach
    public void tearDown() {
        realList = null;
    }

    @Test
    public void addAndGetByIndexMockTest() {
        String item = "234";
        mockedList.add(item);
        assertThat(mockedList.get(0)).isEqualTo(item);
    }

    @Test
    public void addAndGetByIndexRealTest() {
        String item = "234";
        realList.add(item);
        assertThat(realList.get(0)).isEqualTo(item);
    }

    @Test
    public void getByNegativeIndexMockTest() {
        assertThat(mockedList.get(-35)).isNull();
    }

    @Test
    public void getByNegativeIndexRealTest() {
        assertThatThrownBy(() -> realList.get(-35))
                .isInstanceOf(ArrayIndexOutOfBoundsException.class).hasMessage("-35");
    }

    @Test
    public void getSizeMockTest() {
        mockedList.add("1");
        mockedList.add("2");
        mockedList.add("3");
        assertThat(mockedList.size()).isEqualTo(0);
    }

    @Test
    public void getSizeRealTest() {
        realList.add("1");
        realList.add("2");
        realList.add("3");
        assertThat(realList.size()).isEqualTo(3);
    }

    @Test
    public void getByAnyIndexMockTest() {
        assertThat(mockedList.get(5)).isNull();
        assertThat(mockedList.get(100)).isNull();
        assertThat(mockedList.get(9379992)).isNull();
    }

    @Test
    public void getByAnyIndexRealTest() {
        assertThatThrownBy(() -> realList.get(5))
                .isInstanceOf(IndexOutOfBoundsException.class).hasMessageContaining("Index: 5, Size: 0");
    }

    @Test
    public void containsItemMockTest() {
        String item = "blabla";
        mockedList.add(item);
        assertThat(mockedList.contains(item)).isFalse();
    }

    @Test
    public void containsItemRealTest() {
        String item = "blabla";
        realList.add(item);
        assertThat(realList.contains(item)).isTrue();
    }

    @Test
    public void sizeTest() {
        String item = "blabla";
        String item2 = "trololo";
        String item3 = "hophey";
        realList.add(item);
        realList.add(item2);
        realList.add(item3);
        assertThat(realList.size()).isEqualTo(3);
    }

    @Test
    public void doAnswerTest() {
        String item = "blabla";
        String item2 = "trololo";
        doAnswer(invocation -> {
            int argument = invocation.getArgument(0);
            return argument < 5 ? item : item2;
        }).when(mockedList).get(anyInt());

        assertThat(mockedList.get(3)).isEqualTo(item);
        assertThat(mockedList.get(8)).isEqualTo(item2);
    }
}
