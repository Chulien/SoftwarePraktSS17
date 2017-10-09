package camera;

import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core.IplImage;


public class cam {

	    public static final String FILENAME = "output.mp4";

	    public static void main(String[] args) throws Exception {
	        OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
	        grabber.start();
	        Frame grabbedImage = grabber.grab();
	        
	        //FrameGrabber fGrabber = FrameGrabber.createDefault(0);
	        //fGrabber.start();
	        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
	        //IplImage fGrabbedImage = converter.convert(fGrabber.grab());
	        //int width = fGrabbedImage.width();
	        //int height = fGrabbedImage.height();

	        CanvasFrame canvasFrame = new CanvasFrame("Cam");
	        canvasFrame.setCanvasSize(grabbedImage.imageWidth, grabbedImage.imageHeight);

	        System.out.println("framerate = " + grabber.getFrameRate());
	        grabber.setFrameRate(grabber.getFrameRate());
	        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(FILENAME,  grabber.getImageWidth(),grabber.getImageHeight());

	        recorder.setVideoCodec(13);
	        recorder.setFormat("mp4");
	        recorder.setPixelFormat(avutil.AV_PIX_FMT_YUV420P);
	        recorder.setFrameRate(30);
	        recorder.setVideoBitrate(10 * 1024 * 1024);

	        recorder.start();
	        while (canvasFrame.isVisible() && (grabbedImage = grabber.grab()) != null) {
	            canvasFrame.showImage(grabbedImage);
	            recorder.record(grabbedImage);
	        }
	        recorder.stop();
	        recorder.close();
	        grabber.stop();
	        grabber.close();
	        canvasFrame.dispose();
	    }
}