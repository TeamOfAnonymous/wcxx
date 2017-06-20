package com.anonymous.web;

import com.anonymous.domain.Annex;
import com.anonymous.service.inter.AnnexServiceInter;
import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

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
    public String uploadAnnex(@RequestParam MultipartFile file) throws JsonProcessingException {
        Annex annex = annexService.saveAnnex(file);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonStr = objectMapper.writeValueAsString(annex);
        return jsonStr;
    }
}
