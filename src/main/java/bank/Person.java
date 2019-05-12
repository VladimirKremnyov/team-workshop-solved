package bank;

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String first, String last, int age){
        this.firstName = first;
        this.lastName = last;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }
}
