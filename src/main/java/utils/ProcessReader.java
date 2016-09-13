package utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Created by eyal.neumann on 9/13/2016.
 */
public class ProcessReader implements Runnable {
    private final Process process;
    private String name;
    public ProcessReader(Process process, String name){
        this.process =process;
        this.name= name;
    }
    @Override
    public void run() {
        Thread.currentThread().setName(name);
        try {
            process.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line;
        try(BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));) {
            while (process.isAlive()) {
                System.out.println(stdout.readLine());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
/*powerShellProcess.getOutputStream().close();
		String line;
		BufferedReader stdout = new BufferedReader(new InputStreamReader(
				powerShellProcess.getInputStream()));
		while (true) {
			line = stdout.readLine();
			if (line!=null){
				//System.out.println(line);
				if (line.contains(waitedLine)){
					System.out.println(line);
					break;
				}
			}
			else{
				System.out.println(line);
			}

		}*/