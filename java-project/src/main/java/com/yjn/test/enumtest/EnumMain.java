package com.yjn.test.enumtest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by yjn on 2017/7/3/0003.
 */
public class EnumMain {

    public static void main(String[] args) {
        String str ="{\"bg\":\"\",\"contents\":[{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"byvalue\\\":\\\"coocaa.intent.movie.detailinfo\\\",\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"\\\",\\\"params\\\":{\\\"id\\\":\\\"_tx_dhzimk1qzznf301\\\"},\\\"bywhat\\\":\\\"action\\\"}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170628/20170628094430550086_797x448.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"特工皇妃楚乔传\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":448,\"id\":\"16039\",\"parents\":\"\",\"type\":\"Block\",\"width\":797,\"x\":0,\"y\":0},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"byvalue\\\":\\\"coocaa.intent.movie.detailinfo\\\",\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"\\\",\\\"params\\\":{\\\"id\\\":\\\"_tx_ga7nei8pd5i9mek\\\"},\\\"bywhat\\\":\\\"action\\\"}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170623/20170623163420019326_384x448.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"拆弹专家\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":448,\"id\":\"16040\",\"parents\":\"\",\"type\":\"Block\",\"width\":384,\"x\":825,\"y\":0},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"byvalue\\\":\\\"coocaa.intent.movie.detailinfo\\\",\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"\\\",\\\"params\\\":{\\\"id\\\":\\\"_tx_71bctb897dwx46m\\\"},\\\"bywhat\\\":\\\"action\\\"}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170623/20170623165233102164_384x448.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"金刚：骷髅岛\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":448,\"id\":\"16041\",\"parents\":\"\",\"type\":\"Block\",\"width\":384,\"x\":1238,\"y\":0},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"bywhat\\\":\\\"action\\\",\\\"byvalue\\\":\\\"coocaa.intent.movie.search\\\",\\\"exception\\\":{\\\"name\\\":\\\"onclick_exception\\\",\\\"value\\\":{\\\"packagename\\\":\\\"com.tianci.appstore\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"params\\\":{\\\"appId\\\":\\\"com.tianci.movieplatform\\\"},\\\"byvalue\\\":\\\"com.tianci.appstore.ui.SkyAppDetailsActivity\\\",\\\"bywhat\\\":\\\"class\\\"}},\\\"params\\\":{}}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170425/20170425114437838402_200x200.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"搜索\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":200,\"id\":\"16042\",\"parents\":\"\",\"type\":\"Block\",\"width\":200,\"x\":0,\"y\":476},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"bywhat\\\":\\\"action\\\",\\\"byvalue\\\":\\\"coocaa.intent.movie.collect\\\",\\\"exception\\\":{\\\"name\\\":\\\"onclick_exception\\\",\\\"value\\\":{\\\"packagename\\\":\\\"com.tianci.appstore\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"params\\\":{\\\"appId\\\":\\\"com.tianci.movieplatform\\\"},\\\"byvalue\\\":\\\"com.tianci.appstore.ui.SkyAppDetailsActivity\\\",\\\"bywhat\\\":\\\"class\\\"}},\\\"params\\\":{}}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170425/20170425114437318474_200x200.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"播放记录\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":200,\"id\":\"16043\",\"parents\":\"\",\"type\":\"Block\",\"width\":200,\"x\":228,\"y\":476},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"bywhat\\\":\\\"action\\\",\\\"byvalue\\\":\\\"coocaa.intent.vip.center\\\",\\\"exception\\\":{\\\"name\\\":\\\"onclick_exception\\\",\\\"value\\\":{\\\"packagename\\\":\\\"com.tianci.appstore\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"params\\\":{\\\"appId\\\":\\\"com.tianci.movieplatform\\\"},\\\"byvalue\\\":\\\"com.tianci.appstore.ui.SkyAppDetailsActivity\\\",\\\"bywhat\\\":\\\"class\\\"}},\\\"params\\\":{}}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170505/20170505203356654533_341x200.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"会员中心\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":200,\"id\":\"16044\",\"parents\":\"\",\"type\":\"Block\",\"width\":341,\"x\":456,\"y\":476},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"bywhat\\\":\\\"action\\\",\\\"byvalue\\\":\\\"coocaa.intent.movie.home\\\",\\\"exception\\\":{\\\"name\\\":\\\"onclick_exception\\\",\\\"value\\\":{\\\"packagename\\\":\\\"com.tianci.appstore\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"versioncode\\\":\\\"-1\\\",\\\"params\\\":{\\\"appId\\\":\\\"com.tianci.movieplatform\\\"},\\\"byvalue\\\":\\\"com.tianci.appstore.ui.SkyAppDetailsActivity\\\",\\\"bywhat\\\":\\\"class\\\"}},\\\"params\\\":{}}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170627/20170627171911516243_384x200.jpg\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"影视中心\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":200,\"id\":\"16045\",\"parents\":\"\",\"type\":\"Block\",\"width\":384,\"x\":825,\"y\":476},{\"bg\":\"\",\"contents\":null,\"extra\":{\"block_content_info\":{\"action\":\"{\\\"packagename\\\":\\\"com.tianci.movieplatform\\\",\\\"versioncode\\\":\\\"\\\",\\\"dowhat\\\":\\\"startActivity\\\",\\\"bywhat\\\":\\\"action\\\",\\\"byvalue\\\":\\\"coocaa.intent.action.HOME_COMMON_LIST\\\",\\\"exception\\\":{},\\\"params\\\":{\\\"id\\\":178}}\",\"app_devices\":\"\",\"app_packagename\":\"\",\"imgs\":{\"corner_icons\":[{\"icon_url\":\"\",\"position\":0}],\"fg\":{\"align\":0,\"url\":\"\"},\"poster\":{\"images\":[\"http://img.sky.fs.skysrt.com/tvos6_imgs_master/20170627/20170627170943590839_384x200.png\"],\"interval\":0}},\"score\":\"0\",\"sub_title\":\"\",\"title\":\"本周新片指南\",\"title_align\":0,\"videoSources\":[{\"id\":\"\",\"source\":\"\",\"source_name\":\"\",\"url\":\"\",\"url_type\":\"\"}],\"video_is_vip\":false,\"video_source_company\":\"\",\"video_source_sign\":\"\"},\"block_type_info\":{\"auto_play_video\":false,\"conver_type\":\"base\",\"lucency_flag\":\"null\"},\"data_gather_info\":{\"data_id\":\"\",\"data_version\":0,\"position_id\":\"\"}},\"focusable\":1,\"height\":200,\"id\":\"16046\",\"parents\":\"\",\"type\":\"Block\",\"width\":384,\"x\":1238,\"y\":476}],\"extra\":{\"panelVersion\":\"V91\",\"panel_source_type\":\"0\",\"title\":{\"align\":1,\"color\":\"\",\"size\":0,\"text\":\"今日推荐\"}},\"focusable\":0,\"height\":0,\"id\":\"2266\",\"parents\":\"\",\"type\":\"Panel\",\"width\":0,\"x\":0,\"y\":0}";
        JSONObject jb = JSON.parseObject(str);
        JSONArray ja = new JSONArray();
        for (int i =1 ;i<11;i++){
            ja.add(jb);
        }

        Map map = new HashMap();
        for (int i = 0; i < 10001; i++) {
            String key = "tvos.service.tvosSixService-getPreviousMD5ByTabId-tabId=131" + "--" + i;
            boolean flag = "tvos.service.tvosSixService-getPreviousMD5ByTabId-tabId=131--888".equals(key);
            if (flag) {
                long e = System.currentTimeMillis();
                System.out.println(key);
            }
            map.put(key, ja);
        }
        Set set = map.keySet();
        System.out.println("set.size():::>>"+set.size());
        long s = System.currentTimeMillis();
        for (Object ss : set) {
                long e = System.currentTimeMillis();
                System.out.println("找到了：。。"+(e - s));
                break;

        }

        System.out.println("over");


    }
}