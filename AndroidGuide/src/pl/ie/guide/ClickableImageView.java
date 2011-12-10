package pl.ie.guide;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ClickableImageView extends ImageView
{
	private static final int POINT_RADIUS = 25;

	private int screenWidth;
	private int screenHeight;
	private int bitmapWidth;
	private int bitmapHeight;
	private ArrayList<ClickablePoint> clickablePoints;

	boolean tapped;

	// set maximum scroll amount (based on center of image)
	int maxX = (int) ((bitmapWidth / 2) - (screenWidth / 2));
	int maxY = (int) ((bitmapHeight / 2) - (screenHeight / 2));

	// set scroll limits
	int maxLeft = (maxX * -1);
	int maxRight = maxX;
	int maxTop = (maxY * -1);
	int maxBottom = maxY;

	float downX, downY;
	int totalX, totalY;
	int scrollByX, scrollByY;
	private Context context;

	public ClickableImageView(Context context)
	{
		super(context);
		this.context=context;
		clickablePoints = new ArrayList<ClickablePoint>();
	}

	public ClickableImageView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		this.context=context;
		clickablePoints = new ArrayList<ClickablePoint>();
	}

	@Override
	public void setImageBitmap(Bitmap bm)
	{
		// TODO Auto-generated method stub
		super.setImageBitmap(bm);

		bitmapWidth = bm.getWidth();
		bitmapHeight = bm.getHeight();

		// set maximum scroll amount (based on center of image)
		maxX = (int) ((bitmapWidth / 2) - (screenWidth / 2));
		maxY = (int) ((bitmapHeight / 2) - (screenHeight / 2));

		// set scroll limits
		maxLeft = (maxX * -1);
		maxRight = maxX;
		maxTop = (maxY * -1);
		maxBottom = maxY;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		// int posX = getScrollX() + bitmapWidth / 2 - screenWidth / 2;
		// int posY = getScrollY() + bitmapHeight / 2 - screenHeight / 2;
		// Log.d("ACTUALC SCROLL: ", posX + " " + posY);

		for (ClickablePoint point : clickablePoints)
		{
			Paint paint = new Paint();
			paint.setARGB(255, 0, 255, 0);
			canvas.drawCircle(point.getPosX() - bitmapWidth / 2 + screenWidth
					/ 2, point.getPosY() - bitmapHeight / 2 + screenHeight / 2,
					15, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		float currentX, currentY;
		switch (event.getAction())
		{
		case MotionEvent.ACTION_DOWN:
			downX = event.getX();
			downY = event.getY();
			tapped = true;
			break;

		case MotionEvent.ACTION_UP:
			Log.d("TAPPED: ", tapped + "");
			if (tapped)
			{
				checkPoints(event.getX(), event.getY());
			}
			break;

		case MotionEvent.ACTION_MOVE:
			currentX = event.getX();
			currentY = event.getY();
			scrollByX = (int) (downX - currentX);
			scrollByY = (int) (downY - currentY);
			tapped = false;

			// scrolling to left side of image (pic moving to the right)
			if (currentX > downX)
			{
				if (totalX == maxLeft)
				{
					scrollByX = 0;
				}
				if (totalX > maxLeft)
				{
					totalX = totalX + scrollByX;
				}
				if (totalX < maxLeft)
				{
					scrollByX = maxLeft - (totalX - scrollByX);
					totalX = maxLeft;
				}
			}

			// scrolling to right side of image (pic moving to the left)
			if (currentX < downX)
			{
				if (totalX == maxRight)
				{
					scrollByX = 0;
				}
				if (totalX < maxRight)
				{
					totalX = totalX + scrollByX;
				}
				if (totalX > maxRight)
				{
					scrollByX = maxRight - (totalX - scrollByX);
					totalX = maxRight;
				}
			}

			// scrolling to top of image (pic moving to the bottom)
			if (currentY > downY)
			{
				if (totalY == maxTop)
				{
					scrollByY = 0;
				}
				if (totalY > maxTop)
				{
					totalY = totalY + scrollByY;
				}
				if (totalY < maxTop)
				{
					scrollByY = maxTop - (totalY - scrollByY);
					totalY = maxTop;
				}
			}

			// scrolling to bottom of image (pic moving to the top)
			if (currentY < downY)
			{
				if (totalY == maxBottom)
				{
					scrollByY = 0;
				}
				if (totalY < maxBottom)
				{
					totalY = totalY + scrollByY;
				}
				if (totalY > maxBottom)
				{
					scrollByY = maxBottom - (totalY - scrollByY);
					totalY = maxBottom;
				}
			}

			scrollBy(scrollByX, scrollByY);
			downX = currentX;
			downY = currentY;
			break;
		}
		return true;
	}

	private void checkPoints(float posX, float posY)
	{
		for (ClickablePoint point : clickablePoints)
		{
			if (posX > point.getPosX() - POINT_RADIUS
			&& posX < point.getPosX() + POINT_RADIUS
			&& posY > point.getPosY() - POINT_RADIUS
			&& posY < point.getPosY() + POINT_RADIUS)
			{	
				final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.short_desc);
                dialog.setTitle(point.getName());
                dialog.setCancelable(true);
                
                //set up text
                TextView text = (TextView) dialog.findViewById(R.id.short_desc_textview);
                Log.d("shORT",point.getShortDescription());
                text.setText(point.getFullDescription());
 
//                //set up image view
//                ImageView img = (ImageView) dialog.findViewById(R.id.ImageView01);
//                img.setImageResource(R.drawable.nista_logo);
 
                Button button = (Button) dialog.findViewById(R.id.short_desc_close_button);
                button.setText("Zamknij");
                button.setOnClickListener(new OnClickListener() {
                @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                
                button = (Button) dialog.findViewById(R.id.short_desc_expand_button);
                button.setText("Wi«cej");
                button.setOnClickListener(new OnClickListener() {
                @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        //pokaz pelen opis
                    }
                });
                
                dialog.show();
			}
		}

	}

	public int getScreenWidth()
	{
		return screenWidth;
	}

	public void setScreenWidth(int screenWidth)
	{
		this.screenWidth = screenWidth;
	}

	public int getScreenHeight()
	{
		return screenHeight;
	}

	public void setScreenHeight(int screenHeight)
	{
		this.screenHeight = screenHeight;
	}

	public int getBitmapWidth()
	{
		return bitmapWidth;
	}

	public void setBitmapWidth(int bitmapWidth)
	{
		this.bitmapWidth = bitmapWidth;
	}

	public int getBitmapHeight()
	{
		return bitmapHeight;
	}

	public void setBitmapHeight(int bitmapHeight)
	{
		this.bitmapHeight = bitmapHeight;
	}

	public ArrayList<ClickablePoint> getClickablePoints()
	{
		return clickablePoints;
	}

	public void setClickablePoints(ArrayList<ClickablePoint> clickablePoints)
	{
		this.clickablePoints = clickablePoints;
	}

	public void setContext(Context context)
	{
		this.context = context;
	}
}
