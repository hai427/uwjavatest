package com.tedneward.example;

import java.beans.*;
import java.util.*;

public class Person implements Comparable<Person> {
  private int age;
  private String name;
  private double salary;
  private String ssn;
  private static int count = 0;
  private boolean propertyChangeFired = false;
  
  public Person() {
    this("", 0, 0.0d);
  }
  
  public Person(String n, int a, double s) {
    name = n;
    age = a;
    salary = s;
    ssn = "";
    count++;
  }

  public void setSSN(String value) {
    String old = ssn;
    ssn = value;
    
    this.pcs.firePropertyChange("ssn", old, value);
    propertyChangeFired = true;
  }
  
  public int getAge() {
      return age;
  }
  
  public String getName() {
      return name;
  }
  
  public double getSalary() {
      return salary;
  }
  
  public void setAge(int age) {
      if (age < 0) {
          throw new IllegalArgumentException();
      }
      this.age = age;
  }
  
  public void setName(String name) {
      if (name == null) {
          throw new IllegalArgumentException();
      }
      this.name = name;
  }
  
  public void setSalary(double salary) {
      this.salary = salary;
  }
  
  public boolean getPropertyChangeFired() {
    return propertyChangeFired;
  }

  public double calculateBonus() {
    return salary * 1.10;
  }
  
  public String becomeJudge() {
    return "The Honorable " + name;
  }
  
  public int timeWarp() {
    return age + 10;
  }
  
  @Override
  public String toString() {
    return "[Person name:" + name + " age:" + age + " salary:" + salary + "]";
  }

  // PropertyChangeListener support; you shouldn't need to change any of
  // these two methods or the field
  //
  private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
  public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
  }
  public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
  }

  @Override
  public boolean equals(Object other) {
      if (other instanceof Person) {
          Person p = (Person)other;
          return p.name == this.name && p.age == this.age;
      }
      return false;
  }
  
  public static class AgeComparator implements Comparator<Person> {
	   public int compare(Person a, Person b) {
			return a.age - b.age;
	   }
  }
  
  @Override
    public int compareTo(Person other) {
		return (int)(other.salary - this.salary);
	}
    
  public static List<Person> getNewardFamily() {
    List<Person> fam = new ArrayList<Person>();
    fam.add(new Person("Matthew", 15, 0));
    fam.add(new Person("Michael", 22, 10000));
    fam.add(new Person("Ted", 41, 250000));
    fam.add(new Person("Charlotte", 43, 150000));
    
    return fam;
  }
}
