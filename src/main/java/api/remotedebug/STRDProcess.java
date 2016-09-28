package api.remotedebug;

import api.manual.LogReader;
import utils.ProcessReader;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class STRDProcess extends Process {

	public static final String PROCESS_NAME = "rdb.exe";
	private static Process process;
	static  private String seeTestRemoteDebugger ="C:/Program Files (x86)/Experitest/SeeTestRemoteDebugging";
	static private String launchInSTAConfirmationLine ="(imagestudio.ImageStudioApp) INFO 	Load time";
	static private String seeTestRDExecutable ="rdb.exe";

	//static private String seeTestRDExecutable="SeeTestManual.exe";
	static private LogReader logReader =null;
	//static private String logDirStr ="C:\\Users\\eyal.neumann\\AppData\\Roaming\\seetestmanual";
	static private File lastLog;
	static private Thread launchMonitorThread=null;

	File stmDir = new File (seeTestRemoteDebugger);
	File stmstrd = new File (seeTestRemoteDebugger +"/"+ seeTestRDExecutable);

	public STRDProcess(boolean logReaderFlag, String udid, String ip, String port, String project, String userName, String password) throws IOException {
		// TODO Auto-generated constructor stub




		ProcessBuilder pb = new ProcessBuilder()
				.redirectErrorStream(true)
				.directory(stmDir)
				.command(stmstrd.getAbsolutePath(),"connect" ,"-udid", udid, "-ip", ip,
												   "-port", port, "-project", project,
						 						   "-username", userName,"-password", password,"-v"

				);

//        this.process =  Runtime.getRuntime().exec(command);
		System.out.println("Now Starting to Launch SeeTestRemoteDebugger");
		STRDProcess.process = pb.start();
		ProcessReader processReader =new ProcessReader(process,"Remote Debugger");
		Thread thread =new Thread(processReader);
		thread.start();
		//STMProcess.process =  Runtime.getRuntime().exec(seeTestRemoteDebugger+"\\"+seeTestRDExecutable);
		System.out.println("Launching SeeTestRemoteDebugger started");

	}

	public STRDProcess(boolean logReaderFlag, String udid, String ip, String port, String project) throws IOException {
		// TODO Auto-generated constructor stub




		ProcessBuilder pb = new ProcessBuilder()
				.redirectErrorStream(true)
				.directory(stmDir)
				.command(stmstrd.getAbsolutePath(),"connect" ,"-udid", udid, "-ip", ip, "-port", port, "-project", project,"-v"

				);

//        this.process =  Runtime.getRuntime().exec(command);
		System.out.println("Now Starting to Launch SeeTestRemoteDebugger");
		STRDProcess.process = pb.start();
		ProcessReader processReader =new ProcessReader(process,"Remote Debugger");
		Thread thread =new Thread(processReader);
		thread.start();
		//STMProcess.process =  Runtime.getRuntime().exec(seeTestRemoteDebugger+"\\"+seeTestRDExecutable);
		System.out.println("Launching SeeTestRemoteDebugger started");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		STRDProcess.process.destroy();

	}
	public void waitForLaunch() throws InterruptedException{
		if (launchMonitorThread!=null) {
			launchMonitorThread.join();
			System.out.println("Launching SeeTestManual ended");
		}
		else{
			Thread.sleep(20000);
			System.out.println("Time for Launching SeeTestManual has ended");
		}

	}
	
	
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return STRDProcess.process.isAlive();
	}

	@Override
	public int exitValue() {
		// TODO Auto-generated method stub
		return STRDProcess.process.exitValue();
	}

	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return STRDProcess.process.getErrorStream();
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return STRDProcess.process.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return STRDProcess.process.getOutputStream();
	}

	@Override
	public int waitFor() throws InterruptedException {
		// TODO Auto-generated method stub
		return STRDProcess.process.waitFor();
	}



	public static void main(String[] args) throws IOException, InterruptedException {
		
		STRDProcess sTAProccess= new STRDProcess(true, "3230d293cf7611a3", "192.168.4.63", "8090", "Default");
		
		Thread.sleep(10000);
	}

	/**
	 * Only on Windows OS
	 * @throws IOException
	 * @throws InterruptedException
	 */




	@Override
	public Process destroyForcibly() {
		// TODO Auto-generated method stub
		return STRDProcess.process.destroyForcibly();
	}

	@Override
	public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return STRDProcess.process.waitFor(timeout, unit);
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return STRDProcess.process.equals(obj);
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return STRDProcess.process.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return STRDProcess.process.toString();
	}
/*	private static int getMemoryUsage() throws IOException{
		String command = "TASKLIST | findstr studio.exe";
		 Executing the command
		Process powerShellProcess = Runtime.getRuntime().exec(command);
		// Getting the results
		powerShellProcess.getOutputStream().close();
		String line;
		BufferedReader stdout = new BufferedReader(new InputStreamReader(
				powerShellProcess.getInputStream()));
		line = stdout.readLine();
		line = stdout.readLine();
		System.out.println(line);

		stdout.close();
		String[] sLine = line.split(" ");
		int i=0;
		for (String string:sLine){
			System.out.println((i++)+") "+string);
		}
		
		
		
		
		return 0;
		
	}*/

}
