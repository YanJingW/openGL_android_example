/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanjingw.opengldemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLSurfaceView;
import android.opengl.GLU;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;


public class MyGLRenderer implements GLSurfaceView.Renderer {

    private Square square;


    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {

        gl10.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        square = new Square();


        gl10.glEnable(GL10.GL_TEXTURE_2D);

        gl10.glActiveTexture(GL10.GL_TEXTURE0);

        int[] textures = new int[1];
        gl10.glGenTextures(1, textures, 0);

        Bitmap bitmap = BitmapFactory.decodeResource(DemoApp.instance.getResources(), R.drawable.sanliuling);
        square.setTex(gl10, textures[0], bitmap);

        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int width, int height) {

        //设置窗口大小
        gl10.glViewport(0, 0, width, height);//
        gl10.glMatrixMode(GL10.GL_PROJECTION);//矩阵种类PROJECTION
        gl10.glLoadIdentity();

        //计算窗口纵横比
        GLU.gluPerspective(gl10, 45.0f,
                (float) width / (float) height,
                0.1f, 100.0f);

        gl10.glMatrixMode(GL10.GL_MODELVIEW);//矩阵种类MODEVIEW
        gl10.glLoadIdentity();//将当前矩阵设为单位矩阵

        gl10.glClearDepthf(1.0f);

        initGL(gl10);

        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();//将当前矩阵设为单位矩阵
        GLU.gluLookAt(gl10, 0.0f, 0.0f, 3.0f,
                0.0f, 0.0f, 0.0f,
                0.0f, 1.0f, 0.0f);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        gl10.glClear(GL10.GL_COLOR_BUFFER_BIT
                | GL10.GL_DEPTH_BUFFER_BIT);
        gl10.glColor4f(1f, 1f, 1f, 1f);
        gl10.glRotatef(1f, 1f, 1f, 1f);

        square.draw(gl10);
    }


    public void initGL(GL10 mGL) {

        mGL.glEnable(GL10.GL_LIGHTING);

        mGL.glEnable(GL10.GL_LIGHT0);

        mGL.glLightfv(GL10.GL_LIGHT0, GL10.GL_AMBIENT, new float[]{0.1f, 0.1f, 0.1f, 1f}, 0);

        mGL.glLightfv(GL10.GL_LIGHT0, GL10.GL_DIFFUSE, new float[]{1f, 1f, 1f, 1f}, 0);

        mGL.glLightfv(GL10.GL_LIGHT0, GL10.GL_POSITION, new float[]{10f, 0f, 10f, 1}, 0);

        mGL.glEnable(GL10.GL_COLOR_MATERIAL);
        mGL.glShadeModel(GL10.GL_SMOOTH);

        mGL.glEnable(GL10.GL_DEPTH_TEST);
        mGL.glEnable(GL10.GL_CULL_FACE);

        mGL.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
        mGL.glEnableClientState(GL10.GL_VERTEX_ARRAY);

    }


}