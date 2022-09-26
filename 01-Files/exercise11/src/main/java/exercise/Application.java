
package exercise;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class Application {

	private static void copyWithBuffer(File in, File out, int bufferSize) {

		try (FileInputStream fin = new FileInputStream(in);
				BufferedInputStream bis = new BufferedInputStream(fin)) {

			try (FileOutputStream fos = new FileOutputStream(out);
					BufferedOutputStream bos = new BufferedOutputStream(fos)) {

				int i;
				byte[] buffer = new byte[bufferSize];
				while ((i = bis.read(buffer)) != -1) {
					bos.write(buffer,0,buffer.length);
				}

			} catch (Exception e) {
			}

		} catch (Exception e) {
			System.err.println("Error copy with buffer");
		}

	}

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

	public static void main(String[] args) {
		long startTime;
		long estimatedTime;
		File in = new File("C:\\Users\\Gabriel\\Downloads\\in.dat");
		File out = new File("C:\\Users\\Gabriel\\Downloads\\out.dat");

		startTime = System.currentTimeMillis();
		copyWithBuffer(in,out,10);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Buffer 10: "+estimatedTime);

		startTime = System.currentTimeMillis();
		copyWithBuffer(in,out,100);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Buffer 100: "+estimatedTime);

		startTime = System.currentTimeMillis();
		copyWithBuffer(in,out,1000);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Buffer 1000: "+estimatedTime);

		
		startTime = System.currentTimeMillis();
		copyWithoutBuffer(in,out);
		estimatedTime = System.currentTimeMillis() - startTime;
		System.out.println("Without: "+estimatedTime);

		//The best is with buffer 100 
	}
}
