package model;

import model.mocks.MockPasswordDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOTests {
    private MockPasswordDAO mockDao;
    private Password testPassword;

    @BeforeEach
    void setUp() {
        mockDao = new MockPasswordDAO();
        testPassword = new Password(1, "test", "password");
    }

    @Test
    void testAddPassword() {
        mockDao.addPassword(testPassword);
        assertTrue(mockDao.addCalled);
        assertEquals(1, mockDao.getAllPasswords().size());
    }

    @Test
    void testUpdatePassword() {
        mockDao.addPassword(testPassword);
        testPassword.setPassword("newpassword");
        mockDao.updatePassword(testPassword);
        assertTrue(mockDao.updateCalled);
        assertEquals("newpassword", mockDao.getAllPasswords().get(0).getPassword());
    }

    @Test
    void testDeletePassword() {
        mockDao.addPassword(testPassword);
        mockDao.deletePassword(testPassword.getId());
        assertTrue(mockDao.deleteCalled);
        assertEquals(0, mockDao.getAllPasswords().size());
    }
}