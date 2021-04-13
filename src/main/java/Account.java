import lombok.Data;

@Data
public class Account {

    private int id;
    private int customerId;
    private String type;
    private float balance;
}
