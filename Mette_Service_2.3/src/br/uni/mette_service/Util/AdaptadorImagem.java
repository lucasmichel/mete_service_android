package br.uni.mette_service.Util;

import java.io.File
;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.uni.mette_service.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class AdaptadorImagem extends BaseAdapter {
	private Context ctx;
	private final Uri[] imagens;

	public AdaptadorImagem(Context c, Uri[] imagens) {
		this.ctx = c;
		this.imagens = imagens;
	}

	public int getCount() {
		// TODO Auto-generated method stub
		return imagens.length;
	}

	public Uri getItem(int arg0) {
		// TODO Auto-generated method stub
		return imagens[arg0];
	}

	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	public View getView(int posicao, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.activity_gallery_itens, parent, false);

		}
		Uri uriImagem = getItem(posicao);
		ImageView img = (ImageView) convertView
				.findViewById(R.galleryItem.item);
		BitmapManager.getInstance().loadBitmap(uriImagem.toString(), img);
		return convertView;
	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
