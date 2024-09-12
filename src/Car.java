/**
 * This class represents a car which has a make and an owner name
 *
 * @author Sean Shiroma
 *      sean.shiroma@stonybrook.edu
 *      id: 115827064
 */
public class Car {
    private Make make; //The make of the car
    private String owner;//The string of the name of the owner

    /**
     * This is a constructor used to create an instance of a Car object
     * @param make
     * The make of the car
     * @param owner
     * the name of the owner of the car
     */
    public Car(Make make, String owner){
        this.make = make;
        this.owner = owner;
    }

    /**
     * This method gets the make of the car object
     *
     * @return
     * The make of the car object
     */
    public Make getMake(){
        return make;
    }

    /**
     * This method sets the make of the car object
     *
     * @param make
     * The make to set the car object
     */
    public void setMake(Make make){
        this.make = make;
    }

    /**
     * This method gets the name of the owner of the car object
     *
     * @return
     * The name of owner
     */
    public String getOwner(){
        return owner;
    }

    /**
     * This method sets the name of the owner
     *
     * @param owner
     * The name of owner
     */
    public void setOwner(String owner){
        this.owner = owner;
    }

    /**
     * This method compares the equality of the current object with the object of same type
     *
     * @param obj
     * The object to compare with the current object
     *
     * @return
     * True if the Car objects are equal to each other and false otherwise
     */
    public boolean equals(Object obj) {
        if(obj instanceof Car)//make sure obj is a car
            return this.owner.equals(((Car) obj).owner) && make == ((Car) obj).make;
        return false;
    }
    /**
     * This method gives a string representation of the car object
     *
     * @return
     * A string representation of the object
     */
    public String toString(){
        return make + "        " + owner;
    }

}
