package springclouddemo.mall.event;

import org.springframework.cloud.bus.event.RemoteApplicationEvent;

public class ProductEvent extends RemoteApplicationEvent {
    /** Message type：update, value: {@value} */
    public static final String ET_UPDATE = "update";
    /** Message type：delete, value: {@value} */
    public static final String ET_DELETE = "delete";

    // ========================================================================
    // fields =================================================================
    private String action;
    private String itemCode;

    public ProductEvent() {
        super();
    }

    public ProductEvent(Object source, String originService, String destinationService, String action, String itemCode) {
        super(source, originService, destinationService);
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
