package ru.job4j.early;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PasswordValidatorTest {

    @Test
    public void whenIsNullThenThrowException() {
        String password = null;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Пароль не может быть null!");
    }

    @Test
    public void whenTooShort() {
        String password = "aX_9-tz";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Длина пароля должна находиться в диапазоне [8, 32]");
    }

    @Test
    public void whenTooLong() {
        String password = "Dw\";USF]vJrOch~0tceZd-fi8\"mqJ4szZ5L";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Длина пароля должна находиться в диапазоне [8, 32]");
    }

    @Test
    public void whenHasNoUpperCase() {
        String password = "u6bl4i?u*c}/\"kb3";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Пароль должен содержать хотя бы один символ в верхнем регистре");
    }

    @Test
    public void whenHasNoLowerCase() {
        String password = "QY\"7%+6A5B8O<M4D";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Пароль должен содержать хотя бы один символ в нижнем регистре");
    }

    @Test
    public void whenHasNoDigits() {
        String password = "eexDzG[LaCfIDN-I";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Пароль должен содержать хотя бы одну цифру");
    }

    @Test
    public void whenHasNoSpecialChars() {
        String password = "fUrX9qJshDPJ11Dl";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Пароль должен содержать хотя бы один специальный символ");
    }

    @Test
    public void whenHasBanWords() {
        String password = "!S)2q.s{NXHqWeRtYG]\"zpT";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo("Пароль не должен содержать запрещенных/небезопасных последовательностей символов");
    }

    @Test
    public void whenIsValid() {
        String password = ">v8i7?ZE%i;;pL]7";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo(password);
    }
}