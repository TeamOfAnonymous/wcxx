package com.anonymous.service.inter;

import com.anonymous.domain.Annex;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by WangZK on 2017/6/18.
 */
public interface AnnexServiceInter {
    Annex saveAnnex(MultipartFile file);

    List<Annex> contactAnnex(String common_id, String[] fileIds);
}
