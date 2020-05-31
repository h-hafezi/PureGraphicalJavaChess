package PreGame;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

public class Person implements Serializable {
    private static ArrayList<Person> allPeople = new ArrayList<>();
    private static Person personLoged;
    private String password;
    private String name;
    private String username;
    private int draw;
    private int win;
    private int numberOfPlays;

    public Person(String username, String password, String name) {
        this.password = password;
        this.username = username;
        draw = 0;
        win = 0;
        numberOfPlays = 0;
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

    public static boolean isPassWordCorrect(String password, String username) {
        Person person = getPersonWithUsername(username);
        assert person != null;
        return person.password.equals(password);
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
        return draw;
    }

    public int getWin() {
        return win;
    }

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public static ArrayList<Person> getBestPlayers(String sortFactor) {
        ArrayList<Person> arrayList = new ArrayList<>(allPeople);
        switch (sortFactor) {
            case "number of wins":
                arrayList.sort((Comparator.comparingInt(Person::getWin)));
                break;
            case "number of point":
                arrayList.sort((Comparator.comparingInt(Person::getPoint)));
                break;
            case "number of games":
                arrayList.sort((Comparator.comparingInt(Person::getNumberOfPlays)));
                break;
        }
        return arrayList;
    }

    public String getUsername() {
        return username;
    }

    public static void setPersonLoged(Person personLoged) {
        Person.personLoged = personLoged;
    }
}
