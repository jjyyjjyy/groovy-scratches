package me.jy

import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * @author jy
 */
class Operators {

    static main(args) {

        // Math operators
        int a = 0b00101010
        assert a == 42
        int b = 0b00001000
        println b
        println(a & a)
        println(a & b)
        println(a | a)
        println(a | b)

        int mask = 0b11111111
        assert ((a ^ a) & mask) == 0b00000000
        assert ((a ^ b) & mask) == 0b00100010
        assert ((~a) & mask) == 0b11010101

        // Reference operators
        println null ?: "abc"

        Person p = new Person(name: "Monster")
        println p.name  // getName
        println p.@name // name

        println(transform([1, 23, 4], Operators.&action)) // method pointer

        // Regex operators
        assert ~/foo/ instanceof Pattern
        assert ~'foo' instanceof Pattern

        assert ("learnGroovy" =~ /Groovy/) instanceof Matcher //Matcher
        assert "GroovyLearning" ==~ /Groovy\w+/ //Matcher#match

        // Other operators
        def persons = [
            new Person(id: 1, name: 'a'),
            new Person(id: 2, name: 'b')
        ]
        println persons*.name

        assert mix(4, 5, 6) == 26
        def arr = [4, 5, 6]
        assert mix(*arr) == 26
        assert [1, 2, 3, *arr] == [1, 2, 3, 4, 5, 6]

        assert 1 <=> 2 == -1 // compareTo

        println([1, 2, 3, 4, 5][1..2])

        // Operator overload
        // http://www.groovy-lang.org/operators.html
    }

    private static def mix(a, b, c) {
        a * b + c
    }

    private static def action(a) {
        "Additional $a"
    }

    private static def transform(List elements, Closure fun) {
        def result = []
        elements.each { result << fun(it) }
        result
    }

}
