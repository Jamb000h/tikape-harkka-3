package app.pojo;

public class Employee {
    int id;
    String name;
    int age;
    String address;
    float salary;

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
    

}
