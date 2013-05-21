package br.uni.mette_service.Controller.Acompanhante;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import br.uni.mette_service.R;
import br.uni.mette_service.Controller.Acompanhante.GaleriaFotosActivity.listarFotosAsyncTask;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

public class GalleryAdapter extends BaseAdapter {

	int galleryItem;
	private Context context;
	private ArrayList<String> gallery_list;
	//private GalleryConfig galleryConfig;
	
	public GalleryAdapter(Context context, ArrayList<String> gallery_list) {
		super();
		this.context = context;
		this.gallery_list = gallery_list;
	}
	
	

	public int getCount() {
		return gallery_list.size();
	}

	public Object getItem(int position) {
		return gallery_list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView = new ImageView(context);
		imageView.setImageDrawable(loadImageFromURL(gallery_list.get(position)));
		imageView.setLayoutParams(new Gallery.LayoutParams(150,120));
		imageView.setScaleType(ImageView.ScaleType.FIT_XY);
		return imageView;
	}

	private Drawable loadImageFromURL(String url)
	{
		try
		{
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src");
			return d;
		}catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
}
