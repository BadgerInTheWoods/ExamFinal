import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Registry {
    private List<Animal> animals;
    private Counter counter;

    public Registry() {
        animals = new ArrayList<>();
        counter = new Counter(); // Initialize the counter
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        counter.add(); // Increment the counter
        System.out.println("Animal registered. Total animals registered: " + counter.getCount());
    }

    public void showAnimals() {
        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }

    public void showCommands(String name) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                System.out.println("Commands: " + animal.getCommands());
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    public void trainCommand(String name, String newCommand) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                animal.addCommand(newCommand);
                System.out.println("Animal " + name + " trained with new command.");
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Register a new animal");
            System.out.println("2. Show all animals");
            System.out.println("3. Show animal's commands");
            System.out.println("4. Train animal with a new command");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter animal type: ");
                    String type = scanner.nextLine();
                    System.out.print("Enter animal name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter commands the animal knows: ");
                    String commands = scanner.nextLine();
                    System.out.print("Enter birth date (YYYY-MM-DD): ");
                    String birthDate = scanner.nextLine();

                    Animal animal = null;
                    switch (type.toLowerCase()) {
                        case "dog":
                            animal = new Dog(name, commands, birthDate);
                            break;
                        case "cat":
                            animal = new Cat(name, commands, birthDate);
                            break;
                        case "hamster":
                            animal = new Hamster(name, commands, birthDate);
                            break;
                        case "horse":
                            animal = new Horse(name, commands, birthDate);
                            break;
                        case "donkey":
                            animal = new Donkey(name, commands, birthDate);
                            break;
                        default:
                            System.out.println("Unknown animal type.");
                            continue;
                    }

                    addAnimal(animal);
                    break;

                case "2":
                    showAnimals();
                    break;

                case "3":
                    System.out.print("Enter animal name: ");
                    name = scanner.nextLine();
                    showCommands(name);
                    break;

                case "4":
                    System.out.print("Enter animal name: ");
                    name = scanner.nextLine();
                    System.out.print("Enter new command: ");
                    String newCommand = scanner.nextLine();
                    trainCommand(name, newCommand);
                    break;

                case "5":
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Registry registry = new Registry();
        registry.menu();
    }
}
