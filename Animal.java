class Animal {
    private String name;
    private String commands;
    private String birthDate;

    public Animal(String name, String commands, String birthDate) {
        this.name = name;
        this.commands = commands;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getCommands() {
        return commands;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void addCommand(String command) {
        commands += ", " + command;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", Commands: " + commands + ", Birth Date: " + birthDate;
    }
}

class DomesticAnimal extends Animal {
    public DomesticAnimal(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}

class PackAnimal extends Animal {
    public PackAnimal(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}

class Dog extends DomesticAnimal {
    public Dog(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}

class Cat extends DomesticAnimal {
    public Cat(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}

class Hamster extends DomesticAnimal {
    public Hamster(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}

class Horse extends PackAnimal {
    public Horse(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}

class Donkey extends PackAnimal {
    public Donkey(String name, String commands, String birthDate) {
        super(name, commands, birthDate);
    }
}
