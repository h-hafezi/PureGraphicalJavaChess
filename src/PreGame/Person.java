package PreGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Person implements Serializable {
    private static ArrayList<Person> allPeople = new ArrayList<>();
    private static Person personLoged;
    public String password;
    private String username;
    private int point;

    public Person(String username, String password, String name) {
        this.password = password;
        this.username = username;
        this.point = 0;
        allPeople.add(this);
    }

    public static void register(String username, String password, String name) {
        new Person(username, password, name);
    }

    public static void deleteAccount() {
        allPeople.remove(personLoged);
    }

    public static boolean login(String username, String password) {
        Person person = getPersonWithUsername(username);
        assert person != null;
        if (person.password.equals(password)) {
            personLoged = person;
            return true;
        } else {
            return false;
        }

    }

    public static boolean isTherePersonWithUsername(String username) {
        return getPersonWithUsername(username) != null;
    }

    public static Person getPersonWithUsername(String username) {
        for (Person person : allPeople) {
            if (person.username.equals(username)) {
                return person;
            }
        }
        return null;
    }

    public static Person getPersonLoged() {
        return personLoged;
    }

    public static void changePassWord(String newPassword) {
        personLoged.password = newPassword;
    }

    public static ArrayList<String> getAllUserName() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (Person person : allPeople) {
            arrayList.add(person.username);
        }
        return arrayList;
    }

    public int getPoint() {
        return point;
    }


    public static ArrayList<Person> getBestPlayers() {
        allPeople.sort((Comparator.comparingInt(Person::getPoint)));
        return allPeople;
    }

    public String getUsername() {
        return username;
    }

    public static void setPersonLoged(Person personLoged) {
        Person.personLoged = personLoged;
    }

    public void win() {
        this.point += 3;
    }

    public void lose() {
        this.point -= 1;
    }

    public void draw() {
        this.point++;
    }

}
