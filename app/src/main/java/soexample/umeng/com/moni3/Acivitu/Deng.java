package soexample.umeng.com.moni3.Acivitu;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Deng extends View {

    private Paint paint;

    public Deng(Context context) {
        super(context);
        init(context);
    }

    public Deng(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public Deng(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(Color.parseColor("#d43c3c"));
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getRight() / 2, getBottom() / 2, 60, paint);
    }
}
