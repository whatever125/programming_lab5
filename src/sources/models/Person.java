package sources.models;

import sources.exceptions.WrongArgumentException;
import sources.models.helpers.PersonArgumentChecker;

import java.time.LocalDateTime;
import java.util.HashSet;

import static sources.models.helpers.PersonArgumentChecker.*;

/**
 * A class representing a Person
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private java.time.LocalDateTime birthday; //Поле не может быть null
    private Integer weight; //Поле может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 32, Длина строки должна быть не меньше 7, Значение этого поля должно быть уникальным, Поле может быть null
    private static final HashSet<String> passportIDSet = new HashSet<>();

    /**
     * Constructs a Person object with the given name, birthday, weight, and passport ID.
     *
     * @param name       the name of the person
     * @param birthday   the birthday of the person
     * @param weight     the weight of the person
     * @param passportID the passport ID of the person
     * @throws IllegalArgumentException if any of the arguments are invalid
     */
    public Person(String name, LocalDateTime birthday, Integer weight, String passportID) throws WrongArgumentException {
        PersonArgumentChecker.checkArguments(name, birthday, weight, passportID);
        this.name = name;
        this.birthday = birthday;
        this.weight = weight;
        this.passportID = passportID;
        if (passportID != null) {
            passportIDSet.add(passportID);
        }
    }

    /**
     * Gets the name of the person.
     *
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     *
     * @param name the new name of the person
     */
    public void setName(String name) throws WrongArgumentException {
        checkName(name);
        this.name = name;
    }

    /**
     * Gets the birthday of the person.
     *
     * @return the birthday of the person
     */
    public LocalDateTime getBirthday() {
        return birthday;
    }

    /**
     * Sets the birthday of the person.
     *
     * @param birthday the new birthday of the person
     */
    public void setBirthday(LocalDateTime birthday) throws WrongArgumentException {
        checkBirthday(birthday);
        this.birthday = birthday;
    }

    /**
     * Gets the weight of the person.
     *
     * @return the weight of the person
     */
    public Integer getWeight() {
        return weight;
    }

    /**
     * Sets the weight of the person.
     *
     * @param weight the new weight of the person
     */
    public void setWeight(Integer weight) throws WrongArgumentException {
        checkWeight(weight);
        this.weight = weight;
    }

    /**
     * Gets the passport ID of the person.
     *
     * @return the passport ID of the person
     */
    public String getPassportID() {
        return passportID;
    }

    /**
     * Sets the passport ID of the person.
     *
     * @param passportID the new passport ID of the person
     */
    public void setPassportID(String passportID) throws WrongArgumentException {
        checkPassportID(passportID);
        this.passportID = passportID;
    }

    /**
     * Returns a boolean indicating if the given passport ID is already in use by another person.
     *
     * @param passportID the passport ID to check
     * @return true if the passport ID is already in use, else false
     */
    public static boolean isUsedPassportID(String passportID) {
        return passportIDSet.contains(passportID);
    }

    /**
     * Returns a string representation of the Person object, including its name, birthday, weight, and passport ID
     *
     * @return a string representation of the Person object
     */
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", weight=" + weight +
                ", passportID='" + passportID + '\'' +
                '}';
    }
}
