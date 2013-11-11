package robertalblas.nordland.input;

public class InputAction {
	private String actionType;
	private int value;
	
	public InputAction(String actionType, int value){
		this.actionType = actionType;
		this.value = value;
	}
	
	public String getActionType() {
		return actionType;
	}

	public int getValue() {
		return value;
	}
}
