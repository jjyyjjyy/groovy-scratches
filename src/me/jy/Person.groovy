package me.jy

/**
 * @author jy
 */
class Person {

    private int id;

    private String name;

    int getId() {
        return id
    }

    void setId(int id) {
        this.id = id
    }

    String getName() {
        return "I am " + name
    }

    void setName(String name) {
        this.name = name
    }
}
