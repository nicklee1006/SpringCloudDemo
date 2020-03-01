package springclouddemo.productservice.message;

public class ProductMessage {
    /** Message type：update, value: {@value} */
    public static final String MA_UPDATE = "update";
    /** Message type：delete, value: {@value} */
    public static final String MA_DELETE = "delete";

    // ========================================================================
    // fields =================================================================
    private String action;
    private String itemCode;

    public ProductMessage() {
    }

    public ProductMessage(String action, String itemCode) {
        this.action = action;
        this.itemCode = itemCode;
    }

    @Override
    public String toString() {
        return "action: " + this.action + " itemCode: " + this.itemCode;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
