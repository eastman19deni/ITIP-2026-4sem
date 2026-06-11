package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

public class SpyExampleTest {

    @Test
    void shouldVerifySpyMethodCall() {
        List<String> list = new ArrayList<>();
        List<String> spyList = spy(list);

        spyList.add("Spring");
        spyList.add("Boot");

        verify(spyList).add("Spring");
        verify(spyList).add("Boot");
        assertEquals(2, spyList.size());
    }
}