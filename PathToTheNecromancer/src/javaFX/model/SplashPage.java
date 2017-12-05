package javaFX.model;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;

/**
 * creates a splash page for when the game launchers
 * @author HangedDragon96
 *
 */
public class SplashPage extends Frame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id of the current background image
	 */
	private String imageId;
	private MediaTracker fMediaTracker;
	private Image fImage;
	private static final ImageObserver NO_OBSERVER = null; 
	private static final int IMAGE_ID = 0;
	
	public SplashPage(String imageId) throws HeadlessException {
		super();
		if (imageId == null || imageId.trim().length() == 0){
		      throw new IllegalArgumentException("Image Id does not have content.");
		    }
		this.imageId = imageId;
	}
	/**
	  * Show the splash screen to the end user.
	  */
	  public void splash(){
	    initImageAndTracker();
	    setSize(fImage.getWidth(NO_OBSERVER), fImage.getHeight(NO_OBSERVER));
	    center();
	    fMediaTracker.addImage(fImage, IMAGE_ID);
	    try {
	      fMediaTracker.waitForID(IMAGE_ID);
	    }
	    catch(InterruptedException ex){
	      System.out.println("Cannot track image load.");
	    }
	    SplashWindow splashWindow = new SplashWindow(this,fImage);
	  }
	  
	  private void initImageAndTracker(){
		    fMediaTracker = new MediaTracker(this);
		    URL imageURL = SplashScreen.class.getResource(imageId);
		    fImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		  }

	  /**
	  * Centers the frame on the screen.
	  */
	  private void center(){
	    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	    Rectangle frame = getBounds();
	    setLocation((screen.width - frame.width)/2, (screen.height - frame.height)/2);
	  }
	  /**
	   * inner class for splash window
	   * @author HangedDragon96
	   *
	   */
	  private final class SplashWindow extends Window {
	 /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
			SplashWindow(Frame aParent, Image aImage) {
		       super(aParent);
		       fImage = aImage;
		       setSize(fImage.getWidth(NO_OBSERVER), fImage.getHeight(NO_OBSERVER));
		       Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		       Rectangle window = getBounds();
		       setLocation((screen.width - window.width) / 2,(screen.height - window.height)/2);
		       setVisible(true);
		    }
		    @Override public void paint(Graphics graphics) {
		      if (fImage != null) {
		        graphics.drawImage(fImage,0,0,this);
		      }
		    }
		    private Image fImage;
		  }
		  
}
