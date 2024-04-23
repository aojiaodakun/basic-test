package com.hzk.test;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TopicTest {

    public static void main(String[] args) throws Exception {
        String vhost = "prd_vhost";
        List<String> scheduleTagList = Arrays.asList("BIZJOB", "REALTIMEJOB", "WorkFlowJOB");

        List<String> vhostTopicList = new ArrayList<>(100);
        List<String> normalTopicList = new ArrayList<>(100);
        List<String> scheduleTopicList = new ArrayList<>(100);
        Set<String> appIdSet = new HashSet<>(128);


        List<String> stringList = FileUtils.readLines(new File("D:\\temp\\topic.txt"), "utf-8");
        stringList.forEach(topic ->{
            if (topic.startsWith(vhost)) {
                vhostTopicList.add(topic);
                if (topic.contains(scheduleTagList.get(0)) || topic.contains(scheduleTagList.get(1)) || topic.contains(scheduleTagList.get(2))){
                    scheduleTopicList.add(topic);
                    String tempTopic = topic.replace(vhost + "_", "");
                    String tempAppId = tempTopic.split("-")[0];
                    appIdSet.add(tempAppId);
                } else {
                    normalTopicList.add(topic);
                }
            }
        });
        normalTopicList.forEach(s->{
            System.out.println(s);
        });
        System.err.println("-----------------------------------");
        scheduleTopicList.forEach(s->{
            System.out.println(s);
        });
    }


}
