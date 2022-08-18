package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class JobAscByNameTest {

    @Test
    public void whenComparatorByNameAsc() {
        Comparator<Job> jobAscByName = new JobAscByName();
        int result = jobAscByName.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(result).isGreaterThan(0);
    }
}