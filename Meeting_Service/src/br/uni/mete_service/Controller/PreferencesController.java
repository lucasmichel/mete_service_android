package br.uni.mete_service.Controller;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesController {

	public static final String PREFS_NAME = "Preferences";
	public static final String USER_EMAIL = "user_email";
	public static final String USER_SENHA = "user_senha";
	private static SharedPreferences preferences = null;

	public static synchronized SharedPreferences getPreferences(Context context) {
		if (preferences == null) {
			preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
		}
		return preferences;
	}

	public static void setUserPreferences(Context context, String email,
			String senha) {

		Editor editor = getPreferences(context).edit();
		editor.putString(USER_EMAIL, email);
		editor.putString(USER_SENHA, senha);
		editor.commit();
	}

	public static Map<String, String> getUserPreferences(Context context) {

		Map<String, String> valuesReference = new HashMap<String, String>();
		valuesReference.put(USER_EMAIL,
				getPreferences(context).getString(USER_EMAIL, ""));
		valuesReference.put(USER_SENHA,
				getPreferences(context).getString(USER_SENHA, ""));

		return valuesReference;
	}

	public static void clearUserPreferences(Context context) {
		Editor editor = getPreferences(context).edit();
		editor.clear();
		editor.commit();
	}

}

