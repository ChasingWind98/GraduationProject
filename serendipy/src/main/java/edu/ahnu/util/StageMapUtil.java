package edu.ahnu.util;

import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

/**
 * 存储stage以及scene 当作全局变量使用 进行切换
 */
public class StageMapUtil {
    public static Map<String, Stage> STAGE = new HashMap<String, Stage>();
    public static Map<String, Object> CONTROLLER = new HashMap<String, Object>();
}
