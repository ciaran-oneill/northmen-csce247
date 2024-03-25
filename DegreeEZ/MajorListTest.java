package DegreeEZ;

import java.util.UUID;

public class MajorListTest {

    public static void main(String[] args) {
        testSingletonBehavior_EnsuresOnlyOneInstanceCreated();
        testGetMajorByUUID_WithExistingID_ReturnsCorrectMajor();
    }

    private static void assertTrue(boolean condition, String testName) {
        if (condition) {
            System.out.println(testName + ": Test passed");
        } else {
            System.out.println(testName + ": Test failed");
        }
    }

    private static void assertNull(Object object, String testName) {
        assertTrue(object == null, testName);
    }

    private static void assertNotNull(Object object, String testName) {
        assertTrue(object != null, testName);
    }

    private static void testSingletonBehavior_EnsuresOnlyOneInstanceCreated() {
        MajorList instanceOne = MajorList.getInstance();
        MajorList instanceTwo = MajorList.getInstance();
        assertTrue(instanceOne == instanceTwo, "Singleton Test");
    }

    private static void testGetMajorByUUID_WithExistingID_ReturnsCorrectMajor() {
        UUID testUUID = UUID.fromString("27212152-de50-4c17-ad9b-6303d934cf80");
        Major testMajor = MajorList.getMajorByUUID(testUUID);
        assertNotNull(testMajor, "Get Major by UUID Test");
    }

}
