package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestStatus {
    private Status status;

    @Test
    void getDescription() {
        for (Status s : Status.values()) {
            status = s;
            assertEquals(s, status);
            assertEquals(s.getDescription(), status.getDescription());
            assertEquals(s.getDescription(), status.toString());
        }
    }

}