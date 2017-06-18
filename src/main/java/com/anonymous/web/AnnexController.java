package com.anonymous.web;

import com.anonymous.domain.Annex;
import com.anonymous.service.inter.AnnexServiceInter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by WangZK on 2017/6/18.
 */
@Controller
@Api(value = "附件管理", description = "提供附件的添加功能")
public class AnnexController {

    @Autowired
    private AnnexServiceInter annexService;

    @RequestMapping(value = "uploadAnnex", method = RequestMethod.POST)
    @ApiOperation(value = "添加附件")
    @ResponseBody
    public Annex uploadAnnex(@RequestParam MultipartFile file) {
        return annexService.saveAnnex(file);
    }
}
