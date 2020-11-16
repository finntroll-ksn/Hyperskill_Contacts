package contacts;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {
    private Scanner scanner = new Scanner(System.in);
    private List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void getList() {
        if (contacts.size() == 0) {
            System.out.println("We have no elements");
            System.out.println();

            return;
        }

        String output;
        int i = 1;

        for (Contact contact : contacts) {
            if (contact.getClass() == Person.class) {
                output = contact.getField("name") + " " + contact.getField("surname");
            } else {
                output = contact.getField("name");
            }

            System.out.println(i + ". " + output);
            i++;
        }

        System.out.println();
    }

    public void removeContact(int nextInt) {
        contacts.remove(nextInt - 1);
    }

    public int getListSize() {
        return contacts.size();
    }

    public void infoElements(int id) {
        contacts.get(--id).getAllFields();

        System.out.println();
    }

    public Contact getContact(int id) {
        return contacts.get(--id);
    }

    public void init() {
        while (true) {
            System.out.println("Enter action (add, list, search, count, exit): ");
            String input = scanner.nextLine();

            switch (input) {
                case "add":
                    addElement();
                    break;
                case ("list"):
                    getList();

                    while (true) {
                        if (input.equals("menu")) {
                            break;
                        }

                        System.out.print("[list] Enter action ([number], back): ");
                        input = scanner.nextLine();

                        if (input.equals("back")) {
                            break;
                        } else {
                            int number = Integer.parseInt(input);
                            contacts.get(number - 1).getAllFields();
                            System.out.println();

                            while (!input.equals("menu")) {
                                System.out.print("[record] Enter action (edit, delete, menu): ");
                                input = scanner.nextLine();

                                switch (input) {
                                    case ("edit"):
                                        editElements(getContact(number));
                                        break;
                                    case ("delete"):
                                        removeContact(number);
                                        input = "menu";
                                        break;
                                    case ("menu"):
                                        System.out.println();
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case ("search"):
                    searchElement();
                    break;
                case "count":
                    countElements();
                    break;
                case "exit":
                    return;
            }
        }
    }

    public void addElement() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();

        switch (type) {
            case ("person"):
                addPerson();
                break;
            case ("organization"):
                addOrganization();
                break;
        }

        System.out.println("A record added!");
        System.out.println();
    }

    public void addPerson() {
        Person element = new Person();

        System.out.print("Enter the name: ");
        String name = scanner.nextLine();
        element.setName(name);

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();
        element.setSurname(surname);

        System.out.print("Enter the birth date: ");
        String inputDay = scanner.nextLine();
        element.setBirthDay(inputDay);

        System.out.print("Enter the gender (M, F): ");
        String gender = scanner.nextLine();
        element.setGender(gender);

        System.out.print("Enter the number: ");
        String phone = scanner.nextLine();
        element.setPhone(phone);

        element.setCreationDate(String.valueOf(LocalDateTime.now()));
        element.setLastEditDate(String.valueOf(LocalDateTime.now()));

        addContact(element);
    }

    public void addOrganization() {
        Organization element = new Organization();

        System.out.print("Enter the organization name: ");
        String brand = scanner.nextLine();
        element.setBrand(brand);

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();
        element.setAddress(address);

        System.out.print("Enter the number: ");
        String phone = scanner.nextLine();
        element.setPhone(phone);

        element.setCreationDate(String.valueOf(LocalDateTime.now()));
        element.setLastEditDate(String.valueOf(LocalDateTime.now()));

        addContact(element);
    }

    public void countElements() {
        System.out.println("The Phone Book has " + getListSize() + " records.");
        System.out.println();
    }

    public void editElements(Contact contact) {
        if (contact.getClass() == Person.class) {
            System.out.print("Select a field (name, surname, number): ");
            String field = scanner.nextLine();

            switch (field) {
                case ("name"):
                    System.out.print("Enter name: ");
                    ((Person) contact).setName(scanner.nextLine());
                    break;
                case ("surname"):
                    System.out.print("Enter surname: ");
                    ((Person) contact).setSurname(scanner.nextLine());
                    break;
                case ("birth"):
                    System.out.print("Enter birth: ");
                    String birthDay = scanner.nextLine();
                    ((Person) contact).setBirthDay(birthDay);
                    break;
                case ("gender"):
                    System.out.print("Enter gender: ");
                    String gender = scanner.nextLine();
                    ((Person) contact).setGender(gender);
                    break;
                case ("number"):
                    System.out.print("Enter number: ");
                    String number = scanner.nextLine();
                    contact.setPhone(number);
                    break;
            }
        } else {
            System.out.print("Select a field (name, address, number): ");
            String field = scanner.nextLine();

            switch (field) {
                case ("name"):
                    System.out.print("Enter the organization name: ");
                    ((Organization) contact).setBrand(scanner.nextLine());
                    break;
                case ("address"):
                    System.out.print("Enter the address: ");
                    ((Organization) contact).setAddress(scanner.nextLine());
                    break;
                case ("number"):
                    System.out.print("Enter number: ");
                    String phone = scanner.nextLine();
                    contact.setPhone(phone);
                    break;
            }
        }

        contact.setLastEditDate(String.valueOf(LocalDateTime.now()));
        System.out.println("Saved");

        contact.getAllFields();
        System.out.println();
    }

    public void searchElement() {
        ArrayList<Integer> keyListSearch = new ArrayList<>();
        HashMap<Integer, String> resultSearch = new HashMap<>();

        System.out.print("Enter search query: ");
        String output;
        String query = scanner.nextLine();

        for (int i = 0; i < contacts.size(); i++) {
            for (String each : contacts.get(i).getAllItemField()) {
                if (each.toLowerCase().matches("(.*)" + query + "(.*)")) {
                    if (contacts.get(i).getClass() == Person.class) {
                        output = contacts.get(i).getField("name") + " " + contacts.get(i).getField("surname");
                    } else {
                        output = contacts.get(i).getField("name");
                    }

                    resultSearch.put(i, output);
                    keyListSearch.add(i);
                    break;
                }
            }
        }

        System.out.printf("Found %d results:\n", resultSearch.size());

        for (int i = 0; i < keyListSearch.size(); i++) {
            System.out.println((i + 1) + ". " + resultSearch.get(keyListSearch.get(i)));
        }

        System.out.println();
    }
}
