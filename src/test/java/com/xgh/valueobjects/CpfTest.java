package com.xgh.valueobjects;

import com.xgh.model.command.valueobjects.Cpf;
import org.junit.Test;

public class CpfTest {

    @Test
    public void testValid() {
        new Cpf("38944154015");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalid() {
        new Cpf("12345678901");
    }

}
