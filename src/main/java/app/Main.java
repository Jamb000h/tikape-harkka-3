package app;

import app.db.Database;
import app.pojo.Employee;
import app.pojo.EmployeeDao;

public class Main {

    public static void main(String args[]) throws Exception {
        Database database = new Database("test2.db");

        EmployeeDao employeeDao = new EmployeeDao(database);

        Employee e = new Employee("Pekka", 37, "Haaga", 3560.0f);
        employeeDao.create(e);

        for (Employee employee : employeeDao.findAll()) {
            System.out.println(employee);
        }

    }
}
