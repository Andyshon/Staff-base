public class UserData {
    private String name;
    private String specialty;
    private int salary;

    UserData(String name, String specialty, int salary){
        this.name = name;
        this.specialty = specialty;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getSalary() {
        return salary;
    }
}