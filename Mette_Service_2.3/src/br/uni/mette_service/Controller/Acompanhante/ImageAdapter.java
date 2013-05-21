package br.uni.mette_service.Controller.Acompanhante;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.GaleriaFotosActivity.listarFotosAsyncTask;
import android.content.Context;
import android.content.res.TypedArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter 
{
    private Context context;
    private int itemBackground;

    public ImageAdapter(listarFotosAsyncTask listarFotosAsyncTask) 
    {
     //   context = listarFotosAsyncTask;
        //---setting the style---
  //      TypedArray a = obtainStyledAttributes(R.styleable.Gallery1);
  //      itemBackground = a.getResourceId(
  //          R.styleable.Gallery1_android_galleryItemBackground, 0);
  //      a.recycle();                    
    }


    //---returns the ID of an item--- 
    public Object getItem(int position) {
        return position;
    }            

    public long getItemId(int position) {
        return position;
    }

    //---returns an ImageView view---
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new Gallery.LayoutParams(150, 120));
        imageView.setBackgroundResource(itemBackground);
        return imageView;
    }


	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
}