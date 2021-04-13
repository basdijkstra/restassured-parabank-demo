import lombok.Data;

@Data
public class BillPayment {

    private String name;
    private Address address;
    private String phoneNumber;
    private int accountNumber;

    public BillPayment(String name, Address address, String phoneNumber, int accountNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.accountNumber = accountNumber;
    }
}
