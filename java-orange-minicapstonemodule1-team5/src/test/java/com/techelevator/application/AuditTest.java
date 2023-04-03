package com.techelevator.application;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class AuditTest extends TestCase {

    private Audit audit;

    @Before
    public void setUp() {
        audit = new Audit(BigDecimal.ZERO);
    }

    @Test
    public void testAuditLog() {
        BigDecimal amount = new BigDecimal("1.50");
        audit.auditLog("Test message", amount);
        assertEquals(amount, audit.getBalance());
    }

    @Test
    public void testSetBalance() {
        BigDecimal amount = new BigDecimal("2.00");
        audit.setBalance(amount);
        assertEquals(amount, audit.getBalance());
    }

}