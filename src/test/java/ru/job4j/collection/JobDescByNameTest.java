package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class JobDescByNameTest {

    @Test
    public void whenComparatorByNameDesc() {
        Comparator<Job> jobDescByName = new JobDescByName();
        int result = jobDescByName.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(result).isLessThan(0);
    }
}