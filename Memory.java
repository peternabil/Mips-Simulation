package projectmodule1;

public class Memory {

    private int value;  // Value stored inside the memory object
    private int location;  // Location of the memory object 

    public Memory(int value, int location) {
        this.value = value;
        this.location = location;
    }  // Initialize a memory object with a location and a value

    public int read() {
        return value;  // Returns the value stored inside a memory object with specified location
    }

    public void write(int value) {
        this.value = value;  // Overwrites the value of a memory object
    }
}
