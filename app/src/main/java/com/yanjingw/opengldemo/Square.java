package com.yanjingw.opengldemo;

import android.graphics.Bitmap;
import android.opengl.GLUtils;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by wangyanjing on 2017/9/1.
 */

public class Square {

    // center our unit cube around the origin so translations make sense
    float vertices[] = {
            // front
            -0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f,

            // back
            0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f,

            // top
            -0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f,

            // bottom
            -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, -0.5f, -0.5f, -0.5f,

            // right
            0.5f, 0.5f, 0.5f, 0.5f, 0.5f, -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, 0.5f,

            // left
            -0.5f, 0.5f, -0.5f, -0.5f, 0.5f, 0.5f, -0.5f, -0.5f, 0.5f, -0.5f, -0.5f, -0.5f,
    };

    short indices[] = {
            //front
            0, 1, 2, 2, 3, 0,
            //back
            4, 5, 6, 6, 7, 4,
            // top
            8, 9, 10, 10, 11, 8,
            // bottom
            12, 13, 14, 14, 15, 12,
            //right
            16, 17, 18, 18, 19, 16,
            //left
            20, 21, 22, 22, 23, 20,
    };

    float normals[] = {
            // front
            0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1,
            // back
            0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1,
            // top
            0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0,
            // bottom
            0, -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0,
            // right
            1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0,
            // left
            -1, 0, 0, -1, 0, 0, -1, 0, 0, -1, 0, 0,};

    float texCoords[] = {
            1, 0, 1, 1, 0, 1, 0, 0,
            1, 0, 1, 1, 0, 1, 0, 0,
            1, 0, 1, 1, 0, 1, 0, 0,
            1, 0, 1, 1, 0, 1, 0, 0,
            1, 0, 1, 1, 0, 1, 0, 0,
            1, 0, 1, 1, 0, 1, 0, 0,
    };


    float[] colors = {
            0f, 0f, 0f, 1f,
            0f, 0f, 1f, 1f,
            0f, 1f, 0f, 1f,
            0f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            1f, 0f, 1f, 1f,
            1f, 1f, 0f, 1f,
            1f, 1f, 1f, 1f,
            1f, 0f, 0f, 1f,
            0f, 1f, 0f, 1f,
            0f, 0f, 1f, 1f,
            1f, 0f, 1f, 1f
    };


    private FloatBuffer mVertexBuffer;
    private FloatBuffer mNormalBuffer;
    private ShortBuffer mIndexBuffer;
    private FloatBuffer mCoordBuffer;
    private boolean texEnabled = false;
    private int mTextureID;
    private FloatBuffer colorBuffer;


    public Square() {
        super();

        mVertexBuffer = BufferTransUtils.getFloatBufferFromFloatArray(vertices);
        mNormalBuffer = BufferTransUtils.getFloatBufferFromFloatArray(normals);
        mIndexBuffer = BufferTransUtils.getShortBufferFromShortArray(indices);
        colorBuffer = BufferTransUtils.getFloatBufferFromFloatArray(colors);
    }

    public void draw(GL10 gl10) {
        if (texEnabled) {
            gl10.glEnable(GL10.GL_TEXTURE_2D);
            gl10.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);
            gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl10.glTexCoordPointer(2, GL10.GL_FLOAT, 0, mCoordBuffer);
        }

        gl10.glFrontFace(GL10.GL_CCW);
        gl10.glEnable(GL10.GL_CULL_FACE);
        gl10.glCullFace(GL10.GL_FRONT);

        gl10.glEnableClientState(GL10.GL_NORMAL_ARRAY);
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glEnableClientState(GL10.GL_COLOR_ARRAY);

        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, mVertexBuffer);
        gl10.glNormalPointer(GL10.GL_FLOAT, 0, mNormalBuffer);
        gl10.glColorPointer(4, GL10.GL_FLOAT, 0, colorBuffer);


        gl10.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_SHORT, mIndexBuffer);

        gl10.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl10.glDisableClientState(GL10.GL_NORMAL_ARRAY);

        gl10.glDisable(GL10.GL_CULL_FACE);

        if (texEnabled) {
            gl10.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
            gl10.glDisable(GL10.GL_TEXTURE_2D);
        }
    }


    void setTex(GL10 gl, int textureID, Bitmap bitmap) {
        mCoordBuffer = BufferTransUtils.getFloatBufferFromFloatArray(texCoords);

        mTextureID = textureID;

        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureID);

        Bitmap bitmap256 = Bitmap.createScaledBitmap(bitmap, 256, 256, false);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap256, 0);
        bitmap.recycle();
        bitmap256.recycle();

        gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER,
                GL10.GL_NEAREST);
        gl.glTexParameterf(GL10.GL_TEXTURE_2D,
                GL10.GL_TEXTURE_MAG_FILTER,
                GL10.GL_LINEAR);
        texEnabled = true;

    }


}
