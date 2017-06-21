package com.anonymous.service;

import com.anonymous.domain.Annex;
import com.anonymous.repository.AnnexRepository;
import com.anonymous.service.inter.AnnexServiceInter;
import com.anonymous.util.UuidUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZK on 2017/6/18.
 */
@Service
public class AnnexService implements AnnexServiceInter {

    //附件的相对路径文件夹
    @Value("${annexUrl}")
    private String annexUrl;

    @Autowired
    private AnnexRepository annexRepository;

    @Override
    public Annex saveAnnex(MultipartFile file) {

        File newFile = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/" + annexUrl);
        //判断文件夹是否存在，如果不存在创建文件夹
        if (!newFile.exists()) {
            newFile.mkdir();
        }
        String originalFileName = file.getOriginalFilename();
        String newFileName = UuidUtil.get32UUID() + getSuffix(originalFileName);
        newFile = new File(ClassUtils.getDefaultClassLoader().getResource("").getPath() + "static/" + annexUrl + newFileName);
        Annex annex = new Annex();
        try {
            file.transferTo(newFile);
            annex.setName(originalFileName);
            annex.setPath(annexUrl + newFileName);
            annex = annexRepository.save(annex);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return annex;
    }

    @Override
    public List<Annex> contactAnnex(String common_id, String[] fileIds) {
        List<Annex> annexes = null;
        if (fileIds.length > 0) {
            annexes = new ArrayList<>();
            for (String id : fileIds) {
                Annex annex = annexRepository.findOne(id);
                annex.setCommon_id(common_id);
                annex = annexRepository.save(annex);
                annexes.add(annex);
            }
        }
        return annexes;
    }

    //获取文件后缀名
    private String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
