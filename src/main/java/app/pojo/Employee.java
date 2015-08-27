package app.pojo;

public class Employee {
    private int id;
    private String name;
    private int age;
    private String address;
    private float salary;

    public Employee(int id, String name, int age, String address, float salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }

    public Employee(String name, int age, String address, float salary) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.salary = salary;
    }    
    
    @Override
    public String toString() {
        return name + " ("+id+") "+address;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public float getSalary() {
        return salary;
    }
    
    
}
