package api.manual;

import org.apache.xpath.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LogReader implements Runnable {

	private String fileName;
	private String waitedLine;

	public LogReader(String fileName,String waitedLine) {
		this.setFileName(fileName);
		this.setWaitedLine(waitedLine);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			waitForWordInFile(this.fileName,this.waitedLine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//String command = "powershell.exe  your command";
		//Getting the version

		String fileName ="C:\\Users\\eyal.neumann\\AppData\\Roaming\\seetest\\SeeTest-2016-04-19-15-18-29.log";	
		String waitedLine ="Init bridge connect: true";	

		waitForWordInFile(fileName, waitedLine);

		System.out.println("Done");



	}

	/**
	 * @param fileName
	 * @param waitedLine
	 * @throws IOException
	 */
	public static void waitForWordInFile(String fileName, String waitedLine) throws IOException {
		String command = "powershell  Get-Content "+fileName+" -Wait ";






		// Executing the command
		System.out.println("[Manual Launcher] Before Command:"+command);
		ProcessBuilder pb = new ProcessBuilder()
				.redirectErrorStream(true)
		//		.directory(stmDir)
				.command(
						"powershell",
						"Get-Content",
						fileName,
						"-Wait"
				);

//        this.process =  Runtime.getRuntime().exec(command);
		System.out.println("Now Starting to Launch SeeTest Manual");

		Process powerShellProcess = pb.start();

		//Process powerShellProcess = Runtime.getRuntime().exec(command);
		// Getting the results
		System.out.println("[Manual Launcher] After Command:"+command);
		System.out.println("[Manual Launcher] Before closing output stream");
		powerShellProcess.getOutputStream().close();
		System.out.println("[Manual Launcher] After closing output stream");
		//powerShellProcess.getOutputStream().close();
		String line;
		System.out.println("[Manual Launcher] Before Setting Buffer Reader");
		BufferedReader stdout = new BufferedReader(new InputStreamReader(
				powerShellProcess.getInputStream()));
		System.out.println("[Manual Launcher] After Setting Buffer Reader");
		System.out.println("[Manual Launcher] Powershell process is alive ? "+powerShellProcess.isAlive());
		while (powerShellProcess.isAlive()) {
			line = stdout.readLine();
			if (line!=null){
				System.out.println("[Manual Log]"+line);//*****************Debug Line!!! , will print all Log till waited Line
				if (line.contains(waitedLine)){
					System.out.println(line);
					System.out.println("[Manual Launcher] Waited Line is found");

					break;
				}
			}
			else{
				System.out.println(line);
			}
				
		}
		stdout.close();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getWaitedLine() {
		return waitedLine;
	}

	public void setWaitedLine(String waitedLine) {
		this.waitedLine = waitedLine;
	}

}
