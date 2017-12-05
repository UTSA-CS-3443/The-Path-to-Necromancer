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
	 * serial id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Id of the current background image
	 */
	private String imageId;
	/**
	 * tracker to keep track of the image
	 */
	private MediaTracker fMediaTracker;
	/**
	 * the current image
	 */
	private Image fImage;
	/**
	 * Observer for the splash page image
	 */
	private static final ImageObserver NO_OBSERVER = null; 
	/**
	 * Id for the image
	 */
	private static final int IMAGE_ID = 0;
	/**
	 * constructor.
	 * @param imageId
	 * @throws HeadlessException
	 */
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
	  /**
	   * sets up the image that is to be drawn
	   */
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
		 * serial ID
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * image to be drawn
		 */
		private Image fImage;
		/**
		 * constructer 
		 * @param aParent the main frame
		 * @param aImage  the current image
		 */
			SplashWindow(Frame aParent, Image aImage) {
		       super(aParent);
		       fImage = aImage;
		       setSize(fImage.getWidth(NO_OBSERVER), fImage.getHeight(NO_OBSERVER));
		       Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		       Rectangle window = getBounds();
		       setLocation((screen.width - window.width) / 2,(screen.height - window.height)/2);
		       setVisible(true);
		    }
			/**
			 * draws the image
			 */
		    @Override public void paint(Graphics graphics) {
		      if (fImage != null) {
		        graphics.drawImage(fImage,0,0,this);
		      }
		    }
		    
		  }
		  
}
