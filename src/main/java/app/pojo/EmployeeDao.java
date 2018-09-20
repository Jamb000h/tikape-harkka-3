package app.pojo;

import app.db.Database;
import java.sql.SQLException;
import java.util.List;

public class EmployeeDao {

    private Database db;

    public EmployeeDao(Database db) {
        this.db = db;
    }

    public void create(Employee company) throws SQLException {
        String sql = "INSERT INTO EMPLOYEE "
                + "(NAME,AGE,ADDRESS,SALARY) VALUES ("
                + s(company.getName()) + ", "
                + company.getAge() + ", "
                + s(company.getAddress()) + ", "
                + company.getSalary() + " );";

        db.update(sql);
    }

    private String s(String s) {
        return "'" + s + "'";
    }

    public List<Employee> findAll() throws SQLException {
        return db.queryAndCollect("SELECT * FROM EMPLOYEE;", rs -> {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getFloat("salary"));
        });
    }
    
    public List<Employee> findAllWithSalaryAtLeast(double palkka) throws SQLException {
        return db.queryAndCollect("SELECT * FROM EMPLOYEE WHERE salary >=" + palkka + ";", rs -> {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getFloat("salary"));
        });
    }
    
    public List<Employee> findAmmattiryhmaWithName(String name) throws SQLException {
        return db.queryAndCollect("SELECT * FROM EMPLOYEE WHERE name LIKE '%" + name + "%' ORDER BY salary DESC;", rs -> {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getFloat("salary"));
        });
    }

    public Employee find(int id) throws SQLException {
        List<Employee> matches = db.queryAndCollect("SELECT * FROM EMPLOYEE WHERE ID=" + id, rs -> {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getFloat("salary"));
        });

        if (matches.isEmpty()) {
            return null;
        }

        return matches.get(0);
    }
}
