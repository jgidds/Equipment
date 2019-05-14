package model.exceptions;


public class EquipmentStatusException extends RuntimeException {
    public EquipmentStatusException() {
        super();
    }

    public EquipmentStatusException(String msg) {
        super(msg);
    }
}
