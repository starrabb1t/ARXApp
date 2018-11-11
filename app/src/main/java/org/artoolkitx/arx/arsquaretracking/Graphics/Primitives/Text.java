package org.artoolkitx.arx.arsquaretracking.Graphics.Primitives;

/*import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.opengl.GLUtils;*/

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

//import org.artoolkitx.arx.arsquaretracking.R;
import org.artoolkitx.arx.arxj.rendering.ARDrawable;
import org.artoolkitx.arx.arxj.rendering.RenderUtils;
import org.artoolkitx.arx.arxj.rendering.ShaderProgram;

//import javax.microedition.khronos.opengles.GL10;

public final class Text implements ARDrawable {
    private FloatBuffer mVertexBuffer;
    private FloatBuffer mColorBuffer;
    private ByteBuffer mIndexBuffer;
    private ShaderProgram shaderProgram;

    public Text() {
        this(1.0F);
    }

    public Text(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    public Text(float size) {
        this(size, 0.0F, 0.0F, 0.0F);
    }

    public Text(float size, float x, float y, float z) {
        this.setArrays(size, x, y, z);
    }

    public FloatBuffer getmVertexBuffer() {
        return this.mVertexBuffer;
    }

    public FloatBuffer getmColorBuffer() {
        return this.mColorBuffer;
    }

    public ByteBuffer getmIndexBuffer() {
        return this.mIndexBuffer;
    }

    private void setArrays(float size, float x, float y, float z) {
        float hs = size / 2.0F;
        float[] vertices = new float[]{x - hs, y - hs, z - hs, x + hs, y - hs, z - hs, x + hs, y + hs, z - hs, x - hs, y + hs, z - hs, x - hs, y - hs, z + hs, x + hs, y - hs, z + hs, x + hs, y + hs, z + hs, x - hs, y + hs, z + hs};
        float c = 1.0F;
        float[] colors = new float[]{0.0F, 0.0F, 0.0F, c, c, 0.0F, 0.0F, c, c, c, 0.0F, c, 0.0F, c, 0.0F, c, 0.0F, 0.0F, c, c, c, 0.0F, c, c, c, c, c, c, 0.0F, c, c, c};
        byte[] indices = new byte[]{1, 0, 2, 2, 0, 3, 1, 2, 5, 5, 2, 6, 4, 5, 7, 7, 5, 6, 0, 4, 3, 3, 4, 7, 7, 6, 3, 6, 2, 3, 0, 1, 4, 4, 1, 5};
        this.mVertexBuffer = RenderUtils.buildFloatBuffer(vertices);
        this.mColorBuffer = RenderUtils.buildFloatBuffer(colors);
        this.mIndexBuffer = RenderUtils.buildByteBuffer(indices);
    }

    public void draw(float[] projectionMatrix, float[] modelViewMatrix) {
        this.shaderProgram.setProjectionMatrix(projectionMatrix);
        this.shaderProgram.setModelViewMatrix(modelViewMatrix);
        this.shaderProgram.render(this.getmVertexBuffer(), this.getmColorBuffer(), this.getmIndexBuffer());
    }

    public void setShaderProgram(ShaderProgram shaderProgram) {
        this.shaderProgram = shaderProgram;
    }

    //TODO: implement textToBitmap(); BtimapToTexture()

    /*public void createTextureFromString(){
        // Create an empty, mutable bitmap
        Bitmap bitmap = Bitmap.createBitmap(256, 256, Bitmap.Config.ARGB_4444);
// get a canvas to paint over the bitmap
        Canvas canvas = new Canvas(bitmap);
        bitmap.eraseColor(0);

// get a background image from resources
// note the image format must match the bitmap format
        Drawable background = context.getResources().getDrawable(R.drawable.background);
        background.setBounds(0, 0, 256, 256);
        background.draw(canvas); // draw the background to our bitmap

// Draw the text
        Paint textPaint = new Paint();
        textPaint.setTextSize(32);
        textPaint.setAntiAlias(true);
        textPaint.setARGB(0xff, 0x00, 0x00, 0x00);
// draw the text centered
        canvas.drawText("Hello World", 16,112, textPaint);

//Generate one texture pointer...
        gl.glGenTextures(1, textures, 0);
//...and bind it to our array
        gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);

//Create Nearest Filtered Texture
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_LINEAR);

//Different possible texture parameters, e.g. GL10.GL_CLAMP_TO_EDGE
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_S, GL10.GL_REPEAT);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_WRAP_T, GL10.GL_REPEAT);

//Use the Android GLUtils to specify a two-dimensional texture image from our bitmap
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);

//Clean up
        bitmap.recycle();
    }*/
}