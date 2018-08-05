package me.jy

import groovy.transform.TypeChecked

/**
 * @author jy
 */
@TypeChecked
class ObjectOrientation {

    static main(args) {

        def p = new PersonConstructor('a', 11)
        PersonConstructor pc = ["a", 1]

    }


    static class PersonConstructor {
        String name
        int age

        PersonConstructor(name, age) {
            this.name = name
            this.age = age
        }
    }
}
