package cf.substance.natter.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.TextView;

//==============================================================================
public class BaselineTextView extends TextView {
	//--------------------------------------------------------------------------

	public BaselineTextView( Context context ) {
		super( context );
	}

	//--------------------------------------------------------------------------

	public BaselineTextView( Context context, AttributeSet attrs ) {
		super( context, attrs );
	}

	//--------------------------------------------------------------------------

	public BaselineTextView( Context context, AttributeSet attrs, int defStyleAttr ) {
		super( context, attrs, defStyleAttr );
	}

	//--------------------------------------------------------------------------

	@TargetApi( Build.VERSION_CODES.LOLLIPOP )
	public BaselineTextView( Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes ) {
		super( context, attrs, defStyleAttr, defStyleRes );
	}

	//--------------------------------------------------------------------------

	@Override
	protected void onDraw( @NonNull Canvas canvas ) {
		int target = getHeight() - getPaddingBottom();
		canvas.translate( 0, target - getBaseline() );
		super.onDraw( canvas );
	}

	//--------------------------------------------------------------------------
}
//------------------------------------------------------------------------------
