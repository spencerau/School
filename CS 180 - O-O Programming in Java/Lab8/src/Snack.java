

/**
 * Representation of a snack to be sold in a vending machine.
 *
 * <p>Lab -- File IO</p>
 *
 * @author Logan Kulinski
 * @version June 12, 2017
 */
public class Snack {
    /**
     * The ID of the snack.
     */
    private int id;

    /**
     * The name of the snack.
     */
    private String name;

    /**
     * The price of the snack.
     */
    private double price;

    /**
     * The number of calories in the snack.
     */
    private int numCalories;

    /**
     * Constructs a newly allocated {@code Snack} object.
     *
     * @param id the id of the snack
     * @param name the name of the snack
     * @param price the price of the snack
     * @param numCalories the number of calories in the snack
     * @throws IllegalArgumentException if the {@code name} argument passed is {@code null}, or if the {@code id}, {@code price}, or{@code numCalories} argument passed is less than zero
     */
    public Snack(int id, String name, double price, int numCalories) throws IllegalArgumentException {
        if (id < 0) {
            throw new IllegalArgumentException("id cannot be less than zero!");
        } else if (name == null) {
            throw new IllegalArgumentException("name cannot be null!");
        } else if (Double.compare(price, 0.0) < 0) {
            throw new IllegalArgumentException("price cannot be less than zero!");
        } else if (numCalories < 0) {
            throw new IllegalArgumentException("numCalories cannot be less than zero!");
        } else {
            this.id = id;
            this.name = name;
            this.price = price;
            this.numCalories = numCalories;
        } //end if
    } //Snack

    /**
     * Gets the ID of the snack.
     *
     * @return the ID of the snack
     */
    public int getId() {
        return this.id;
    } //getId

    /**
     * Gets the name of the snack.
     *
     * @return the name of the snack
     */
    public String getName() {
        return this.name;
    } //getName

    /**
     * Gets the price of the snack.
     *
     * @return the price of the snack
     */
    public double getPrice() {
        return this.price;
    } //getPrice

    /**
     * Gets the number of calories in the snack.
     *
     * @return the number of calories in the snack
     */
    public int getNumCalories() {
        return this.numCalories;
    } //getNumCalories

    /**
     * Sets the name of the snack.
     *
     * @param name the name of the snack
     * @throws IllegalArgumentException if the argument passed is {@code null}
     */
    public void setName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("name cannot be null!");
        } else {
            this.name = name;
        } //end if
    } //setName

    /**
     * Sets the price of the snack.
     *
     * @param price the price of the snack
     * @throws IllegalArgumentException if the argument passed is less than zero
     */
    public void setPrice(double price) throws IllegalArgumentException {
        if (Double.compare(price, 0.0) < 0) {
            throw new IllegalArgumentException("price cannot be less than zero!");
        } else {
            this.price = price;
        } //end if
    } //setPrice

    /**
     * Sets the number of calories in the snack.
     *
     * @param numCalories the number of calories in the snack
     * @throws IllegalArgumentException if the argument passed is less than zero
     */
    public void setNumCalories(int numCalories) throws IllegalArgumentException {
        if (numCalories < 0) {
            throw new IllegalArgumentException("numCalories cannot be less than zero!");
        } else {
            this.numCalories = numCalories;
        } //end if
    } //setNumCalories

    @Override
    public boolean equals(Object o) {
        return o instanceof Snack && this.id == ((Snack) o).id && this.name.equals(((Snack) o).name) && Double.compare(this.price, ((Snack) o).price) == 0 && this.numCalories == ((Snack) o).numCalories;
    } //equals

    @Override
    public String toString() {
        return String.format("%s#%.2f#%d", this.name, this.price, this.numCalories);
    } //toString
}