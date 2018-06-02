public class QueueTest {
    static class Animal{
        private String name;
        String getName() {
            return name;
        }
        Animal(String name){
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    static class Cat extends Animal{
        Cat(String name) {
            super(name);
        }
    }

    static class Dog extends Animal{
        Dog(String name) {
            super(name);
        }
    }

    public static void main(String[] args) {
        Queue<Animal> q = new Queue<>();

        System.out.println("测试add");
        System.out.println(q.add(new Dog("dog")));
        System.out.println(q.add(new Cat("cat")));
        System.out.println(q.add(new Dog("small dog")));
        System.out.println(q.add(new Cat("big cat")));

        System.out.println("测试element");
        Animal cute = q.element();
        System.out.println(cute);

        System.out.println("测试offer");
        System.out.println(q.add(new Animal("mouse")));

        System.out.println("测试remove 和 poll 和 peek");
        System.out.print(q.peek() + " "); System.out.println(q.remove());
        System.out.print(q.peek() + " " ); System.out.println(q.remove());
        System.out.print(q.peek() + " "); System.out.println(q.poll());
        System.out.print(q.peek() + " "); System.out.println(q.poll());
        System.out.print(q.peek() + " "); System.out.println(q.poll());
        System.out.print(q.peek() + " "); System.out.println(q.poll());

    }
}
