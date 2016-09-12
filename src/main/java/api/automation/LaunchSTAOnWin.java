package api.automation;

import java.io.IOException;


public class LaunchSTAOnWin {
	static private boolean isOn=false;
	static private Process process=null;
	static private String seeTestAutoPath="C:\\Program Files (x86)\\Experitest\\SeeTest";
	static private String seeTestAutoExecutable="studio.exe";

	public LaunchSTAOnWin() {
		// TODO Auto-generated constructor stub
	}


	public static void start(){
		if (true){//(!isOn){
			try {
				process = Runtime.getRuntime().exec(seeTestAutoPath+"\\"+seeTestAutoExecutable);
				

				setOn(true);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				setOn(false);

				e.printStackTrace();
			}
		}

	}
	public static void stop(){
		if(isOn){
			if (process!=null){
				process.destroy();
				isOn=false;

			}
		}


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//	SeeTestAuto.start();
		
		LaunchSTAOnWin.start();
		try {
			Thread.sleep(60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LaunchSTAOnWin.stop();


	}
	public static String getSeeTestAutoPath() {
		return seeTestAutoPath;
	}
	public static void setSeeTestAutoPath(String seeTestAutoPath) {
		LaunchSTAOnWin.seeTestAutoPath = seeTestAutoPath;
	}
	public static boolean isOn() {
		return isOn;
	}
	private static void setOn(boolean isOn) {
		LaunchSTAOnWin.isOn = isOn;
	}


}
