import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

public class MultiScaleTemplateMatching {
	
	int match_method = Imgproc.TM_CCOEFF;

	/*
	 * image를 resize하면서 맞춘다.
	 */
	public Mat match(String templateName, String imageName) {
		/*
		 * image load
		 */
		Mat img = readImage(imageName);
		Mat templ = readImage(templateName);
		this.canny(templ, 50, 200);
		this.canny(img, 50, 200);
		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
		MinMaxLocResult mmr = null;
		
		double bestPercent=0.0;
		double bestValue=0.0;
		Point bestPoint = null;
		
		for(int i=0;i<10;i++){
			double percent=1+i*0.08;
			
			
			
			Mat resizedImg=resize(img,percent);
			Imgproc.matchTemplate(resizedImg, templ, result, match_method);
			mmr = Core.minMaxLoc(result);
			
			if((bestPercent==0.0&&bestPoint==null)||bestValue<mmr.maxVal){
				bestPercent=percent;
				bestPoint = mmr.maxLoc;
				bestValue = mmr.maxVal;
			}
		}
		
		Point matchLoc;
		if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}
		
		Core.rectangle(img, matchLoc, new Point(matchLoc.x*bestPercent + templ.cols(), matchLoc.y*bestPercent + templ.rows()),
				new Scalar(255, 255, 255));
		return img;
	}

	public Mat readImage(String imageName) {
		Mat mat=Highgui.imread(imageName);
		Mat matG= new Mat(mat.width(),mat.height(),CvType.CV_8UC3);
		Imgproc.cvtColor(mat, matG, Imgproc.COLOR_RGB2GRAY);
		return matG;
	}

	public Mat resize(Mat beforeMat, double percent) {
		Mat afterMat = new Mat();
		Size sz = new Size(beforeMat.width() * percent, beforeMat.height() * percent);
		Imgproc.resize(beforeMat, afterMat, sz);
		return afterMat;
	}
	
	public void canny(Mat beforeMat, int width, int height){
		Imgproc.Canny(beforeMat, beforeMat, 50, 200);
	}
}
