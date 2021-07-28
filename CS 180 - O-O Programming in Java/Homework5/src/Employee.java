import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;

/**
 * Created by Spencer on 6/26/2017.
 */
public class Employee {

    private int idNumber;
    private String name;
    private String position;
    private double salary;

    public Employee(String name, String position) {
        setName(name);
        setPosition(position);
        this.idNumber = generateIdNumber();
    }

    public int getIdNumber() {
        return idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && !name.equals("")) {
            this.name = name;
        }
        else this.name = "";
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        if (position != null && !position.equals("")){
            this.position = position;
            if (this.position.equals("Manager")) this.salary = 50000.00;
            else this.salary = 25000.00;
        }
        else {
            this.position = "";
            this.salary = 25000.00;
        }
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) this.salary = 0;
        else {
            NumberFormat f = new DecimalFormat("#0.00");
            this.salary = Double.parseDouble(f.format(salary));
        }
    }

    public String toString() {
        NumberFormat f = new DecimalFormat("#0.00");
        return "ID number: " + this.idNumber + '\n' +
                "Name: " + this.name + '\n' +
                "Position: " + position + '\n' +
                "Salary: $" + f.format(salary);
    }

    private int generateIdNumber() {
        Random r = new Random();
        return 100000 + r.nextInt(900000);
    }

    public static void main(String[] args) {
        Employee e = new Employee("Spongebob", "Fry Cook");
        Employee emp = new Employee(null, null);
        /*
        System.out.println(e.getIdNumber());
        System.out.println(e.getName());
        System.out.println(e.getPosition());
        System.out.println(e.getSalary());
        e.setPosition("Manager");
        System.out.println(e.getSalary());
        */
        System.out.println(e.toString());
        System.out.println();
        e.setPosition("Manager");
        System.out.println(e.toString());
        System.out.println();
        e.setPosition("Not Manager");
        System.out.println(e.toString());
        System.out.println();
        e.setPosition("Manager");
        System.out.println(e.toString());
        System.out.println();
        System.out.println(emp.toString());

    }

}
