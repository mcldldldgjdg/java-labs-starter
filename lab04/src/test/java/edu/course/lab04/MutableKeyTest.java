
package edu.course.lab04;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.Test;

class MutableKeyTest {

    private static final class MutableIssueId {

        private String projectCode;
        private final int number;

        MutableIssueId(String projectCode, int number) {
            this.projectCode = projectCode;
            this.number = number;
        }

        void changeProjectCode(String newCode) {
            this.projectCode = newCode;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (!(obj instanceof MutableIssueId)) {
                return false;
            }

            MutableIssueId other = (MutableIssueId) obj;

            return number == other.number
                    && projectCode.equals(other.projectCode);
        }

        @Override
        public int hashCode() {
            return 31 * projectCode.hashCode() + number;
        }
    }

    @Test
    void modifyingKeyBreaksLookup() {
        Map<MutableIssueId, String> map =
                new HashMap<>();

        MutableIssueId key =
                new MutableIssueId("APP", 1);

        map.put(key, "Задача");

        assertEquals("Задача", map.get(key));

        int originalHash = key.hashCode();

        key.changeProjectCode("CHANGED");

        assertNotEquals(originalHash, key.hashCode());
        assertNull(map.get(key));
        assertEquals(1, map.size());
    }
}
