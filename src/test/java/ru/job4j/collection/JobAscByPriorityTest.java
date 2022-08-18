package ru.job4j.collection;

import org.junit.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;

public class JobAscByPriorityTest {

    @Test
    public void whenComparatorByPriorityAsc() {
        Comparator<Job> jobAscByPriority = new JobAscByPriority();
        int result = jobAscByPriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(result).isLessThan(0);
    }
}