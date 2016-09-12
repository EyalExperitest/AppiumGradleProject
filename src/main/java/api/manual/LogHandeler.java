package api.manual;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.SortedSet;
import java.util.TreeSet;

public class LogHandeler {

	private static final String LOG_HEAD = "SeeTest-2016";

	public LogHandeler() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String logDirStr ="C:\\Users\\eyal.neumann\\AppData\\Roaming\\seetest";
		
		File lastLog = findLatestLogFile(logDirStr);
		System.out.println(lastLog.getName());
		
		clearFile(lastLog);
		


		

	}

	/**
	 * @param file
	 * @throws IOException
	 */
	public static void clearFile(File file) throws IOException {
		file.delete();
		file.createNewFile();
		
		PrintWriter writer = new PrintWriter(file);
		writer.print("");
		writer.close();
		
	}

	/**
	 * @param logDirStr
	 * @return Latest log file
	 */
	public static File findLatestLogFile(String logDirStr) {
		File logDir =new File(logDirStr);
		File[] files =logDir.listFiles();
		SortedSet<File> logFiles=new  TreeSet<File>();
		for(File file:files){
			//System.out.println(file.getName());
			if(file.getName().startsWith(LOG_HEAD)){
				logFiles.add(file);
			}
			
		}


		File lastLog =logFiles.last();
		return lastLog;
	}


}