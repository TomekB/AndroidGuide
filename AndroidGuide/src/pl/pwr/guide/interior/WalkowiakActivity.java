package pl.pwr.guide.interior;

import java.util.ArrayList;

import pl.pwr.guide.R;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class WalkowiakActivity extends Activity
{
	boolean tapped = false;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		final ClickableImageView switcherView = (ClickableImageView) this
				.findViewById(R.id.main_image_view);
		switcherView.setContext(this);

		Display display = getWindowManager().getDefaultDisplay();
		int screenWidth = display.getWidth();
		int screenHeight = display.getHeight();
		switcherView.setScreenWidth(screenWidth);
		switcherView.setScreenHeight(screenHeight);

		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.plan1);
		int bitmapWidth = bitmap.getWidth();
		int bitmapHeight = bitmap.getHeight();
		switcherView.setBitmapWidth(bitmapWidth);
		switcherView.setBitmapHeight(bitmapHeight);

		InteriorPropertiesParser parser = new InteriorPropertiesParser();
		parser.parse("place1.xml", WalkowiakActivity.this);

		ArrayList<ClickablePoint> clickablePoints = parser.getClickablePoints();
		switcherView.setClickablePoints(clickablePoints);
		switcherView.setImageBitmap(bitmap);
	}
}