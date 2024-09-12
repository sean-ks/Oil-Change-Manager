/**
 * This class represents a node in a doubly linked list which has the car data, a link to the previous node, and a link to the next node
 *  @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115827064
 */
public class CarListNode {
    private Car data; // The data the node holds
    private CarListNode next; // A link to the next node
    private CarListNode prev; // A link to the previous node

    /**
     * This constructor creates an instance of the node with specified data and links set to null
     *
     * @param initData
     * The car object data to be stored
     *
     * Precondition:
     * initData is not null
     *
     * Postcondition:
     * This CarListNode has been initialized to wrap the indicated Car, and prev and next have been set to null.
     *
     * @throws IllegalArgumentException
     */
    public CarListNode(Car initData) throws IllegalArgumentException{
        if(initData == null)
            throw new IllegalArgumentException();
        data = initData;
        next = null;
        prev = null;
    }

    /**
     * This method gets the reference of the car object in the data
     *
     * @return
     * the car object that is wrapped by the node
     */
    public Car getData(){
        return data;
    }

    /**
     * This method sets the data
     *
     * @param data
     * Car data to be set
     */
    public void setData(Car data){
        this.data = data;
    }

    /**
     * This method gets the next node in the list
     *
     * @return
     * link to the next node
     */
    public CarListNode getNext(){
        return next;
    }

    /**
     * This method sets the next node in the list
     *
     * @param next
     * Link to the next node
     */
    public void setNext(CarListNode next){
        this.next = next;
    }

    /**
     * This method gets the previous node in the list
     *
     * @return
     * Link to the previous node
     */
    public CarListNode getPrev(){
        return prev;
    }

    /**
     * This method sets the previous node
     *
     * @param prev
     * Link to the previous node
     */
    public void setPrev(CarListNode prev){
        this.prev = prev;
    }
}
