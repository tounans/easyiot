package com.tounans.easyiot.weboperation.controller.push;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tounans.easyiot.common.view.Response;
import com.tounans.easyiot.push.entity.PushHttp;
import com.tounans.easyiot.push.service.IPushHttpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/pushHttp")
public class PushHttpController {

    @Autowired
    IPushHttpService pushHttpService;

    @GetMapping("/list")
    public String list(@RequestParam(defaultValue = "1", required = false) int page, Model model){

        int userId  = 1;

        IPage<PushHttp> pushHttpIPage = pushHttpService.pageByUserParam(page,userId);

        model.addAttribute("pushHttpList", pushHttpIPage.getRecords())
                .addAttribute("pageNum", pushHttpIPage.getTotal() % pushHttpIPage.getSize() == 0 ? 1 : (pushHttpIPage.getTotal() / pushHttpIPage.getSize()+1))
                .addAttribute("current",pushHttpIPage.getCurrent())
                .addAttribute("total",pushHttpIPage.getTotal());

        return "admin/pushHttp/list";
    }

    @GetMapping("/toEdit")
    public String Edit(Integer userPushId, Model model){

        int userId  = 1;

        if (userPushId !=null){
            PushHttp pushHttp =pushHttpService.getByUserAndUserPushId(userId, userPushId);
            model.addAttribute("pushHttp",pushHttp);
        }

        return "admin/pushHttp/edit";
    }

    @ResponseBody
    @PostMapping( "/edit")
    public Object edit(@RequestBody PushHttp pushHttp) {
        Integer userId  = 1;
        pushHttpService.saveOrUpdatePushHttp(userId, pushHttp);
        return new Response().success();
    }

}
