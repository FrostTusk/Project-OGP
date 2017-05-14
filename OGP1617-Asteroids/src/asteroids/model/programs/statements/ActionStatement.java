package asteroids.model.programs.statements;

import asteroids.helper.program.ActionType;
import asteroids.model.programs.MyStatement;

public abstract class ActionStatement implements MyStatement {
		
		private ActionType actionType;
		
		
		protected void setActionType(ActionType actionType) {
			this.actionType = actionType;
		}
		
		
		public ActionType getActionType() {
			return this.actionType;
		}
	
}
