package ru.job4j.collection;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanov"
        );
        assertThat(rst).isEqualTo(0);
    }

    @Test
    public void whenLeftLessThanRightResultShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Ivanov",
                "Ivanova"
        );
        assertThat(rst).isLessThan(0);
    }

    @Test
    public void whenLeftGreaterThanRightResultShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Ivanova"
        );
        assertThat(rst).isGreaterThan(0);
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst).isGreaterThan(0);
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst).isLessThan(0);
    }

    @Test
    public void whenUpperCaseVersusLowerCaseThenNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "ABCDEFGH",
                "abcdefgh"
        );
        assertThat(rst).isLessThan(0);
    }

    @Test
    public void whenLowercaseVersusUpperCaseThenPositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "abcdefgh",
                "ABCDEFGH"
        );
        assertThat(rst).isGreaterThan(0);
    }

    @Test
    public void whenAnythingVersusEmptyThenPositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "ABCDEFGH",
                ""
        );
        assertThat(rst).isGreaterThan(0);
    }

    @Test
    public void whenEmptyVersusAnythingThenNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "",
                "ABCDEFGH"
        );
        assertThat(rst).isLessThan(0);
    }

    @Test
    public void whenEmptyVersusEmptyThenZero() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "",
                ""
        );
        assertThat(rst).isEqualTo(0);
    }
}