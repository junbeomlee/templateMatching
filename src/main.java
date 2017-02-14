import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.Highgui;

public class main {

	public static void main(String[] args){
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		TemplateMatch templateMatch = new TemplateMatch();
		
		/*
		 * template 
		 */
		Mat outImg=templateMatch.match("input/soldier-weapon.jpg","input/soldier.jpg");
//		Highgui.imwrite("out.png", outImg);
		
//		/*
//		 * surf
//		 */
//		SurfMatch surfMatch = new SurfMatch("surf-high-soldier2-",FeatureDetector.SURF);
//		/*
//		 * match(object, scene)
//		 */
//		surfMatch.match("input/soldier-weapon.jpg","input/soldier.jpg");

	}
	
	
	//public static 

}
