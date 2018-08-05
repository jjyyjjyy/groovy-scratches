package me.jy

/**
 * @author jy
 */
class Closures {

    static def m() {
        def mClosure = {

            println this
            println owner
            println delegate

            def nestedClosure = {
                println this
                println owner
                println delegate
            }
            println "======closure in closure======"
            nestedClosure()
        }
        println "======closure in method======"
        mClosure()

        mClosure.delegate = "abc"
        println "======closure delegate changed======"
        mClosure()
    }

    static main(args) {
        def listener = { e -> println "Event $e hit" }
        assert listener instanceof Closure

        def code = { -> 123 }
        assert code() == 123

        def greeting = { "Hello $it" }
        assert greeting("Daniel") == "Hello Daniel"

        // this owner delegate
        m()

        // delegate -> dynamic call
        def p = new Person(age: 1)
        def p2 = new Person(age: 11)
        def fun = p.getAgeFromDelegate
        assert fun() == 1
        fun.delegate = p2
        assert fun() == 11

    }

    private static class Person {
        private String name

        private int age

        private def getAgeFromDelegate = { -> delegate.age }
    }

}
