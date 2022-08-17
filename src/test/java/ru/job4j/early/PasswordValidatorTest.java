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
    public void whenTooShortThenThrowException() {
        String password = "aX_9-tz";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Длина пароля должна находиться в диапазоне [8, 32]");
    }

    @Test
    public void whenTooLongThenThrowException() {
        String password = "Dw\";USF]vJrOch~0tceZd-fi8\"mqJ4szZ5L";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Длина пароля должна находиться в диапазоне [8, 32]");
    }

    @Test
    public void whenHasNoUpperCaseThenThrowException() {
        String password = "u6bl4i?u*c}/\"kb3";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Пароль должен содержать хотя бы один символ в верхнем регистре");
    }

    @Test
    public void whenHasNoLowerCaseThenThrowException() {
        String password = "QY\"7%+6A5B8O<M4D";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );

    }

    @Test
    public void whenHasNoDigitsThenThrowException() {
        String password = "eexDzG[LaCfIDN-I";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Пароль должен содержать хотя бы одну цифру");
    }

    @Test
    public void whenHasNoSpecialCharsThenThrowException() {
        String password = "fUrX9qJshDPJ11Dl";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Пароль должен содержать хотя бы один специальный символ");
    }

    @Test
    public void whenHasBanWordsThenThrowException() {
        String password = "!S)2q.s{NXHqWeRtYG]\"zpT";
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    PasswordValidator.validate(password);
                }
        );
        assertThat(exception.getMessage()).isEqualTo("Пароль не должен содержать запрещенных/небезопасных последовательностей символов");
    }

    @Test
    public void whenIsValid() {
        String password = ">v8i7?ZE%i;;pL]7";
        String result = PasswordValidator.validate(password);
        assertThat(result).isEqualTo(password);
    }
}