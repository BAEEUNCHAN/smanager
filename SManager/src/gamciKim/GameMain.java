package gamciKim;

import co.yedam.control.StudentControl;

public class GameMain {
	public static void main(String[] args) {
		
		GameControl gcontrol = new GameControl();
		gcontrol.selectGameid();
		gcontrol.run();
		
	}

}
