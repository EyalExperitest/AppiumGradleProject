package api.manual;
import utils.ProcessReader;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class STMProcess extends Process {

	public static final String PROCESS_NAME = "SeeTestManual.exe";
	public static final String MCLI_EXE = "mcli";
	private static Process process;
	static  private String seeTestAutoPath="C:/Program Files (x86)/Experitest/SeeTestManual_Trunk";
	static private String launchInSTAConfirmationLine ="(imagestudio.ImageStudioApp) INFO 	Load time";

	static private String seeTestAutoExecutable="SeeTestManual.exe";
	static private LogReader logReader =null;
	static private String logDirStr ="C:\\Users\\eyal.neumann\\AppData\\Roaming\\seetestmanual";
	static private File lastLog;
	static private Thread launchMonitorThread=null;

	public STMProcess() throws IOException {
		// TODO Auto-generated constructor stub
		lastLog = LogHandeler.findLatestLogFile(logDirStr);
		System.out.println("Last Log Found at: "+lastLog.getAbsolutePath());
		LogHandeler.clearFile(lastLog);
		logReader=new LogReader(lastLog.getAbsolutePath(),launchInSTAConfirmationLine);
		launchMonitorThread=new Thread (logReader);
		launchMonitorThread.start();
		File stmDir = new File (seeTestAutoPath);
		File stmEXE = new File (seeTestAutoPath+"/"+seeTestAutoExecutable);

		System.out.println("Dir Exist ? "+stmDir.exists());
		System.out.println("exe Exist ? "+stmEXE.exists());
		System.out.println("Dir Path: ? "+stmDir.getAbsolutePath());
		System.out.println("exe Path: ? "+stmEXE.getAbsolutePath());

		ProcessBuilder pb = new ProcessBuilder()
				.redirectErrorStream(true)
				.directory(stmDir)
				.command(
						stmEXE.getAbsolutePath()
				);

//        this.process =  Runtime.getRuntime().exec(command);
		System.out.println("Now Starting to Launch SeeTest Manual");
		STMProcess.process = pb.start();
		ProcessReader processReader =new ProcessReader(process,"Manual");
		Thread thread =new Thread(processReader);
		thread.start();
		//STMProcess.process =  Runtime.getRuntime().exec(seeTestAutoPath+"\\"+seeTestAutoExecutable);
		System.out.println("Launching SeeTestManual started");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		STMProcess.process.destroy();

	}
	public void waitForLaunch() throws InterruptedException{
		launchMonitorThread.join();
		System.out.println("Launching SeeTest ended");

	}
	
	
	@Override
	public boolean isAlive() {
		// TODO Auto-generated method stub
		return STMProcess.process.isAlive();
	}

	@Override
	public int exitValue() {
		// TODO Auto-generated method stub
		return STMProcess.process.exitValue();
	}

	@Override
	public InputStream getErrorStream() {
		// TODO Auto-generated method stub
		return STMProcess.process.getErrorStream();
	}

	@Override
	public InputStream getInputStream() {
		// TODO Auto-generated method stub
		return STMProcess.process.getInputStream();
	}

	@Override
	public OutputStream getOutputStream() {
		// TODO Auto-generated method stub
		return STMProcess.process.getOutputStream();
	}

	@Override
	public int waitFor() throws InterruptedException {
		// TODO Auto-generated method stub
		return STMProcess.process.waitFor();
	}

	public void reset() throws IOException, InterruptedException{
		this.destroy();
		lastLog = LogHandeler.findLatestLogFile(logDirStr);
		LogHandeler.clearFile(lastLog);
		logReader=new LogReader(lastLog.getAbsolutePath(),launchInSTAConfirmationLine);
		Thread resetMonitorThread=new Thread (logReader);
		resetMonitorThread.start();

		STMProcess.process =  Runtime.getRuntime().exec(seeTestAutoPath+"\\"+seeTestAutoExecutable);
		resetMonitorThread.join();


	}
	public int reserve(String id)throws IOException, InterruptedException{

		String command = seeTestAutoPath + "\\" + MCLI_EXE + " reserve -id " + id + " -appium";
		System.out.println("Command: "+command);
		Process mCLI =Runtime.getRuntime().exec(command);
		ProcessReader processReader =new ProcessReader(mCLI,"MCLI");
		Thread thread =new Thread(processReader);
		thread.start();
		int waitFor = mCLI.waitFor();
		System.out.println("Result: "+waitFor);
		return waitFor;
	}
	public int reserve(String id,int duration)throws IOException, InterruptedException{
		String command = seeTestAutoPath + "\\" + MCLI_EXE + " reserve -id " + id + " -d " + duration + " -appium";
		System.out.println("Command: "+command);
		Process mCLI =Runtime.getRuntime().exec(command);
		int waitFor = mCLI.waitFor();
		System.out.println("Result: "+waitFor);
		return waitFor;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		
		
		STMProcess.closeSeeTest();
		
		STMProcess sTAProccess= new STMProcess();
		
		int memoryUsage = sTAProccess.getMemoryUsage();
		System.out.println("Memory Usage :"+memoryUsage);
		sTAProccess.waitForLaunch();
		memoryUsage = sTAProccess.getMemoryUsage();
		System.out.println("Memory Usage :"+memoryUsage);
		Thread.sleep(10000);
		memoryUsage = sTAProccess.getMemoryUsage();
		System.out.println("Memory Usage :"+memoryUsage);
		sTAProccess.reserve("dcd3b654");
	}

	/**
	 * Only on Windows OS
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void closeSeeTest() throws IOException, InterruptedException {
		System.out.println("Killing SeeTestManual started");
		Process shutdown =Runtime.getRuntime().exec("TASKKILL /F /IM " + PROCESS_NAME);
		System.out.println(shutdown.waitFor());
		System.out.println("Killing SeeTestManual ended");

	}

	/**
	 * @return
	 * @throws IOException
	 */
	public  int getMemoryUsage() throws IOException {
		String command = "powershell TASKLIST | findstr  " + PROCESS_NAME;
		Process process =Runtime.getRuntime().exec(command);
		process.getOutputStream().close();
		String line;
		BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
		line=stdout.readLine();
		//System.out.println(line);
		String[] words =line.split(" +");
		int memoryUsage =Integer.parseInt(words[4].replaceAll(",", ""));
		return memoryUsage;
	}

	@Override
	public Process destroyForcibly() {
		// TODO Auto-generated method stub
		return STMProcess.process.destroyForcibly();
	}

	@Override
	public boolean waitFor(long timeout, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return STMProcess.process.waitFor(timeout, unit);
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return STMProcess.process.equals(obj);
	}


	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return STMProcess.process.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return STMProcess.process.toString();
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
