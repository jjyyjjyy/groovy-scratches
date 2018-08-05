package me.jy

import java.sql.Date as SqlDate

/**
 * @author jy
 */
class Syntax {

    static main(args) {

        // Map
        def map = [1: 2, 3: 4]
        map.entrySet().forEach(System.out::println)

        def part = [*: map, 22: 33]
        assert part == [1: 2, 3: 4, 22: 33]

        int i = 5
        def mixMap = [(i): 1]
        assert mixMap[5] == 1

        // Array
        def arr = [1]
        assert arr instanceof List
        assert arr as int[] instanceof int[]

        // List
        def list = [1, 2, 3]
        assert list instanceof ArrayList
        list << 4
        assert list.size() == 4

        // String
        def number = 1
        def eageGString = "value == $number"
        def lazyGString = "value == ${-> number}"

        assert eageGString == "value == 1"
        assert lazyGString == "value == 1"

        // Different hashCode between String and GString
        assert eageGString.hashCode() !== lazyGString.hashCode()

        number = 2
        assert eageGString == "value == 1"
        assert lazyGString == "value == 2"

        // Number
        assert 1g instanceof BigInteger

        // Import
        assert new Date() instanceof Date
        assert new SqlDate(1) instanceof SqlDate

    }
}
