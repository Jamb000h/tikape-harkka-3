package app;

import app.db.Database;
import app.pojo.Employee;
import app.pojo.EmployeeDao;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) throws Exception {
        Database database = new Database("palkkatilastot.db");

        EmployeeDao employeeDao = new EmployeeDao(database);
        
        Scanner lukija = new Scanner(System.in);
        
        System.out.println("Anna ammattiryhmä: ");
        String ammattiRyhma = lukija.nextLine();
        
        List<Employee> employees = employeeDao.findAmmattiryhmaWithName(ammattiRyhma);
        
        OptionalDouble average = employees.stream().mapToDouble(Employee::getSalary).average();
        double asDouble = average.getAsDouble();
        
        for (Employee employee : employees.subList(0, 5)) {
            System.out.println(employee.getName() + "\t" + employee.getSalary());
        }
        
        System.out.println("Palkkojen keskiarvo: " + asDouble);

//        Employee e = new Employee("Ada", 42, "Lontoo", 666f);
//        employeeDao.create(e);
//
//        for (Employee employee : employeeDao.findAll()) {
//            System.out.println(employee);
//        }
//        System.out.println("Työntekijä tunnuksella 1: ");
//        Employee e2 = employeeDao.find(1);
//        System.out.println(e2);
//        
//        System.out.println("Palkka yli 20000: ");
//        for (Employee e1: employeeDao.findAllWithSalaryAtLeast(20000)) {
//            System.out.println(e1.getName() + "\t" + e1.getSalary());
//        }


    }
}
