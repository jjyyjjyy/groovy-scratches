package me.jy

/**
 * @author jy
 */
class HelloWorld {

    static def pick(n, fun) {
        (1..n).each {
            fun(it)
        }
    }

    static main(args) {

        println "Hello World $args"

        pick(3, System.out::println)
    }
}
