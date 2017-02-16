

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;

public class Test {

	public static void main(String[] args){
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		TemplateMatch templateMatch = new TemplateMatch();
		Mat beforeMat=templateMatch.readImage("input/soldier-weapon.jpg");
		Mat afterMat=templateMatch.resize(beforeMat, 1);
		templateMatch.canny(afterMat, 50, 200);
		Highgui.imwrite("1.png", afterMat);
	}
}
