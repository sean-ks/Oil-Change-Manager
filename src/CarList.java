/**
 * This class represents a list of cars implementing a doubly-linked list data structure
 *
 *  @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115827064
 */
public class CarList {
    private CarListNode head; // Head of the list
    private CarListNode tail; // Tail of the list
    private CarListNode cursor; // Cursor of the list
    private int numberOfCars = 0; //The number of cars in the list

    /**
     * This constructor creates an instance of the car list
     * Post-conditions:
     * This CarList has been initialized with head, tail, and cursor all set to null.
     */
    public CarList(){
        this(null,null,null);
    }

    /**
     * This constructor creates an instance of the car list with specified head, tail, and cursor
     *
     * @param head
     * head of the list
     *
     * @param tail
     * tail of the list
     *
     * @param cursor
     * cursor of the list
     */
    public CarList(CarListNode head, CarListNode tail, CarListNode cursor){
        this.head = head;
        this.tail = tail;
        this.cursor = cursor;
    }

    /**
     * This method gets the number of cars in the list
     *
     * @return
     * number of cars
     */
    public int numCars(){
        return numberOfCars;
    }

    /**
     * This method gets the reference to the Car object wrapped by the cursor node
     *
     * @return
     * returns the reference of the car object wrapped by the cursor node
     */
    public Car getCursorCar(){
        if(cursor.getData() == null)
            return null;
        return cursor.getData();
    }

    /**
     * This method resets the cursor position to the head
     * Post-conditions:
     * If head is not null, the cursor now references the first CarListNode in this list.
     * If head is null, the cursor is set to null as well (there are no Cars in this list).
     */
    public void resetCursorToHead(){
        cursor = head;
    }


    /**
     * This method resets the cursor position to the tail
     * Post-conditions:
     * If tail is not null, the cursor now references the last CarListNode in this list.
     * If tail is null, the cursor is set to null as well (there are no Cars in this list).
     */
    public void resetCursorToTail(){ cursor = tail;}

    /**
     * This method moves the cursor forward in the list.
     * If the cursor is on the tail, throw EndOfListException
     *
     * @throws EndOfListException
     */
    public void cursorForward() throws EndOfListException{
        if (cursor == tail)
            throw new EndOfListException();
        cursor = cursor.getNext();
    }

    /**
     * This method moves the cursor backward in the list.
     * If the cursor is on the head, throw EndOfListException
     *
     * @throws EndOfListException
     */
    public void cursorBackward() throws EndOfListException{
        if (cursor == head)
            throw new EndOfListException();
        else {
            cursor = cursor.getPrev();
        }
    }

    /**
     * Inserts a node in the list before the car
     *
     * Preconditions:
     * newCar is not null.
     *
     * Post-conditions:
     * newCar has been wrapped in a new CarListNode object.
     * If cursor was previously not null, the newly created CarListNode has been inserted into the list before the cursor.
     * If cursor was previously null, the newly created CarListNode has been set as the new head of the list (as well as the tail and cursor).
     * The cursor remains unchanged.
     *
     * @param newCar
     * car to be inserted in node
     * @throws IllegalArgumentException
     */

    public void insertBeforeCursor(Car newCar) throws IllegalArgumentException{
        if(newCar == null)
            throw new IllegalArgumentException();
        CarListNode newNode = new CarListNode(newCar);
        if(cursor == null){
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }

        else if(cursor.getPrev() == null){
            head = newNode;
            newNode.setNext(cursor);
            cursor.setPrev(newNode);
        }

        else {
            newNode.setNext(cursor);
            newNode.setPrev(cursor.getPrev());
            cursor.getPrev().setNext(newNode);
            cursor.setPrev(newNode);
            cursor.setPrev(newNode);
        }
        numberOfCars += 1;
    }

    /**
     * appends a node to the tail of the list
     *
     * Preconditions:
     * newCar is not null.
     *
     * Post-conditions:
     * newCar has been wrapped in a new CarListNode object.
     * If tail was previously not null, the newly created CarListNode has been inserted into the list after the tail.
     * If tail was previously null, the newly created CarListNode has been set as the new head of the list (as well as the tail and the cursor).
     * The tail now references the newly created CarListNode.
     * @param newCar
     * car to be wrapped in the node
     * @throws IllegalArgumentException
     */
    public void appendToTail(Car newCar) throws IllegalArgumentException{
        if(newCar == null)
            throw new IllegalArgumentException();
        CarListNode newNode = new CarListNode(newCar);
        if(tail == null){
            head = newNode;
            tail = newNode;
            cursor = newNode;
        }
        else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        numberOfCars += 1;
    }

