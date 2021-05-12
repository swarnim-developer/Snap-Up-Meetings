package snap.up.meetings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.animation.ObjectAnimator;
import android.view.animation.LinearInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.media.MediaPlayer;
import android.webkit.WebViewClient;
import android.view.View;
import com.bumptech.glide.Glide;
import android.graphics.Typeface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class ProfileAnimatorActivity extends  AppCompatActivity  { 
	
	private Timer _timer = new Timer();
	
	private FloatingActionButton _fab;
	private String name = "";
	
	private LinearLayout linear1;
	private ImageView hidden_profile_pic;
	private WebView webview1;
	private LinearLayout linear2;
	private TextView textview2;
	private TextView textview3;
	private ImageView imageview1;
	private TextView textview1;
	
	private Intent S1 = new Intent();
	private SharedPreferences S;
	private TimerTask T;
	private ObjectAnimator X = new ObjectAnimator();
	private ObjectAnimator Y = new ObjectAnimator();
	private ObjectAnimator A = new ObjectAnimator();
	private ObjectAnimator O = new ObjectAnimator();
	private MediaPlayer M;
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.profile_animator);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
		|| ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
			ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
		}
		else {
			initializeLogic();
		}
	}
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		hidden_profile_pic = (ImageView) findViewById(R.id.hidden_profile_pic);
		webview1 = (WebView) findViewById(R.id.webview1);
		webview1.getSettings().setJavaScriptEnabled(true);
		webview1.getSettings().setSupportZoom(true);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview2 = (TextView) findViewById(R.id.textview2);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		S = getSharedPreferences("s", Activity.MODE_PRIVATE);
		
		webview1.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				T = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_Convert(hidden_profile_pic);
							}
						});
					}
				};
				_timer.schedule(T, (int)(3000));
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		_fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				S1.setClass(getApplicationContext(), HomeActivity.class);
				_libsben_animation(imageview1, "libsben", S1);
				T = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								finish();
							}
						});
					}
				};
				_timer.schedule(T, (int)(700));
			}
		});
	}
	
	private void initializeLogic() {
		Window w = this.getWindow();
		w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); w.setStatusBarColor(Color.parseColor("#FFFFFF"));
		getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
		_hide();
		Glide.with(getApplicationContext()).load(Uri.parse(S.getString("photo", "").replace("s96-c", "s2000-c"))).into(hidden_profile_pic);
		webview1.loadUrl(S.getString("photo", "").replace("s96-c", "s2000-c"));
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/roboto_regular.ttf"), Typeface.BOLD);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/poppins_regular.ttf"), Typeface.BOLD);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/oxygen_regular.ttf"), Typeface.NORMAL);
		textview1.setText(S.getString("name", ""));
		_custom_loading(true);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _Convert (final View _view) {
		name = "snap".concat(String.valueOf((long)(SketchwareUtil.getRandom((int)(10000), (int)(99999)))).concat(".png"));
		S.edit().putString("photo path", FileUtil.getPublicDir(Environment.DIRECTORY_DOWNLOADS).concat("/".concat(name))).commit();
		Bitmap returnedBitmap = Bitmap.createBitmap(_view.getWidth(), _view.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(returnedBitmap);
		android.graphics.drawable.Drawable bgDrawable =_view.getBackground();
		if (bgDrawable!=null) {
			bgDrawable.draw(canvas);
		} else {
			canvas.drawColor(Color.WHITE);
		}
		_view.draw(canvas);
		java.io.File pictureFile = new java.io.File(Environment.getExternalStorageDirectory() + "/Download/" + name);
		if (pictureFile == null) {
			showMessage("Error creating media file, check storage permissions: ");
			return; }
		try {
			java.io.FileOutputStream fos = new java.io.FileOutputStream(pictureFile); returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
			fos.close();
			FileUtil.resizeBitmapFileToCircle(S.getString("photo path", ""), S.getString("photo path", ""));
			imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(S.getString("photo path", ""), 1024, 1024));
			_custom_loading(false);
			_Animation();
		} catch (java.io.FileNotFoundException e) {
			showMessage("File not found: " + e.getMessage()); } catch (java.io.IOException e) {
			showMessage("Error accessing file: " + e.getMessage());
		}
	}
	
	
	public void _Animation () {
		X.setTarget(imageview1);
		X.setPropertyName("scaleX");
		X.setFloatValues((float)(0), (float)(1));
		X.setDuration((int)(500));
		X.setRepeatMode(ValueAnimator.REVERSE);
		X.setRepeatCount((int)(0));
		X.start();
		Y.setTarget(imageview1);
		Y.setPropertyName("scaleY");
		Y.setFloatValues((float)(0), (float)(1));
		Y.setDuration((int)(500));
		Y.setRepeatMode(ValueAnimator.REVERSE);
		Y.setRepeatCount((int)(0));
		Y.start();
		T = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						X.setTarget(imageview1);
						X.setPropertyName("scaleX");
						X.setFloatValues((float)(1), (float)(0.7d));
						X.setDuration((int)(1500));
						X.setRepeatMode(ValueAnimator.REVERSE);
						X.setRepeatCount((int)(0));
						X.start();
						Y.setTarget(imageview1);
						Y.setPropertyName("scaleY");
						Y.setFloatValues((float)(1), (float)(0.7d));
						Y.setDuration((int)(1500));
						Y.setRepeatMode(ValueAnimator.REVERSE);
						Y.setRepeatCount((int)(0));
						Y.start();
						M = MediaPlayer.create(getApplicationContext(), R.raw.air_drop);
						M.start();
						T = new TimerTask() {
							@Override
							public void run() {
								runOnUiThread(new Runnable() {
									@Override
									public void run() {
										O.setTarget(linear2);
										O.setPropertyName("translationY");
										O.setFloatValues((float)(0), (float)(-500));
										O.setDuration((int)(1000));
										O.setRepeatMode(ValueAnimator.REVERSE);
										O.setRepeatCount((int)(0));
										O.start();
										T = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														A.setTarget(textview1);
														A.setPropertyName("alpha");
														A.setFloatValues((float)(0), (float)(1));
														A.setDuration((int)(750));
														A.setRepeatMode(ValueAnimator.REVERSE);
														A.setRepeatCount((int)(0));
														A.start();
														T = new TimerTask() {
															@Override
															public void run() {
																runOnUiThread(new Runnable() {
																	@Override
																	public void run() {
																		A.setTarget(textview2);
																		A.setPropertyName("alpha");
																		A.setFloatValues((float)(0), (float)(1));
																		A.setDuration((int)(500));
																		A.setRepeatMode(ValueAnimator.REVERSE);
																		A.setRepeatCount((int)(0));
																		A.start();
																		T = new TimerTask() {
																			@Override
																			public void run() {
																				runOnUiThread(new Runnable() {
																					@Override
																					public void run() {
																						A.setTarget(textview3);
																						A.setPropertyName("alpha");
																						A.setFloatValues((float)(0), (float)(1));
																						A.setDuration((int)(500));
																						A.setRepeatMode(ValueAnimator.REVERSE);
																						A.setRepeatCount((int)(0));
																						A.start();
																						T = new TimerTask() {
																							@Override
																							public void run() {
																								runOnUiThread(new Runnable() {
																									@Override
																									public void run() {
																										_show();
																										T = new TimerTask() {
																											@Override
																											public void run() {
																												runOnUiThread(new Runnable() {
																													@Override
																													public void run() {
																														S1.setClass(getApplicationContext(), HomeActivity.class);
																														_libsben_animation(imageview1, "libsben", S1);
																														T = new TimerTask() {
																															@Override
																															public void run() {
																																runOnUiThread(new Runnable() {
																																	@Override
																																	public void run() {
																																		finish();
																																	}
																																});
																															}
																														};
																														_timer.schedule(T, (int)(700));
																													}
																												});
																											}
																										};
																										_timer.schedule(T, (int)(10000));
																									}
																								});
																							}
																						};
																						_timer.schedule(T, (int)(723));
																					}
																				});
																			}
																		};
																		_timer.schedule(T, (int)(500));
																	}
																});
															}
														};
														_timer.schedule(T, (int)(750));
													}
												});
											}
										};
										_timer.schedule(T, (int)(1100));
									}
								});
							}
						};
						_timer.schedule(T, (int)(500));
					}
				});
			}
		};
		_timer.schedule(T, (int)(500));
	}
	
	
	public void _custom_loading (final boolean _visibility) {
		if (_visibility) {
			if (coreprog == null){
				coreprog = new ProgressDialog(this);
				coreprog.setCancelable(false);
				coreprog.setCanceledOnTouchOutside(false);
				
				coreprog.requestWindowFeature(Window.FEATURE_NO_TITLE);  coreprog.getWindow().setBackgroundDrawable(new android.graphics.drawable.ColorDrawable(Color.TRANSPARENT));
				
			}
			coreprog.setMessage(null);
			coreprog.show();
			View _view = getLayoutInflater().inflate(R.layout.loader, null);
			LinearLayout linear_base = (LinearLayout) _view.findViewById(R.id.linear_base);
			android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
			gd.setColor(Color.TRANSPARENT);
			gd.setCornerRadius(25);
			linear_base.setBackground(gd);
			coreprog.setContentView(_view);
			
		}
		else {
			if (coreprog != null){
				coreprog.dismiss();
			}
		}
	}
	private ProgressDialog coreprog;
	{
	}
	
	
	public void _libsben_animation (final View _view, final String _libsben, final Intent _libsben_intent) {
		_view.setTransitionName(_libsben);
		
		android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(ProfileAnimatorActivity.this, _view, _libsben);
		        startActivity(_libsben_intent, optionsCompat.toBundle());
	}
	
	
	public void _hide () {
		_fab.hide();
	}
	
	
	public void _show () {
		_fab.show();
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}
