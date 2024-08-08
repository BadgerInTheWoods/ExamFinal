import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Registry {
    private List<Animal> animals;
    private Counter counter;
    private static final String FILE_NAME = "animals.txt";

    public Registry() {
        animals = new ArrayList<>();
        counter = new Counter();
        loadAnimals(); // Load animals when the registry is initialized
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        counter.add();
        System.out.println("Animal registered. Total animals registered: " + counter.getCount());
        saveAnimals(); // Save the animals list after adding a new animal
    }

    public void showAnimals() {
        if (animals.isEmpty()) {
            System.out.println("No animals registered.");
        } else {
            for (Animal animal : animals) {
                System.out.println(animal);
            }
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
                saveAnimals(); // Save changes after training
                return;
            }
        }
        System.out.println("Animal not found.");
    }

    private void saveAnimals() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Animal animal : animals) {
                writer.write(animalToString(animal));
                writer.newLine();
            }
            System.out.println("Animals saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving animals: " + e.getMessage());
        }
    }

    private void loadAnimals() {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Animal animal = stringToAnimal(line);
                    if (animal != null) {
                        animals.add(animal);
                    }
                }
                System.out.println("Animals loaded from file.");
            } catch (IOException e) {
                System.out.println("Error loading animals: " + e.getMessage());
            }
        } else {
            System.out.println("No existing animal data found.");
        }
    }

    private String animalToString(Animal animal) {
        return String.join(";", animal.getClass().getSimpleName(), animal.getName(), animal.getCommands(),
                animal.getBirthDate());
    }

    private Animal stringToAnimal(String line) {
        String[] parts = line.split(";");
        if (parts.length < 4)
            return null;

        String type = parts[0];
        String name = parts[1];
        String commands = parts[2];
        String birthDate = parts[3];

        switch (type) {
            case "Dog":
                return new Dog(name, commands, birthDate);
            case "Cat":
                return new Cat(name, commands, birthDate);
            case "Hamster":
                return new Hamster(name, commands, birthDate);
            case "Horse":
                return new Horse(name, commands, birthDate);
            case "Donkey":
                return new Donkey(name, commands, birthDate);
            default:
                System.out.println("Unknown animal type: " + type);
                return null;
        }
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
                    System.out.print("Enter animal type: (dog, cat, hamster, horse, donkey)");
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
