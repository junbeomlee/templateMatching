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

	public Mat match(String templateName, String imageName) {
		int match_method = Imgproc.TM_CCOEFF;

		Mat img = readImage(imageName);
		Mat templ = readImage(templateName);
		this.canny(templ, 50, 200);
		this.canny(img, 50, 200);

		int result_cols = img.cols() - templ.cols() + 1;
		int result_rows = img.rows() - templ.rows() + 1;
		Mat result = new Mat(result_rows, result_cols, CvType.CV_32FC1);
		// / Do the Matching and Normalize
		Imgproc.matchTemplate(img, templ, result, match_method);
		//System.out.println(Core.norm(result));
		//Core.normalize(result, result, 0, 1, Core.NORM_MINMAX, -1, new Mat());
		//Highgui.imwrite("result.png", result);
		//Localizing the best match with minMaxLoc
		MinMaxLocResult mmr = Core.minMaxLoc(result);
		//mmr.maxVal;
		System.out.println(mmr.maxVal);
		System.out.println(mmr.minVal);
		
		Point matchLoc;
		if (match_method == Imgproc.TM_SQDIFF || match_method == Imgproc.TM_SQDIFF_NORMED) {
			matchLoc = mmr.minLoc;
		} else {
			matchLoc = mmr.maxLoc;
		}

		// / Show me what you got
		Core.rectangle(img, matchLoc, new Point(matchLoc.x + templ.cols(), matchLoc.y + templ.rows()),
				new Scalar(255, 255, 255));

		// Highgui.imwrite("out.png", img);

		return img;
	}

	public Mat readImage(String imageName) {
		Mat mat=Highgui.imread(imageName);
		Mat matG= new Mat(mat.width(),mat.height(),CvType.CV_8UC3);
		Imgproc.cvtColor(mat, matG, Imgproc.COLOR_RGB2GRAY);
		return matG;
		//return Highgui.imread(imageName);
	}

	public Mat resize(Mat beforeMat, double percent) {
		Mat afterMat = new Mat();
		Size sz = new Size(beforeMat.width() * percent, beforeMat.height() * percent);
		Imgproc.resize(beforeMat, afterMat, sz);
		return afterMat;
	}
	
	public Mat resize(Mat beforeMat, double pW, double pH){
		Mat afterMat = new Mat();
		Size sz = new Size(beforeMat.width() * pW, beforeMat.height() * pW);
		Imgproc.resize(beforeMat, afterMat, sz);
		return afterMat;
	}
	
	public void canny(Mat beforeMat, int width, int height){
		Imgproc.Canny(beforeMat, beforeMat, 50, 200);
	}
}
