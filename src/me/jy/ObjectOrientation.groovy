package me.jy


import java.lang.reflect.Modifier

/**
 * @author jy
 */
class ObjectOrientation {

    static main(args) {

        def p = new PersonConstructor('a', 11)
        PersonConstructor pc = ['a', 1]
        assert p.name == pc.name

        def task = TaskRunner.run(Tasks)
        assert task.result.containsAll([1, 'JDK6'])

        def bird = new Bird()
        assert bird.fly() == 'I can fly'
        assert bird.speak() == 'I can speak'

        def db = new DummyBird() as Flyable
        assert db.fly() == 'I can fly'

        def dbs = new DummyBird().withTraits Flyable, Speakable
        assert dbs.fly() == 'I can fly'
        assert dbs.speak() == 'I can speak'

    }


    static class PersonConstructor {
        String name
        int age

        PersonConstructor(name, age) {
            this.name = name
            this.age = age
        }
    }


    static class Tasks {

        List result = []

        void alwaysExecuted() {
            result << 1
        }

        @OnlyIf({ jdk > 6 })
        void supportJdk6AndLater() {
            result << "JDK6"
        }

        @OnlyIf({ jdk > 7 && windows })
        void supportJdk7AndWindows() {
            result << "JDK7&Windows"
        }

    }

    private static class TaskRunner {

        static <T> T run(Class<T> taskClass) {
            def task = taskClass.newInstance()

            def params = [jdk: 7, windows: true]

            taskClass.declaredMethods.each { m ->
                if (Modifier.isPublic(m.modifiers) && m.parameterTypes.length == 0) {
                    def onlyIf = m.getAnnotation(OnlyIf)
                    if (onlyIf) {
                        Closure cl = onlyIf.value().newInstance(task, task)
                        cl.delegate = params
                        if (!cl()) {
                            return
                        }
                    }
                    m.invoke(task)

                }
            }
            task
        }
    }

    private static trait Flyable {
        def fly() {
            "I can fly"
        }
    }

    private static trait Speakable {
        def speak() {
            "I can speak"
        }
    }

    private static class Bird implements Flyable, Speakable {

    }

    private static class DummyBird {}
}
