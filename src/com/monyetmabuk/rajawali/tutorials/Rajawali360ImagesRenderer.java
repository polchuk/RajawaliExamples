package com.monyetmabuk.rajawali.tutorials;

import javax.microedition.khronos.opengles.GL10;

import rajawali.materials.SimpleMaterial;
import rajawali.materials.TextureInfo;
import rajawali.primitives.Plane;
import rajawali.renderer.RajawaliRenderer;
import android.content.Context;
import android.graphics.BitmapFactory;

public class Rajawali360ImagesRenderer extends RajawaliRenderer {
	private TextureInfo[] mTexInf;
	private Plane mPlane;
	private int mFrameCount;
	private SimpleMaterial mMaterial;
	private final static int NUM_TEXTURES = 80;
	
	public Rajawali360ImagesRenderer(Context context) {
		super(context);
		setFrameRate(60);
	}
	
	protected void initScene() {
    	setBackgroundColor(0xffffff);
    	
    	// -- create an array that will contain all TextureInfo objects
    	mTexInf = new TextureInfo[NUM_TEXTURES];
    	
    	for(int i=1; i<=NUM_TEXTURES; ++i) {
    		// -- load all the textures from the drawable folder
    		int identifier = mContext.getResources().getIdentifier(i < 10 ? "m0" + i : "m" + i, "drawable", "com.monyetmabuk.rajawali.tutorials");
    		mTexInf[i-1] = mTextureManager.addTexture(BitmapFactory.decodeResource(mContext.getResources(), identifier), false, true);
    	}
    	
    	mMaterial = new SimpleMaterial();
    	
    	mCamera.setZ(-2);
    	
    	mPlane = new Plane(1, 1, 1, 1, 1);
    	mPlane.setMaterial(mMaterial);
    	mMaterial.addTexture(mTexInf[0]);
    	mPlane.addTexture(mTexInf[0]);
    	addChild(mPlane);
	}
	
    public void onDrawFrame(GL10 glUnused) {
        super.onDrawFrame(glUnused);
        // -- get the texture info list and remove the previous TextureInfo object
        mMaterial.getTextureInfoList().remove(mTexInf[mFrameCount++ % NUM_TEXTURES]);
        // -- add a new TextureInfo object
        mMaterial.getTextureInfoList().add(mTexInf[mFrameCount % NUM_TEXTURES]);
    }
}