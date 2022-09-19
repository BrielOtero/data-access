import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Exercise8 {

	/**
	 * It reads one byte at a time from the input file and writes it to the output file
	 * 
	 * @param in The file to copy from.
	 * @param out The file to write to.
	 */
	static void copyWithoutBuffer(File in , File out){

		try (FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out)) {

			int i;
			while((i=fis.read())!=-1){
				fos.write(i);
			}
			
		} catch (Exception e) {
			System.err.println("Error copy without buffer");
		}

	}

	/**
	 * It reads the file in chunks of size bufferSize and writes it to the output file
	 * 
	 * @param in The file to copy from.
	 * @param out The file to write to.
	 * @param bufferSize The size of the buffer to use.
	 */
	static void copyWithBuffer(File in , File out, int bufferSize){

		try (FileInputStream fis = new FileInputStream(in);
		FileOutputStream fos = new FileOutputStream(out)) {

			int i;
			byte[] buffer = new byte[bufferSize];
			while((i=fis.read(buffer))!=-1){
				fos.write(buffer,0,i);
			}
			
		} catch (Exception e) {
			System.err.println("Error copy with buffer");
		}

	}

	

	public static void main(String[] args) {

		File in = new File("C:\\Users\\Gabriel\\Downloads\\File.txt");

		File out = new File("C:\\Users\\Gabriel\\Downloads\\File-Out.txt");
		
		//copyWithoutBuffer(in, out);
		copyWithBuffer(in, out, 100);
		//copyWithBuffer(in, out, 1000);
	}
	
}
