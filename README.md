# 奥丁InstallSDK

- [奥丁InstallSDK官网](http://www.odinanalysis.com/odininstall.html)
-  一款致力于提升渠道推广效果的基础工具

## 一、集成说明

## **下载并导入SDK**

 从下载中心下载Android最新版本SDK



从下载的文件中，奖jar文件拷贝到项目的libs文件夹中，并使用gradle导入

  Implementation files(‘ libs/OdinInstall_x.x.x.jar’)



或者导入libs目录中所有jar文件

  Implementation fileTree（dir: ‘libs’,include:[‘*.jar’]）



### 权限申请

- 请在AndroidManifest中添加如下权限

```xml
<!-- 网络连接 -->
<uses-permission android:name="android.permission.INTERNET"/>
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!-- 获取mac地址 -->
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
<!-- 获取deviceId -->
<uses-permission android:name="android.permission.READ_PHONE_STATE" />
<!-- 写文件存储 -->
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
```

### 应用配置项

在AndroidManifest.xml 的application 标签设置**Odin-AppKey**

```XML
<meta-data
    android:name="Odin-AppKey"
    android:value="ODININSTALL_APPKEY" />
<meta-data
    android:name="Odin-Secret"
    android:value="ODININSTALL_SECRET" />
```

### 混淆设置

- OdinInstallSDK已经做了混淆处理，再次混淆会导致不可预期的错误，请在您的混淆脚本中添加如下的配置，跳过对OdinInstallSDK的混淆操作

```
-keep class com.odin.odininstall.listener.* {
    public *;
}
-keep public class com.odin.odininstall.OdinInstall{
 *;
}
-keep class com.odin.odininstall.model.* {
    public *;
}
```

## 二、初始化

在自定义Application 中初始化为例

```Java
public class MyApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        if (isMainProcess()) {
         //OdinInstallSDK初始化
         OdinInstall.init(this);
        }
    }

    public boolean isMainProcess() {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return getApplicationInfo().packageName.equals(appProcess.processName);
            }
        }
        return false;
    }
}
```

**备注：**当应用存在多个进程时，确保只在主进程进行初始化

## 三、功能集成

### **快速下载**

如果只需要快速下载功能，无需其他功能（携带参数安装、渠道统计、一键拉起），完成初始化即可

### **一键拉起**

- **AndroidManfiest.xml配置**

在AndroidManfiest.xml 的拉起页面activity标签中添加intent-filter（一般为Mainactivity），配置scheme，用于浏览器中拉起

```xml
<activity
    android:name=".ui.OneKeyWakeupActivity"
    android:launchMode="singleTask">
    <intent-filter>
        <action android:name="android.intent.action.VIEW" />

        <category android:name="android.intent.category.DEFAULT" />
        <category android:name="android.intent.category.BROWSABLE" />
		<!--scheme是奥丁开发者中心odininstall为应用分配的-->
        <data android:scheme="ODININSTALL_SCHEME" />
    </intent-filter>
</activity>
```

**备注**：将ODININSTALL_SCHEME配置成odininstall为应用分配的scheme

- **唤醒参数处理**

```java
public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取唤醒参数
        OdinInstall.getWakeUp(getIntent(), wakeUpAdapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // 此处要调用，否则App在后台运行时，会无法截获
        OdinInstall.getWakeUp(intent, wakeUpAdapter);
    }

    AppWakeUpAdapter wakeUpAdapter = new AppWakeUpAdapter() {

        @Override
        public void onWakeUp(AppData appData) {
            //获取渠道数据
            String channelCode = appData.getChannel();
            //获取绑定数据
            String bindData = appData.getData();
            Log.d("OdinInstall", "onWakeUp : wakeupData = " + appData.toString());
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        wakeUpAdapter = null;
    }
}
```

### **携带参数安装**

```java
OdinInstall.getInstall(new AppInstallAdapter() {

    @Override
    public void onInstall(AppData appData) {
        //获取渠道数据
        String channelCode = appData.getChannel();
        //获取自定义数据
        String bindData = appData.getData();
        Log.i(TAG, "Install安装携带的参数，channelCode = " + channelCode + ", bindData = " + bindData);
    }
});
```

### **渠道统计**

SDK会自动完成访问量，等数据统计工作。

### **注册量统计**

如需统计每个渠道的注册量（对评估渠道质量很重要），可根据自身的业务规则，在确保用户完成 app 注册的情况下调用以下接口：

```java
//用户注册成功后调用
OdinInstall.reportRegister(userId, "email", "phone_num", new AppRegisterListener() {

    @Override
    public void onRegisterRecive(String parentUserID, Error error) {
        Log.i(TAG, "注册完成，上级用户Id: " + parentUserID + "，error: " + error);
    }
});
```