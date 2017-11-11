package com.sergey.a2hpc;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.Dialog;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    String currentUrl = "";
    String userAgent = "Client2HPC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        app:srcCompat="@android:drawable/ic_dialog_email" />*/

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawer.setDrawerListener(toggle);
            toggle.syncState();

            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);

                    // Load home page site
                    final WebView main = (WebView) findViewById(R.id.webWindow);
                    main.getSettings().setUserAgentString(userAgent);
                    main.getSettings().setJavaScriptEnabled(true);
                    main.setWebViewClient(new MyWebViewClient());
                    main.loadUrl("https://2hpc.ru/android_home/?androidClient");

                    //Error Connect
                    main.setWebViewClient(new MyWebViewClient() {
                        @Override
                        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                            currentUrl = failingUrl;
                            View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                            final Toast toast = new Toast(MainActivity.this);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.setDuration(Toast.LENGTH_SHORT);
                            toast.setView(layout);
                            toast.show();
                        }
                    });
                    //End Error Connect

                    //ProgressBar
                    View layout = getLayoutInflater().inflate(R.layout.dialog_main, null);
                    final Toast toast = new Toast(MainActivity.this);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(layout);

                    main.setWebChromeClient(new WebChromeClient() {
                        public void onProgressChanged(WebView main, int progress) {
                        currentUrl = main.getUrl();
                        if (progress > 0 || progress < 100) {
                            toast.show();
                        }
                            if (progress == 100) {
                                toast.cancel();
                            }
                        }
                    });
                    //End ProgressBar

    }

    private static long back_pressed;

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        WebView main = (WebView) findViewById(R.id.webWindow);
        WebView soft = (WebView) findViewById(R.id.webWindow);
        WebView hard = (WebView) findViewById(R.id.webWindow);
        WebView external = (WebView) findViewById(R.id.webWindow);
        WebView lan = (WebView) findViewById(R.id.webWindow);
        WebView internet = (WebView) findViewById(R.id.webWindow);
        WebView notes = (WebView) findViewById(R.id.webWindow);
        WebView search = (WebView) findViewById(R.id.webWindow);
        WebView reLoadUrl = (WebView) findViewById(R.id.webWindow);
        WebView about = (WebView) findViewById(R.id.webWindow);

            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else if (main.canGoBack()) {
                main.goBack();
            } else if (soft.canGoBack()) {
                soft.goBack();
            } else if (hard.canGoBack()) {
                hard.goBack();
            } else if (external.canGoBack()) {
                external.goBack();
            } else if (lan.canGoBack()) {
                lan.goBack();
            } else if (internet.canGoBack()) {
                internet.goBack();
            } else if (notes.canGoBack()) {
                notes.goBack();
            } else if (search.canGoBack()) {
                search.goBack();
            } else if (reLoadUrl.canGoBack()) {
                reLoadUrl.goBack();
            } else if (about.canGoBack()) {
                about.goBack();
            } else if (back_pressed + 2500 > System.currentTimeMillis()) {
                finish();
            } else {
                    View layout=getLayoutInflater().inflate(R.layout.exit_main,null);
                    final Toast toast = new Toast(MainActivity.this);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();
            }
            back_pressed = System.currentTimeMillis();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.soft) {

                                final WebView soft=(WebView)findViewById(R.id.webWindow);
                                soft.getSettings().setUserAgentString(userAgent);
                                soft.getSettings().setJavaScriptEnabled(true);
                                soft.setWebViewClient(new MyWebViewClient());
                                soft.loadUrl("https://2hpc.ru/category/software/?androidClient");

                                //Error Connect
                                soft.setWebViewClient(new MyWebViewClient() {
                                    @Override
                                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                        currentUrl = failingUrl;
                                        View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                        final Toast toast = new Toast(MainActivity.this);
                                        toast.setGravity(Gravity.CENTER,0,0);
                                        toast.setDuration(Toast.LENGTH_SHORT);
                                        toast.setView(layout);
                                        toast.show();

                                    }
                                });
                                //End Error Connect

                                //ProgressBar
                                View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                final Toast toast = new Toast(MainActivity.this);
                                toast.setGravity(Gravity.CENTER,0,0);
                                toast.setDuration(Toast.LENGTH_LONG);
                                toast.setView(layout);

                                soft.setWebChromeClient(new WebChromeClient() {
                                    public void onProgressChanged(WebView soft, int progress) {
                                    currentUrl = soft.getUrl();
                                    if ( progress > 0 || progress < 100 ) { toast.show(); }
                                    if ( progress == 100 ) { toast.cancel(); }
                                    }
                                });
                                //End ProgressBar

        } else if (id == R.id.hard) {

                                        final WebView hard=(WebView)findViewById(R.id.webWindow);
                                        hard.getSettings().setUserAgentString(userAgent);
                                        hard.getSettings().setJavaScriptEnabled(true);
                                        hard.setWebViewClient(new MyWebViewClient());
                                        hard.loadUrl("https://2hpc.ru/category/hardware/?androidClient");

                                        //Error Connect
                                        hard.setWebViewClient(new MyWebViewClient() {
                                            @Override
                                            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                currentUrl = failingUrl;
                                                View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                final Toast toast = new Toast(MainActivity.this);
                                                toast.setGravity(Gravity.CENTER,0,0);
                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                toast.setView(layout);
                                                toast.show();

                                            }
                                        });
                                        //End Error Connect

                                        //ProgressBar
                                        View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                        final Toast toast = new Toast(MainActivity.this);
                                        toast.setGravity(Gravity.CENTER,0,0);
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);

                                        hard.setWebChromeClient(new WebChromeClient() {
                                            public void onProgressChanged(WebView hard, int progress) {
                                            currentUrl = hard.getUrl();
                                            if ( progress > 0 || progress < 100 ) { toast.show(); }
                                            if ( progress == 100 ) { toast.cancel(); }
                                            }
                                        });
                                        //End ProgressBar

        } else if (id == R.id.external) {

                                            final WebView external=(WebView)findViewById(R.id.webWindow);
                                            external.getSettings().setUserAgentString(userAgent);
                                            external.getSettings().setJavaScriptEnabled(true);
                                            external.setWebViewClient(new MyWebViewClient());
                                            external.loadUrl("https://2hpc.ru/category/external_device/?androidClient");

                                            //Error Connect
                                            external.setWebViewClient(new MyWebViewClient() {
                                                @Override
                                                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                    currentUrl = failingUrl;
                                                    View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                    final Toast toast = new Toast(MainActivity.this);
                                                    toast.setGravity(Gravity.CENTER,0,0);
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.setView(layout);
                                                    toast.show();

                                                }
                                            });
                                            //End Error Connect

                                            //ProgressBar
                                            View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                            final Toast toast = new Toast(MainActivity.this);
                                            toast.setGravity(Gravity.CENTER,0,0);
                                            toast.setDuration(Toast.LENGTH_LONG);
                                            toast.setView(layout);

                                            external.setWebChromeClient(new WebChromeClient() {
                                                public void onProgressChanged(WebView hard, int progress) {
                                               currentUrl = external.getUrl();
                                               if ( progress > 0 || progress < 100 ) { toast.show(); }
                                               if ( progress == 100 ) { toast.cancel(); }
                                                }
                                            });
                                            //End ProgressBar

        } else if (id == R.id.lan) {

                                        final WebView lan=(WebView)findViewById(R.id.webWindow);
                                        lan.getSettings().setUserAgentString(userAgent);
                                        lan.getSettings().setJavaScriptEnabled(true);
                                        lan.setWebViewClient(new MyWebViewClient());
                                        lan.loadUrl("https://2hpc.ru/category/lan/?androidClient");

                                        //Error Connect
                                        lan.setWebViewClient(new MyWebViewClient() {
                                            @Override
                                            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                currentUrl = failingUrl;
                                                View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                final Toast toast = new Toast(MainActivity.this);
                                                toast.setGravity(Gravity.CENTER,0,0);
                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                toast.setView(layout);
                                                toast.show();

                                            }
                                        });
                                        //End Error Connect

                                        //ProgressBar
                                        View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                        final Toast toast = new Toast(MainActivity.this);
                                        toast.setGravity(Gravity.CENTER,0,0);
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);

                                        lan.setWebChromeClient(new WebChromeClient() {
                                            public void onProgressChanged(WebView lan, int progress) {
                                                currentUrl = lan.getUrl();
                                                if ( progress > 0 || progress < 100 ) { toast.show(); }
                                                if ( progress == 100 ) { toast.cancel(); }
                                            }
                                        });
                                        //End ProgressBar

        } else if (id == R.id.internet) {

                                            final WebView internet=(WebView)findViewById(R.id.webWindow);
                                            internet.getSettings().setUserAgentString(userAgent);
                                            internet.getSettings().setJavaScriptEnabled(true);
                                            internet.setWebViewClient(new MyWebViewClient());
                                            internet.loadUrl("https://2hpc.ru/category/internet/?androidClient");

                                            //Error Connect
                                            internet.setWebViewClient(new MyWebViewClient() {
                                                @Override
                                                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                    currentUrl = failingUrl;
                                                    View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                    final Toast toast = new Toast(MainActivity.this);
                                                    toast.setGravity(Gravity.CENTER,0,0);
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.setView(layout);
                                                    toast.show();

                                                }
                                            });
                                            //End Error Connect

                                            //ProgressBar
                                            View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                            final Toast toast = new Toast(MainActivity.this);
                                            toast.setGravity(Gravity.CENTER,0,0);
                                            toast.setDuration(Toast.LENGTH_LONG);
                                            toast.setView(layout);

                                            internet.setWebChromeClient(new WebChromeClient() {
                                                public void onProgressChanged(WebView internet, int progress) {
                                                    currentUrl = internet.getUrl();
                                                    if ( progress > 0 || progress < 100 ) { toast.show(); }
                                                    if ( progress == 100 ) { toast.cancel(); }
                                                }
                                            });
                                            //End ProgressBar


        } else if (id == R.id.notes) {

                                        final WebView notes=(WebView)findViewById(R.id.webWindow);
                                        notes.getSettings().setUserAgentString(userAgent);
                                        notes.getSettings().setJavaScriptEnabled(true);
                                        notes.setWebViewClient(new MyWebViewClient());
                                        notes.loadUrl("https://2hpc.ru/category/notes/?androidClient");

                                        //Error Connect
                                        notes.setWebViewClient(new MyWebViewClient() {
                                            @Override
                                            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                currentUrl = failingUrl;
                                                View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                final Toast toast = new Toast(MainActivity.this);
                                                toast.setGravity(Gravity.CENTER,0,0);
                                                toast.setDuration(Toast.LENGTH_SHORT);
                                                toast.setView(layout);
                                                toast.show();

                                            }
                                        });
                                        //End Error Connect

                                        //ProgressBar
                                        View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                        final Toast toast = new Toast(MainActivity.this);
                                        toast.setGravity(Gravity.CENTER,0,0);
                                        toast.setDuration(Toast.LENGTH_LONG);
                                        toast.setView(layout);

                                        notes.setWebChromeClient(new WebChromeClient() {
                                            public void onProgressChanged(WebView notes, int progress) {
                                            currentUrl = notes.getUrl();
                                            if ( progress > 0 || progress < 100 ) { toast.show(); }
                                            if ( progress == 100 ) { toast.cancel(); }
                                            }
                                        });
                                        //End ProgressBar

        } else if (id == R.id.about) {

                                        final WebView about=(WebView)findViewById(R.id.webWindow);
                                        about.loadUrl("file:///android_asset/about.html");

        } else if (id == R.id.exit) {

                                        finish();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Button reLoad
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_icons, menu);
        return true;
    }

    //Click on Button reLoad
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.home){

                                            final WebView main = (WebView) findViewById(R.id.webWindow);
                                            main.getSettings().setUserAgentString(userAgent);
                                            main.getSettings().setJavaScriptEnabled(true);
                                            main.setWebViewClient(new MyWebViewClient());
                                            main.loadUrl("https://2hpc.ru/android_home/?androidClient");

                                            //Error Connect
                                            main.setWebViewClient(new MyWebViewClient() {
                                                @Override
                                                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                    //main.loadUrl("file:///android_asset/custom_url_error.htm");
                                                    currentUrl = failingUrl;
                                                    View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                    final Toast toast = new Toast(MainActivity.this);
                                                    toast.setGravity(Gravity.CENTER,0,0);
                                                    toast.setDuration(Toast.LENGTH_SHORT);
                                                    toast.setView(layout);
                                                    toast.show();

                                                }
                                            });
                                            //End Error Connect

                                            //ProgressBar
                                            View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                            final Toast toast = new Toast(MainActivity.this);
                                            toast.setGravity(Gravity.CENTER,0,0);
                                            toast.setDuration(Toast.LENGTH_LONG);
                                            toast.setView(layout);

                                            main.setWebChromeClient(new WebChromeClient() {
                                                public void onProgressChanged(WebView search, int progress) {
                                                    currentUrl = search.getUrl();
                                                    if ( progress > 0 || progress < 100 ) { toast.show(); }
                                                    if ( progress == 100 ) { toast.cancel(); }
                                                }
                                            });
                                            //End ProgressBar

                                            return true;
        }
        if(item.getItemId() == R.id.search){

                                                final WebView search = (WebView) findViewById(R.id.webWindow);
                                                search.getSettings().setUserAgentString(userAgent);
                                                search.getSettings().setJavaScriptEnabled(true);
                                                search.setWebViewClient(new MyWebViewClient());
                                                search.loadUrl("file:///android_asset/search.html");

                                                //Error Connect
                                                search.setWebViewClient(new MyWebViewClient() {
                                                    @Override
                                                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                        //main.loadUrl("file:///android_asset/custom_url_error.htm");
                                                        currentUrl = failingUrl;
                                                        View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                        final Toast toast = new Toast(MainActivity.this);
                                                        toast.setGravity(Gravity.CENTER,0,0);
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.setView(layout);
                                                        toast.show();

                                                    }
                                                });
                                                //End Error Connect

                                                //ProgressBar
                                                View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                                final Toast toast = new Toast(MainActivity.this);
                                                toast.setGravity(Gravity.CENTER,0,0);
                                                toast.setDuration(Toast.LENGTH_LONG);
                                                toast.setView(layout);

                                                search.setWebChromeClient(new WebChromeClient() {
                                                    public void onProgressChanged(WebView search, int progress) {
                                                        currentUrl = search.getUrl();
                                                        if ( progress > 0 || progress < 100 ) { toast.show(); }
                                                        if ( progress == 100 ) { toast.cancel(); }
                                                    }
                                                });
                                                //End ProgressBar

                                                return true;
        }
        if(item.getItemId() == R.id.reLoad){

                                                final WebView reLoadUrl = (WebView) findViewById(R.id.webWindow);
                                                reLoadUrl.getSettings().setUserAgentString(userAgent);
                                                reLoadUrl.getSettings().setJavaScriptEnabled(true);
                                                reLoadUrl.setWebViewClient(new MyWebViewClient());
                                                reLoadUrl.loadUrl(currentUrl);

                                                //Error Connect
                                                reLoadUrl.setWebViewClient(new MyWebViewClient() {
                                                    @Override
                                                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                                                        currentUrl = failingUrl;
                                                        View layout=getLayoutInflater().inflate(R.layout.disconnect_main,null);
                                                        final Toast toast = new Toast(MainActivity.this);
                                                        toast.setGravity(Gravity.CENTER,0,0);
                                                        toast.setDuration(Toast.LENGTH_SHORT);
                                                        toast.setView(layout);
                                                        toast.show();

                                                    }
                                                });
                                                //End Error Connect

                                                //ProgressBar
                                                View layout=getLayoutInflater().inflate(R.layout.dialog_main,null);
                                                final Toast toast = new Toast(MainActivity.this);
                                                toast.setGravity(Gravity.CENTER,0,0);
                                                toast.setDuration(Toast.LENGTH_LONG);
                                                toast.setView(layout);

                                                reLoadUrl.setWebChromeClient(new WebChromeClient() {
                                                    public void onProgressChanged(WebView reLoadUrl, int progress) {
                                                        currentUrl = reLoadUrl.getUrl();
                                                        if ( progress > 0 || progress < 100 ) { toast.show(); }
                                                        if ( progress == 100 ) { toast.cancel(); }
                                                    }
                                                });
                                                //End ProgressBar

                                                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
