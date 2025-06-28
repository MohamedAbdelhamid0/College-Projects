package org.example.model;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ManagerTest {
    private static Manager manager;
    private static Long id;
    private static String name;
    private static String email;
    private static String Password;
    private static String department;
    private static double salary;

    @BeforeAll
    public static void setUp() {
        manager = new Manager(02l, name, email, Password, department,salary);
    }
    @Test
    @Order(1)
    public void testgetid() {
        long id2 = 0023l;
        manager.setId(id2);
        assertEquals(id2, manager.getId());//check for get id working properly
    }
    @Test
    @Order(2)
    public void testgetname() {
        String name2 = "Mohamed";
        manager.setName(name2);
        assertEquals(name2, manager.getName());
    }
    @Test
    @Order(3)
    public void testgetemail() {
        String email2 = "mohamed@gmail.com";
        manager.setEmail(email2);
        assertEquals(email2, manager.getEmail());
    }
    @Test
    @Order(4)
    public void testgetPassword() {
        String password2 = "Mohamed2004!";
        manager.setPassword("Mohamed2004!");
        assertEquals(password2, manager.getPassword());
    }
    @Test
    @Order(5)
    public void testgetdepartment() {
        String department2 = "Sales";
        manager.setDepartment(department2);
        assertEquals(department2, manager.getDepartment());
    }
    @Test
    @Order(6)
    public void testgetsalary() {
        double salary2 = 8000;
        manager.setSalary("HR");
        assertEquals(salary2, manager.getSalary());//check if it returns the correct salary 8000
    }
    @Test
    public void testwrongdepartment() {
        String department2 = "Development";
        //test department that is not available
        assertThrows(IllegalArgumentException.class, () -> {
            manager.setDepartment(department2);
        });
        // test department is null
        assertThrows(IllegalArgumentException.class, () -> {
            manager.setDepartment(null);
        });
        //test valid department
        assertDoesNotThrow(
                () -> manager.setDepartment("Sales")
        );
    }
    @Test
    public void testwrongsalary() {
        //test with null department
        assertThrows(IllegalArgumentException.class, () -> {
            manager.setSalary(null);
        });
        //test with wrong department
        assertThrows(IllegalArgumentException.class, () -> {
            manager.setSalary("Development");
        });
        //test with valid department
        assertDoesNotThrow(
                () -> manager.setSalary("HR")
        );
    }
    @AfterAll
    public static void tearDown() {
        salary = 0.0;  // Set a default value
        department = null;
        name = null;
        email = null;
        Password = null;
    }


}
