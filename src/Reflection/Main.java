package Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


public class Main {
    static private final class Car{
        public String name = "Volkswagen";
        private int price = 32500;
        protected double weight = 1.5;

        public Car() {
        }

        public Car(int price) {
            this.price = price;
        }

        public Car(String name, int price, double weight) {
            this.name = name;
            this.price = price;
            this.weight = weight;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public double getWeight() {
            return weight;
        }

        public void setWeight(double weight) {
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        String s = (char) 27 + "[36m";
        String s1 = (char) 27 + "[32m";
        String s2 = (char) 27 + "[34m";
        String s3 = (char) 27 + "[35m";
//  Create an object of type Class, which describe the class Car.
        Class cl = Car.class;
//  Recognize the class name.
        System.out.println(s + "The Class name: " + s1 + cl.getName());
        System.out.print(s + "Modifiers of the class: " + s1);
//  getModifiers() returns an integer value.
        int mods = cl.getModifiers();
        if (Modifier.isStatic(mods)) {
            System.out.print("static ");
        }
        if (Modifier.isPrivate(mods)) {
            System.out.print("private ");
        }
        if (Modifier.isProtected(mods)) {
            System.out.print("protected ");
        }
        if (Modifier.isFinal(mods)) {
            System.out.println("final ");
        }
        System.out.println(s3 + "-----------------------------------------");
        System.out.println(s + "Public reflection fields: " + s1);
//  Method getFields() returns only fields with a modifier public.
        Field[] fields = cl.getFields();
//  Run by the class Field and output all public fields.
        for (Field field : fields) {
            Class fieldType = field.getType();
            System.out.println(s2 + "\tName: " + s1 + field.getName());
            System.out.println(s2 + "\tType: " + s1 + fieldType.getName());
        }
        System.out.println(s + "All Modifier Reflection fields: ");
        fields = cl.getDeclaredFields();
        for (Field field : fields) {
            Class fieldType = field.getType();
            System.out.println(s2 + "\tName: " + s1 + field.getName());
            System.out.println(s2 + "\tType: " + s1 + fieldType.getName());
        }
        System.out.println(s3 + "-----------------------------------------");
        System.out.println(s + "Constructors: " + s1);
//  Create an object array of the type Constructor.
        Constructor[] constructors = cl.getConstructors();
        for (Constructor con : constructors) {
//  Output types of parameters of each constructor (except parameters)
            Class[] paramTypes = con.getParameterTypes();
            for (Class paramType : paramTypes) {
                System.out.print(paramType.getName() + " ");
            }
            System.out.println();
        }
        try {
//  Create an array and get a reference to a class with a specific type of parameters.
            Class[] paramTypes = new Class[]{
                    int.class
            };
//  Get a reference to a constructor with a specific parameter.
            Constructor con = cl.getConstructor(paramTypes);
//  newInstance = new, pass object
            Car c = (Car) con.newInstance(Integer.valueOf(29600));
            System.out.println(s + "Fields: "+ s1 + " Name - "  + c.getName() + ", Price - " + c.getPrice() + ", Weight - " + c.getWeight());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Method[] methods = cl.getMethods();
        for (Method method : methods) {
            System.out.println(s + "Name: " + s2 + method.getName());
            System.out.println(s + "\tReturn types: " + s1 + method.getReturnType().getName());
            Class[] paramTypes = method.getParameterTypes();
            System.out.print(s + "\tParam types:" + s1);
            for (Class<?> paramType : paramTypes) {
                System.out.println(" " + paramType.getName());
            }
            System.out.println();
        }

        try {
            Car c = new Car();
            Class [] paramTypes = new Class[]{String.class};
//  Get a reference to a method with a name setName and a parameter String.
            Method method = cl.getMethod("setName", paramTypes);
            Object [] objArguments = new Object[]{String.valueOf("Mercedes")};
//  The method invoke takes two parameters:
//  the first - it is an object whose class declares or inherits this method,
//  the second - an array of parameters' values that are passed to the method, that is called.
            method.invoke(c, objArguments);
            System.out.println(s + "Name: " + s1 + c.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
