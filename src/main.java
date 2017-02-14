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
		//Mat outImg=templateMatch.match("img.png", "template.png");
		//Highgui.imwrite("out.png", outImg);
		
		/*
		 * surf
		 */
		SurfMatch surfMatch = new SurfMatch("surf-high-soldier",FeatureDetector.SURF);
		/*
		 * match(object,scene)
		 */
		surfMatch.match("input/soldier.jpg","input/soldier-weapon.jpg");
		
		/*
		 * 
		 */
	}

}
