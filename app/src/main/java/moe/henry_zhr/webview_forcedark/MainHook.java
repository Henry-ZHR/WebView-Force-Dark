package moe.henry_zhr.webview_forcedark;

import android.webkit.WebSettings;

import androidx.annotation.Keep;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import static android.webkit.WebSettings.FORCE_DARK_ON;

@Keep
public class MainHook implements de.robv.android.xposed.IXposedHookLoadPackage {
  @Override
  public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) {
    XposedHelpers.findAndHookMethod(
        "android.webkit.WebView",
        lpparam.classLoader,
        "getSettings",
        new XC_MethodHook() {
          @Override
          protected void afterHookedMethod(MethodHookParam param) {
            WebSettings webSettings = (WebSettings) param.getResult();
            webSettings.setForceDark(FORCE_DARK_ON);
            param.setResult(webSettings);
          }
        }
    );
  }
}
