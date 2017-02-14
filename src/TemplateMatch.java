import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class TemplateMatch {

	public Mat match(String templateName, String imageName){
		int match_method = Imgproc.TM_CCOEFF;

		Mat img = readImage(imageName);
		Mat templ = readImage(templateName);
		
		
		for(int i=1;i<5;i++){
			
			/*
			 *
			 */
			Mat resizeimage = new Mat();
			Size sz = new Size(img.width()*i*0.2,img.height()*i*0.2);
			Imgproc.resize(img, resizeimage, sz );
			
			
			int result_cols = img.cols() - templ.cols() + 1;
	        int result_rows = img.rows() - templ.rows() + 1;
	        Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
	        // / Do the Matching and Normalize
	        Imgproc.matchTemplate(img, templ, result, match_method);
	        Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
	        
	        // / Localizing the best match with minMaxLoc
	        MinMaxLocResult mmr = Core.minMaxLoc(result);

	        Point matchLoc;
	        if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
	            matchLoc = mmr.minLoc;
	        } else {
	            matchLoc = mmr.maxLoc;
	        }

	        // / Show me what you got
	        Core.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(),
	                matchLoc.y + templ.rows()), new Scalar(0, 255, 0));

	        Highgui.imwrite(i+"out.png", img);
		}
		
		return img;
	}
	
	
	public Mat readImage(String imageName){
		return Highgui.imread(imageName);
	}
}
