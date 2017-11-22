package javaFX.model;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.net.URL;


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
	  *
	  * <P>Once this method returns, the splash screen is realized, which means 
	  * that almost all work on the splash screen should proceed through the event 
	  * dispatch thread. In particular, any call to <tt>dispose</tt> for the 
	  * splash screen must be performed in the event dispatch thread.
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
	  *
	  *<P>This centering service is more or less in {@link hirondelle.stocks.util.ui.UiUtil}; 
	  * this duplication is justified only because the use of  
	  * {@link hirondelle.stocks.util.ui.UiUtil} would entail more class loading, which is 
	  * not desirable for a splash screen.
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