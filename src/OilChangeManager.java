/**
 *This class represents an interface for a user to manipulate the doubly-linked lists by adding and removing cars
 *
 * @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115872064
 */
import java.util.Scanner;
public class OilChangeManager {

    /**
     * This method converts the string input of the make of a car and converts it into an enum type
     *
     * @param makeOfCar
     * Make of the car
     * @return
     * enum make of car
     */
    public static Make stringToEnumMake(String makeOfCar){//FORD, GMC, CHEVY, JEEP, DODGE, CHRYSLER, LINCOLN
        switch(makeOfCar.toUpperCase()){
            case "FORD":
                return Make.FORD;
            case "GMC":
                return Make.GMC;
            case "CHEVY":
                return Make.CHEVY;
            case "JEEP":
                return Make.JEEP;
            case "DODGE":
                return Make.DODGE;
            case "CHRYSLER":
                return Make.CHRYSLER;
            case "LINCOLN":
                return Make.LINCOLN;
        }
        return null;
    }

    /**
     * This method converts the enum input of the make of the car to String
     *
     * @param makeOfCar
     * make of the car
     * @return
     * String of the make of the car
     */
    public static String enumToStringMake(Make makeOfCar){
        switch(makeOfCar){
            case FORD:
                return "Ford";
            case GMC:
                return "GMC";
            case CHEVY:
                return "Chevy";
            case JEEP:
                return "Jeep";
            case DODGE:
                return "Dodge";
            case CHRYSLER:
                return "Chrysler";
            case LINCOLN:
                return "Lincoln";
        }
        return null;
    }

