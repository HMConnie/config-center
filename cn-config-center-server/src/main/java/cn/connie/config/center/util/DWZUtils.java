package cn.connie.config.center.util;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;

public abstract class DWZUtils {

    private static final String SUCCESS_STATUS_CODE="200";

    private static final String ERROR_STATUS_CODE="300";

    private static final String SESSION_OUT_STATUS_CODE="301";

    public static String ajaxForwardSuccess(String message, String navTabId, String forwardUrl,
        CallbackType callbackType) {
        JSONObject json=new JSONObject();
        json.put("statusCode", SUCCESS_STATUS_CODE);
        json.put("message", StringUtils.isNotBlank(message) ? message : "");
        json.put("navTabId", navTabId == null ? "" : navTabId);
        json.put("forwardUrl", forwardUrl == null ? "" : forwardUrl);
        json.put("callbackType", callbackType == null ? "" : callbackType.name());
        return json.toJSONString();
    }

    public static String ajaxForwardSuccess(String message, String navTabId, String forwardUrl,
        CallbackType callbackType, Map<String, Object> extInfo) {
        JSONObject json=new JSONObject();
        json.put("statusCode", SUCCESS_STATUS_CODE);
        json.put("message", StringUtils.isNotBlank(message) ? message : "");
        json.put("navTabId", navTabId == null ? "" : navTabId);
        json.put("forwardUrl", forwardUrl == null ? "" : forwardUrl);
        json.put("callbackType", callbackType == null ? "" : callbackType.name());
        json.put("extInfo", extInfo);
        return json.toJSONString();
    }

    public static String ajaxForwardError(String message) {
        JSONObject json=new JSONObject();
        json.put("statusCode", ERROR_STATUS_CODE);
        json.put("message", message == null ? "" : message);
        return json.toJSONString();
    }

    public static String ajaxForwordSessionOut(String message) {
        JSONObject json=new JSONObject();
        json.put("statusCode", SESSION_OUT_STATUS_CODE);
        json.put("message", message == null ? "" : message);
        return json.toJSONString();
    }

    public static String ajaxForwardDataSuccess(Object data) {
        JSONObject json=new JSONObject();
        json.put("statusCode", SUCCESS_STATUS_CODE);
        json.put("message", data);
        return json.toJSONString();
    }
}
