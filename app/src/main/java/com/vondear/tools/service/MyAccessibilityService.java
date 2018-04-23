package com.vondear.tools.service;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.List;

/**
 * Created by haohz on 2018/1/31.
 * <p>
 * AccessibilityService
 */


public class MyAccessibilityService extends AccessibilityService {
    /**
     * Callback for {@link AccessibilityEvent}s.
     *
     * @param event The new event. This event is owned by the caller and cannot be used after
     *              this method returns. Services wishing to use the event after this method returns should
     *              make a copy.
     */
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        //获取eventType
        int eventType = event.getEventType();
        if (eventType == AccessibilityEvent.TYPE_VIEW_CLICKED) {
            AccessibilityNodeInfo nodeInfo = null;
            //api>16
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                nodeInfo = getRootInActiveWindow();
            }
            if (nodeInfo != null) {
                //查询文案为BUTTON3的View
                List<AccessibilityNodeInfo> button3 = nodeInfo.findAccessibilityNodeInfosByText("BUTTON3");
                nodeInfo.recycle();
                for (AccessibilityNodeInfo item : button3) {
                    //对这个View执行点击操作
                    item.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                }
            }
        }
    }

    /**
     * Callback for interrupting the accessibility feedback.
     */
    @Override
    public void onInterrupt() {

    }
 /*
    IAccessibilityServiceClientWrapper继承了IAccessibilityServiceClient.Stub，嗯~看到这你应该就明白一大块了，
    AccessibilityService是一个跨进程通信Service。IAccessibilityServiceClientWrapper是这个类的重点关注对象了，
    那他作为一个AIDL的一个server端，他有哪些对外提供的方法呢？
 */
//    public interface IAccessibilityServiceClient{
//        void init(in IAccessibilityServiceConnection connection, int connectionId, IBinder windowToken);
//        void onAccessibilityEvent(in AccessibilityEvent event);
//        void onInterrupt();
//        void onGesture(int gesture);
//        void clearAccessibilityCache();
//        void onKeyEvent(in KeyEvent event, int sequence);
//        void onMagnificationChanged(in Region region, float scale, float centerX, float centerY);
//        void onSoftKeyboardShowModeChanged(int showMode);
//        void onPerformGestureResult(int sequence, boolean completedSuccessfully);
//    }
}
