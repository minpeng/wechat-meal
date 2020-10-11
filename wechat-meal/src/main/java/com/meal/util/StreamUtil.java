package com.meal.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by pengmin on 2020/10/11.
 */
public class StreamUtil {

    public static void main(String[] args) {
        List<MyInfo> myInfoList = new ArrayList<>();
        //1.stream某一个元素计数
        long count = myInfoList.stream().mapToInt(item -> item.getAge()).sum();
        System.out.println(count);

//2.stream过滤后某一个元素计数
        long count2 = myInfoList.stream().filter(item -> item.getAge() > 21).mapToInt(item -> item.getAge()).sum();
        System.out.println(count2);


//3.过滤后统计集合大小
        long count1 = myInfoList.stream().filter(item -> item.getAge() > 21).count();
        System.out.println(count1);

//4.过滤后收集
        List<MyInfo> collect = myInfoList.stream().filter(item -> item.getAge() > 21).collect(Collectors.toList());
        System.out.println(collect);

//5.判断是否有该元素
        boolean flag = myInfoList.stream().anyMatch(item -> item.getName().equals("pengm1"));
        System.out.println(flag);

//6.分组
        Map<Integer, List<MyInfo>> collect1 = myInfoList.stream().collect(Collectors.groupingBy(item -> item.getAge()));
        System.out.println(collect1);
        Map<Integer, Map<String, List<MyInfo>>> collect2 = myInfoList.stream().collect(Collectors.groupingBy(item -> item.getAge(), Collectors.groupingBy(item -> item.getName())));
        System.out.println(collect2);


//7.list转map
//        Map<String, MyInfo> myInfoMap = list.stream().collect(
//                Collectors.toMap(item -> String.valueOf(item.getName()), item -> item));、
//        Map<String, UserExposureTaskDTO> userExposureTaskDTOMap = userExposureTaskDTOList.stream().collect(
//                Collectors.toMap(item -> String.valueOf(item.getResourceId()), item -> item, (oldValue, newValue) -> newValue));

//8.map转list
//        map.values().stream().collect(Collectors.toList());
//        map.keySet().stream().collect(Collectors.toList());


//9.收集一个元素
        List<String> uidList = myInfoList.stream()
                .map(MyInfo::getUid)
                .collect(Collectors.toList());


//10.分组求和

//11.两个list匹配


//12.排序
//        List<MyInfo> collect =
//                myInfoList.stream().sorted(Comparator.comparing(MyInfo::getTalentOrderStatus, Comparator.nullsLast(Integer::compareTo))
//                        .thenComparing(MyInfo::getPushOrderTime, Comparator.reverseOrder())).collect(Collectors.toList());

    }
}
