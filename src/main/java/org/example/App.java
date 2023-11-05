package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    private String name;
    private int id;

    public App(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
