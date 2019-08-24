/**
 * This class represents a customer.
 * It has the ability to get and set characteristics of a customer.
 */

public class Customer {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Address shippingAddress;
    private Address billingAddress;
    private boolean isRewardsMember;

    public Customer() {
    }

    // in case only name and email provided; ex., in person sale
    // initializes (sets internal state of) objects created by instantiating the class
    public Customer(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // in case all info provided; ex. online sign up form
    // initializes (sets internal state of) objects created by instantiating the class
    public Customer(String firstName, String lastName, String email, String phoneNumber, Address shippingAddress,
                    Address billingAddress, boolean isRewardsMember) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
        this.isRewardsMember = isRewardsMember;
    }

    // setters and getters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Address getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(Address billingAddress) {
        this.billingAddress = billingAddress;
    }

    public boolean isRewardsMember() {
        return isRewardsMember;
    }

    public void setRewardsMember(boolean rewardsMember) {
        isRewardsMember = rewardsMember;
    }

}
