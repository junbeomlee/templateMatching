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
		Mat mat=Highgui.imread("input/on1.png");
		System.out.println("size: "+"width:"+mat.width()+" height: "+mat.height());
		
		Mat mat2=Highgui.imread("input/soldier.jpg");
		System.out.println("size: "+"width:"+mat2.width()+" height: "+mat2.height());
//		
		Mat mat3=Highgui.imread("input/soldier-weapon.jpg");
		Mat mat4=templateMatch.resize(mat3, ((double)mat2.width()/(double)mat.width()),((double)mat2.height()/(double)mat.height()));
		Highgui.imwrite("input/resizeWeapon.png", mat4);
		
		Mat outImg=templateMatch.match("input/resizeWeapon.png","input/soldier.jpg");
		Highgui.imwrite("out.png", outImg);
		//}
//		/*
//		 * surf
//		 */
//		SurfMatch surfMatch = new SurfMatch("surf-high-soldier2-",FeatureDetector.SURF);
//		/*
//		 * match(object, scene)
//		 */
//		surfMatch.match("input/soldier-weapon.jpg","input/soldier.jpg");

	}
	
	
	public static void multiScaleMatching(){
		
	}

}