    /**
     * This method gives the interface for a user to manipulate three doubly-linked lists through adding nodes, removing nodes, and merging lists
     *
     * @param args
     * arguments
     */
    public static void main(String[] args) {
        CarList JoeList = new CarList();
        CarList DonnyList = new CarList();
        CarList FinishedList = new CarList();
        Car CutCar = null;
        Scanner input = new Scanner(System.in);
        String selection = null;
        do {
            System.out.println("Welcome to Tony's Discount Oil Change Computer Management System! It's way better than a rolodex, cork board, post-its, and pre-chewed bubblegum!");
            System.out.println("Menu:");
            System.out.println("    L) Edit Job Lists for Joe and Donny");
            System.out.println("    M) Merge Job Lists");
            System.out.println("    P) Print Job Lists");
            System.out.println("    F) Paste car to end of finished car list");
            System.out.println("    S) Sort Job Lists");//DO THE EXTRA CREDITS
            System.out.println("    Q) Quit");
            System.out.print("Please select an option: ");
            selection = input.next();

            if(selection.equalsIgnoreCase("L")) {
                input.nextLine();
                String listSelection;
                System.out.print("Please select a list - Joe (J) or Donny (D): ");
                listSelection = input.nextLine();
                System.out.println("Options: ");
                System.out.println("    A) Add a car to the end of the list");
                System.out.println("    F) Cursor Forward");
                System.out.println("    H) Cursor to Head");
                System.out.println("    T) Cursor to Tail");
                System.out.println("    B) Cursor Backward");
                System.out.println("    I) Insert car before cursor");
                System.out.println("    X) Cut car at cursor");
                System.out.println("    V) Paste before cursor");
                System.out.println("    R) Remove cursor");
                System.out.print("Please select an option: ");
                String optionSelection = input.nextLine();

                //Adds a card to the end of the list
                if(optionSelection.equalsIgnoreCase("A")){
                    System.out.print("Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): ");
                    String makeSelection = input.nextLine();
                    Make makeOfCar = stringToEnumMake(makeSelection);
                    if(makeOfCar == null) {
                        System.out.println("We don't service " + makeSelection + "!\n");
                        continue;
                    }

                    System.out.print("Please enter owner's name: ");
                    String nameSelection = input.nextLine();
                    if(listSelection.equalsIgnoreCase("J")){
                        JoeList.appendToTail(new Car(makeOfCar, nameSelection));
                        System.out.println(nameSelection+"'s " + enumToStringMake(makeOfCar) + " has been scheduled for an oil change with Joe and has been added to his list\n");
                    }
                    if(listSelection.equalsIgnoreCase("D")) {
                        DonnyList.appendToTail(new Car(makeOfCar, nameSelection));
                        System.out.println(nameSelection + "'s " + enumToStringMake(makeOfCar) + " has been scheduled for an oil change with Donny and has been added to his list\n");
                    }
                }

                //cursor moves forward
                if(optionSelection.equalsIgnoreCase("F")){
                    if(listSelection.equalsIgnoreCase("J")){
                        try {
                            JoeList.cursorForward();
                            System.out.println("Cursor Moved Forward in Joe's List.\n");
                        } catch (EndOfListException e) {
                            System.out.println("Cursor did not move forward in Joe's List as The cursor is at the tail of the list.\n");
                            continue;
                        }
                    }
                    if(listSelection.equalsIgnoreCase("D")){
                        try{
                            DonnyList.cursorForward();
                            System.out.println("Cursor Moved Forward in Joe's List.\n");
                        } catch (EndOfListException e) {
                            System.out.println("Cursor did not move forward in Donny's List as the cursor is at the tail of the list.\n");
                            continue;
                        }
                    }
                }

                //cursor to head
                if(optionSelection.equalsIgnoreCase("H")){
                    if(listSelection.equalsIgnoreCase("J")){
                        try {
                            JoeList.resetCursorToHead();
                            System.out.println("The cursor moved back to the head in Joe's list.\n");
                        } catch (NullPointerException  | IllegalArgumentException e){
                            System.out.println("Joe's list is empty.\n");
                            continue;
                        }
                    }
                    if(listSelection.equalsIgnoreCase("D")){
                        try {
                            DonnyList.resetCursorToHead();
                            System.out.println("The cursor moved back to the head in Donny's list.\n");
                        } catch (NullPointerException | IllegalArgumentException e){
                            System.out.println("Donny's list is empty.\n");
                            continue;
                        }
                    }
                }

                //cursor to tail
                if(optionSelection.equalsIgnoreCase("T")) {
                    if (listSelection.equalsIgnoreCase("J")) {
                        try {
                            JoeList.resetCursorToTail();
                            System.out.println("The cursor moved to the tail in Joe's list.\n");
                        } catch (NullPointerException | IllegalArgumentException e) {
                            System.out.println("Joe's list is empty.\n");
                            continue;
                        }
                    }
                    if (listSelection.equalsIgnoreCase("D")) {
                        try {
                            DonnyList.resetCursorToTail();
                            System.out.println("The cursor moved to the tail in Donny's list.\n");
                        } catch (NullPointerException | IllegalArgumentException e) {
                            System.out.println("Donny's list is empty.\n");
                            continue;
                        }
                    }
                }

                //cursor backward
                if(optionSelection.equalsIgnoreCase("B")){
                    if(listSelection.equalsIgnoreCase("J")){
                        try {
                            JoeList.cursorBackward();
                            System.out.println("The cursor moved backwards in Joe's list.\n");
                        } catch (EndOfListException e){
                            System.out.println("The cursor did not move backwards in Joe's list because The cursor is at the tail of the list!\n");
                            continue;
                        }
                    }
                    if(listSelection.equalsIgnoreCase("D")){
                        try {
                            DonnyList.cursorBackward();
                            System.out.println("The cursor moved backwards in Donny's list.\n");
                        } catch (EndOfListException e){
                            System.out.println("The cursor did not move backwards in Donny's list because The cursor is at the tail of the list!\n");
                            continue;
                        }
                    }
                }

                //Insert Car before cursor
                if(optionSelection.equalsIgnoreCase("I")){
                    System.out.print("Please enter vehicle make (Ford, GMC, Chevy, Jeep, Dodge, Chrysler, and Lincoln): ");
                    String makeSelection = input.nextLine();
                    Make makeOfCar = stringToEnumMake(makeSelection);
                    if(makeOfCar == null) {
                        System.out.println("We don't service " + makeSelection + "!\n");
                        continue;
                    }

                    System.out.print("Please enter owner's name: ");
                    String nameSelection = input.nextLine();
                    if(listSelection.equalsIgnoreCase("J")){
                        JoeList.insertBeforeCursor(new Car(makeOfCar, nameSelection));
                        System.out.println(nameSelection+"'s " + enumToStringMake(makeOfCar) + " has been scheduled for an oil change with Joe and has been added to his list before the cursor\n");
                    }
                    if(listSelection.equalsIgnoreCase("D")) {
                        DonnyList.insertBeforeCursor(new Car(makeOfCar, nameSelection));
                        System.out.println(nameSelection + "'s " + enumToStringMake(makeOfCar) + " has been scheduled for an oil change with Donny and has been added to his list before the cursor\n");
                    }
                }

                //Cut cursor
                if(optionSelection.equalsIgnoreCase("X")){
                    if(listSelection.equalsIgnoreCase("J")){
                        try{
                            CutCar = JoeList.removeCursor();
                            System.out.println("Cursor cut in Joe's list\n");
                        } catch (EndOfListException e){
                            System.out.println("Cursor did not cut in Joe's list because there is no car at cursor or the cursor is at the tail of the list!\n");
                            continue;
                        }
                    }
                    if(listSelection.equalsIgnoreCase("D")){
                        try{
                            CutCar = DonnyList.removeCursor();
                            System.out.println("Cursor cut in Donny's list\n");
                        } catch (EndOfListException e){
                            System.out.println("Cursor did not cut in Donny's list because there is no car at cursor or the cursor is at the tail of the list!\n");
                            continue;
                        }
                    }
                }

                //Cursor Paste
                if(optionSelection.equalsIgnoreCase("V")){
                    if(listSelection.equalsIgnoreCase("J")){
                        try{
                            JoeList.insertBeforeCursor(CutCar);
                            CutCar = null;
                            System.out.println("Cursor pasted in Joe's List\n");
                        } catch(NullPointerException | IllegalArgumentException e){
                            System.out.println("Nothing to paste\n");
                            continue;
                        }
                    }
                    if(listSelection.equalsIgnoreCase("D")){
                        try{
                            DonnyList.insertBeforeCursor(CutCar);
                            CutCar = null;
                            System.out.println("Cursor pasted in Donny's List\n");
                        } catch(NullPointerException | IllegalArgumentException e){
                            System.out.println("Nothing to paste\n");
                            continue;
                        }
                    }
                }

                //Remove cursor
                if(optionSelection.equalsIgnoreCase("R")){
                    if(listSelection.equalsIgnoreCase("J")){
                        try{
                            JoeList.removeCursor();
                            System.out.println("Cursor removed in Joe's list\n");
                        } catch (EndOfListException e){
                            System.out.println("Cursor did not remove in Joe's list because there is no car at cursor or the cursor is at the tail of the list.\n");
                            continue;
                        }
                    }
                    if(listSelection.equalsIgnoreCase("D")){
                        try{
                            DonnyList.removeCursor();
                            System.out.println("Cursor removed in Donny's list\n");
                        } catch (EndOfListException e){
                            System.out.println("Cursor did not remove in Donny's list because there is no car at cursor or the cursor is at the tail of the list.\n");
                            continue;
                        }
                    }
                }


            }

            //Merge Job lists
            //Order: if chosen Donny:
            //          D1,J1,D2,J2,...
            //      if chosen Joe
            //          J1,D1,J2,D2,...
            if(selection.equalsIgnoreCase("M")){
                input.nextLine();
                System.out.print("Please select a destination list - Joe (J) or Donny (D): ");
                String destinationSelection = input.nextLine();
                if(destinationSelection.equalsIgnoreCase("J")){
                    JoeList.merge(DonnyList);
                    System.out.println("Donny's List merged into Joe's list\n");
                }
                else if(destinationSelection.equalsIgnoreCase("D")){
                    DonnyList.merge(JoeList);
                    System.out.println("Joe's list merged into Donny's list\n");
                }
                else {
                    System.out.println("Invalid choice. Try again");
                }

            }

            //Print the list
            if(selection.equalsIgnoreCase("P")) {
                input.nextLine();
                System.out.println("Joe's List:");
                System.out.println("Make        Owner");
                System.out.println("----------------------");
                System.out.println(JoeList);

                System.out.println("Donny's List:");
                System.out.println("Make        Owner");
                System.out.println("----------------------");
                    System.out.println(DonnyList);

                System.out.println("Finished List:");
                System.out.println("Make        Owner");
                System.out.println("----------------------");
                System.out.println(FinishedList.toString().replace("->",""));
            }

            //paste car at the end of finished list
            if(selection.equalsIgnoreCase("F")){
                try{
                    FinishedList.appendToTail(CutCar);
                    CutCar = null;
                    System.out.println("Car pasted in finished list.\n");
                } catch(NullPointerException | IllegalArgumentException e){
                    System.out.println("Nothing to paste\n");
                }
            }

            //Sorts the List
            if(selection.equalsIgnoreCase("S")){
                JoeList.sort();
                DonnyList.sort();
                FinishedList.sort();
                System.out.println("All Job lists have been sorted");
            }
        }while(!selection.equalsIgnoreCase("Q"));
        input.close();
        System.out.println("Enjoy your retirement!");
    }

}