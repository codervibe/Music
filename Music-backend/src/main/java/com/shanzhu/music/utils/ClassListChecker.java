package com.shanzhu.music.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.lang.reflect.Field;
/**
 * Created by codervibe on 2024/5/21
 * DateTime:2024/05/21 上午3:55
 * Description:
 * Others:
 */

public class ClassListChecker {
    public static List<Class<?>> removeSameClasses(List<Class<?>> list1, List<Class<?>> list2) {
        // 使用HashSet来存储list2中的类，以提高查找效率
        Set<Class<?>> set2 = new HashSet<>(list2);

        List<Class<?>> result = new ArrayList<>();

        // 遍历list1中的每个类
        for (Class<?> clazz : list1) {
            // 获取clazz的所有属性
            Field[] fields = clazz.getDeclaredFields();
            // 创建一个临时的Class对象来存储属性值
            Class<?> tempClazz = clazz;
            // 比较list2中的类是否具有相同的属性值
            if (!set2.contains(tempClazz)) {
                result.add(clazz);
            }
        }

        return result;
    }
}
