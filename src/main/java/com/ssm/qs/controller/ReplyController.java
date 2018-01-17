package com.ssm.qs.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.qs.pojo.Reply;
import com.ssm.qs.pojo.User;
import com.ssm.qs.service.ReplyService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/reply")
public class ReplyController {

    @Autowired
    private ReplyService replyService;
    //结果容器
    Map<String, Object> result = new HashMap<>();

    //1.文件上传
    @Value("${Reply_IMAGE_DIR}")
    private String Reply_IMAGE_DIR;
    @Value("${Reply_IMAGE_URL}")
    private String Reply_IMAGE_URL;

    @RequestMapping("/upload.html")
    @ResponseBody
    public Map<String, Object> upload(MultipartFile file) throws Exception {

        String oldFileName = file.getOriginalFilename();
        String extName = oldFileName.substring(oldFileName.lastIndexOf("."));
        String newFileName = System.currentTimeMillis() + extName;

        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(Reply_IMAGE_DIR, newFileName));
        result.put("success", true);
        result.put("result", true);
        result.put("error", null);
        return result;
    }

    //2.添加评论
    @RequestMapping("/add.html")
    @ResponseBody
    public Map<String, Object> add(Reply reply) {

        replyService.addReply(reply);
        result.put("success", true);
        result.put("result", true);
        result.put("error", null);
        return result;
    }

    //3.查询所有评论
    @RequestMapping("/list.html")
    @ResponseBody
    public Map<String, Object> list(int id, int page, int rows) {

        PageInfo<User> pageInfo = replyService.getReply(id, page, rows);
        List<User> list = pageInfo.getList();
        result.put("list", list);//返回
        result.put("success", true);
        result.put("error", null);
        return result;
    }

}
