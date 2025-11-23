package com.jay.gulimall.third.controller;

import com.jay.common.utils.R;
import com.jay.gulimall.third.service.GcsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping("third/cgs")
public class GcsController {
    @Autowired
    private GcsService gcsService;

    @Value("${test.get.name}")
    private String bucketName;


    @RequestMapping("/policy")
    public R getUploadUrl(@RequestParam("itemName") String itemName){

        String uploadUrl = gcsService.getUploadUrl(itemName);
        return R.ok().put("data", uploadUrl);

    }
}
