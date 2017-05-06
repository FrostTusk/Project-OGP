package asteroids.model.programs.statements;

import asteroids.helper.program.ActionType;
import asteroids.helper.program.StatementType;
import asteroids.model.programs.MyStatement;

public abstract class ActionStatement extends MyStatement {

		public ActionStatement() {
			setType(StatementType.ACTION);
		}
		
		
		private ActionType actionType;
		
		
		protected void setActionType(ActionType actionType) {
			this.actionType = actionType;
		}
		
		
		public ActionType getActionType() {
			return this.actionType;
		}
	
}