    /**
     *This method removes the node referenced by the cursor
     * Preconditions:
     * Cursor is not null.
     *
     * Postconditions:
     * The CarListNode referencedby cursor has been removed from the list.
     * All other CarListNodes in the list exist in the same Car as before.
     * The cursor now references the previous CarListNode (or the head, if the cursor previously referenced the head of the list).
     *
     * @return
     * The car inside the node
     * @throws EndOfListException
     */
    public Car removeCursor() throws EndOfListException {
        if (cursor == null)
            throw new EndOfListException();

        Car removedCar = cursor.getData();
        if(cursor == head && cursor == tail){
            head = null;
            tail = null;
            cursor = null;
        }
        else if (cursor == head) {
            head = head.getNext();
            head.setPrev(null);
            cursor = head;
        } else if (cursor == tail) {
            tail = tail.getPrev();
            tail.setNext(null);
            cursor = tail;
        } else {
            cursor = cursor.getPrev();
            cursor.setNext(cursor.getNext().getNext());
            cursor.getNext().setPrev(cursor);
        }
        numberOfCars -= 1;
        return removedCar;
    }

    /**
     * This method merges both lists into one list
     *
     * @param list
     */
    public void merge(CarList list){
        list.resetCursorToHead();

       CarListNode dummyNode = new CarListNode(new Car(null, null));
       CarListNode nodePtr = head;
       CarListNode listHead = list.cursor;
       CarListNode tailOfDummy = dummyNode;

        while(true){
            //if either list runs out of cars, connect the other part of the list to the dummy node
            if(nodePtr == null){
                tailOfDummy.setNext(listHead);
                listHead.setPrev(tailOfDummy);
                tail = list.tail;
                break;
            }
            if(listHead == null){
                tailOfDummy.setNext(nodePtr);
                nodePtr.setPrev(tailOfDummy);
                tail = list.tail;
                break;
            }
            //Set the tail to the destination list's car, then advance the tail
            tailOfDummy.setNext(nodePtr);
            nodePtr.setPrev(tailOfDummy);
            nodePtr = nodePtr.getNext();
            tailOfDummy = tailOfDummy.getNext();

            //Set the tail to the other list's car, then advance the tail
            tailOfDummy.setNext(listHead);
            listHead.setPrev(tailOfDummy);
            listHead = listHead.getNext();
            tailOfDummy = tailOfDummy.getNext();
        }
    head = dummyNode.getNext();
        list.head = null;
        list.tail = null;

    }

    /**
     *This method sorts the doubly linked list alphabetically with the Make of the car as 1st priority and Owner name as 2nd priority
     *
     * Preconditions:
     * list should not be empty
     */
    public void sort(){
        if(head != null){
        CarListNode currentNode = head;
        CarListNode nodePtr;
        Car temp;
        Car tempCursorData = cursor.getData();
            while(currentNode.getNext() != null){
                nodePtr = currentNode.getNext();
                while(nodePtr != null){
                    int CurrentMakeOfCar = enumToInteger(currentNode.getData().getMake());//Numerical vale of the current make of the car
                    int PtrMakeOfCar = enumToInteger(nodePtr.getData().getMake());//Numerical value of the Node pointer make of the car
                    int comparingOwners = currentNode.getData().getOwner().compareToIgnoreCase(nodePtr.getData().getOwner());//Compares the owner's names alphabetically
                    if(CurrentMakeOfCar > PtrMakeOfCar || (CurrentMakeOfCar == PtrMakeOfCar && comparingOwners > 0)){
                        temp = currentNode.getData();
                        currentNode.setData(nodePtr.getData());
                        nodePtr.setData(temp);
                    }
                    nodePtr = nodePtr.getNext();
                }
                currentNode = currentNode.getNext();
            }
            //The code below finds the cursor and puts it in its previous place
            CarListNode cursorSearch = head;
            while(cursorSearch != null){
                if(cursorSearch.getData() == tempCursorData)
                    cursor = cursorSearch;
                cursorSearch = cursorSearch.getNext();
            }
        }

    }

    /**
     * Creates an order/ranking of the makes of the cars using integers
     *
     * @param makeOfCar
     * Make of the car
     * @return
     * an integer value for the make of the car
     */
    public int enumToInteger(Make makeOfCar){
        switch(makeOfCar){
            case CHEVY:
                return 1;
            case CHRYSLER:
                return 2;
            case DODGE:
                return 3;
            case FORD:
                return 4;
            case GMC:
                return 5;
            case JEEP:
                return 6;
            default:
                return 7;
        }
    }
    /**
     * This method gives a string representation of the car list
     *
     * @return
     * A string represenation of the CarList object
     */
    public String toString(){
        CarListNode nodePtr = head;
        String s = "";
        while(nodePtr != null){
            if(nodePtr == cursor)
                s += "->" + nodePtr.getData() + "\n";
            else
                s += nodePtr.getData() + "\n";
            nodePtr = nodePtr.getNext();
        }
        if(s.equals(""))
            return "[empty]";
        return s;
    }
}
