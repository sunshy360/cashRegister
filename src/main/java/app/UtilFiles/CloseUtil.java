package app.UtilFiles;

import java.io.Closeable;
import java.io.IOException;

public final class CloseUtil {
	
	public static void close(Closeable closeable) {
		if(null != closeable){
			try {
				closeable.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}